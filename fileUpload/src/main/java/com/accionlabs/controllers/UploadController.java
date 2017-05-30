package com.accionlabs.controllers;
import java.io.IOException;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.accionlabs.data.ResponseMessage;
import com.accionlabs.service.EmployeeService;

@Controller
public class UploadController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/upload")
	public String welcome() {
		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/uploadEmployeeDetails", method = RequestMethod.POST)
	public ResponseMessage uploadEmpDetails(@RequestParam(value = "file") MultipartFile file)
			throws IOException {
		ResponseMessage	responseMessage=employeeService.saveEmployeeDetails(file);	
		return responseMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/uploadAttendenceDetails", method = RequestMethod.POST)
	public ResponseMessage uploadAttendenceDetails(@RequestParam(value = "file") MultipartFile file)
			throws IOException, ParseException {
		ResponseMessage responseMessage=employeeService.saveAttendanceDetails(file);
		
		return responseMessage;
	}

}
