package vttp2023.batch3.paf.day29;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2023.batch3.paf.day29.repositories.BookRepository;
import vttp2023.batch3.paf.day29.repositories.PokemonRepository;

@SpringBootApplication
public class Day29Application implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepo;

	@Autowired 
	private PokemonRepository pokemonRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day29Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		/*
		pokemonRepo.findPokemonByName("Ivysaur");

		for (String t: bookRepo.getAllTitles())
			System.out.printf(">>> %s\n", t);

		System.exit(0);
		*/

	}

}
