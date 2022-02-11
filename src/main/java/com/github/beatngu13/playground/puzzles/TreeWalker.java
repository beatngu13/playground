package com.github.beatngu13.playground.puzzles;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeWalker {

	public static record Node(String name, List<Node> children) {
	}

	public static List<String> listNamesDepthFirst(Node root) {
		List<String> names = new ArrayList<>();
		names.add(root.name());

		for (Node node : root.children()) {
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
			names.add(node.name());
			queue.addAll(node.children());
		}

		return names;
	}

}
