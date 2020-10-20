package com.github.beatngu13.playground;

import org.junitpioneer.jupiter.CartesianProductTest;

class CartProdTest {

	@CartesianProductTest(factory = "classWithObjectParams")
	void testClassWithObject(String clazz, Object object) {
		System.out.println(clazz + " " + object);
	}

	static CartesianProductTest.Sets classWithObjectParams() {
		return new CartesianProductTest.Sets()
				.add(classParams())
				.add(new Object());
	}

	@CartesianProductTest(factory = "classWithIntegerParams")
	void testClassWithInteger(String clazz, int integerParam) {
		System.out.println(clazz + " " + integerParam);
	}

	static CartesianProductTest.Sets classWithIntegerParams() {
		return new CartesianProductTest.Sets()
				.add(classParams())
				.add(1, 2, 3, 4, 5, 6);
	}

	@CartesianProductTest(factory = "classWithBooleanParams")
	void testClassWithBoolean(String clazz, boolean booleanParam) {
		System.out.println(clazz + " " + booleanParam);
	}

	static CartesianProductTest.Sets classWithBooleanParams() {
		return new CartesianProductTest.Sets()
				.add(classParams())
				.add(false, true);
	}

	static String[] classParams() {
		return new String[]{"classParam1", "classParam2", "classParam3"};
	}

}
