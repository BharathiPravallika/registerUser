package com.adduser.springbootproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adduser.springbootproject.dao.UserDAO;
import com.adduser.springbootproject.models.UserData;
import com.adduser.springbootproject.utils.Response;
import com.adduser.springbootproject.utils.UserValidators;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	// CREATE
	public Response addUser(UserData user) throws Exception {
		System.out.println("In adduser : UserService");
		// return user;
		// int id = userDAO.getUserId(user.getEmail());

		if (user == null || user.getEmail() == null || user.getEmail().isBlank()) {
			System.out.println("email is empty or null");
			return Response.getResponse("email is empty or null", null, null);
		}
		
		List<UserData> userData = userDAO.getUserId(user.getEmail());
//		System.out.println("userdata is: " + userData.get(0).toString());
		if ((userData).size()>0 && userData.get(0).getUserId()>0) {
			System.out.println("Email is already exits");
			return Response.getResponse("Email is already exist", null, null);
		}

		int id = userDAO.addUser(user);
		user.setUserId(id);
		return Response.getResponse(user, null, "user inserted");

//		return null;
	}

}