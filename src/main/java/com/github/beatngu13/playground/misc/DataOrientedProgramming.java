package com.github.beatngu13.playground.misc;

import java.util.List;
import java.util.Map;

import static com.github.beatngu13.playground.misc.DataOrientedProgramming.TooManyFavorites.MAX_NUM_FAVORITES;

class DataOrientedProgramming {

	sealed interface UpdateFavoritesResult permits Success, Failure {
	}

	record Success() implements UpdateFavoritesResult {
	}

	sealed interface Failure extends UpdateFavoritesResult {
		String errorMessage();
	}

	record TooManyFavorites(int maxFavorites, int actualFavorites) implements Failure {
		static final int MAX_NUM_FAVORITES = 3;

		@Override
		public String errorMessage() {
			return String.format("Expected %s but was %s.", maxFavorites, actualFavorites);
		}
	}

	record UserNotFound(int userId) implements Failure {
		@Override
		public String errorMessage() {
			return String.format("User ID %s not found.", userId);
		}
	}

	Map<Integer, List<String>> user2Favorites = Map.of(
			1, List.of("foo", "bar", "baz"),
			2, List.of("baz")
	);

	UpdateFavoritesResult updateFavorites(int userId, List<String> favoritesIds) {
		if (favoritesIds.size() > MAX_NUM_FAVORITES) {
			return new TooManyFavorites(MAX_NUM_FAVORITES, favoritesIds.size());
		}
		if (!user2Favorites.containsKey(userId)) {
			return new UserNotFound(userId);
		}
		user2Favorites.replace(userId, List.copyOf(favoritesIds));
		return new Success();
	}

	public static void main(String[] args) {
		var dop = new DataOrientedProgramming();
		var updateFavoritesResult = dop.updateFavorites(3, List.of());
		switch (updateFavoritesResult) {
			case Success _ -> System.out.println("🎉");
			case Failure e -> System.out.println(e.errorMessage());
		}
	}

}
