package com.github.beatngu13.playground.puzzles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// See: https://youtu.be/rw4s4M3hFfs
public class BestBlock {

	public enum Poi {
		GYM, SCHOOL, STORE
	}

	/**
	 * @param position The block's position.
	 * @param pois     The block's POIs, where absent means false.
	 */
	public record Block(int position, Set<Poi> pois) {

	}

	public record BlockDistances(Block block, Map<Poi, Integer> distances) {

		public int sumDistances() {
			return distances.values().stream()
					.mapToInt(Integer::intValue)
					.sum();
		}

	}

	public static Optional<Block> of(Set<Poi> requirements, List<Block> blocks) {
		List<BlockDistances> blockDistances = new ArrayList<>();

		for (Block block : blocks) {
			if (block.pois().containsAll(requirements)) {
				return Optional.of(block);
			}

			Map<Poi, Integer> distances = new HashMap<>();
			// Technically not necessary to determine best block, but ensures a complete distance mapping.
			block.pois.forEach(poi -> distances.put(poi, 0));

			Set<Poi> remainingRequirements = requirements.stream()
					.filter(r -> !block.pois().contains(r))
					.collect(Collectors.toSet());
			List<Block> otherBlocks = blocks.stream()
					.filter(b -> !block.equals(b))
					.toList();

			for (Poi requiredPoi : remainingRequirements) {
				for (Block otherBlock : otherBlocks) {
					if (!otherBlock.pois().contains(requiredPoi)) {
						continue;
					}

					int distance = Math.abs(block.position() - otherBlock.position());
					distances.merge(requiredPoi, distance, Math::min);
				}
			}

			if (distances.keySet().containsAll(requirements)) {
				blockDistances.add(new BlockDistances(block, distances));
			}
		}

		return blockDistances.stream()
				.min(Comparator.comparing(BlockDistances::sumDistances))
				.map(BlockDistances::block);
	}

}
