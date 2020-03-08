package com.github.beatngu13.playground;

import com.google.gson.annotations.SerializedName;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class Rest {

	private static class UUID {

		@SerializedName(value = "uuid")
		private final String value;

		@SuppressWarnings("unused")
		public UUID(final String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public static void main(final String[] args) {
		jsonResponse();
		objectResponse();
	}

	private static void jsonResponse() {
		final HttpResponse<JsonNode> response = Unirest.get("https://httpbin.org/uuid") //
				.accept("application/json") //
				.asJson();
		System.out.println(response.getStatus());
		System.out.println(response.getHeaders());

		final JsonNode json = response.getBody();
		System.out.println(json.getObject().get("uuid"));
	}

	private static void objectResponse() {
		final HttpResponse<UUID> getResponse = Unirest.get("https://httpbin.org/uuid") //
				.accept("application/json") //
				.asObject(UUID.class);

		final UUID uuid = getResponse.getBody();
		final String value = uuid.getValue();
		System.out.println(value);
	}

}
