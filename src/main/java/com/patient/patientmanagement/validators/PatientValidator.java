package com.patient.patientmanagement.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.patient.patientmanagement.schemaobjects.PatientDetailsSO;

@Component
public class PatientValidator implements Validator{
	
	@Override
	 public boolean supports(Class<?> clazz) {
	     return (clazz == PatientDetailsSO.class);
	   }
	 
	@Override
	 public void validate(Object target, Errors errors) {
	     if (target == null) {
	       errors.reject("PatientDetailsSO object is null");
	       return;
	     } 
	     PatientDetailsSO requestSo = (PatientDetailsSO)target;
	     if (requestSo != null && (requestSo.getPatientName().isEmpty() || requestSo.getPatientName()=="")) {
	    	 
	    	 errors.rejectValue("patientName", "Patient Name is Mandatory", "Patient Name is Mandatory"); 
	     /*  DocumentDetailsSO documentDetailsSO = requestSo.getDocumentDetails();
	      
	       if (StringUtils.isEmpty(airlinePrefix))
	         errors.rejectValue("airlinePrefix", "CRO.API.021", "CRO.API.021"); 
	     
	       // JIRA-1899 end
	       
	       errors.pushNestedPath("documentDetails");
	     

	       errors.popNestedPath();*/
	     } 
	   }

}
