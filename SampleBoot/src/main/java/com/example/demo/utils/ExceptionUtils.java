package com.example.demo.utils;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.example.demo.contants.AppConstants;
import com.example.demo.exception.DeniedRequestException;

public class ExceptionUtils {

	 public static void throwDeniedRequestException(final String field, final String errorCode, final String defaultMessage) {
		final Errors errors = new BeanPropertyBindingResult(AppConstants.BLANK, AppConstants.COMMON_ERROR);
		errors.rejectValue(field, errorCode, defaultMessage);
		throw new DeniedRequestException(defaultMessage, errors);
	}
}
