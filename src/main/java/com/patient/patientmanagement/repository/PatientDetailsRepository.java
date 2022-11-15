package com.patient.patientmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patient.patientmanagement.entity.Patient;


@Repository

public interface PatientDetailsRepository extends JpaRepository<Patient, Long>{
	
	
	@Query("SELECT pd FROM Patient pd WHERE  pd.patientId=:patientId")
	Patient getPatientDetailsById (@Param("patientId") Long patientId);
	
	@Query("SELECT pd FROM Patient pd WHERE  pd.patientName=:patientName" )
	List<Patient> getPatientDetailsByName (@Param("patientName") String patientName);
	
	@Query("SELECT pd FROM Patient pd inner join  pd.patientIdentifier pi where pi.identifierNumber=:identifierNumber" )
	Patient getPatientDetailsByGovtId (@Param("identifierNumber") String identifierNumber);
	

}
