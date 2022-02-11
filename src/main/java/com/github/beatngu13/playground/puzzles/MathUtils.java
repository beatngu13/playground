package com.github.beatngu13.playground.puzzles;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MathUtils {

	public static record Pair<T extends Number>(T left, T right) {
	}

	public static Optional<Pair<Integer>> getPairWithSum(List<Integer> data, int sum) {
		Set<Integer> seen = new HashSet<>();
		for (Integer i : data) {
			int complement = sum - i;
			if (seen.contains(complement)) {
				return Optional.of(new Pair<>(complement, i));
			}
			seen.add(i);
		}
		return Optional.empty();
	}

}
