package vttp2023.batch3.csf.day36.gserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import vttp2023.batch3.csf.day36.gserver.services.GiphyService;

@Controller
@RequestMapping(path="/api")
@CrossOrigin
public class GiphyController {

	@Autowired
	private GiphyService giphySvc;

	@GetMapping(path="/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> search(@RequestParam String q) {

		System.out.printf("query = %s\n", q);

		List<String> images = giphySvc.search(q);

		return ResponseEntity.ok(
			Json.createArrayBuilder(images).build().toString()
		);
	}
}
