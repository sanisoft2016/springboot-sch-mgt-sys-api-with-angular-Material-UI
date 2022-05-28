package com.springbootschmgtsysapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootschmgtsysapi.entity.Student;
import com.springbootschmgtsysapi.service.IStudentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	IStudentService studentService;
	@GetMapping("/getAllStudent")
	public ResponseEntity<List<Student>> getAllStudent() {
		try {
			List<Student> student = studentService.GetAllStudent();
			if (student.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(student, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getStudentById/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {
		Optional<Student> studentData = studentService.GetStudentById(id);
		if (studentData.isPresent()) {
			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/createStudent")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		try {
			int studentResult = studentService.createStudent(student);	
			if(studentResult != 0) {
				student.setId(studentResult);
			     return new ResponseEntity<>(student, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>("{'Message': 'Sorry Student Admisino Number already exist !'}", HttpStatus.BAD_REQUEST);
			}			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/updateStudent/{id}")
	public ResponseEntity<Map<String, String>> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
		student.setId(id);
		String studentData = studentService.updateStudent(student);
		
		Map<String, String> mapper = new HashMap<String, String>();
       
		if (studentData == "0") {		
			 mapper.put("Message", "Updated Successfully");
		     return new ResponseEntity<>(mapper, HttpStatus.OK);
		} else {
			 mapper.put("Message", studentData);
		     return new ResponseEntity<>(mapper, HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") int id) {
		try {
			studentService.deleteStudentById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/deleteAll")
	public ResponseEntity<HttpStatus> deleteAllStudent() {
		try {
			studentService.deleteAllStudents();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAllStudentsByClassId/{classId}")//@PathVariable("id") int id
	public ResponseEntity<List<Student>> getAllStudentsByClassId(@PathVariable("classId") int classId) {
		try {
			List<Student> students = studentService.findStudentsByClassId(classId);
			if (students.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAllStudentsByNames")
	public ResponseEntity<List<Student>> getAllStudentsByNames( String names) {
		try {
			List<Student> students = studentService.findStudentsByName(names);
			if (students.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
