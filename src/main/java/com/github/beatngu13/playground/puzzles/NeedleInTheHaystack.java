package com.github.beatngu13.playground.puzzles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeedleInTheHaystack {

	public static List<Integer> findIndexes(String needle, String haystack) {
		if (needle.isEmpty() || haystack.isEmpty()) {
			return Collections.emptyList();
		}

		List<Integer> foundIndexes = new ArrayList<>();
		char[] haystackChars = haystack.toCharArray();
		char[] needleChars = needle.toCharArray();
		int needleIndex = 0;

		for (int haystackIndex = 0; haystackIndex < haystackChars.length; haystackIndex++) {
			char haystackChar = haystackChars[haystackIndex];
			char needleChar = needleChars[needleIndex];

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
