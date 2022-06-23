package com.github.beatngu13.playground.misc;

import java.util.List;

public class MapMulti {

	public static void main(String[] args) {
		record Child(String name) {
		}
		record Parent(String name, List<Child> children) {
		}
		record Names(String parentName, String childName) {
		}

		var parents = List.of(new Parent("parent0", List.of(new Child("child0"), new Child("child1"))));

		var foos = parents.stream()
				.<Names>mapMulti(
						(parent, consumer) -> parent.children().forEach(
								child -> consumer.accept(new Names(parent.name(), child.name()))))
				.toList();

		System.out.println(foos);
	}

}
