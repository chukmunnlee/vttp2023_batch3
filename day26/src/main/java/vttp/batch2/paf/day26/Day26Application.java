package vttp.batch2.paf.day26;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batch2.paf.day26.repositories.ShowsRepository;

@SpringBootApplication
public class Day26Application implements CommandLineRunner {

	@Autowired
	private ShowsRepository showRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day26Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		List<Document> docs = showRepo.findShowsByName("Bitten");

		for (Document d: docs) {
			String name = d.getString("name");
			String type = d.getString("type");
			Integer runtime = d.getInteger("runtime");
			// Returing the Json array as list of String
			List<String> genres = d.getList("genres", String.class);
			// Get average rating
			Document rateDoc = d.get("rating", Document.class);
			Double avg = rateDoc.getDouble("average");

			System.out.printf(">>>> title: %s, type: %s, rumtime: %d, avg rating: %.2f\n"
					, name, type, runtime, avg);
			System.out.printf("\tgneres: %s\n", genres);
		}
		*/

	}

}
