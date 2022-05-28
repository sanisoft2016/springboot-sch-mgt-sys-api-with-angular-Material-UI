package com.springbootschmgtsysapi.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springbootschmgtsysapi.entity.Classs;
import com.springbootschmgtsysapi.service.IClassService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/class")
public class ClassController {
	@Autowired
	IClassService classService;
	@GetMapping("/getAllClasses")
	public ResponseEntity<List<Classs>> getAllClasss(@RequestParam(required = false) String title) {
		try {
			List<Classs> classs = classService.GetAllClass();
			if (classs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(classs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getClassById/{id}")
	public ResponseEntity<Classs> getClassById(@PathVariable("id") int id) {
		Optional<Classs> classData = classService.GetClassById(id);
		if (classData.isPresent()) {
			return new ResponseEntity<>(classData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/createClass")
	public ResponseEntity<Object> createClass(@RequestBody Classs classs) {
		try {
			int classResult = classService.createClass(classs);	
			if(classResult != 0) {
				classs.setId(classResult);
			     return new ResponseEntity<>(classs, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>("{'Message': 'Sorry Class already exist !'}", HttpStatus.BAD_REQUEST);
			}			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/updateClass/{id}")
	public ResponseEntity<Map<String, String>> updateClass(@PathVariable("id") int id, @RequestBody Classs classs) {
		classs.setId(id);
		String classData = classService.updateClass(classs);
		
		Map<String, String> mapper = new HashMap<String, String>();
       
		if (classData == "0") {		
			 mapper.put("Message", "Updated Successfully");
		     return new ResponseEntity<>(mapper, HttpStatus.OK);
		} else {
			 mapper.put("Message", classData);
		     return new ResponseEntity<>(mapper, HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<HttpStatus> deleteClass(@PathVariable("id") int id) {
		try {
			classService.deleteClassById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/deleteAll")
	public ResponseEntity<HttpStatus> deleteAllClasss() {
		try {
			classService.deleteAllClasses();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@GetMapping("/tutorials/published")
//	public ResponseEntity<List<Classs>> findByPublished() {
//		try {
//			List<Classs> tutorials = tutorialRepository.findByPublished(true);
//			if (tutorials.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(tutorials, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
}
