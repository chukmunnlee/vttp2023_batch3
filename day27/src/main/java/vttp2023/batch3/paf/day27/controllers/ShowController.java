package vttp2023.batch3.paf.day27.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2023.batch3.paf.day27.models.TvShow;
import vttp2023.batch3.paf.day27.respositories.ShowRepository;
import vttp2023.batch3.paf.day27.services.ShowService;

@Controller
@RequestMapping
public class ShowController {

	@Autowired
	private ShowRepository showRepo;

	@Autowired
	private ShowService showSvc;

	@GetMapping(path={"/", "/index.html" })
	public ModelAndView getTypes() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("types");
		mav.addObject("types", showRepo.getAllTypes());
		mav.setStatus(HttpStatusCode.valueOf(200));

		return mav;
	}

	@GetMapping(path="/type/{type}")
	public ModelAndView getShowsByType(@PathVariable String type) {

		List<TvShow> shows = showSvc.getShowsByType(type, 3, 0);
		System.out.printf("\n\n>>> %s\n\\n", shows);

		ModelAndView mav = new ModelAndView();

		mav.setViewName("shows");
		mav.addObject("type", type);
		mav.addObject("tvshows", shows);
		mav.setStatus(HttpStatusCode.valueOf(200));

		return mav;
	}
}
