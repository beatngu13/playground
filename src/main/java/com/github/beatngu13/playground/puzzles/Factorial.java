package com.github.beatngu13.playground.puzzles;

import java.util.stream.LongStream;

public class Factorial {

	public static long of(final int n) {
		return LongStream.rangeClosed(1L, n).reduce(1L, Math::multiplyExact);
	}

}
