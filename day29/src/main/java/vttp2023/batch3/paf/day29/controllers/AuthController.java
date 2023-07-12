package vttp2023.batch3.paf.day29.controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vttp2023.batch3.paf.day29.services.CounterService;

@Controller
@RequestMapping
public class AuthController {

	public static final String ATTR_PERSONAL_COUNTER = "personalCounter";
	public static final String ATTR_LIST = "list";

	@Autowired
	private CounterService counterSvc;

	@GetMapping("/logout")
	public String logout(Model model, HttpSession sess) {

		sess.invalidate();

		model.addAttribute("personalCounter", 0);
		model.addAttribute("dates", new LinkedList<String>());

		return "dynamic_index.html";
	}

	@GetMapping("/display")
	public String getDisplay(Model model, HttpSession sess) {

		int personalCounter = (Integer)sess.getAttribute(ATTR_PERSONAL_COUNTER);
		personalCounter++;

		// Update personalCounter back to session
		sess.setAttribute(ATTR_PERSONAL_COUNTER, personalCounter);

		List<String> list = (List<String>)sess.getAttribute(ATTR_LIST);

		model.addAttribute("personalCounter", personalCounter);
		model.addAttribute("dates", list);

		return "display";
	}

	@GetMapping("/index.html")
	public String index(Model model, HttpSession sess) {

		// Check if this is a new session, if it is, initialize one or more attributes
		if (null == sess.getAttribute(ATTR_PERSONAL_COUNTER)) {
			sess.setAttribute(ATTR_PERSONAL_COUNTER, 0);
			sess.setAttribute(ATTR_LIST, new LinkedList<String>());
		}

		int personalCounter = (Integer)sess.getAttribute(ATTR_PERSONAL_COUNTER);
		personalCounter++;

		// Update personalCounter back to session
		sess.setAttribute(ATTR_PERSONAL_COUNTER, personalCounter);

		List<String> list = (List<String>)sess.getAttribute(ATTR_LIST);
		list.add(new Date().toString());

		//sess.setAttribute(ATTR_LIST, list);

		model.addAttribute("message", "hello, world: %s".formatted(new Date()));
		model.addAttribute("counter", counterSvc.inc());
		model.addAttribute("personalCounter", personalCounter);
		model.addAttribute("dates", list);
		model.addAttribute("mylist", list.stream().collect(Collectors.joining("|")));

		return "dynamic_index.html";
	}
}
