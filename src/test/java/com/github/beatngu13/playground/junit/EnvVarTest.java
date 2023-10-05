package com.github.beatngu13.playground.junit;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * See {@code --add-opens} in Maven Surefire Plugin configuration.
 */
class EnvVarTest {

	@Test
	void fooUnset() {
		assertThat(System.getenv("FOO")).isNull();
	}

	@SetEnvironmentVariable(key = "FOO", value = "BAR")
	@Test
	void fooSet() {
		assertThat(System.getenv("FOO")).isEqualTo("BAR");
	}

}
