package com.patient.patientmanagement.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.patient.patientmanagement.entity.Patient;
import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;

/**
 * @author KL105911
 *
 */
public interface PatientManagementService {
	
	/**
	 * Service Request to save new patient details
	 * 
	 * @param patientDetailsSO Patient Details for save
	 * @return
	 * @throws ValidationException Throws ValidationException
	 */
	public PatientDetailsSO savePatientDetails(PatientDetailsSO patientDetailsSO);
	
	/**
	 * Service Request to get patient details
	 * 
	 * @param patientDetailsSO
	 * @return
	 * @throws ValidationException
	 */
	public List<PatientDetailsSO> getPatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException;
	
	/**
	 * 
	 * Service Request to update patient details
	 * 
	 * @param patientDetailsSO
	 * @return
	 * @throws ValidationException
	 */
	public PatientDetailsSO updatePatientDetails(Patient patientDetailsSO) throws ValidationException;
	
	/**
	 * 
	 * Service Request to remove patient details
	 * 
	 * @param patientDetailsSO
	 * @return
	 * @throws ValidationException
	 */
	public String removePatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException;


}
