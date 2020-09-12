package com.github.beatngu13.playground.jsfel;

import org.apache.commons.beanutils.PropertyUtils;

import java.nio.file.Path;
import java.util.List;

public class JsfElValidator {

	public static void validate(Path xhtml, Object bean) {
		List<String> expressions = JsfElParser.parse(xhtml);
		expressions.forEach(expression -> validate(expression, bean));
	}

	private static void validate(String expression, Object bean) {
		// "#{bean.property}" => "bean.property"
		String fullPath = expression.substring(2, expression.length() - 1);
		int firstDotIndex = fullPath.indexOf(".");

		String actualBeanName = bean.getClass().getSimpleName();
		String referredBeanName = fullPath.substring(0, firstDotIndex);
		if (!actualBeanName.equalsIgnoreCase(referredBeanName)) {
			String msg = String.format("Bean name does not match. '%s' referred to as '%s' in XHTML.",
					actualBeanName,
					referredBeanName);
			throw new AssertionError(msg);
		}

		String remainingPath = fullPath.substring(firstDotIndex + 1);
		try {
			PropertyUtils.getProperty(bean, remainingPath);
		} catch (Exception e) {
			throw new AssertionError(e);
		}
	}

}
