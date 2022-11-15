package com.patient.patientmanagement.util;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author KL105911
 *
 */
@Component
public class GenericUtils {
	
	 /**
	 * @param dest Object to be copied
	 * @param orig Source Object
	 * @throws IllegalAccessException Throws IllegalAccessException
	 * @throws InvocationTargetException Throws InvocationTargetException
	 */
	public void copyProperties(Object dest, Object orig) throws IllegalAccessException,InvocationTargetException {
			if ((dest != null) && (orig != null)) {
			BeanUtils.copyProperties(dest, orig);
			}
		    }

}
