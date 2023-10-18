package com.github.beatngu13.playground.misc;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MapMultiTest {

	record Child(String name) {
	}

	record Parent(String name, List<Child> children) {
	}

	record Names(String parentName, String childName) {
	}

	Child child0 = new Child("child0");
	Child child1 = new Child("child1");
	List<Parent> parents = List.of(new Parent("parent0", List.of(child0, child1)));

	@Test
	void testMapMulti() {
		var names = parents.stream()
				.<Names>mapMulti((parent, consumer) -> parent.children()
						.forEach(child -> consumer.accept(new Names(parent.name(), child.name()))))
				.toList();

		assertThat(names).containsExactly(
				new Names("parent0", "child0"),
				new Names("parent0", "child1"));
	}

	@Test
	void testFlatMap() {
		var names = parents.stream()
				.flatMap(parent -> parent.children().stream()
						.map(child -> new Names(parent.name(), child.name())))
				.toList();

		assertThat(names).containsExactly(
				new Names("parent0", "child0"),
				new Names("parent0", "child1"));
	}

}
