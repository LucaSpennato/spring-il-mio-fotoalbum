package org.generation.italy.demo.controller;

import org.generation.italy.demo.service.PhotoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/photo")
public class PhotoController {

	@Autowired
	private PhotoServ ps;
	
}
