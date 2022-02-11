package com.github.beatngu13.playground.puzzles;

import com.github.beatngu13.playground.puzzles.MathUtils.Pair;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MathUtilsTest {

	@Test
	void pair_with_sum_should_be_empty_if_data_is_empty() {
		List<Integer> data = Collections.emptyList();
		int sum = 0;
		assertThat(MathUtils.getPairWithSum(data, sum)).isEmpty();
	}

	@Test
	void pair_with_sum_should_be_empty_if_no_pair_exists() {
		List<Integer> data = List.of(1, 2, 3, 9);
		int sum = 8;
		assertThat(MathUtils.getPairWithSum(data, sum)).isEmpty();
	}

	@Test
	void pair_with_sum_should_not_be_empty_if_pair_exists() {
		List<Integer> data = List.of(1, 2, 4, 4);
		int sum = 8;
		assertThat(MathUtils.getPairWithSum(data, sum)).hasValue(new Pair<Integer>(4, 4));
	}

	@Test
	void pair_with_sum_should_handle_negative_numbers() {
		List<Integer> data = List.of(1, -3, 5, 2);
		int sum = -1;
		assertThat(MathUtils.getPairWithSum(data, sum)).hasValue(new Pair<Integer>(-3, 2));
	}

}
