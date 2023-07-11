package vttp2023.batch3.paf.day28;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2023.batch3.paf.day28.repositories.TVShowRepository;

@SpringBootApplication
public class Day28Application implements CommandLineRunner {

	@Autowired
	private TVShowRepository tvshowRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day28Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//List<Document> result = tvshowRepo.findShowsByLanguage("english");
		//List<Document> result = tvshowRepo.getTitleSummary();
		List<Document> result = tvshowRepo.getGenresStats();

		for (Document d: result) 
			System.out.printf(">>> %s\n\n", d.toJson());

		System.exit(0);
	}

}
