package vttp2023.batch3.csf.day36.server.controllers;

import java.io.StringReader;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@RestController
@RequestMapping(path="/api")
@CrossOrigin
public class RegistrationController {

	@PostMapping(path="/register", consumes = MediaType.APPLICATION_JSON_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postRegister(@RequestBody String payload) {
		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject registration = reader.readObject();
		System.out.printf(">>>> registration: %s\n", registration);
		if (!registration.getString("name").trim().equals("fred"))
			// catch
			return ResponseEntity.status(400)
				.body(
					Json.createObjectBuilder()
						.add("message", "Cannot register %s".formatted(registration.getString("name")))
						.build().toString()
				);

		//then
		return ResponseEntity.ok(
			Json.createObjectBuilder()
				.add("message", "%s registered".formatted(registration.getString("name")))
				.build().toString()
		);
	}
}
