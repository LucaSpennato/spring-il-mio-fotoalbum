package org.generation.italy.demo.apicontroller;

import java.util.List;

import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1/photo")
@CrossOrigin("*")
public class PhotoApiController {

	@Autowired
	private PhotoServ ps;
	
	@GetMapping("/all")
	public List<Photo> getAllPhoto() {
		
		List<Photo> photos = ps.findAllTrue();
		
		return photos;
	}
	
	
}
