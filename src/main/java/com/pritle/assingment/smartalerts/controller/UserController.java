package com.pritle.assingment.smartalerts.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.ws.rs.FormParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pritle.assingment.smartalerts.bean.UserProfile;
import com.pritle.assingment.smartalerts.dao.UserDao;
import com.pritle.assingment.smartalerts.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/updateUserProfile", method = POST)
	@ResponseBody
	public ResponseEntity updateUserProfile(@FormParam("userID") String userID,
			@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@FormParam("dateOfBirth") String dateOfBirth, @FormParam("profilePicPath") String profilePicPath,
			@FormParam("email") String email, @FormParam("phone") String phone) {
		try {
			userDao.getUserAlert(Integer.parseInt(userID));

			if (userID != null) {
				UserProfile persistUser = userDao.findUser(userID);
				if (persistUser != null) {
					if (email != null && !email.equals(persistUser.getEmail())) {

						userService.sendAlert(persistUser); // Send Alert to User
						persistUser.setEmail(email); // Set Email
					}
					userDao.update(persistUser); // Save User
				}
				return new ResponseEntity(HttpStatus.OK);
			} else {
				return new ResponseEntity(HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
