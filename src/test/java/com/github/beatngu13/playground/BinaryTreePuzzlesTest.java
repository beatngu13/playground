package com.github.beatngu13.playground;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.github.beatngu13.playground.BinaryTreePuzzles.Node;

class BinaryTreePuzzlesTest {

	@Test
	void testGetLevelAverages() {
		final Node n5 = new Node(7, null, null);
		final Node n4 = new Node(15, null, null);
		final Node n3 = new Node(8, null, null);
		final Node n2 = new Node(20, n4, n5);
		final Node n1 = new Node(9, n3, null);
		final Node n0 = new Node(3, n1, n2);
		assertThat(BinaryTreePuzzles.getLevelAverages(n0)).containsExactly(3.0, 14.5, 10.0);
	}

	@Test
	void testGetNextSuccessor() {
		final Node n5 = new Node(7, null, null);
		final Node n4 = new Node(15, null, null);
		final Node n3 = new Node(8, null, null);
		final Node n2 = new Node(20, n4, n5);
		final Node n1 = new Node(9, n3, null);
		final Node n0 = new Node(3, n1, n2);
		assertThat(BinaryTreePuzzles.getNextSuccessor(n0, 3)).isEqualTo(n1);
		assertThat(BinaryTreePuzzles.getNextSuccessor(n0, 9)).isEqualTo(n2);
		assertThat(BinaryTreePuzzles.getNextSuccessor(n0, 15)).isEqualTo(n5);
		assertThat(BinaryTreePuzzles.getNextSuccessor(n0, 7)).isNull();
	}

}
