package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TesteUnirest {

	@Test
	public void deveRetornarStatusOk() {
		HttpResponse<JsonNode> jsonResponse = null;
		try {
			jsonResponse = Unirest.get("http://www.mocky.io/v2/5a9ce37b3100004f00ab5154")
					.header("accept", "application/json").queryString("apiKey", "123").asJson();

		} catch (UnirestException e) {
			e.printStackTrace();
		}
		
		System.out.println("Response: " + jsonResponse.getBody() + "\n" + jsonResponse.getStatus());
		
		assertNotNull(jsonResponse.getBody());
		assertEquals(200, jsonResponse.getStatus());
	}

	@Test
	public void deveRetornarStatusAceito() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("accept", "application/json");
		headers.put("Authorization", "Bearer 5a9ce37b3100004f00ab5154");

		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("name", "Sam Baeldung");
		fields.put("id", "PSP123");

		HttpResponse<JsonNode> jsonResponse = null;
		try {
			jsonResponse = Unirest.put("http://www.mocky.io/v2/5a9ce7853100002a00ab515e").headers(headers)
					.fields(fields).asJson();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		
		System.out.println("Response: " + jsonResponse.getBody() + "\n" + jsonResponse.getStatus() + "\n"
				+ jsonResponse.getHeaders() + "\n" + jsonResponse.getStatusText());
		
		assertNotNull(jsonResponse.getBody());
		assertEquals(202, jsonResponse.getStatus());
	}

	@Test
	public void givenRequestBodyWhenCreatedThenCorrect() {

		HttpResponse<JsonNode> jsonResponse = null;
		
		try {
			jsonResponse = Unirest.post("http://www.mocky.io/v2/5a9ce7663100006800ab515d")
					.body("{\"name\":\"Sam Baeldung\", \"city\":\"viena\"}").asJson();

		} catch (UnirestException e) {
			e.printStackTrace();
		}
		
		System.out.println("Response: " + jsonResponse.getBody() + "\n" + jsonResponse.getStatus());
		assertEquals(201, jsonResponse.getStatus());
	}
}