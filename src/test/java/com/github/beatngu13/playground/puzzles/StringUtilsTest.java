package com.github.beatngu13.playground.puzzles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

	@ParameterizedTest
	@MethodSource
	void testIsUnique(String s, boolean isUnique) {
		assertThat(StringUtils.isUnique(s)).isEqualTo(isUnique);
	}

	static Stream<Arguments> testIsUnique() {
		return Stream.of(
				Arguments.of("", true),
				Arguments.of(" ", true),
				Arguments.of("a", true),
				Arguments.of("ab", true),
				Arguments.of("aa", false),
				Arguments.of("aba", false),
				Arguments.of("abc", true),
				Arguments.of("abcba", false));
	}

	@ParameterizedTest
	@MethodSource
	void testIsPalindrome(String s, boolean isPalindrome) {
		assertThat(StringUtils.isPalindrome(s)).isEqualTo(isPalindrome);
	}

	static Stream<Arguments> testIsPalindrome() {
		return Stream.of(
				Arguments.of("", true),
				Arguments.of(" ", true),
				Arguments.of("a", true),
				Arguments.of("ab", false),
				Arguments.of("aa", true),
				Arguments.of("aba", true),
				Arguments.of("abc", false),
				Arguments.of("abcba", true));
	}

	@Test
	void testFirstRecurringChar() {
		assertThat(StringUtils.firstRecurringChar("abca")).hasValue('a');
		assertThat(StringUtils.firstRecurringChar("bcaba")).hasValue('b');
		assertThat(StringUtils.firstRecurringChar("abc")).isNotPresent();
	}

	@Test
	void testCountVowels() {
		assertThat(StringUtils.countVowels("I want to know the number of vowels in this string.")).isEqualTo(13L);
	}

	@Test
	void testRemoveWhitespace() {
		assertThat(StringUtils.removeWhitespace("g eeks for ge eeks ")).isEqualTo("geeksforgeeeks");
	}

	@ParameterizedTest
	@MethodSource
	void testMatchingParenthesis(String s, boolean matchingParenthesis) {
		assertThat(StringUtils.matchingParenthesis(s)).isEqualTo(matchingParenthesis);
	}

	static Stream<Arguments> testMatchingParenthesis() {
		return Stream.of(
				Arguments.of("", true),
				Arguments.of("()", true),
				Arguments.of("([])", true),
				Arguments.of("(", false),
				Arguments.of(")", false),
				Arguments.of("(((", false),
				Arguments.of(")))", false),
				Arguments.of("([({})])", true),
				Arguments.of("([({}))", false));
	}

}
