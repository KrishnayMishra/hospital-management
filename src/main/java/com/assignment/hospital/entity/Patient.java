package com.assignment.hospital.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity


public class Patient {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String firstName;
  
    private String lastName;
    private LocalDate dob;
    private String gender;
    private String emrNumber;
    
    
 @Override
	public String toString() {
		return "Patient [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	// One patient can belong to only one clinic
    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;
    
 // One patient can have only one doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
 // One patient can have many prescriptions
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;
    
    public Patient() {
       
    }
    

	public Patient(Long id, String firstName, String lastName, LocalDate dob, String gender, String emrNumber,
			Clinic clinic, Doctor doctor, List<Prescription> prescriptions) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.emrNumber = emrNumber;
		this.clinic = clinic;
		this.doctor = doctor;
		this.prescriptions = prescriptions;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
    
    
	

}
