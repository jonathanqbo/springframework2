package bq.springdata.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bq.springdata.jpa.entity.User;
import bq.springdata.jpa.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@RequestMapping(method=RequestMethod.POST, path="/user")
	@ResponseBody
	public long create(@RequestBody User entity) {
		User result = repository.save(entity);
		return result.getId();
	}
	
	@RequestMapping(method=RequestMethod.PUT, path="/user")
	@ResponseBody
	public boolean update(@RequestBody User entity) {
		repository.save(entity);
		return true;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, path="/user/{id}")
	@ResponseBody
	public boolean delete(@PathVariable long id) {
		repository.delete(id);
		return true;
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/user")
	@ResponseBody
	public List<User> find(@RequestParam String email, @RequestParam String lastName) {
		List<User> entity = repository.findByEmailAndLastName(email, lastName);
		return entity;
	}
	
}
