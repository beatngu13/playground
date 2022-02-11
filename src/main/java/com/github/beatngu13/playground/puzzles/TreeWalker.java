package com.github.beatngu13.playground.puzzles;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeWalker {

	public static class Node {

		private final String name;
		private final List<Node> children;

		public Node(String name, List<Node> children) {
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

	public static List<String> listNamesDepthFirst(Node root) {
		List<String> names = new ArrayList<>();
		names.add(root.getName());

		for (Node node : root.getChildren()) {
			names.addAll(listNamesDepthFirst(node));
		}

		return names;
	}

	public static List<String> listNamesBreadthFirst(Node root) {
		List<String> names = new ArrayList<>();
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			names.add(node.getName());
			queue.addAll(node.getChildren());
		}

		return names;
	}

}
