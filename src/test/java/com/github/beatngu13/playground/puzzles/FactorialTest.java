package com.github.beatngu13.playground.puzzles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FactorialTest {

	@ParameterizedTest
	@MethodSource("params")
	void test(final int n, final long factorial) {
		assertThat(Factorial.of(n)).isEqualTo(factorial);
	}

	static Stream<Arguments> params() {
		return Stream.of( //
				Arguments.of(-1, 1L), //
				Arguments.of(0, 1L), //
				Arguments.of(1, 1L), //
				Arguments.of(2, 2L), //
				Arguments.of(3, 6L), //
				Arguments.of(4, 24L), //
				Arguments.of(5, 120L));
	}

	@Test
	void testOverflow() throws Exception {
		assertThatThrownBy(() -> Factorial.of(21)).isInstanceOf(ArithmeticException.class);
	}

}
