package com.github.beatngu13.playground.puzzles;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

public class StringUtils {

	public static boolean isUnique(String s) {
		return s.codePoints().distinct().count() == s.length();
	}

	public static boolean isPalindrome(String s) {
		int length = s.length();
		for (int i = 0, j = length - 1; i < length / 2; i++, j--) {
			char left = s.charAt(i);
			char right = s.charAt(j);
			if (left != right) {
				return false;
			}
		}
		return true;
	}

	public static Optional<Character> firstRecurringChar(String s) {
		Set<Character> chars = new HashSet<>();
		for (char c : s.toCharArray()) {
			if (!chars.add(c)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}

	public static long countVowels(String s) {
		return s.toLowerCase() //
				.codePoints() //
				.mapToObj(c -> (char) c) //
				.filter(StringUtils::isVowel) //
				.count();
	}

	private static boolean isVowel(char c) {
		return switch (c) {
			case 'a', 'e', 'i', 'o', 'u' -> true;
			default -> false;
		};
	}

	public static String removeWhitespace(String s) {
		return s.codePoints() //
				.filter(c -> !Character.isWhitespace(c)) //
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append) //
				.toString();
	}

	public static boolean matchingParenthesis(String s) {
		Stack<Character> parenthesis = new Stack<>();
		for (char c : s.toCharArray()) {
			if (isOpeningParenthesis(c)) {
				parenthesis.push(c);
			} else {
				if (parenthesis.isEmpty()) {
					return false;
				}
				if (!matchingParenthesis(parenthesis.pop(), c)) {
					return false;
				}
			}
		}
		return parenthesis.isEmpty();
	}

	private static boolean isOpeningParenthesis(char c) {
		return switch (c) {
			case '(', '[', '{' -> true;
			default -> false;
		};
	}

	private static boolean matchingParenthesis(char left, char right) {
		if (left == '(' && right == ')') {
			return true;
		}
		if (left == '[' && right == ']') {
			return true;
		}
		if (left == '{' && right == '}') {
			return true;
		}
		return false;
	}

}
