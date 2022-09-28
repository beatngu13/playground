package com.github.beatngu13.playground.misc;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

// See: https://openjdk.org/jeps/427
class PatternMatchingForSwitchTest {

	@ParameterizedTest
	@CsvSource(textBlock = """
			4, 0, 0, foo
			1, 0, 2, bar
			1, 2, 3, baz
			""")
	void test(Integer x, Integer y, Integer z, String expected) {
		String actual = switch (x) {
			case 4 -> "foo";
			case Integer i when y < i && i < z -> "bar";
			default -> "baz";
		};
		assertThat(actual).isEqualTo(expected);
	}

}
