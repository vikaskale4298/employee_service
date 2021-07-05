package com.edusol.employee.service;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edusol.employee.model.Employee;
import com.edusol.employee.repository.EmployeeRepository;

import com.google.gson.JsonObject;

@Service
public class EmployeeService {
	

	private final Logger logger =  org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeRepository employeeRepository;

	public Object getEmployee() {
		return employeeRepository.findAll();
	}

	public Object addEmployee(Employee employee) {
		logger.info(employee.toString());
		employeeRepository.save(employee);
		logger.info("employee Added Sucessfully");
		return new ResponseEntity<>("employee added Successfully", HttpStatus.CREATED);
	}

	public Object getEmployeeById(int id) {
		return employeeRepository.findById(id);
	}

	public Object getEmployeeByName(String name) {
		return employeeRepository.findByName(name);
	}

	public Object getEmployeeByDepartment(String department) {
		return employeeRepository.findBydepartment(department);
		
	}

	public Object updateEmployee(Employee employee) {
		/*System.out.println("Request employee:"+employee);
		int id=employee.getId();
		
		Employee emp = employeeRepository.getOne(id);
		System.out.println("Request employee:"+emp);
		
		System.out.println("old employee:"+emp);
    	emp.setName(employee.getName());
		//emp.setDepartment(employee.getDepartment());
		System.out.println("New employee:"+emp);
		employeeRepository.save(emp);
		return "Employee updated sucessfully";
	}*/
		JsonObject response = new JsonObject();

		System.out.println("Request products:" + employee);
		int id = employee.getId();
		try {

			Employee emp = employeeRepository.getOne(id);
			System.out.println("Request employee :" + emp);

			System.out.println("old employee:" + emp);
			employee.setName(employee.getName());
			System.out.println("New products:" + emp);
			employeeRepository.save(emp);
		} catch (Exception e) {
			logger.error(e.getMessage());

			response.addProperty("statusCode", 404);
			response.addProperty("statusMessage", e.getMessage());

			return new ResponseEntity<>(response.toString(), HttpStatus.NOT_FOUND);

		}
		logger.info("employee updated Successfully");
		return new ResponseEntity<>("employee updated succesfully", HttpStatus.OK);

	}

	public Object deleteEmployee(int id) {
		try {
			employeeRepository.deleteById(id);
		}catch(Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		logger.info("Employee deleted sucessfully");
		return new ResponseEntity<>("employee deleted successfully",HttpStatus.OK);		
		}
		 
	}


