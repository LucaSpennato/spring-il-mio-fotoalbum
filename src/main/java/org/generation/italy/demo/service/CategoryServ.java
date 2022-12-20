package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Category;
import org.generation.italy.demo.pojo.Photo;
import org.generation.italy.demo.repo.CategoryRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CategoryServ {

	@Autowired
	private CategoryRepo cr;
	
	public Category saveCategory(Category c) {
		return cr.save(c);
	}
	
	public void deleteCatecory(Category c) {
		cr.delete(c);
	}
	
	public List<Category> findAll(){
		return cr.findAll();
	}
	
	public Optional<Category> findbyId(int id){
		return cr.findById(id);
	}
	
	@Transactional
	public Category findCategoryByIdWithPhoto(int id){
		
		Category cat = cr.findById(id).get();
		
		for (Photo p : cat.getPhotos()) {
			Hibernate.initialize(p);
		}
		
		return cat;
	}
}
