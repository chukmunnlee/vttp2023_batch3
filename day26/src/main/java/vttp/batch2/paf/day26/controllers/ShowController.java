package vttp.batch2.paf.day26.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vttp.batch2.paf.day26.models.TvShow;
import vttp.batch2.paf.day26.repositories.ShowsRepository;
import vttp.batch2.paf.day26.services.ShowService;

@Controller
@RequestMapping
public class ShowController {

   @Autowired
   private ShowService showSvc;

	@Autowired
	private ShowsRepository showRepo;

	@GetMapping(path="/show", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getShowAsJson(@RequestParam String showName) {

		List<Document> result = showRepo.findShowsByName(showName);

		if (result.size() <= 0)
			return ResponseEntity.status(404)
				.body("{}");

		Document doc = result.get(0);

		return (ResponseEntity.ok(doc.toJson()));
	}

   @GetMapping(path="/show")
   public ModelAndView getShow(@RequestParam String showName) {

      Optional<TvShow> opt = showSvc.findShowByName(showName);

      ModelAndView mav = new ModelAndView();

      if (opt.isEmpty()) {
         mav.setViewName("not-found");
         mav.addObject("message", "Cannot find %s".formatted(showName));
         mav.setStatus(HttpStatusCode.valueOf(404));
         return mav;
      }

      mav.setViewName("result");
      mav.addObject("title", showName);
      mav.addObject("show", opt.get());
      mav.setStatus(HttpStatusCode.valueOf(200));

      return mav;
   }

}
