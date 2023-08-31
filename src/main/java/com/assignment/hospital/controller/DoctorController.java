package com.assignment.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.hospital.dao.DoctorDTO;
import com.assignment.hospital.entity.Doctor;
import com.assignment.hospital.service.DoctorService;

@RestController
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/addDoctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor addedDoctor = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(addedDoctor, HttpStatus.CREATED);
    }
	
	@GetMapping("/doctors/{clinicId}")
    public ResponseEntity<List<DoctorDTO>> getAllDoctorsByClinic(@PathVariable Long clinicId) {
        return doctorService.getAllDoctorsByClinic(clinicId);
    }
	
	 

}
