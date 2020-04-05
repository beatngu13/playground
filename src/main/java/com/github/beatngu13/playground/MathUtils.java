package com.github.beatngu13.playground;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class MathUtils {

	public static class Pair<T extends Number> {

		public T left;
		public T right;

		public Pair(final T left, final T right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((left == null) ? 0 : left.hashCode());
			result = prime * result + ((right == null) ? 0 : right.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object other) {
			if (other == null) {
				return false;
			}
			if (this == other) {
				return true;
			}
			if (getClass() != other.getClass()) {
				return false;
			}
			final Pair<?> otherPair = (Pair<?>) other;
			return Objects.equals(left, otherPair.left) && Objects.equals(right, otherPair.right);
		}

	}

	public static Optional<Pair<Integer>> getPairWithSum(final List<Integer> data, final int sum) {
		final Set<Integer> seen = new HashSet<>();
		for (final Integer i : data) {
			final int complement = sum - i;
			if (seen.contains(complement)) {
				return Optional.of(new Pair<Integer>(complement, i));
			}
			seen.add(i);
		}
		return Optional.empty();
	}

}
