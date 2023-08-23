package vttp2023.batch3.csf.day38.upload.controllers;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2023.batch3.csf.day38.upload.services.AudioRepository;

@Controller
@RequestMapping
public class AudioUploadController {

	@Autowired
	private AudioRepository audioRepo;

	@GetMapping(path="/audio/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getUrl(@PathVariable String id) {
		String url = audioRepo.getURL(id);
		JsonObject resp = Json.createObjectBuilder()
			.add("url", url)
			.build();
		//TODO: set media type
		return ResponseEntity.ok(resp.toString());
	}

	@PostMapping(path="/audio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postAudio(@RequestPart MultipartFile audio) {
		try {
			String contentType = audio.getContentType();
			InputStream is = audio.getInputStream();
			String id = audioRepo.upload(contentType, is);
			JsonObject resp = Json.createObjectBuilder()
				.add("id", id)
				.build();
			return ResponseEntity.ok(resp.toString());

		} catch (Exception ex) {
			JsonObject resp = Json.createObjectBuilder()
				.add("error", ex.getMessage())
				.build();
			return ResponseEntity.status(500)
				.body(resp.toString());
		}
	}
}
