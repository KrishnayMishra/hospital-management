package com.assignment.hospital.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.hospital.entity.Clinic;

@Repository
public interface ClinicDao extends CrudRepository<Clinic,Long>{
	

}
