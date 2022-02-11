package com.github.beatngu13.playground.puzzles;

import com.github.beatngu13.playground.puzzles.TreeWalker.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TreeWalkerTest {

	Node root;

	@BeforeEach
	void setUp() throws Exception {
		Node node11 = new Node("11", List.of());
		Node node1 = new Node("1", List.of(node11));
		Node node211 = new Node("211", List.of());
		Node node21 = new Node("21", List.of(node211));
		Node node2 = new Node("2", List.of(node21));
		Node node3 = new Node("3", List.of());
		root = new Node("0", List.of(node1, node2, node3));
	}

	@Test
	void testDepthFirst() throws Exception {
		assertThat(TreeWalker.listNamesDepthFirst(root)).containsExactly("0", "1", "11", "2", "21", "211", "3");
	}

	@Test
	void testBreadthFirst() throws Exception {
		assertThat(TreeWalker.listNamesBreadthFirst(root)).containsExactly("0", "1", "2", "3", "11", "21", "211");
	}

}
