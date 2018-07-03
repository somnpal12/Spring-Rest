package com.sample.rest.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.rest.model.User;
import com.sample.rest.service.UserService;

@RestController
public class MyRestController {
	
	private static Logger log = LogManager.getLogger();
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(method = RequestMethod.GET , value="/")
	public String sayHello() {
		return "Hello World !" ;
	}
	
	
	@RequestMapping(method = RequestMethod.GET , value="/api/v1.0/users")
	public List<User> fetchUsers() {
		log.info("fetching all user....");
		return  userService.getUsers();
	}
	
	@RequestMapping(method = RequestMethod.GET , value="/api/v1.0/user/name/{id}")
	public List<User> fetchSelectedUsers(@PathVariable("id") String param) {
		log.info("fetching user name start with {}....",param);
		return userService.getSelectedUser(param);
	}
	
	/*@GetMapping(value="/api/filtereduser" , headers="X-API-VERSION=1")*/
	@GetMapping(value="/api/filtereduser" ,produces="application/vnd.user.app-v1+json")
	public List<User> fetchFilteredUsersAM() {
		log.info("fetching user name starts  [A-M]....");
		return  userService.getUsers("^[A-M].*");
	}
	
	/*@GetMapping(value="/api/filtereduser" , headers="X-API-VERSION=2")*/
	@GetMapping(value="/api/filtereduser" ,produces="application/vnd.user.app-v2+json")
	
	/**
	 * set Accept = application/vnd.user.app-v2+json at header 
	 * OR
	 * set X-API-VERSION=2
	 * 
	 */
	public List<User> fetchFilteredUsersNZ() {
		log.info("fetching user name starts  [N-Z]....");
		return  userService.getUsers("^[N-Z].*");
	}
	
}
