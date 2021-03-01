package com.gall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gall.model.DemoModel;

@RestController
public class Demo {

	@GetMapping("/demo")
	public ResponseEntity<?> demo() throws Exception {

		List<String> result = new ArrayList<>();
		result.add("irfan");
		result.add("irfan");
		result.add("irfan");
		result.add("irfan");
		result.add("irfan");

		return new ResponseEntity<List<String>>(result, HttpStatus.OK);
	}

	@PostMapping("/demopost")
	public ResponseEntity<?> demopost(@RequestBody DemoModel demo) throws Exception {
		return new ResponseEntity<String>(
				"Hello Dear " + demo.getName() + ". we are happy to tell your address " + demo.getAddress(),
				HttpStatus.OK);
	}

}
