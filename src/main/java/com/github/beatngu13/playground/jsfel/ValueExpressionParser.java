package com.github.beatngu13.playground.jsfel;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ValueExpressionParser {

	private static final String VALUE_EXPRESSION_START = "#{";
	private static final String VALUE_EXPRESSION_END = "}";

	private static final int BEGIN_INDEX_OFFSET = VALUE_EXPRESSION_START.length();
	private static final int END_INDEX_OFFSET = VALUE_EXPRESSION_END.length() - 1;

	public static List<String> parse(Path xhtml) {
		try {
			return Files.lines(xhtml)
					.map(ValueExpressionParser::getExpressions)
					.flatMap(els -> els.stream())
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	// based on https://stackoverflow.com/a/3940217/3429133
	private static List<String> getExpressions(String line) {
		int i = line.indexOf(VALUE_EXPRESSION_START);
		if (i == -1) {
			return Collections.emptyList();
		}

		List<String> expressions = new ArrayList<String>();
		while (i != -1) {
			int j = line.indexOf(VALUE_EXPRESSION_END, i);
			if (j == -1) {
				return Collections.emptyList();
			}

			String expression = line.substring(i + BEGIN_INDEX_OFFSET, j - END_INDEX_OFFSET);
			expressions.add(expression);

			i = line.indexOf(VALUE_EXPRESSION_START, j + 1);
		}

		return expressions;
	}

}
