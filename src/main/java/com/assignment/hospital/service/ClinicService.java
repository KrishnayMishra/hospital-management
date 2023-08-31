package com.assignment.hospital.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.hospital.dao.ClinicDTO;
import com.assignment.hospital.dao.ClinicDao;
import com.assignment.hospital.dao.PatientDTO;
import com.assignment.hospital.dao.PatientDao;
import com.assignment.hospital.entity.Clinic;
import com.assignment.hospital.entity.Patient;

@Service
public class ClinicService {
	
	@Autowired
	private ClinicDao clinicDao;
	
	
	 public Clinic addClinic(Clinic clinic) {
	        // Save the new clinic to the database
	        return clinicDao.save(clinic);
	    }
	 
	 
	 private ClinicDTO convertToDTO(Clinic clinic)
	 {
		 ClinicDTO clinicDTO=new ClinicDTO();
		 clinicDTO.setId(clinic.getId());
         clinicDTO.setName(clinic.getName());
         clinicDTO.setAddress(clinic.getAddress());
         clinicDTO.setCity(clinic.getCity());
         clinicDTO.setState(clinic.getState());
//         clinicDTO.setDoctors(clinic.getDoctors());
//         clinicDTO.setPatients(clinic.getPatients());
         
		 return clinicDTO;
	 }
	 
	 public ResponseEntity<List<ClinicDTO>> getAllClinics() {
	        List<Clinic> clinics = (List<Clinic>) clinicDao.findAll();
	      //  System.out.println(clinics.size());
	        if (clinics.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	        	List<ClinicDTO> clinicDTOs = clinics.stream()
	                    .map(this::convertToDTO)
	                    .collect(Collectors.toList());
	            return new ResponseEntity<>(clinicDTOs, HttpStatus.OK);
	        }
	    }

}
