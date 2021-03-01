package com.gall.model;

import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationError {

	private final String status;
	private final String message;
	private List<HashMap<String, String>> details;

}