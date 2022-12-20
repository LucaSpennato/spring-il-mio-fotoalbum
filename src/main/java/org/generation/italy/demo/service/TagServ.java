package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.pojo.Tag;
import org.generation.italy.demo.repo.TagRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TagServ {

	@Autowired
	private TagRepo tr;
	
	public Tag saveTag(Tag t) {
		return tr.save(t);
	}
	
	public void deleteTag(Tag t) {
		tr.delete(t);
	}
	
	public List<Tag> findAll(){
		return tr.findAll();
	}
	
	public Optional<Tag> finById(int id){
		return tr.findById(id);
	}
	
	@Transactional
	public Tag findTagByIdWithPhoto(int id){
		
		Tag tag = tr.findById(id).get();
		
		for (Photo p : tag.getPhotos()) {
			Hibernate.initialize(p);
		}
		
		return tag;
	}
}
