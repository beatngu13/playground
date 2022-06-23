package com.github.beatngu13.playground.puzzles;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WordsInPhoneNumberTest {

	@ParameterizedTest
	@MethodSource
	void test(List<String> words, String phoneNumber, List<String> wordsInPhoneNumber) {
		assertThat(WordsInPhoneNumber.get(words, phoneNumber)).isEqualTo(wordsInPhoneNumber);
	}

	static Stream<Arguments> test() {
		return Stream.of(
				Arguments.of(
						List.of("clement", "clemdot", "foo"),
						"2536368",
						List.of("clement", "clemdot")
				)
		);
	}

}