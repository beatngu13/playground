package com.github.beatngu13.playground.puzzles;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeWalker {

	public static class Node {

		private final String name;
		private final List<Node> children;

		public Node(final String name, final List<Node> children) {
			this.name = name;
			this.children = children;
		}

		public String getName() {
			return name;
		}

		public List<Node> getChildren() {
			return children;
		}

	}

	public static List<String> listNamesDepthFirst(final Node root) {
		final List<String> names = new ArrayList<>();
		names.add(root.getName());

		for (final Node node : root.getChildren()) {
			names.addAll(listNamesDepthFirst(node));
		}

		return names;
	}

	public static List<String> listNamesBreadthFirst(final Node root) {
		final List<String> names = new ArrayList<>();
		final Queue<Node> queue = new ArrayDeque<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			final Node node = queue.poll();
			names.add(node.getName());
			queue.addAll(node.getChildren());
		}

		return names;
	}

}
