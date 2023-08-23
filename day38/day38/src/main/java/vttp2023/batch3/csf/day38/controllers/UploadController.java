package vttp2023.batch3.csf.day38.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
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
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2023.batch3.csf.day38.models.UploadContent;
import vttp2023.batch3.csf.day38.repositories.BlobRepository;
import vttp2023.batch3.csf.day38.repositories.S3Repository;

@Controller
@RequestMapping
public  class UploadController {

	@Autowired
	private BlobRepository blobRepo;

	@Autowired
	private S3Repository s3Repo;

	@GetMapping(path="/image/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> getImageById(@PathVariable Integer id) {

		Optional<UploadContent> opt = blobRepo.selectUploadsById(id);
		if (opt.isEmpty())
			return ResponseEntity.status(404).body(new byte[0]);

		UploadContent uc = opt.get();

		return ResponseEntity.status(200)
			.contentType(MediaType.valueOf(uc.contentType()))
			.contentLength(uc.content().length)
			.body(uc.content());
	}

	@GetMapping(path="/upload/{id}") 
	public ModelAndView getUploadById(@PathVariable Integer id) {

		ModelAndView mav = new ModelAndView();

		Optional<UploadContent> opt = blobRepo.selectUploadsById(id);

		if (opt.isEmpty()) {
			mav.setStatus(HttpStatusCode.valueOf(404));
			mav.setViewName("error");
			mav.addObject("errorMsg", "Not found");
			return mav;
		}

		UploadContent uc = opt.get();

		mav.addObject("description", uc.description());
		mav.addObject("id", uc.id());
		mav.setViewName("content");
		mav.setStatus(HttpStatusCode.valueOf(200));

		return mav;
	}

	@PostMapping(path="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
			, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> postUploadReturnJson(@RequestPart String description
			, @RequestPart MultipartFile myfile) {

		try {
			String mediaType = myfile.getContentType();
			InputStream is = myfile.getInputStream();

			blobRepo.upload(description, mediaType, is);
			String id = s3Repo.saveImage(myfile);
			System.out.printf(">>> saving to S3: %s\n", id);
			JsonObject resp = Json.createObjectBuilder()
				.add("id", id)
				.build();
			return ResponseEntity.ok(resp.toString());
		} catch (IOException ex) {
			JsonObject resp = Json.createObjectBuilder()
				.add("error", ex.getMessage())
				.build();
			return ResponseEntity.status(500)
				.body(resp.toString());
		}
	}

	@PostMapping(path="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView postUpload(@RequestPart String description
			, @RequestPart MultipartFile myfile) {

		ModelAndView mav = new ModelAndView();

		try {
			String mediaType = myfile.getContentType();
			InputStream is = myfile.getInputStream();

			blobRepo.upload(description, mediaType, is);
			String id = s3Repo.saveImage(myfile);
			System.out.printf(">>> saving to S3: %s\n", id);
		} catch (IOException ex) {
			mav.setStatus(HttpStatusCode.valueOf(500));
			mav.setViewName("error");
			mav.addObject("errorMsg", ex.getMessage());
			return mav;
		}

		mav.addObject("controlName", myfile.getName());
		mav.addObject("fileName", myfile.getOriginalFilename());
		mav.addObject("mediaType", myfile.getContentType());
		mav.addObject("size", myfile.getSize());
		mav.setViewName("upload");
		mav.setStatus(HttpStatusCode.valueOf(200));

		return mav;
	}
}
