package com.github.beatngu13.playground;

public class Fibonacci {

	public static long of(final int n) {
		return n <= 2 ? 1L : of(n - 1) + of(n - 2);
	}

}
