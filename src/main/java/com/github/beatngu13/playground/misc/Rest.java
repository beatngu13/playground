package com.github.beatngu13.playground.misc;

import com.fasterxml.jackson.annotation.JsonProperty;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;

public class Rest {

	record UUID(@JsonProperty("uuid") String value) {
	}

	public static void main(String[] args) {
		jsonResponse();
		objectResponse();
	}

	private static void jsonResponse() {
		HttpResponse<JsonNode> response = Unirest.get("https://httpbin.org/uuid")
				.accept("application/json")
				.asJson();
		System.out.println(response.getStatus());
		System.out.println(response.getHeaders());

		JsonNode json = response.getBody();
		System.out.println(STR."UUID from JSON: \{json.getObject().get("uuid")}");
	}

	private static void objectResponse() {
		HttpResponse<UUID> getResponse = Unirest.get("https://httpbin.org/uuid")
				.accept("application/json")
				.asObject(UUID.class);

		UUID uuid = getResponse.getBody();
		System.out.println(STR."UUID from object: \{uuid.value()}");
	}

}
