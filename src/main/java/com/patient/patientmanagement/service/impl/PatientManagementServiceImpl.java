package com.patient.patientmanagement.service.impl;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;
import com.patient.patientmanagement.service.PatientManagementService;
import com.patient.patientmanagement.dao.PatientDao;

/**
 * @author KL105911
 *
 */
@Service

public class PatientManagementServiceImpl implements PatientManagementService {

	@Autowired
	private PatientDao patientDao;

	/**
	 * Service Request to save new patient details
	 * 
	 * @param patientDetailsSO Patient Details for save
	 * @return
	 * @throws ValidationException Throws ValidationException
	 */
	
	@Override
	public PatientDetailsSO savePatientDetails(PatientDetailsSO patientDetailsSO) 

	{
		return patientDao.savePatientDetails(patientDetailsSO);
	}

	/**
	 * Service Request to get patient details
	 * 
	 * @param patientDetailsSO
	 * @return
	 * @throws ValidationException
	 */
	@Override
	public List<PatientDetailsSO> getPatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException

	{
		return patientDao.getPatientDetails(patientDetailsSO);
	}

	/**
	 * 
	 * Service Request to update patient details
	 * 
	 * @param patientDetailsSO
	 * @return
	 * @throws ValidationException
	 */
	@Override
	public PatientDetailsSO updatePatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException

	{
		return patientDao.updatePatientDetails(patientDetailsSO);
	}

	/**
	 * 
	 * Service Request to remove patient details
	 * 
	 * @param patientDetailsSO
	 * @return
	 * @throws ValidationException
	 */
	@Override
	public String removePatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException

	{
		return patientDao.removePatientDetails(patientDetailsSO);
	}

}
