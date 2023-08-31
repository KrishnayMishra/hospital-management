package com.assignment.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.hospital.dao.PrescriptionDao;
import com.assignment.hospital.entity.Doctor;
import com.assignment.hospital.entity.Patient;
import com.assignment.hospital.entity.Prescription;

@Service
public class PrescriptionService {
	
	@Autowired
	private PrescriptionDao prescriptionDao;
	
	public Prescription addPrescription(Prescription prescription) {
        // Set the patient reference for the prescription
        Patient patient = prescription.getPatient();
        if (patient != null) {
            prescription.setPatient(patient);
        }
        else {
            throw new IllegalArgumentException("NA");
        }

        // Set the doctor reference for the prescription
        Doctor doctor = prescription.getDoctor();
        if (doctor != null) {
            prescription.setDoctor(doctor);
        }
        else {
            throw new IllegalArgumentException("NA");
        }

        // Save the new prescription to the database
        return prescriptionDao.save(prescription);
    }
	
	public Prescription updatePrescription(Prescription updatedPrescription) {
        Long prescriptionId = updatedPrescription.getId();
        Prescription existingPrescription = prescriptionDao.findById(prescriptionId).orElse(null);

        if (existingPrescription == null) {
            return null; // Prescription not found
        }

        // Update the fields of the existing prescription with the data from the updatedPrescription object
        existingPrescription.setCause(updatedPrescription.getCause());
        existingPrescription.setNotes(updatedPrescription.getNotes());

        // Set the patient reference for the prescription
        Patient patient = updatedPrescription.getPatient();
        if (patient != null) {
            existingPrescription.setPatient(patient);
        } else {
            throw new IllegalArgumentException("Patient cannot be null in the prescription.");
        }

        // Set the doctor reference for the prescription
        Doctor doctor = updatedPrescription.getDoctor();
        if (doctor != null) {
            existingPrescription.setDoctor(doctor);
        } else {
            throw new IllegalArgumentException("Doctor cannot be null in the prescription.");
        }
        
        existingPrescription.setUpdatedDateTime(updatedPrescription.getUpdatedDateTime());
        existingPrescription.setCreatedDateTime(updatedPrescription.getCreatedDateTime());
        

        // Save the updated prescription to the database
        return prescriptionDao.save(existingPrescription);
    }

    public void deletePrescription(Long prescriptionId) {
        prescriptionDao.deleteById(prescriptionId);
    }

    
    public Prescription getPrescriptionById(Long prescriptionId) {
        return prescriptionDao.findById(prescriptionId)
                .orElse(null);
    }

}
