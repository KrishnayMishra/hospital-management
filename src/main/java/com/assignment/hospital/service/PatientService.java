package com.assignment.hospital.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.assignment.hospital.dao.PatientDTO;
import com.assignment.hospital.dao.PatientDao;
import com.assignment.hospital.entity.Clinic;
import com.assignment.hospital.entity.Doctor;
import com.assignment.hospital.entity.Patient;
import com.assignment.hospital.entity.Prescription;


@Service
public class PatientService {
	
	@Autowired
	private PatientDao patientDao;
	
	public Patient addPatient(Patient patient) {
		Clinic clinic = patient.getClinic();
        if (clinic != null) {
            // Assuming the clinic object is a detached entity with an existing ID
            // If not, you may need to fetch the clinic from the database
            patient.setClinic(clinic);
        } else {
            throw new IllegalArgumentException("Clinic cannot be null in the patient.");
        }

        // Set the doctor reference for the patient
        Doctor doctor = patient.getDoctor();
        if (doctor != null) {
            // Assuming the doctor object is a detached entity with an existing ID
            // If not, you may need to fetch the doctor from the database
            patient.setDoctor(doctor);
        } else {
            throw new IllegalArgumentException("Doctor cannot be null in the patient.");
        }

        return patientDao.save(patient);
       
    }
	
	public Patient updatePatient(Patient patient) {
		Long patientId = patient.getId();
		Patient existingPatient = patientDao.findById(patientId).orElse(null);
		if (existingPatient == null) {
            // Patient with the provided ID does not exist, return null or throw an exception as needed
            return null;
        }
		
		existingPatient.setFirstName(patient.getFirstName());
        existingPatient.setLastName(patient.getLastName());
        existingPatient.setDob(patient.getDob());
        existingPatient.setGender(patient.getGender());
        existingPatient.setEmrNumber(patient.getEmrNumber());
		
     // Update the Doctor
        Doctor updatedDoctor = patient.getDoctor();
        existingPatient.setDoctor(updatedDoctor);
        
     // Update the Clinic
        Clinic updatedClinic = patient.getClinic();
        existingPatient.setClinic(updatedClinic);
        
        List<Prescription> updatedPrescriptions = patient.getPrescriptions();
        existingPatient.getPrescriptions().clear();
        existingPatient.getPrescriptions().addAll(updatedPrescriptions);
        updatedPrescriptions.forEach(p -> p.setPatient(existingPatient));
		return patientDao.save(patient);
    }
	
	
	
	public Patient getPatientById(Long id) {
        return patientDao.findById(id).orElse(null);
    }

	public void deletePatient(Long id) {
		patientDao.deleteById (id);
    }
	public ResponseEntity<List<Patient>> searchPatientsByName(String name) {
        List<Patient> patients = patientDao.findAllByFirstNameOrLastName(name, name);

        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }
    }
	
//	public ResponseEntity<List<PatientDTO>> searchPatientsByName(String name) {
//        List<Patient> patients = patientDao.findAllByFirstNameOrLastName(name, name);
//
//        if (patients.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            List<PatientDTO> patientDTOs = patients.stream()
//                    .map(this::convertToDTO)
//                    .collect(Collectors.toList());
//
//            return new ResponseEntity<>(patientDTOs, HttpStatus.OK);
//        }
//    }

//    private PatientDTO convertToDTO(Patient patient) {
//        PatientDTO patientDTO = new PatientDTO();
//        patientDTO.setId(patient.getId());
//        patientDTO.setFirstName(patient.getFirstName());
//        patientDTO.setLastName(patient.getLastName());
//        // Set other necessary fields
//
//        return patientDTO;
//    }
	
	 private PatientDTO convertToDTO(Patient patient)
	 {
		 PatientDTO patientDTO=new PatientDTO();
		 patientDTO.setId(patient.getId());
         patientDTO.setFirstName(patient.getFirstName());
         patientDTO.setLastName(patient.getLastName());
         patientDTO.setDob(patient.getDob());
         patientDTO.setGender(patient.getGender());
         patientDTO.setEmrNumber(patient.getEmrNumber());
         
		 return patientDTO;
	 }
	
	public ResponseEntity<PatientDTO> searchPatientByEmrNumber(String emrNumber) {
        Patient patient = patientDao.findByEmrNumber(emrNumber);
       // System.out.println(patient+" "+emrNumber);
        if (patient != null) {
        	
            return new ResponseEntity<>(convertToDTO(patient), HttpStatus.OK);
        } else {
        	
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	public ResponseEntity<List<PatientDTO>> getPatientsAboveAge60() {
        LocalDate sixtyYearsAgo = LocalDate.now().minusYears(60);
       // System.out.println(sixtyYearsAgo);
        List<Patient> patients = patientDao.findByDobBefore(sixtyYearsAgo);
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
        	List<PatientDTO> patientDTOs = patients.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(patientDTOs, HttpStatus.OK);
        }
    }
	
	public ResponseEntity<List<PatientDTO>> getAllPatientsByDoctor(Long doctorId) {
        List<Patient> patients = patientDao.findAllByDoctor_Id(doctorId);
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
        	List<PatientDTO> patientDTOs = patients.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(patientDTOs, HttpStatus.OK);


        }
    }
	
	public ResponseEntity<List<PatientDTO>> getAllPatientsByClinic(Long clinicId) {
        List<Patient> patients = patientDao.findAllByClinic_Id(clinicId);
        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
        	
        	List<PatientDTO> patientDTOs = patients.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(patientDTOs, HttpStatus.OK);
        }
    }
	
	
}
