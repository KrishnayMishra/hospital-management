package com.assignment.hospital.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.hospital.entity.Prescription;


@Repository
public interface PrescriptionDao extends CrudRepository<Prescription,Long>{
	Prescription save(Prescription prescription);
	 void deleteById(Long id);

}
