package com.patient.patientmanagement.service;

import java.util.List;

import javax.xml.bind.ValidationException;


import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;

/**
 * @author KL105911
 *
 */
public interface PatientManagementService {
	
	public PatientDetailsSO savePatientDetails(PatientDetailsSO patientDetailsSO);
	
	public List<PatientDetailsSO> getPatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException;
	
	public PatientDetailsSO updatePatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException;
	
	public String removePatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException;


}
