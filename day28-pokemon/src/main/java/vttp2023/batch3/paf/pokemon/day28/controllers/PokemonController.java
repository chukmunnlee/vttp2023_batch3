package vttp2023.batch3.paf.pokemon.day28.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2023.batch3.paf.pokemon.day28.repositories.PokemonRepository;

@Controller
@RequestMapping
public class PokemonController {

	@Autowired
	private PokemonRepository pokemonRepo;

	@GetMapping(path={"/", "/index.html"})
	public String get(Model model) {

		List<String> types = pokemonRepo.getTypes();

		model.addAttribute("types", types);

		return "types";
	}
}
