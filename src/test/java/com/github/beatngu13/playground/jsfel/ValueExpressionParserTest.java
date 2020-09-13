package com.github.beatngu13.playground.jsfel;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ValueExpressionParserTest {

	@Test
	void test() throws Exception {
		Path xhtml = Paths.get(getClass().getResource("/valid.xhtml").toURI());

		List<String> expressions = ValueExpressionParser.parse(xhtml);

		assertThat(expressions).containsExactly("helloBean.name");
	}

}
