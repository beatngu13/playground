package com.github.beatngu13.playground.puzzles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeedleInTheHaystack {

	public static List<Integer> findIndexes(final String needle, final String haystack) {
		if (needle.isEmpty() || haystack.isEmpty()) {
			return Collections.emptyList();
		}

		final List<Integer> foundIndexes = new ArrayList<>();
		final char[] haystackChars = haystack.toCharArray();
		final char[] needleChars = needle.toCharArray();
		int needleIndex = 0;

		for (int haystackIndex = 0; haystackIndex < haystackChars.length; haystackIndex++) {
			final char haystackChar = haystackChars[haystackIndex];
			final char needleChar = needleChars[needleIndex];

			if (haystackChar == needleChar) {
				needleIndex++;

				if (needleIndex == needleChars.length) {
					foundIndexes.add(haystackIndex - needleChars.length + 1);
					needleIndex = 0;
				}
			} else {
				needleIndex = 0;
			}
		}

		return foundIndexes;
	}

}
