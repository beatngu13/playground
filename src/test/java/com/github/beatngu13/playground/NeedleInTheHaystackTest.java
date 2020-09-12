package com.github.beatngu13.playground;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NeedleInTheHaystackTest {

	@Test
	void test() {
		assertThat(NeedleInTheHaystack.findIndexes("", "")).isEmpty();
		assertThat(NeedleInTheHaystack.findIndexes("", "abc")).isEmpty();
		assertThat(NeedleInTheHaystack.findIndexes("abc", "")).isEmpty();
		assertThat(NeedleInTheHaystack.findIndexes("abc", "a")).isEmpty();
		assertThat(NeedleInTheHaystack.findIndexes("abc", "abc")).containsExactly(0);
		assertThat(NeedleInTheHaystack.findIndexes("abc", "abbaaccabcabca")).containsExactly(7, 10);
	}

}
