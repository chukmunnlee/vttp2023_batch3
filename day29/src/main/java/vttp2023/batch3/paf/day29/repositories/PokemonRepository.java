package vttp2023.batch3.paf.day29.repositories;

import java.io.StringReader;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Repository
public class PokemonRepository {

	@Autowired
	private MongoTemplate template;

	public void findPokemonByName(String name) {

		Criteria criteria = Criteria.where("name").regex(name, "i");

		Query query = Query.query(criteria);

		List<Document> results = template.find(query, Document.class, "mons");

		if (results.size() <= 0)
			return;

		Document root = results.get(0);

		Document stats = root.get("stats", Document.class);
		for (String i: List.of("hp", "attack", "defense", "spattack", "spdefense", "speed"))  
			System.out.printf("%s: %s\n", i, stats.getString(i));

		Document moves = root.get("moves", Document.class);
		List<Document> level = moves.getList("level", Document.class);
		for (Document d: level) {
			System.out.printf("name: %s\n", d.getString("name"));
		}

		System.out.println("\n\n================ with JSON-P ==============================");

		// Create a JsonObject
		JsonReader jsonReader = Json.createReader(new StringReader(root.toJson()));
		JsonObject pokemonJson = jsonReader.readObject();
		JsonObject statsJson = pokemonJson.getJsonObject("stats");

		for (String i: List.of("hp", "attack", "defense", "spattack", "spdefense", "speed"))  
			System.out.printf("%s: %s\n", i, statsJson.getString(i, "no value"));

		// moves.level
		JsonArray levelJson = pokemonJson.getJsonObject("moves").getJsonArray("level");
		levelJson.stream()
			.map(o -> o.asJsonObject())
			.forEach(o -> {
				System.out.printf("name: %s\n", o.getString("name"));
			});
	}
}
