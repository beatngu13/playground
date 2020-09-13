package com.github.beatngu13.playground.jsfel;

import org.apache.commons.beanutils.PropertyUtils;

import java.nio.file.Path;
import java.util.List;

public class ValueExpressionValidator {

	public static void validate(Path xhtml, Object bean) {
		List<String> expressions = ValueExpressionParser.parse(xhtml);
		expressions.forEach(expression -> validate(expression, bean));
	}

	private static void validate(String expression, Object bean) {
		int firstDotIndex = expression.indexOf(".");

		String actualBeanName = bean.getClass().getSimpleName();
		String referencedBeanName = expression.substring(0, firstDotIndex);
		if (!actualBeanName.equalsIgnoreCase(referencedBeanName)) {
			String msg = String.format("Bean name does not match: '%s' referenced as '%s' in XHTML.",
					actualBeanName,
					referencedBeanName);
			throw new AssertionError(msg);
		}

		String remainingPath = expression.substring(firstDotIndex + 1);
		try {
			PropertyUtils.getProperty(bean, remainingPath);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

}
