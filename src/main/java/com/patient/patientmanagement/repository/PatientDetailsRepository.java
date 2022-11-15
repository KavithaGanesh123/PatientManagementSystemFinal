package com.patient.patientmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.patient.patientmanagement.entity.Patient;


/**
 * @author KL105911
 *
 */
@Repository

public interface PatientDetailsRepository extends JpaRepository<Patient, Long>{
	
	
	/**
	 * Repository Method to get Patient details By patient ID
	 * 
	 * @param patientId patient Id 
	 * @return Patient Patient details
	 */
	@Query("SELECT pd FROM Patient pd WHERE  pd.patientId=:patientId")
	Patient getPatientDetailsById (@Param("patientId") Long patientId);
	
	/**
	 * Repository Method to get list of Patients with the entered name
	 * 
	 * @param patientName Patient Name
	 * @return Patient List of patients
	 */
	@Query("SELECT pd FROM Patient pd WHERE  pd.patientName=:patientName" )
	List<Patient> getPatientDetailsByName (@Param("patientName") String patientName);
	
	/**
	 * 
	 * Repository Method to get Patient details by giving government ID number
	 * 
	 * @param identifierNumber government identifier number
	 * @return Patient Patient details
	 */
	@Query("SELECT pd FROM Patient pd inner join  pd.patientIdentifier pi where pi.identifierNumber=:identifierNumber" )
	Patient getPatientDetailsByGovtId (@Param("identifierNumber") String identifierNumber);
	

}
