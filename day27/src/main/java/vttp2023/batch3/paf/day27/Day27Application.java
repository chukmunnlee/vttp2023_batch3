package vttp2023.batch3.paf.day27;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2023.batch3.paf.day27.respositories.ShowRepository;

@SpringBootApplication
public class Day27Application implements CommandLineRunner {

	@Autowired
	private ShowRepository showRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day27Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JsonObject json = Json.createObjectBuilder()
				.add("name", "fred")
				.add("email", "fred@gmail.com")
				.add("hobbies",
						Json.createArrayBuilder()
							.add("jogging")
							.add("music")
							.add("travel")
							.build()
				 )
				.build();
		System.out.printf(">>> json: %s\n", json.toString());

		Document doc = Document.parse(json.toString());

		System.out.printf(">>> bson: %s\n", doc);

		/*
		for(String t: showRepo.getAllTypes()) {
			System.out.printf(">>> type: %s\n", t);

			System.out.println("---------------------");

			for(Document d: showRepo.getShowsByType(t, 3, 0))
					System.out.printf("\t%s\n", d);
			System.out.println();
		}
		*/
	}

}
