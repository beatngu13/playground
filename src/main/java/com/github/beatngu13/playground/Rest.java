package com.github.beatngu13.playground;

import com.google.gson.annotations.SerializedName;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class Rest {

	// GSON doesn't support records yet: https://github.com/google/gson/issues/1794
	private static class UUID {

		@SerializedName(value = "uuid")
		private final String value;

		@SuppressWarnings("unused")
		public UUID(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public static void main(String[] args) {
		jsonResponse();
		objectResponse();
	}

	private static void jsonResponse() {
		HttpResponse<JsonNode> response = Unirest.get("https://httpbin.org/uuid") //
				.accept("application/json") //
				.asJson();
		System.out.println(response.getStatus());
		System.out.println(response.getHeaders());

		JsonNode json = response.getBody();
		System.out.println(json.getObject().get("uuid"));
	}

	private static void objectResponse() {
		HttpResponse<UUID> getResponse = Unirest.get("https://httpbin.org/uuid") //
				.accept("application/json") //
				.asObject(UUID.class);

		UUID uuid = getResponse.getBody();
		String value = uuid.getValue();
		System.out.println(value);
	}

}
