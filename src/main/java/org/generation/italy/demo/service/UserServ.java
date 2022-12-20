package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServ {

	@Autowired
	private UserRepo ur;
	
	public void save(User u) {
		ur.save(u);
	}
	
	public void deleteUser(User u) {
		ur.delete(u);
	}
	
	public List<User> findAll(){
		return ur.findAll();
	}
	
	public Optional<User> findUserById(int id){
		return ur.findById(id);
	}
	
}
