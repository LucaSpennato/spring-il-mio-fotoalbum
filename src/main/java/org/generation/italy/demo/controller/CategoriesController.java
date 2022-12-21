package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;

import org.generation.italy.demo.pojo.Photo;

import org.generation.italy.demo.pojo.Tag;
import org.generation.italy.demo.service.CategoryServ;
import org.generation.italy.demo.service.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class CategoriesController {

	@Autowired
	private CategoryServ sv;
	@Autowired
	private PhotoServ photoS;
	
	@GetMapping
	public String getAllCategories(Model model) {
		
		model.addAttribute("cs", sv.findAllCategoryByIdWithPhoto());
		
		return "categories/categoriesHome";
	}
	
	@GetMapping("/create")
	public String createCategory(Model model) {
		
		Category c = new Category();
		
		
		model.addAttribute("cat", c);
	
		
		return "categories/createCategory";
	}
	
	@PostMapping("/store")
	public String storeCategory(@Valid @ModelAttribute("category") Category cat,
			BindingResult br, RedirectAttributes redAttr, Model model) {
		
		if(br.hasErrors()) {
			
			redAttr.addFlashAttribute("errors", br.getAllErrors());
			
			model.addAttribute("cat",cat);
			
			return "redirect:/admin/photo/create";
		}
		
		try {
			
			sv.saveCategory(cat);
			
		}catch(Exception e) {

			redAttr.addFlashAttribute("exception", "Il nome inserito è già esistente." );
			
			model.addAttribute("cat",cat);
			
			return "redirect:/admin/categories/create";
		}
		
		return "redirect:/admin/categories";
	}
	
	@PostMapping("/update")
	public String updateCategory(@Valid @ModelAttribute("category") Category cat,
			BindingResult br, RedirectAttributes redAttr, Model model) {
		
		Optional<Category> opt = sv.findbyId(cat.getId());
		
		if (!opt.isEmpty()) {
			Category category = opt.get();
			for (Photo photo : category.getPhotos()) {
				photo.getCategories().remove(category);
			}
		}
		
		List<Photo> photos = cat.getPhotos();
		if (photos != null) {
			for (Photo photo : photos) {
				photo.getCategories().add(cat);
			}
		}
		
		if(br.hasErrors()) {
			
			redAttr.addFlashAttribute("errors", br.getAllErrors());
			
			model.addAttribute("cat",cat);
			
			return "redirect:/admin/photo/create";
		}
		
		try {
			
			sv.saveCategory(cat);
			
		}catch(Exception e) {

			redAttr.addFlashAttribute("exception", "Il nome inserito è già esistente." );
			
			model.addAttribute("cat",cat);
			
			return "redirect:/admin/categories/create";
		}
		
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/edit/{id}")
	public String editCategory(Model model, @PathVariable("id")int id) {
		
		List<Photo> photos = photoS.findAll();
		
		Category c = sv.findbyId(id).get();
		
		model.addAttribute("cat", c);
		
		model.addAttribute("ps", photos);
		
		return "categories/editCategory";
	}
	
	@GetMapping("/show/{id}")
	public String showCategory(Model model, @PathVariable("id")int id) {
				
		Category c = sv.findCategoryByIdWithPhoto(id);
		
		model.addAttribute("cat", c);
		
		return "categories/showCategory";
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
