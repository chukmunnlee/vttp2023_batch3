package vttp2023.batch3.csf.day36.gserver.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GiphyService {

	public static final String URL = "https://api.giphy.com/v1/gifs/search";

	@Value("${giphy.api.key}")
	private String apiKey;

	public List<String> search(String q) {

		String url = UriComponentsBuilder.fromUriString(URL)
				.queryParam("q", q)
				.queryParam("limit", 20)
				.queryParam("api_key", apiKey)
				.toUriString();

		RequestEntity<Void> get = RequestEntity.get(url).build();

		RestTemplate template = new RestTemplate();

		try {
			ResponseEntity<String> resp = template.exchange(get, String.class);
			String payload = resp.getBody();
			JsonReader reader = Json.createReader(new StringReader(payload));
			JsonObject result = reader.readObject();
			JsonArray data = result.getJsonArray("data");
			return data.stream()
				.map(v -> v.asJsonObject())
				.map(v -> v.getJsonObject("images").getJsonObject("fixed_height").getString("url"))
				.toList();
		} catch (Exception ex) {
			// 400 - 500
			ex.printStackTrace();
		}

		return new LinkedList<>();
	}
}
