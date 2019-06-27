package com.poc.employeerecruitment.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.employeerecruitment.dao.EmployeeDao;
import com.poc.employeerecruitment.entity.Employee;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
    private DiscoveryClient discoveryClient;
 
    public Optional<URI> serviceUrl() {
        return discoveryClient.getInstances("myApp")
          .stream()
          .map(si -> si.getUri())
          .findFirst();
    }
	
	
	//TODO: Validate request data in every method if required.
	
	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		return employeeDao.findAll();
	}
	
	@PostMapping("/employees")
	public void createEmployee(@RequestBody String reqBody) throws JsonParseException, JsonMappingException, IOException {
		//This is designed to accept single JSON object or array of json objects
		ObjectMapper mapper = new ObjectMapper();
		if (reqBody.trim().startsWith("{")) { // here means it is a JSON object
			/* ***above Trimming of the string is important to remove unnecessary chars at the beginning
			 * if request body starts with space or enter, trim will remove those.
			 */
			
			Employee emp = mapper.readValue(reqBody, Employee.class);
			employeeDao.insertEmployee(emp);			
		} else {
			List<Employee> employees = Arrays.asList(mapper.readValue(reqBody, Employee[].class));
			//TODO: below is not the way to do it
			employees.stream().forEach(employeeDao::insertEmployee);
		}
	}
	
	@PutMapping("/employees")
	public void putEmployee(@RequestBody Employee emp) {
		employeeDao.updateEmployee(emp);
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable int id) {
		Employee e = new Employee();
		e.setId(id);
		employeeDao.deleteEmployee(e);
		
	}

}
