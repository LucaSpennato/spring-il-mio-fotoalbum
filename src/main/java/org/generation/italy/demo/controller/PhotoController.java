package org.generation.italy.demo.controller;

import java.util.List;

import javax.sound.midi.SysexMessage;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping
	public String getPhotoHome(Model model) {
		
		List<Photo> photos = ps.findAll();
		
		model.addAttribute("photos", photos);
		
		return "photos/photoHome";
	}
	
	@GetMapping("/create")
	public String createPhoto(Model model) {
		
		List<Tag> tags = tagS.findAll();
		List<Category> categories = catS.findAll();
		
		model.addAttribute("photo", new Photo());
		
		model.addAttribute("tags", tags);
		model.addAttribute("categories",categories);
		
		return "photos/createPhoto";	
	}
	
	@PostMapping("/store")
	public String storePhoto(@Valid @ModelAttribute("photo") Photo photo,
			BindingResult br, RedirectAttributes redAttr, Model model) {
		
		System.err.println("TEST---------------------------------------");
		System.err.println(photo);
		
		if(br.hasErrors()) {
			
			redAttr.addFlashAttribute("errors", br.getAllErrors());
			
			model.addAttribute("photo",photo);
			
			return "redirect:/admin/photo/create";
		}
		
		try {
			
			ps.savePhoto(photo);
			
		}catch(Exception e) {

			redAttr.addFlashAttribute("exception", e.getMessage() );
			
			model.addAttribute("photo",photo);
			
			return "redirect:/admin/photo/create";
		}
		
		return "redirect:/admin/photo";
	}
	
	
}
