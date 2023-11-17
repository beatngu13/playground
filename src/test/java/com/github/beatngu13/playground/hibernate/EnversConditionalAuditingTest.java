package com.github.beatngu13.playground.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.envers.AuditReaderFactory;
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

	static SessionFactory sessionFactory;

	@BeforeAll
	static void setUpOnce() {
		var standardServiceRegistry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		var metadata = new MetadataSources(standardServiceRegistry)
				.addAnnotatedClass(Book.class)
				.getMetadataBuilder()
				.build();
		sessionFactory = metadata.buildSessionFactory();
	}

	@AfterAll
	static void tearDownOnce() {
		sessionFactory.close();
	}

	Session session;

	@BeforeEach
	void setUp() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	@AfterEach
	void tearDown() {
		session.getTransaction().commit();
	}

	@Order(1)
	@Test
	void insertBook() {
		var book = new Book();
		book.setTitle("Some book");

		session.persist(book);
	}

	@Order(2)
	@Test
	void selectBook() {
		var book = session.find(Book.class, 1L);

		assertThat(book).isNotNull();
	}

	@Order(3)
	@Test
	void updateBook() {
		var book = session.find(Book.class, 1L);
		book.setTitle("Other book");
	}

	@Order(4)
	@Test
	void readAudit() {
		var auditReader = AuditReaderFactory.get(session);
		var resultList = auditReader.createQuery()
				.forRevisionsOfEntity(Book.class, false, false)
				.add(id().eq(1L))
				.getResultList();

		// If we wouldn't use conditional auditing, there would be two revision: one for ADD (test 1) and one for MOD (test 2).
		assertThat(resultList).hasSize(1);

		Object[] result = (Object[]) resultList.getFirst();

		var book = (Book) result[0];
		assertThat(book.getTitle()).isEqualTo("Other book");

		var refType = (RevisionType) result[2];
		assertThat(refType).isEqualTo(RevisionType.MOD);
	}

}
