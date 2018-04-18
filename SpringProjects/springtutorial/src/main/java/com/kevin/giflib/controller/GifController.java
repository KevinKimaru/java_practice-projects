package com.kevin.giflib.controller;

import com.kevin.giflib.data.GifRepository;
import com.kevin.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 2/12/2018.
 */
@Controller
public class GifController {

    @Autowired
    private GifRepository mGifRepository;

    @RequestMapping("/")
    public String listGifs(ModelMap modelMap) {
        List<Gif> gifs = mGifRepository.getAllGifs();
        modelMap.put("gifs", gifs);
        return "home";
    }

    @RequestMapping("/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap) {
        Gif gif = mGifRepository.findByName(name);
        modelMap.put("gif", gif);
        return "gif-details";
    }
}
