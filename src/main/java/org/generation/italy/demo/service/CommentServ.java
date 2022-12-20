package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Comment;
import org.generation.italy.demo.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServ {

	@Autowired
	private CommentRepo cr;
	
	public Comment saveComment(Comment c) {
		return cr.save(c);
	}
	
	public void deleteComment(Comment c) {
		cr.delete(c);
	}
	
	public List<Comment> findAll(){
		return cr.findAll();
	}
	
	public Optional<Comment> findById(int id){
		return cr.findById(id);
	}
}
