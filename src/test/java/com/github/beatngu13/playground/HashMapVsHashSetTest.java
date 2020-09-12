package com.github.beatngu13.playground;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class HashMapVsHashSetTest {

	@Test
	void test() throws Exception {
		// Use deprecated ctor to circumvent caching.
		final Integer first = new Integer(1);
		final Integer second = new Integer(1);

		assertThat(first).isNotSameAs(second);
		assertThat(first).isEqualTo(second);

		// "If this set already contains the element, the call leaves the set unchanged and returns false."
		final Set<Integer> set = new HashSet<Integer>();
		assertThat(set.add(first)).isTrue();
		assertThat(set.add(second)).isFalse();

		// "If the map previously contained a mapping for the key, the old value is replaced by the specified value."
		final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		assertThat(map.put(first, first)).isNull();
		assertThat(map.put(second, second)).isSameAs(first);

		// According to Javadoc, first should not be replaced ("set unchanged").
		assertThat(set).first().isSameAs(first);

		// According to Javadoc, first should be replaced ("old value replaced").
		assertThat(map.keySet()).first().isSameAs(first);
		assertThat(map.values()).first().isSameAs(second);

		// Although HashSet internally uses a HashMap, this works because keys (not values) are not replaced.
	}

}
