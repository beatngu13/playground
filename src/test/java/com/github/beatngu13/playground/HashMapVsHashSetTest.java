package com.github.beatngu13.playground;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class HashMapVsHashSetTest {

	@Test
	void test() {
		// Use deprecated ctor to circumvent caching.
		var first = new Integer(1);
		var second = new Integer(1);

		assertThat(first)
				.isNotSameAs(second)
				.isEqualTo(second);

		// Javadoc: "If this set already contains the element, the call leaves the set unchanged and returns false."
		var set = new HashSet<>();
		assertThat(set.add(first)).isTrue();
		assertThat(set.add(second)).isFalse();

		// According to Javadoc, first should not be replaced ("set unchanged").
		assertThat(set).first().isSameAs(first);

		// Javadoc: "If the map previously contained a mapping for the key, the old value is replaced by the specified value."
		var map = new HashMap<>();
		assertThat(map.put(first, first)).isNull();
		assertThat(map.put(second, second)).isSameAs(first);

		// According to Javadoc, first should be replaced ("old value replaced").
		assertThat(map.keySet()).first().isSameAs(first);
		assertThat(map.values()).first().isSameAs(second);

		// Although HashSet internally uses a HashMap, this works because keys (not values) are not replaced.
	}

}
