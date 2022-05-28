package com.springbootschmgtsysapi.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springbootschmgtsysapi.entity.Classs;
import com.springbootschmgtsysapi.repository.ClassRepository;

@Service
public class ClassService implements IClassService{
	@Autowired ClassRepository classRepository;
	@Override
	public List<Classs> GetAllClass(){
		return classRepository.findAll();
	}
	
	@Override
	public Optional<Classs> GetClassById(Integer id){
		return classRepository.findById(id);
	}
	
	@Override
	public int createClass (Classs classs) {	
		Optional<Classs> isClassAlreadyTaken = classRepository.findByClassName(classs.getClassName());
		if(isClassAlreadyTaken.isPresent()) {
			return 0; //"Class-name already taken !";
		}
		Classs cls = classRepository.save(classs);
		return cls.getId();	
	}
	
	@Override
	public String updateClass (Classs classs) {		
		Optional<Classs> isIdExist = classRepository.findById(classs.getId());
		if(isIdExist.isPresent()) {
			Optional<Classs> isClassAlreadyTaken = classRepository.findByClassNameAndIdNot(classs.getClassName(), classs.getId());
			if(isClassAlreadyTaken.isPresent()) {
				return "Class-name already taken !";
			}
			classRepository.save(classs);
			return "0";
		}		
		return "Wrong Id value !";
	}
	@Override
	public void deleteClassById(Integer id) {
		classRepository.deleteById(id);
	}	
	@Override
	public void deleteAllClasses() {
		classRepository.deleteAll();
	}
}
