package com.assignment.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.hospital.dao.PatientDTO;
import com.assignment.hospital.entity.Patient;
import com.assignment.hospital.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping(value={"/addPatient"})
	public ResponseEntity<Patient> addPatient(@RequestBody Patient patient)
	{
		 //System.out.println("gh");
		Patient addedPatient =patientService.addPatient(patient);
		return new ResponseEntity<>(addedPatient, HttpStatus.CREATED);
	}
	
	@PutMapping("/updatePatient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        Patient existingPatient = patientService.getPatientById(id);
        if (existingPatient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Set the ID of the updated patient to the existing patient
        updatedPatient.setId(id);
       

        // Update the patient details
        Patient updatedPatientResult = patientService.updatePatient(updatedPatient);
        if (updatedPatientResult != null) {
            return new ResponseEntity<>(updatedPatientResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
	}
	
	@DeleteMapping("/deletePatient/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
//	@GetMapping("/patients/search")
//    public ResponseEntity<List<PatientDTO>> searchPatientsByName(@RequestParam String name) {
//        return patientService.searchPatientsByName(name);
//    }
	@GetMapping("/patients/search")
  public ResponseEntity<List<Patient>> searchPatientsByName(@RequestParam String name) {
      return patientService.searchPatientsByName(name);
  }

	
	 @GetMapping("/patients/searchByEmr")
	    public ResponseEntity<PatientDTO> searchPatientByEmrNumber(@RequestParam String emrNumber) {
	        return patientService.searchPatientByEmrNumber(emrNumber);
	    }
	
	 @GetMapping("/patients/aboveAge60")
	    public ResponseEntity<List<PatientDTO>> getPatientsAboveAge60() {
	        return patientService.getPatientsAboveAge60();
	    }
	 
	 @GetMapping("/patientsbydoctor/{doctorId}")
	    public ResponseEntity<List<PatientDTO>> getAllPatientsByDoctor(@PathVariable Long doctorId) {
	        return patientService.getAllPatientsByDoctor(doctorId);
	    }
	 
	 @GetMapping("/patientsbyclinic/{clinicId}")
	    public ResponseEntity<List<PatientDTO>> getAllPatientsByClinic(@PathVariable Long clinicId) {
	        return patientService.getAllPatientsByClinic(clinicId);
	    }

}
