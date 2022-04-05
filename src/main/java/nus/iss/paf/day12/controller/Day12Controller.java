package nus.iss.paf.day12.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nus.iss.paf.day12.service.GiphyService;

@Controller
@RequestMapping("/")
public class Day12Controller {
        

    @Autowired
    private GiphyService giphySvc;

    @GetMapping("/search")
    public String getSearch(Model model, @RequestParam String search
            , @RequestParam Integer limit, @RequestParam String rating) {

        System.out.printf(">>> search = %s, limit = %d, rating = %s\n", search, limit, rating);

        List<String> imageList = giphySvc.getGiphs(search, limit, rating);
        
        model.addAttribute("imageList", imageList);
        model.addAttribute("search", search);

        return "showPage";
    }   

    

        

}
