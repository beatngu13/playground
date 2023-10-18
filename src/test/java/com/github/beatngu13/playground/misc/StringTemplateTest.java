package com.github.beatngu13.playground.misc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringTemplateTest {

	@Test
	void test() {
		var a = 1;
		var b = 2;

		var string = STR. """
				a = \{ a }
				b = \{ b }
				a + b = \{ a + b }
				""" ;

		assertThat(string).isEqualTo("""
				a = 1
				b = 2
				a + b = 3
				""");
	}

}
