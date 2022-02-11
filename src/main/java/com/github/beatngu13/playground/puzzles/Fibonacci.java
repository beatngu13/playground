package com.github.beatngu13.playground.puzzles;

public class Fibonacci {

	public static long of(int n) {
		return n <= 2 ? 1L : of(n - 1) + of(n - 2);
	}

}
