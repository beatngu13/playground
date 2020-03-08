package com.github.beatngu13.playground;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StringUtilsTest {

	@ParameterizedTest
	@MethodSource("paramsIsUnique")
	void testIsUnique(final String s, final boolean isUnique) {
		assertThat(StringUtils.isUnique(s)).isEqualTo(isUnique);
	}

	static Stream<Arguments> paramsIsUnique() {
		return Stream.of( //
				Arguments.of("", true), //
				Arguments.of(" ", true), //
				Arguments.of("a", true), //
				Arguments.of("ab", true), //
				Arguments.of("aa", false), //
				Arguments.of("aba", false), //
				Arguments.of("abc", true), //
				Arguments.of("abcba", false)); //
	}

	@ParameterizedTest
	@MethodSource("paramsIsPalindrome")
	void testIsPalindrome(final String s, final boolean isPalindrome) {
		assertThat(StringUtils.isPalindrome(s)).isEqualTo(isPalindrome);
	}

	static Stream<Arguments> paramsIsPalindrome() {
		return Stream.of( //
				Arguments.of("", true), //
				Arguments.of(" ", true), //
				Arguments.of("a", true), //
				Arguments.of("ab", false), //
				Arguments.of("aa", true), //
				Arguments.of("aba", true), //
				Arguments.of("abc", false), //
				Arguments.of("abcba", true)); //
	}

	@Test
	void testCountVowels() throws Exception {
		assertThat(StringUtils.countVowels("I want to know the number of vowels in this string.")).isEqualTo(13L);
	}

}
