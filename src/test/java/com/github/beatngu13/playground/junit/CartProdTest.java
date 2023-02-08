package com.github.beatngu13.playground.junit;

import org.junitpioneer.jupiter.cartesian.ArgumentSets;
import org.junitpioneer.jupiter.cartesian.CartesianTest;

class CartProdTest {

	@CartesianTest
	@CartesianTest.MethodFactory("classWithObjectParams")
	void testClassWithObject(String clazz, Object object) {
		System.out.println(clazz + " " + object);
	}

	static ArgumentSets classWithObjectParams() {
		return ArgumentSets
				.argumentsForFirstParameter(classParams())
				.argumentsForNextParameter(new Object());
	}

	@CartesianTest
	@CartesianTest.MethodFactory("classWithIntegerParams")
	void testClassWithInteger(String clazz, int integerParam) {
		System.out.println(clazz + " " + integerParam);
	}

	static ArgumentSets classWithIntegerParams() {
		return ArgumentSets
				.argumentsForFirstParameter(classParams())
				.argumentsForNextParameter(1, 2, 3, 4, 5, 6);
	}

	@CartesianTest
	@CartesianTest.MethodFactory("classWithBooleanParams")
	void testClassWithBoolean(String clazz, boolean booleanParam) {
		System.out.println(clazz + " " + booleanParam);
	}

	static ArgumentSets classWithBooleanParams() {
		return ArgumentSets
				.argumentsForFirstParameter(classParams())
				.argumentsForNextParameter(false, true);
	}

	static String[] classParams() {
		return new String[]{"classParam1", "classParam2", "classParam3"};
	}

}
