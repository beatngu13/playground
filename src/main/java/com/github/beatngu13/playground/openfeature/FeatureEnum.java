package com.github.beatngu13.playground.openfeature;

public enum FeatureEnum {

	FIO_123456(1L);

	private final Long id;

	FeatureEnum(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
