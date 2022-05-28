package com.springbootschmgtsysapi.entity;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.*;
import com.springbootschmgtsysapi.common.EnumContainer;
import com.springbootschmgtsysapi.common.EnumContainer.Gender;
import com.springbootschmgtsysapi.common.EnumContainer.Religion;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(max = 15, message = "{validation.name.size.too_long}")
    private String admissionNo;
    @Size(max = 20, message = "{validation.name.size.too_long}")
    private String firstName;
    @Size(max = 20, message = "{validation.name.size.too_long}")
    private String lastName;
    
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateOfBirth;
    @Size(max = 30, message = "{validation.name.size.too_long}")
    private String placeOfBirth;
    private EnumContainer.Religion religion;
    public EnumContainer.Gender gender;
    @Size(max = 10, message = "{validation.name.size.too_long}")
    private String bloodGroup;
    
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dateOfAdmsn;
    @Size(max = 15, message = "{validation.name.size.too_long}")
    private String startClass;//Class name drop down
    private int currentClassId; //Class name drop down
    
    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "currentClassId", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnoreProperties("student")//"student" is the reference from the other (Classs) but not the Table annotation of this class. This Prevent circular reference
    private Classs classs;
    
    @Size(max = 40, message = "{validation.name.size.too_long}")
    private String passport;
    
    public Student() {
    	
    }
    
	public Student(String admissionNo, String firstName, String lastName, Date dateOfBirth, String placeOfBirth,
			Religion religion, Gender gender, String bloodGroup, Date dateOfAdmsn, String startClass, int classId,
			Classs classs, String passport) {
		
		this.admissionNo = admissionNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.placeOfBirth = placeOfBirth;
		this.religion = religion;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.dateOfAdmsn = dateOfAdmsn;
		this.startClass = startClass;
		this.currentClassId = classId;
		this.classs = classs;
		this.passport = passport;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public EnumContainer.Religion getReligion() {
		return religion;
	}

	public void setReligion(EnumContainer.Religion religion) {
		this.religion = religion;
	}

	public EnumContainer.Gender getGender() {
		return gender;
	}

	public void setGender(EnumContainer.Gender gender) {
		this.gender = gender;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Date getDateOfAdmsn() {
		return dateOfAdmsn;
	}

	public void setDateOfAdmsn(Date dateOfAdmsn) {
		this.dateOfAdmsn = dateOfAdmsn;
	}

	public String getStartClass() {
		return startClass;
	}

	public void setStartClass(String startClass) {
		this.startClass = startClass;
	}

	public int getCurrentClassId() {
		return currentClassId;
	}

	public void setCurrentClassId(int classId) {
		this.currentClassId = classId;
	}

	public Classs getClasss() {
		return classs;
	}

	public void setClasss(Classs classs) {
		this.classs = classs;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}
}
