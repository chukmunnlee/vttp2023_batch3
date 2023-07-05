package vttp.batch2.paf.day26.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp.batch2.paf.day26.models.TvShow;
import vttp.batch2.paf.day26.services.ShowService;

@Controller
@RequestMapping
public class ShowController {

   @Autowired
   private ShowService showSvc;

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