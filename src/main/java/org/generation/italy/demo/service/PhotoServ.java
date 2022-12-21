package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServ {

	@Autowired
	private PhotoRepo ps;
	
	public Photo savePhoto(Photo p){
		return ps.save(p);
	}
	
	public void deletePhoto(Photo p){
		ps.delete(p);
	}
	
	public List<Photo> findAll(){
		return ps.findAll();
	}
	
	public Optional<Photo> findById(int id) {
		return ps.findById(id);
	}
	
	public List<Photo> findByTitle(String needle, String title){
		return ps.findByTagsNameContainingIgnoreCaseOrTitleContainingIgnoreCase(needle, title);
	}
	
}
