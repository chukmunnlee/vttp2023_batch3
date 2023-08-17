package vttp2023.batch3.csf.day37.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp2023.batch3.csf.day37.server.repositories.BookRepository;

@Controller
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin
public class BookController {

	@Autowired
	private BookRepository bookRepo;

	@GetMapping(path="/books")
	@ResponseBody
	public ResponseEntity<String> getBooks() {

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

		this.bookRepo.getBookSummary(20).stream()
			.map(bk -> Json.createObjectBuilder()
					.add("bookId", bk.bookId())
					.add("title", bk.title())
					.build())
			.forEach(j -> arrBuilder.add(j));

		return ResponseEntity.ok(arrBuilder.build().toString());
	}

}

