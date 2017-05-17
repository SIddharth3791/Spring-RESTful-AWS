package com.egen.spring_rest.controller;

import java.util.List;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.egen.spring_rest.entity.User;
import com.egen.spring_rest.service.UserService;



@RestController
@RequestMapping(value = "/users")
public class UserController {
	

	private UserService service;
	
	 public UserController(UserService service) {
		this.service = service;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<User> findAll()
	{	
		return service.findAll();
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public User findOne(@PathVariable("id") String id)
	{
		return service.findOne(id);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody User user)
	{
		return service.create(user);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public User update(@PathVariable("id") String id, @RequestBody User user)
	{
		return service.update(id, user);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable("id") String id)
	{
		service.delete(id);
	}

}
