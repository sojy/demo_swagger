package com.gall;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gall.model.RegistrationError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public RegistrationError methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(fieldErrors);
	}

	private RegistrationError processFieldErrors(List<FieldError> fieldErrors) {
		RegistrationError registrationError = null;
		List<HashMap<String, String>> errorMapList = new ArrayList<HashMap<String, String>>();

		Set<String> filteredErrorList = new HashSet<String>();
		for (FieldError fieldError : fieldErrors) {
			filteredErrorList.add(fieldError.getField());
		}

		for (FieldError fieldError : fieldErrors) {
			HashMap<String, String> errorMap = new HashMap<String, String>();

			String usedfieldname = "";
			for (String str : filteredErrorList) {
				if (fieldError.getField().equals(str)) {
					errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
					errorMapList.add(errorMap);
					usedfieldname = str;
					break;
				}
			}
			filteredErrorList.remove(usedfieldname);

		}
		if (errorMapList.size() > 0) {
			registrationError = new RegistrationError("error", "validation error", errorMapList);
		}
		return registrationError;
	}
}