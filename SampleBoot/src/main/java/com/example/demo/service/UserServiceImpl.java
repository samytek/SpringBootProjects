package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
//
//	public List<Person> getAllPersons() {
//		return personRepository.findAll();
//	}
//
//	public Optional<Person> getPersonById(Long id) {
//		return personRepository.findById(id);
//	}

	@Override
	public void addPerson(Users users) {
		userRepository.save(users);
	}

//	public void updatePerson(Long id, Person updatedPerson) {
//		Optional<Person> existingPerson = personRepository.findById(id);
//		existingPerson.ifPresent(person -> {
//			person.setFirstName(updatedPerson.getFirstName());
//			person.setLastName(updatedPerson.getLastName());
//			personRepository.save(person);
//		});
//	}
//
//	public void deletePerson(Long id) {
//		personRepository.deleteById(id);
//	}
}
