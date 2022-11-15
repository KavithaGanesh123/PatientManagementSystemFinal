package com.patient.patientmanagement.service.impl;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;
import com.patient.patientmanagement.service.PatientManagementService;
import com.patient.patientmanagement.dao.PatientDao;

@Service

public class PatientManagementServiceImpl implements PatientManagementService {

	@Autowired
	private PatientDao patientDao;

	@Override
	public PatientDetailsSO savePatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException

	{
		return patientDao.savePatientDetails(patientDetailsSO);
	}

	@Override
	public List<PatientDetailsSO> getPatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException

	{
		return patientDao.getPatientDetails(patientDetailsSO);
	}

	@Override
	public PatientDetailsSO updatePatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException

	{
		return patientDao.updatePatientDetails(patientDetailsSO);
	}

	@Override
	public String removePatientDetails(PatientDetailsSO patientDetailsSO) throws ValidationException

	{
		return patientDao.removePatientDetails(patientDetailsSO);
	}

}
