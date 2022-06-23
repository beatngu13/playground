package com.github.beatngu13.playground.puzzles;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class WordsInPhoneNumber {

	private static final Map<String, Integer> characterToDigit = Map.ofEntries(
			Map.entry("a", 2),
			Map.entry("b", 2),
			Map.entry("c", 2),
			Map.entry("d", 3),
			Map.entry("e", 3),
			Map.entry("f", 3),
			Map.entry("g", 4),
			Map.entry("h", 4),
			Map.entry("i", 4),
			Map.entry("j", 5),
			Map.entry("k", 5),
			Map.entry("l", 5),
			Map.entry("m", 6),
			Map.entry("n", 6),
			Map.entry("o", 6),
			Map.entry("p", 7),
			Map.entry("q", 7),
			Map.entry("r", 7),
			Map.entry("s", 7),
			Map.entry("t", 8),
			Map.entry("u", 8),
			Map.entry("v", 8),
			Map.entry("w", 9),
			Map.entry("x", 9),
			Map.entry("y", 9),
			Map.entry("z", 9)
	);

	public static List<String> get(List<String> words, String phoneNumber) {
		List<Integer> digits = characters(phoneNumber)
				.map(Integer::valueOf)
				.filter(WordsInPhoneNumber::isNotZeroOrOne)
				.toList();
		return words.stream()
				.filter(word -> isInPhoneNumber(word, digits))
				.toList();
	}

	private static boolean isInPhoneNumber(String word, List<Integer> digits) {
		List<String> characters = characters(word).toList();

		if (characters.size() != digits.size()) {
			return false;
		}

		for (int i = 0; i < digits.size(); i++) {
			String character = characters.get(i);
			Integer digit = digits.get(i);

			if (!characterToDigit.get(character).equals(digit)) {
				return false;
			}
		}

		return true;
	}

	private static boolean isNotZeroOrOne(Integer i) {
		return !(i.equals(0) || i.equals(1));
	}

	private static Stream<String> characters(String s) {
		return s.chars().mapToObj(Character::toString);
	}

}
