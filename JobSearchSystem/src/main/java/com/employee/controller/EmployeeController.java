package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.model.EmployeeDTO;
import com.employee.service.EmployeeService;
import com.employee.util.Converter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController(value = "/secure")
@RequestMapping("/secure")
@Api(tags = "Clients")
@ApiOperation(value = "View a list of available employees", response = List.class)

@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private Converter converter;

	@ApiOperation(value = "This method is used to register employees.")
	@PostMapping(value = "/registeremployee")
	public String registerEmployee(
			@ApiParam(value = "Employee object store in database table") @RequestBody final EmployeeDTO employerDTO) {
		final Employee employee = converter.convertToEmployeeEntity(employerDTO);
		return employeeService.registerEmployee(employee);
	}

	@PutMapping(value = "/updateemployee")
	@ApiOperation(value = "Update an employee")
	public String updateEmployee(
			@ApiParam(value = "Update employee object") @RequestBody final EmployeeDTO employerDTO) {
		final Employee employee = converter.convertToEmployeeEntity(employerDTO);
		return employeeService.updateEmployee(employee);
	}

	@DeleteMapping(value = "/deleteemployee/{employeeId}")
	@ApiOperation(value = "Delete an employee")
	public String updateEmployee(final int employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}

	@GetMapping(value = "/getemployee/{employeeId}")
	@ApiOperation(value = "Get an employee by Id")
	public EmployeeDTO getEmployee(
			@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable("employeeId") final int employeeId) {
		return employeeService.getEmployee(employeeId);
	}

	@GetMapping(value = "/getallemployees")
	@ApiOperation(value = "Get all the employees")
	public List<EmployeeDTO> getAllEmployees() {
		return employeeService.getEmployees();
	}

	@GetMapping(value = "/getmessage")
	public String getTestData() {
		return "I am test data";
	}
}