package com.assignment.hospital.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity

public class Clinic {
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //The database takes care of assigning a new primary key value whenever a new record is inserted.
    private Long id;
    private String name;
    private String address;
    private String city;
    private String state;

    // One clinic can have many doctors
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<Doctor> doctors;

    // One clinic can have many patients
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private List<Patient> patients;
    
    public Clinic() {
       
    }
    
    public Clinic(Long id, String name, String address, String city, String state, List<Doctor> doctors,
			List<Patient> patients) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.doctors = doctors;
		this.patients = patients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
    
	
	

}
