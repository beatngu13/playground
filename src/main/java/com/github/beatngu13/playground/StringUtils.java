package com.github.beatngu13.playground;

public class StringUtils {

	public static boolean isUnique(final String s) {
		return s.chars().distinct().count() == s.length();
	}

	public static boolean isPalindrome(final String s) {
		final int length = s.length();
		for (int i = 0, j = length - 1; i < length / 2; i++, j--) {
			final char left = s.charAt(i);
			final char right = s.charAt(j);
			if (left != right) {
				return false;
			}
		}
		return true;
	}

	public static long countVowels(final String s) {
		return s.toLowerCase() //
				.codePoints() //
				.mapToObj(p -> (char) p) //
				.filter(StringUtils::isVowel) //
				.count();
	}

	private static boolean isVowel(final char c) {
		switch (c) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			return true;
		default:
			return false;
		}
	}

}
