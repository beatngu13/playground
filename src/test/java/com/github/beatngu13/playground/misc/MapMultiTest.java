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

	@Test
	void test() {
		var child0 = new Child("child0");
		var child1 = new Child("child1");
		var parents = List.of(new Parent("parent0", List.of(child0, child1)));

		var names = parents.stream()
				.<Names>mapMulti(
						(parent, consumer) -> parent.children().forEach(
								child -> consumer.accept(new Names(parent.name(), child.name()))))
				.toList();

		assertThat(names).containsExactly(
				new Names("parent0", "child0"),
				new Names("parent0", "child1"));
	}

}
