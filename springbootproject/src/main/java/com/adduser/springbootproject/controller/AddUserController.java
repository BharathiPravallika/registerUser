package com.adduser.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adduser.springbootproject.models.UserData;
import com.adduser.springbootproject.service.UserService;
import com.adduser.springbootproject.utils.Response;

//POST HTTP Method
//http://localhost:8080/api/adduser

@RequestMapping("/api")

@RestController
public class AddUserController {

	@Autowired
	private UserService userService;

	@PostMapping("/adduser")
	public ResponseEntity<Response> adduser(@RequestBody UserData user) throws Exception {
		System.out.println("adding user");
		Response response = userService.addUser(user);
		
		if(user.getUserId() == 0 )
			return ResponseEntity.badRequest().body(response);
		return ResponseEntity.ok(response);	
		}
}