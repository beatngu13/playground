package com.github.beatngu13.playground.jsfel;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JsfElParser {

	public static final String EL_START = "#{";
	public static final String EL_END = "}";

	public static List<String> parse(Path xhtml) {
		try {
			return Files.lines(xhtml)
					.map(JsfElParser::getExpressions)
					.flatMap(els -> els.stream())
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	// borrowed from https://stackoverflow.com/a/3940217/3429133
	private static List<String> getExpressions(String line) {
		int i = line.indexOf(EL_START);
		if (i == -1) {
			return Collections.emptyList();
		}

		List<String> expressions = new ArrayList<String>();
		while (i != -1) {
			int j = line.indexOf(EL_END, i);
			if (j == -1) {
				return Collections.emptyList();
			}

			String expression = line.substring(i, j + 1);
			expressions.add(expression);

			i = line.indexOf(EL_START, j + 1);
		}

		return expressions;
	}

}
