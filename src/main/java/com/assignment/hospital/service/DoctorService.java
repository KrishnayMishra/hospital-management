package com.assignment.hospital.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.hospital.dao.ClinicDao;
import com.assignment.hospital.dao.DoctorDTO;
import com.assignment.hospital.dao.DoctorDao;
import com.assignment.hospital.dao.PatientDTO;
import com.assignment.hospital.entity.Clinic;
import com.assignment.hospital.entity.Doctor;
import com.assignment.hospital.entity.Patient;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private ClinicDao clinicDao;
	
	public Doctor addDoctor(Doctor doctor) {
        // Save the new doctor to the database
		Clinic clinic = doctor.getClinic();
        if (clinic != null) {
            
            doctor.setClinic(clinic);
        } else {
            throw new IllegalArgumentException("Clinic cannot be null in the doctor.");
        }
        
        List<Patient> patients = doctor.getPatients();
        if (patients != null) {
            patients.forEach(patient -> patient.setDoctor(doctor));
        }

        return doctorDao.save(doctor);
    }
	
	 private DoctorDTO convertToDTO(Doctor doctor)
	 {
		 DoctorDTO doctorDTO=new DoctorDTO();
		 doctorDTO.setId(doctor.getId());
		 doctorDTO.setFirstName(doctor.getFirstName());
		 doctorDTO.setLastName(doctor.getLastName());
		 doctorDTO.setSpeciality(doctor.getSpeciality());
         
		 return doctorDTO;
	 }
	
	public ResponseEntity<List<DoctorDTO>> getAllDoctorsByClinic(Long clinicId) {
        List<Doctor> doctors = doctorDao.findAllByClinic_Id(clinicId);
        System.out.println(doctors.size()+" "+clinicId);
        if (doctors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
        	List<DoctorDTO> doctorDTOs = doctors.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(doctorDTOs, HttpStatus.OK);
        }
//        return null;
    }
	
	

}
