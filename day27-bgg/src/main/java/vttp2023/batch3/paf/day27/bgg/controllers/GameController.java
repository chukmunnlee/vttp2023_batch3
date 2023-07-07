package vttp2023.batch3.paf.day27.bgg.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp2023.batch3.paf.day27.bgg.models.Comment;
import vttp2023.batch3.paf.day27.bgg.models.Game;
import vttp2023.batch3.paf.day27.bgg.services.GameService;

@Controller
@RequestMapping
public class GameController {

	@Autowired
	private GameService gameSvc;

	@GetMapping(path="/search")
	public ModelAndView search(@RequestParam String game) {

		List<Game> games = gameSvc.searchGameByName(game);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("search-result");
		mav.addObject("game", game);
		mav.addObject("games", games);
		mav.setStatus(HttpStatusCode.valueOf(200));

		return mav;
	}

	@GetMapping("/game/{gid}")
	public ModelAndView getGameByGid(@PathVariable Integer gid) {

		Optional<Game> opt = gameSvc.findGameByGid(gid);

		ModelAndView mav = new ModelAndView();
		mav.addObject("gid", gid);

		if (opt.isEmpty()) {
			mav.setViewName("not-found");
			mav.setStatus(HttpStatusCode.valueOf(404));
			return mav;
		}

		System.out.printf(">>> %s\n", opt.get());

		mav.setViewName("game-detail");
		mav.addObject("game", opt.get());
		mav.setStatus(HttpStatusCode.valueOf(200));

		return mav;
	}

	@PostMapping(path="/comment")
	public ModelAndView comment(@RequestBody MultiValueMap<String, String> form) {

		Integer gid = Integer.parseInt(form.getFirst("gid"));
		String user = form.getFirst("user");
		Integer rating = Integer.parseInt(form.getFirst("rating"));
		String text = form.getFirst("text");

		Comment comment = new Comment("", user, rating, text, gid);
		gameSvc.postComment(comment);

		return getGameByGid(gid);
	}
}
