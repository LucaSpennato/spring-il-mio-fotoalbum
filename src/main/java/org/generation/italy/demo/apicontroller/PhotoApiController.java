package org.generation.italy.demo.apicontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.service.CommentServ;
import org.generation.italy.demo.service.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/1/photo")
@CrossOrigin("*")
public class PhotoApiController {

	@Autowired
	private PhotoServ ps;
	@Autowired
	private CommentServ cs;
	
	@GetMapping("/all")
	public List<Photo> getAllPhoto() {
		
		List<Photo> photos = ps.findAllWhereVisibleTrue();
		
		return photos;
	}
	
	@GetMapping("/search")
	public List<Photo> searchApi(
			@RequestParam(name="query", required=false) String query) {
		List<Photo> photos = new ArrayList<>();
		
		if(query == null) {
			photos = ps.findAllWhereVisibleTrue();
		}else {
			photos = ps.findByTitle(query,query);

		}
		
		return photos;
	}
	
	@PostMapping("/comment/{id}")
	public Comment storeComment(@Valid @RequestBody String c, 
			@PathVariable("id")int id) {
		
		Photo p = ps.findById(id).get();
		Comment comment = new Comment(c, p);
		
		Comment cmnt = cs.saveComment(comment);
		
		return cmnt;
	}
	
}
