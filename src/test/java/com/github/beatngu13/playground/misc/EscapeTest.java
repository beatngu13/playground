package com.github.beatngu13.playground.misc;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

class EscapeTest {

	@Test
	void test() {
		var originalText = """
				ASCII Text 123.
				Deutscher Text äöüß.
				Smiley Text 😉.
				"""
				.stripTrailing();

		var escapedText = StringEscapeUtils.escapeJava(originalText);

		var unescapedText = StringEscapeUtils.unescapeJava(originalText);

		System.out.println(originalText);
		System.out.println("----------");
		System.out.println(escapedText);
		System.out.println("----------");
		System.out.println(unescapedText);
	}

}
