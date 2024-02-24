package com.github.beatngu13.playground.jpa;

import jakarta.persistence.EntityManager;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junitpioneer.jupiter.DisableIfTestFails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.envers.query.AuditEntity.id;

@DisableIfTestFails
@Jpa(persistenceUnitName = "BookManagement")
@TestMethodOrder(OrderAnnotation.class)
class EnversConditionalAuditingTest {

	@Order(1)
	@Test
	void insertBook(EntityManager entityManager) {
		var book = new Book();
		book.setTitle("Some book");

		entityManager.persist(book);
	}

	@Order(2)
	@Test
	void selectBook(EntityManager entityManager) {
		var book = entityManager.find(Book.class, 1L);

		assertThat(book).isNotNull();
	}

	@Order(3)
	@Test
	void updateBook(EntityManager entityManager) {
		var book = entityManager.find(Book.class, 1L);
		book.setTitle("Other book");
	}

	@Order(4)
	@Test
	void readAudit(EntityManager entityManager) {
		var auditReader = AuditReaderFactory.get(entityManager);
		var resultList = auditReader.createQuery()
				.forRevisionsOfEntity(Book.class, false, false)
				.add(id().eq(1L))
				.getResultList();

		/*
		 * If we wouldn't use conditional auditing, there would be two revisions: one for RevisionType.ADD (insertBook)
		 * and one for RevisionType.MOD (updateBook).
		 */
		assertThat(resultList).hasSize(1);

		Object[] result = (Object[]) resultList.getFirst();
		assertThat(result).hasSize(3);

		assertThat(result[0]).isInstanceOfSatisfying(Book.class,
				book -> assertThat(book.getTitle()).isEqualTo("Other book"));
		assertThat(result[1]).isInstanceOfSatisfying(DefaultRevisionEntity.class,
				defaultRevisionEntity -> assertThat(defaultRevisionEntity.getId()).isEqualTo(1));
		assertThat(result[2]).isInstanceOfSatisfying(RevisionType.class,
				revisionType -> assertThat(revisionType).isEqualTo(RevisionType.MOD));
	}

}
