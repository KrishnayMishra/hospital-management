package com.assignment.hospital.dao;

import java.time.LocalDate;

public class PatientDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate dob;
    private String gender;
    private String emrNumber;
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmrNumber() {
		return emrNumber;
	}
	public void setEmrNumber(String emrNumber) {
		this.emrNumber = emrNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long long1) {
		this.id = long1;
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
	
	

}
