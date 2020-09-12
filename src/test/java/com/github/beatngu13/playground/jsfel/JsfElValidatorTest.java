package com.github.beatngu13.playground.jsfel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThatCode;

class JsfElValidatorTest {

	HelloBean helloBean;
	NestedBean nestedBean;

	@BeforeEach
	void setUp() {
		helloBean = new HelloBean();
		helloBean.setName("foo");

		nestedBean = new NestedBean();
		nestedBean.setHelloBean(helloBean);
	}

	@Test
	void test_valid() throws Exception {
		Path xhtml = Paths.get(getClass().getResource("/valid.xhtml").toURI());
		assertThatCode(() -> JsfElValidator.validate(xhtml, helloBean)).doesNotThrowAnyException();
	}

	@Test
	void test_invalid() throws Exception {
		Path xhtml = Paths.get(getClass().getResource("/invalid.xhtml").toURI());
		assertThatCode(() -> JsfElValidator.validate(xhtml, helloBean)).isExactlyInstanceOf(AssertionError.class);
	}

	@Test
	void test_nested_valid() throws Exception {
		Path xhtml = Paths.get(getClass().getResource("/nested-valid.xhtml").toURI());
		assertThatCode(() -> JsfElValidator.validate(xhtml, nestedBean)).doesNotThrowAnyException();
	}

	@Test
	void test_nested_invalid() throws Exception {
		Path xhtml = Paths.get(getClass().getResource("/nested-invalid.xhtml").toURI());
		assertThatCode(() -> JsfElValidator.validate(xhtml, nestedBean)).isExactlyInstanceOf(AssertionError.class);
	}

}
