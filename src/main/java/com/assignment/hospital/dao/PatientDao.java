package com.assignment.hospital.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.hospital.entity.Clinic;
import com.assignment.hospital.entity.Doctor;
import com.assignment.hospital.entity.Patient;

@Repository
public interface PatientDao extends CrudRepository<Patient,Long>{
	List<Patient> findAllByDoctor_Id(Long doctorId);
	List<Patient> findAllByClinic_Id(Long clinicId);
	List<Patient> findByFirstNameContainingIgnoreCase(String name);
    List<Patient> findByLastNameContainingIgnoreCase(String name);
    List<Patient> findAllByFirstNameOrLastName(String fname,String lname);
    Patient findByEmrNumber(String emrNumber);
    Optional<Patient> findById(Long id);
    List<Patient> findByDobBefore(LocalDate date);
    Patient save(Patient patient);
    void deleteById(Long id);
    
}
