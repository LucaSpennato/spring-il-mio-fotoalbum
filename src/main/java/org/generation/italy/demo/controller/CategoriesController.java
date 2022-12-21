package org.generation.italy.demo.controller;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.CategoryServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categories")
public class CategoriesController {

	@Autowired
	private CategoryServ sv;
	
	@GetMapping
	public String getAllCategories(Model model) {
		
		model.addAttribute("cs", sv.findAllCategoryByIdWithPhoto());
		
		return "categories/categoriesHome";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable("id")int id) {
		
		Category c = sv.findCategoryByIdWithPhoto(id);
		
		for (Photo p : c.getPhotos()) {
			p.getCategories().remove(c);
		}
		
		sv.deleteCatecory(c);
		
		return "redirect:/admin/categories";
	}
	
}
