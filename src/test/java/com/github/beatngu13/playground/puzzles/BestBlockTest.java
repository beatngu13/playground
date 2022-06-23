package com.github.beatngu13.playground.puzzles;

import com.github.beatngu13.playground.puzzles.BestBlock.Block;
import com.github.beatngu13.playground.puzzles.BestBlock.Poi;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BestBlockTest {

	@Test
	void test_one_best_block() {
		Set<Poi> requirements = Set.of(Poi.GYM, Poi.SCHOOL, Poi.STORE);
		Block block0 = new Block(0, Set.of(Poi.SCHOOL));
		Block block1 = new Block(1, Set.of(Poi.GYM));
		Block block2 = new Block(2, Set.of(Poi.GYM, Poi.SCHOOL));
		Block block3 = new Block(3, Set.of(Poi.SCHOOL, Poi.STORE));
		List<Block> blocks = List.of(block0, block1, block2, block3);

		Optional<Block> bestBlock = BestBlock.of(requirements, blocks);

		assertThat(bestBlock).hasValue(block2);
	}

	@Test
	void test_two_best_blocks() {
		Set<Poi> requirements = Set.of(Poi.GYM, Poi.SCHOOL);
		Block block0 = new Block(0, requirements);
		Block block1 = new Block(1, requirements);
		List<Block> blocks = List.of(block0, block1);

		Optional<Block> bestBlock = BestBlock.of(requirements, blocks);

		assertThat(bestBlock).hasValueSatisfying(bb -> assertThat(bb).isIn(block0, block1));
	}

	@Test
	void test_no_best_block() {
		Set<Poi> requirements = Set.of(Poi.GYM);
		List<Block> blocks = List.of(new Block(0, Collections.emptySet()));

		Optional<Block> bestBlock = BestBlock.of(requirements, blocks);

		assertThat(bestBlock).isEmpty();
	}

}
