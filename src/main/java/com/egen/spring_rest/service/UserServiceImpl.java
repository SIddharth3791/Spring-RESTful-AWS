package com.egen.spring_rest.service;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egen.spring_rest.entity.User;
import com.egen.spring_rest.exception.BadRequestException;
import com.egen.spring_rest.exception.NotFoundException;
import com.egen.spring_rest.repository.UserRepository;
import com.egen.spring_rest.service.UserService;


@Service
public class UserServiceImpl  implements UserService{


	private UserRepository  repository;
	
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findOne(String id) {
		
		User existing = repository.findOne(id);
		
		if(existing == null)
		{
			throw new NotFoundException("User with ID " + id + " does not exists");
		}
		return existing;
	}

	@Override
	@Transactional
	public User create(User user) {
		
		User existing = repository.findByEmail(user.getEmail());
		if(existing != null)
		{
			throw new BadRequestException("User with Email: " + user.getEmail() + "does exists");
		}
		return repository.create(user);
	}

	@Override
	@Transactional
	public User update(String id, User user) {
		
		User existing = repository.findOne(id);
		
		if(existing == null)
		{
			throw new NotFoundException("User with ID " + id + " does not exists");
		}
		return repository.update( user);
	}

	@Override
	@Transactional
	public void delete(String id) {

		User existing = repository.findOne(id);
		
		if(existing == null)
		{
			throw new NotFoundException("User with ID " + id + " does not exists");
		}
		repository.delete(existing);
		
	}

}

