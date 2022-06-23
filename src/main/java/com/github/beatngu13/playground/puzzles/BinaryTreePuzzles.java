package com.github.beatngu13.playground.puzzles;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTreePuzzles {

	public record Node(int value, Node leftChild, Node rightChild) {
	}

	public static List<Double> getLevelAverages(Node root) {
		List<Double> avg = new ArrayList<>();
		List<Node> levelNodes = new ArrayList<>();
		levelNodes.add(root);

		while (!levelNodes.isEmpty()) {
			int sum = 0;
			List<Node> nextLevelNodes = new ArrayList<>();

			for (Node node : levelNodes) {
				sum += node.value();

				if (node.leftChild() != null) {
					nextLevelNodes.add(node.leftChild());
				}

				if (node.rightChild() != null) {
					nextLevelNodes.add(node.rightChild());
				}
			}

			avg.add((double) sum / levelNodes.size());
			levelNodes = nextLevelNodes;
		}

		return avg;
	}

	public static Node getNextSuccessor(Node root, int searchValue) {
		Queue<Node> todo = new ArrayDeque<>();
		todo.add(root);

		boolean found = false;

		while (!todo.isEmpty()) {
			Node node = todo.poll();

			if (found) {
				return node;
			}

			if (node.value() == searchValue) {
				found = true;
			}

			if (node.leftChild() != null) {
				todo.add(node.leftChild());
			}

			if (node.rightChild() != null) {
				todo.add(node.rightChild());
			}
		}

		return null;
	}

}
