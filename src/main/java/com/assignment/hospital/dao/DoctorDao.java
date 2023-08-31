package com.assignment.hospital.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.hospital.entity.Clinic;
import com.assignment.hospital.entity.Doctor;

@Repository
public interface DoctorDao extends CrudRepository<Doctor,Long>{
	 List<Doctor> findAllByClinic_Id(Long clinicId);

}
