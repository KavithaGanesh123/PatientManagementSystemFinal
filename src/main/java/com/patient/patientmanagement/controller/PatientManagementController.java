package com.patient.patientmanagement.controller;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;
import com.patient.patientmanagement.service.PatientManagementService;
import java.util.List;

/**
 * @author KL105911
 *
 */
@RestController
@RequestMapping("/patient")
public class PatientManagementController {
	
	@Autowired
	private PatientManagementService patientManagementService;
	
	private static final Logger		logger	= LoggerFactory.getLogger(PatientManagementController.class);

	
	/**
	 * Controller Request to save new patient details
	 * 
	 * @param patientDetailsSO Patient Details for save
	 * @return
	 * @throws ValidationException Throws ValidationException
	 */
	@RequestMapping(value = "/savePatientDetails", produces = "application/json")
	public @ResponseBody PatientDetailsSO savePatientDetails(@RequestBody PatientDetailsSO patientDetailsSO)
	{
		logger.info("Inside the PatientManagementController.savePatientDetails method..");
		
		 patientDetailsSO=patientManagementService.savePatientDetails(patientDetailsSO);
		return patientDetailsSO;
	}
	
	/**
	 * Controller Request to get patient details
	 * 
	 * @param patientDetailsSO
	 * @return
	 * @throws ValidationException
	 */
	@RequestMapping(value = "/getPatientDetails", produces = "application/json")
	public @ResponseBody List<PatientDetailsSO> getPatientDetails(@RequestBody PatientDetailsSO patientDetailsSO) throws ValidationException
	{
		logger.info("Inside the PatientManagementController.savePatientDetails method..");
		
		List<PatientDetailsSO> patientDetailsSOList=patientManagementService.getPatientDetails(patientDetailsSO);
		return patientDetailsSOList;
	}
	
	/**
	 * 
	 * Controller Request to update patient details
	 * 
	 * @param patientDetailsSO
	 * @return
	 * @throws ValidationException
	 */
	@RequestMapping(value = "/updatePatientDetails", produces = "application/json")
	public @ResponseBody PatientDetailsSO updatePatientDetails(@RequestBody PatientDetailsSO patientDetailsSO) throws ValidationException
	{
		logger.info("Inside the PatientManagementController.savePatientDetails method..");
		
		 patientDetailsSO=patientManagementService.updatePatientDetails(patientDetailsSO);
		return patientDetailsSO;
	}
	
	/**
	 * 
	 * Controller Request to remove patient details
	 * 
	 * @param patientDetailsSO
	 * @return
	 * @throws ValidationException
	 */
	@RequestMapping(value = "/removePatientDetails", produces = "application/json")
	public  String removePatientDetails(@RequestBody PatientDetailsSO patientDetailsSO) throws ValidationException
	{
		logger.info("Inside the PatientManagementController.savePatientDetails method..");
		String msg=patientManagementService.removePatientDetails(patientDetailsSO);
		return msg;
	}
}
