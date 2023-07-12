package vttp2023.batch3.paf.day29.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2023.batch3.paf.day29.models.Person;

@Controller
@RequestMapping
public class FormController {

	@GetMapping(path="/form")
	//public String getForm(@RequestParam MultiValueMap<String, String> payload) {
	public String getForm(@RequestParam String name, @RequestParam String email) {

		System.out.printf("GET PAYLOAD: name = %s\n", name);
		System.out.printf("GET PAYLOAD: email = %s\n", email);

		//for (String field: payload.keySet())
		//	System.out.printf("GET PAYLOAD: %s = %s\n", field, payload.getFirst(field));

		return "redirect:/form.html";
	}

	@PostMapping(path="/form")
	//public String postForm(@RequestBody String payload) {
	public String postForm(@RequestBody MultiValueMap<String, String> payload) {
	//public String postForm(@ModelAttribute Person payload) {

		for (String field: payload.keySet())
			System.out.printf("PAYLOAD: %s = %s\n", field, payload.getFirst(field));

		System.out.printf("PAYLOAD: %s\n", payload);

		return "redirect:/form.html";
	}
}
