package com.assignment.hospital.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Prescription {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
	
	 @ManyToOne
	 @JoinColumn(name = "doctor_id")
	 private Doctor doctor;
	 
	 private LocalDateTime createdDateTime;
	 private LocalDateTime updatedDateTime;
	 private String cause;
	 private String notes;
	 
	 public Prescription() {
	      
	    }
	 
	 
	    
	public Prescription(Long id, Patient patient, Doctor doctor, LocalDateTime createdDateTime,
			LocalDateTime updatedDateTime, String cause, String notes) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = updatedDateTime;
		this.cause = cause;
		this.notes = notes;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public LocalDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
