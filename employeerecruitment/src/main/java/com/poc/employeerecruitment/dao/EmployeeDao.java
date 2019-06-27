package com.poc.employeerecruitment.dao;

import java.util.List;

import com.poc.employeerecruitment.entity.Employee;

public interface EmployeeDao {
	List<Employee> findAll();
	void insertEmployee(Employee emp);
	void updateEmployee(Employee emp);
	void executeUpdateEmployee(Employee emp);
	void deleteEmployee(Employee emp);
	
}
