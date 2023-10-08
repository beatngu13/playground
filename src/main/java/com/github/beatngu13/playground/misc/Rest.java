package com.github.beatngu13.playground.misc;

import com.google.gson.annotations.SerializedName;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class Rest {

	record UUID(@SerializedName("uuid") String value) {
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
		System.out.println(json.getObject().get("uuid"));
	}

	private static void objectResponse() {
		HttpResponse<UUID> getResponse = Unirest.get("https://httpbin.org/uuid")
				.accept("application/json")
				.asObject(UUID.class);

		UUID uuid = getResponse.getBody();
		String value = uuid.value();
		System.out.println(value);
	}

}
