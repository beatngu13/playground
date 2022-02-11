package com.github.beatngu13.playground.puzzles;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FibonacciTest {

	@ParameterizedTest
	@MethodSource
	void test(int n, long fibonacci) {
		assertThat(Fibonacci.of(n)).isEqualTo(fibonacci);
	}

	static Stream<Arguments> test() {
		return Stream.of(
				Arguments.of(-1, 1L),
				Arguments.of(0, 1L),
				Arguments.of(1, 1L),
				Arguments.of(2, 1L),
				Arguments.of(3, 2L),
				Arguments.of(4, 3L),
				Arguments.of(5, 5L));
	}

}
