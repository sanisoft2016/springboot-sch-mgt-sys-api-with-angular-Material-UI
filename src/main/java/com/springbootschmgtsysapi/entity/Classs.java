package com.springbootschmgtsysapi.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
@Entity
@Table(name = "classs")
public class Classs 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
     private String className;
     
     @OneToMany(mappedBy="classs")
     //@JsonManagedReference
     @JsonIgnoreProperties("classs")//"classs" is the reference from the other (Student) but not the Table annotation of this class. This Prevent circular reference
     private List<Student> student;
	
     public Classs() {
 	}
    public Classs(String className) {
		this.className = className;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<Student> getStudent() {
		return student;
	}
	public void setStudent(List<Student> student) {
		this.student = student;
	}
}
