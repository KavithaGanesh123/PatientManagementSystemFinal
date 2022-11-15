package com.patient.patientmanagement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


/**
 * @author KL105911
 *
 */
@Entity
@Table(name = "PATIENT_IDENTIFIER")
public class PatientIdentifier {
	
	@TableGenerator(name = "patientIdSeq", table = "sys_uuid", pkColumnName = "uuid_code", pkColumnValue = "pat_id_seq", valueColumnName = "strt_num")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patientItentifier_id")
	
	private long patientItentifierId;
	
	private String identifierType;
	
	private String identifierNumber;
	
	
	 @ManyToOne(cascade = CascadeType.ALL, optional = true)
	 @JoinColumn(name = "patientId", nullable = true)
    private Patient patient;


	/**
	 * @return the patientItentifierId
	 */
	public long getPatientItentifierId() {
		return patientItentifierId;
	}


	/**
	 * @param patientItentifierId the patientItentifierId to set
	 */
	public void setPatientItentifierId(long patientItentifierId) {
		this.patientItentifierId = patientItentifierId;
	}


	/**
	 * @return the identifierType
	 */
	public String getIdentifierType() {
		return identifierType;
	}


	/**
	 * @param identifierType the identifierType to set
	 */
	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}


	/**
	 * @return the identifierNumber
	 */
	public String getIdentifierNumber() {
		return identifierNumber;
	}


	/**
	 * @param identifierNumber the identifierNumber to set
	 */
	public void setIdentifierNumber(String identifierNumber) {
		this.identifierNumber = identifierNumber;
	}


	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}


	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}


}
