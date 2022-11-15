package com.patient.patientmanagement.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author KL105911
 *
 */
@Entity
@Table(name = "PATIENT_HDR")
public class Patient {

	@TableGenerator(name = "patientIdSeq", table = "sys_uuid", pkColumnName = "uuid_code", pkColumnValue = "pat_id_seq", valueColumnName = "strt_num")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patient_id")

	private long patientId;

	private String patientName;

	private Date patientDOB;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "patient", fetch = FetchType.LAZY)

	private List<PatientIdentifier> patientIdentifier;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "patientAdr", fetch = FetchType.LAZY)

	private List<PatientAddress> patientAddresses;

	private String contactNumber;

	private String alternateContactNumber;

	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the patientDOB
	 */
	public Date getPatientDOB() {
		return patientDOB;
	}

	/**
	 * @param patientDOB the patientDOB to set
	 */
	public void setPatientDOB(Date patientDOB) {
		this.patientDOB = patientDOB;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the alternateContactNumber
	 */
	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}

	/**
	 * @param alternateContactNumber the alternateContactNumber to set
	 */
	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	/**
	 * @return the patientAddresses
	 */
	public List<PatientAddress> getPatientAddresses() {
		return patientAddresses;
	}

	/**
	 * @param patientAddresses the patientAddresses to set
	 */
	public void setPatientAddresses(List<PatientAddress> patientAddresses) {
		this.patientAddresses = patientAddresses;
		
		patientAddresses.forEach(entity -> entity.setPatientAdr(this));
	}

	public List<PatientIdentifier> getPatientIdentifier() {
		return patientIdentifier;
	}

	public void setPatientIdentifier(List<PatientIdentifier> patientIdentifier) {
		this.patientIdentifier = patientIdentifier;
		
		patientIdentifier.forEach(entity -> entity.setPatient(this));
	}

	/**
	 * @return the patientId
	 */
	public long getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

}
