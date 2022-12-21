package org.generation.italy.demo.controller;

import java.util.ArrayList;
import java.util.List;


import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.pojo.Tag;
import org.generation.italy.demo.service.CategoryServ;
import org.generation.italy.demo.service.PhotoServ;
import org.generation.italy.demo.service.TagServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/photo")
public class PhotoController {

	@Autowired
	private PhotoServ ps;
	@Autowired
	private TagServ tagS;
	@Autowired
	private CategoryServ catS;
	
	@GetMapping("/home")
	public String Search(@RequestParam(name="query", required=false) String query, Model model) {
		
		
		List<Photo> photos = new ArrayList<>();
		
		if(query == null) {
			photos = ps.findAll();
		}else {
			model.addAttribute("q", true);
			model.addAttribute("query", query);
			photos = ps.findByTitle(query);
		}
		
		model.addAttribute("photos", photos);
		
		return "photos/photoHome";
	}
	
	@GetMapping("/create")
	public String createPhoto(Model model) {
		
		List<Tag> tags = tagS.findAll();
		List<Category> categories = catS.findAll();

		model.addAttribute("photo", new Photo());
		
		model.addAttribute("ts", tags);
		model.addAttribute("cs",categories);
		
		return "photos/createPhoto";	
	}
	
	@PostMapping("/store")
	public String storePhoto(@Valid @ModelAttribute("photo") Photo photo,
			BindingResult br, RedirectAttributes redAttr, Model model) {
		
		if(br.hasErrors()) {
			
			redAttr.addFlashAttribute("errors", br.getAllErrors());
			
			model.addAttribute("photo",photo);
			
			return "redirect:/admin/photo/create";
		}
		
		try {
			
			ps.savePhoto(photo);
			
		}catch(Exception e) {

			redAttr.addFlashAttribute("exception", "Il nome inserito è già esistente." );
			
			model.addAttribute("photo",photo);
			
			return "redirect:/admin/photo/create";
		}
		
		return "redirect:/admin/photo";
	}
	
	@GetMapping("/edit/{id}")
	public String editPhoto(Model model, @PathVariable("id")int id) {
		
		List<Tag> tags = tagS.findAll();
		List<Category> categories = catS.findAll();
		
		Photo p = ps.findById(id).get();
		
		model.addAttribute("photo", p);
		
		model.addAttribute("ts", tags);
		model.addAttribute("cs",categories);
		
		return "photos/editPhoto";
	}
	
	@GetMapping("/show/{id}")
	public String showPhotoDetails(@PathVariable("id") int id, Model model) {
		
		Photo p = ps.findById(id).get();
		
		model.addAttribute("photo",p);
		
		return "photos/showPhoto";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePhoto(@PathVariable("id") int id) {
		
		Photo p = ps.findById(id).get();
		
		ps.deletePhoto(p);
		
		
		return "redirect:/admin/photo";
	}
	
	
}
