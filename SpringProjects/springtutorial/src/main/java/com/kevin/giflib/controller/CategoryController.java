package com.kevin.giflib.controller;

import com.kevin.giflib.data.CategoryRepository;
import com.kevin.giflib.data.GifRepository;
import com.kevin.giflib.model.Category;
import com.kevin.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 2/13/2018.
 */
@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository mCategoryRepository;
    @Autowired
    private GifRepository mGifRepository;

    @RequestMapping("/categories")
    public String listCategories(ModelMap modelMap) {
        List<Category> categories = mCategoryRepository.getAllCategories();
        modelMap.put("categories", categories);
        return "categories";
    }

    @RequestMapping("/category/{id}")
    public String category(@PathVariable int id, ModelMap modelMap) {
        Category category = mCategoryRepository.findById(id);
        modelMap.put("category", category);
        List<Gif> gifs = mGifRepository.findByCategoryId(id);
        modelMap.put("gifs", gifs);
        return "category";
    }
}
