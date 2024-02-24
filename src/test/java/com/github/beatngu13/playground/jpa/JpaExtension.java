package com.github.beatngu13.playground.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.support.TypeBasedParameterResolver;
import org.junit.platform.commons.PreconditionViolationException;
import org.junit.platform.commons.support.AnnotationSupport;

public class JpaExtension extends TypeBasedParameterResolver<EntityManager>
		implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

	private static final Namespace NAMESPACE = Namespace.create(JpaExtension.class);

	private static final String ENTITY_MANAGER_FACTORY_STORE_KEY = "ENTITY_MANAGER_FACTORY_STORE_KEY";
	private static final String ENTITY_MANAGER_STORE_KEY = "ENTITY_MANAGER_STORE_KEY";

	@Override
	public void beforeAll(ExtensionContext context) {
		var entityManagerFactory = AnnotationSupport.findAnnotation(context.getTestClass(), Jpa.class)
				.map(Jpa::persistenceUnitName)
				.map(Persistence::createEntityManagerFactory)
				.orElseThrow(() -> new PreconditionViolationException("Could not create EntityManagerFactory."));
		context.getStore(NAMESPACE).put(ENTITY_MANAGER_FACTORY_STORE_KEY, entityManagerFactory);
	}

	@Override
	public void afterAll(ExtensionContext context) {
		context.getStore(NAMESPACE)
				.get(ENTITY_MANAGER_FACTORY_STORE_KEY, EntityManagerFactory.class)
				.close();
	}

	@Override
	public void beforeEach(ExtensionContext context) {
		var store = context.getStore(NAMESPACE);
		var entityManager = store.get(ENTITY_MANAGER_FACTORY_STORE_KEY, EntityManagerFactory.class)
				.createEntityManager();
		store.put(ENTITY_MANAGER_STORE_KEY, entityManager);
		entityManager.getTransaction().begin();
	}

	@Override
	public void afterEach(ExtensionContext context) {
		context.getStore(NAMESPACE)
				.get(ENTITY_MANAGER_STORE_KEY, EntityManager.class)
				.getTransaction().commit();
	}

	@Override
	public EntityManager resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
		return extensionContext.getStore(NAMESPACE)
				.get(ENTITY_MANAGER_STORE_KEY, EntityManager.class);
	}

}
