package com.github.beatngu13.playground.misc;

import com.github.beatngu13.playground.jpa.Jpa;
import com.github.beatngu13.playground.openfeature.FeatureEntity;
import com.github.beatngu13.playground.openfeature.FeatureEnum;
import com.github.beatngu13.playground.openfeature.JpaFeatureProvider;
import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.OpenFeatureAPI;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Jpa(persistenceUnitName = "FeatureManagement")
class OpenFeatureTest {

	Client openFeatureClient;

	@BeforeEach
	void setUp(EntityManager entityManager) {
		var featureEntity = new FeatureEntity();
		featureEntity.setKey(FeatureEnum.FIO_123456.name());
		featureEntity.setValue(true);
		entityManager.persist(featureEntity);

		OpenFeatureAPI openFeatureAPI = OpenFeatureAPI.getInstance();
		openFeatureAPI.setProviderAndWait(new JpaFeatureProvider(entityManager));
		openFeatureClient = openFeatureAPI.getClient();
	}

	@Test
	void readFeatureAsBoolean() {
		boolean value = openFeatureClient.getBooleanValue(FeatureEnum.FIO_123456.name(), false);

		assertThat(value).isTrue();
	}

	@Test
	void readFeatureAsString() {
		String value = openFeatureClient.getStringValue(FeatureEnum.FIO_123456.name(), "false");

		assertThat(value).isEqualTo("true");
	}

	@Test
	void readFeatureAsInteger() {
		Integer value = openFeatureClient.getIntegerValue(FeatureEnum.FIO_123456.name(), 1);

		assertThat(value).isOne();
	}

	@Test
	void readFeatureAsDouble() {
		Double value = openFeatureClient.getDoubleValue(FeatureEnum.FIO_123456.name(), null);

		assertThat(value).isNull();
	}

	@Test
	void readFeatureAsObject() {
		Object value = openFeatureClient.getObjectValue(FeatureEnum.FIO_123456.name(), null);

		assertThat(value).isNull();
	}

}
