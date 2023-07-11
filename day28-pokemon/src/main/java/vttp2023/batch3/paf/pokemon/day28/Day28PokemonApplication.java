package vttp2023.batch3.paf.pokemon.day28;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2023.batch3.paf.pokemon.day28.repositories.PokemonRepository;

@SpringBootApplication
public class Day28PokemonApplication implements CommandLineRunner {

	@Autowired
	private PokemonRepository pokemonRepo;

	public static void main(String[] args) {
		SpringApplication.run(Day28PokemonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
