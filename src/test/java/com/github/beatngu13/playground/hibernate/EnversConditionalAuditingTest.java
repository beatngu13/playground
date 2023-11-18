package com.github.beatngu13.playground.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.envers.query.AuditEntity.id;

@TestMethodOrder(OrderAnnotation.class)
class EnversConditionalAuditingTest {

	static EntityManagerFactory entityManagerFactory;

	@BeforeAll
	static void setUpOnce() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookManagement");
	}

	@AfterAll
	static void tearDownOnce() {
		entityManagerFactory.close();
	}

	EntityManager entityManager;

	@BeforeEach
	void setUp() {
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	@AfterEach
	void tearDown() {
		entityManager.getTransaction().commit();
	}

	@Order(1)
	@Test
	void insertBook() {
		var book = new Book();
		book.setTitle("Some book");

		entityManager.persist(book);
	}

	@Order(2)
	@Test
	void selectBook() {
		var book = entityManager.find(Book.class, 1L);

		assertThat(book).isNotNull();
	}

	@Order(3)
	@Test
	void updateBook() {
		var book = entityManager.find(Book.class, 1L);
		book.setTitle("Other book");
	}

	@Order(4)
	@Test
	void readAudit() {
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
