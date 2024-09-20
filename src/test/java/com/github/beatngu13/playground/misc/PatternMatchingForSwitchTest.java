package com.github.beatngu13.playground.misc;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * See:
 * - https://openjdk.org/jeps/455
 * - https://twitter.com/TheDonRaab/status/1647805135869943814
 */
class PatternMatchingForSwitchTest {

	@Test
	void test_fizz_buzz() {
		var list = IntStream.rangeClosed(1, 15)
				.mapToObj(value ->
						switch (value) {
							case int i when i % 15 == 0 -> "FizzBuzz";
							case int i when i % 5 == 0 -> "Buzz";
							case int i when i % 3 == 0 -> "Fizz";
							default -> Integer.toString(value);
						}
				)
				.toList();
		assertThat(list).containsExactly(
				"1",
				"2",
				"Fizz",
				"4",
				"Buzz",
				"Fizz",
				"7",
				"8",
				"Fizz",
				"Buzz",
				"11",
				"Fizz",
				"13",
				"14",
				"FizzBuzz"
		);
	}

}
