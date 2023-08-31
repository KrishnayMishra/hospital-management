package com.assignment.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.hospital.dao.ClinicDTO;
import com.assignment.hospital.entity.Clinic;
import com.assignment.hospital.service.ClinicService;
import com.assignment.hospital.service.PatientService;

@RestController
public class ClinicController {
	
	@Autowired
	private ClinicService clinicService;
	
	 @PostMapping("/addClinic")
	    public ResponseEntity<Clinic> addClinic(@RequestBody Clinic clinic) {
	        Clinic addedClinic = clinicService.addClinic(clinic);
	        return new ResponseEntity<>(addedClinic, HttpStatus.CREATED);
	    }
	 
	 @GetMapping("/allClinics")
	    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
		    
	        return clinicService.getAllClinics();
	    }

}
