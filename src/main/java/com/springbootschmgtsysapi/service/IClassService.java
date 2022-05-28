package com.springbootschmgtsysapi.service;

import java.util.*;

import com.springbootschmgtsysapi.entity.Classs;

public interface IClassService {
	List<Classs> GetAllClass();
	Optional<Classs> GetClassById(Integer id); //= tutorialRepository.findById(id);
	int createClass (Classs classs);
	String updateClass (Classs classs);
	
	void deleteClassById(Integer id);
	void deleteAllClasses();
}
