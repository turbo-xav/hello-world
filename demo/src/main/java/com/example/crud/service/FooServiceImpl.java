package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import com.example.crud.entity.Foo;
import com.example.crud.repository.FooRepository;

@Named
public class FooServiceImpl implements FooService {

    @Inject
    FooRepository fooRepository;
	
    public Foo saveFoo(Foo foo) {
	  fooRepository.save(foo);
	  return foo;
    }

    public List<Foo> getAll() {
	  return fooRepository.findAll();
    }
    public Optional<Foo> findbyId(Long id) {
		return fooRepository.findById(id);
    }
    
	public Long deleteFooById(Long id) {
		return fooRepository.deleteFooById(id);
	}

}
