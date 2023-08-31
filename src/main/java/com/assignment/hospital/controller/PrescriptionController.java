package com.assignment.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.hospital.entity.Prescription;
import com.assignment.hospital.service.PrescriptionService;

@RestController
public class PrescriptionController {
	
	@Autowired 
	private PrescriptionService prescriptionService;
	
	@PostMapping("/addPrescription")
    public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription prescription) {
        Prescription addedPrescription = prescriptionService.addPrescription(prescription);
        return new ResponseEntity<>(addedPrescription, HttpStatus.CREATED);
	}
	
	 @PutMapping("/updatePrescription/{prescriptionId}")
	    public ResponseEntity<Prescription> updatePrescription(@PathVariable Long prescriptionId,
	                                                           @RequestBody Prescription updatedPrescription) {
	        Prescription existingPrescription = prescriptionService.getPrescriptionById(prescriptionId);
	        if (existingPrescription == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        // Update the prescription details
	        updatedPrescription.setId(prescriptionId);
	        Prescription updatedPrescriptionResult = prescriptionService.updatePrescription(updatedPrescription);
	        if (updatedPrescriptionResult != null) {
	            return new ResponseEntity<>(updatedPrescriptionResult, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 @DeleteMapping("/deletePrescription/{prescriptionId}")
	    public ResponseEntity<Void> deletePrescription(@PathVariable Long prescriptionId) {
	        prescriptionService.deletePrescription(prescriptionId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

}
