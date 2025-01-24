package com.github.beatngu13.playground.openfeature;

import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.FeatureProvider;
import dev.openfeature.sdk.Metadata;
import dev.openfeature.sdk.ProviderEvaluation;
import dev.openfeature.sdk.Value;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.Optional;

public class JpaFeatureProvider implements FeatureProvider {

	private static final String NAME = "JPA Feature Provider";

	private final EntityManager entityManager;

	public JpaFeatureProvider(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Metadata getMetadata() {
		return () -> NAME;
	}

	@Override
	public ProviderEvaluation<Boolean> getBooleanEvaluation(String key, Boolean defaultValue, EvaluationContext ctx) {
		Boolean value = getValue(key)
				.orElse(defaultValue);
		return ProviderEvaluation.<Boolean>builder()
				.value(value)
				.build();
	}

	@Override
	public ProviderEvaluation<String> getStringEvaluation(String key, String defaultValue, EvaluationContext ctx) {
		String value = getValue(key)
				.map(Object::toString)
				.orElse(defaultValue);
		return ProviderEvaluation.<String>builder()
				.value(value)
				.build();
	}

	@Override
	public ProviderEvaluation<Integer> getIntegerEvaluation(String key, Integer defaultValue, EvaluationContext ctx) {
		Integer value = getValue(key)
				.map(v -> v ? 1 : 0)
				.orElse(defaultValue);
		return ProviderEvaluation.<Integer>builder()
				.value(value)
				.build();
	}

	@Override
	public ProviderEvaluation<Double> getDoubleEvaluation(String key, Double defaultValue, EvaluationContext ctx) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ProviderEvaluation<Value> getObjectEvaluation(String key, Value defaultValue, EvaluationContext invocationContext) {
		throw new UnsupportedOperationException();
	}

	private Optional<Boolean> getValue(String key) {
		return getFeatureEnum(key)
				.map(fe -> entityManager.find(FeatureEntity.class, fe.getId()))
				.map(FeatureEntity::getValue);
	}

	private static Optional<FeatureEnum> getFeatureEnum(String key) {
		return Arrays.stream(FeatureEnum.values())
				.filter(featureEnum -> featureEnum.name().equals(key))
				.findAny();
	}

}
