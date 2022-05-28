package com.springbootschmgtsysapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootschmgtsysapi.entity.Student;
import com.springbootschmgtsysapi.repository.StudentRepository;

@Service
public class StudentService implements IStudentService{
	@Autowired StudentRepository studentRepository;
	public List<Student> GetAllStudent(){
		return studentRepository.findAll();
	}
	
	public Optional<Student> GetStudentById(Integer id){
		return studentRepository.findById(id);
	}
	
	public int createStudent (Student Students) {	
		Optional<Student> isStudentAlreadyTaken = studentRepository.findByAdmissionNo(Students.getAdmissionNo());
		if(isStudentAlreadyTaken.isPresent()) {
			return 0; //"Student-name already taken !";
		}
		Student cls = studentRepository.save(Students);
		return cls.getId();	
	}
	
	public String updateStudent (Student student) {		
		Optional<Student> isIdExist = studentRepository.findById(student.getId());
		if(isIdExist.isPresent()) {
			Optional<Student> isStudentAlreadyTaken = studentRepository.findByAdmissionNoAndIdNot(student.getAdmissionNo(), student.getId());
			if(isStudentAlreadyTaken.isPresent()) {
				return "Student-name already taken !";
			}
			studentRepository.save(student);
			return "0";
		}		
		return "Wrong Id value !";
	}
	
	public List<Student> findStudentsByClassId (int classId) {		
		return studentRepository.findByCurrentClassId(classId);
	}
	
	public List<Student> findStudentsByName (String NameString) {		
		return studentRepository.findByFirstNameOrLastName(NameString, NameString);
	}
	public void deleteStudentById(Integer id) {
		studentRepository.deleteById(id);
	}	
	public void deleteAllStudents() {
		studentRepository.deleteAll();
	}

}
