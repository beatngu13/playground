package com.github.beatngu13.playground;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FibonacciTest {

	@ParameterizedTest
	@MethodSource("params")
	void test(final int n, final long fibonacci) {
		assertThat(Fibonacci.of(n)).isEqualTo(fibonacci);
	}

	static Stream<Arguments> params() {
		return Stream.of( //
				Arguments.of(-1, 1L), //
				Arguments.of(0, 1L), //
				Arguments.of(1, 1L), //
				Arguments.of(2, 1L), //
				Arguments.of(3, 2L), //
				Arguments.of(4, 3L), //
				Arguments.of(5, 5L));
	}

}
