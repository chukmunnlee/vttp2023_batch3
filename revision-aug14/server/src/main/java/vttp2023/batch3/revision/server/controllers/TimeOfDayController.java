package vttp2023.batch3.revision.server.controllers;

import java.io.StringReader;
import java.util.Date;

import javax.print.attribute.standard.Media;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Controller
@RequestMapping(path="/api")
@CrossOrigin
public class TimeOfDayController {

	@PostMapping(path="/register"
		, consumes = MediaType.APPLICATION_JSON_VALUE
		, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postRegisterAsJson(
		@RequestBody String form) {

		System.out.printf(">> json payload: %s\n", form);
		JsonReader reader = Json.createReader(new StringReader(form));
		JsonObject json = reader.readObject();

		String name = json.getString("name");
		String email = json.getString("email");

		JsonObject resp = Json.createObjectBuilder()
			.add("message", "%s have been registered".formatted(name))
			.build();

		return ResponseEntity.ok(resp.toString());
		//return ResponseEntity.status(400).body("{}");
	}

	@PostMapping(path="/register"
		, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
		, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postRegister(
			@RequestBody MultiValueMap<String, String> form) {
		String name = form.getFirst("name");
		String email = form.getFirst("email");
		System.out.printf("name: %s, email: %s\n", name, email);

		JsonObject resp = Json.createObjectBuilder()
			.add("message", "%s have been registered".formatted(name))
			.build();

		return ResponseEntity.ok(resp.toString());
	}

	@GetMapping(path="/time", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getTime() {
		String date = (new Date()).toString();
		JsonObject resp = Json.createObjectBuilder()
			.add("timestamp", date)
			.build();
		return ResponseEntity.ok(resp.toString());
	}
}
