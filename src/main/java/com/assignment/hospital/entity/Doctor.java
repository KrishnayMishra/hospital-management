package com.assignment.hospital.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Doctor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String speciality;

    

	// One doctor can belong to only one clinic
    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    // One doctor can have many patients
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Patient> patients;
    
    public Doctor() {
        
    }
    
    
    public Doctor(Long id, String firstName, String lastName, String speciality, Clinic clinic,
			List<Patient> patients) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.speciality = speciality;
		this.clinic = clinic;
		this.patients = patients;
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

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
    
    

}
