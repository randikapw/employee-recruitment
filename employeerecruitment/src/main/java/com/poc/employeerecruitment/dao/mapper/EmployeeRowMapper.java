package com.poc.employeerecruitment.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.poc.employeerecruitment.constants.DbConstants;
import com.poc.employeerecruitment.entity.Employee;

public class EmployeeRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt(DbConstants.FILED_EMPLOYEE_ID));
		employee.setName(rs.getString(DbConstants.FILED_EMPLOYEE_NAME));
		employee.setAge(rs.getInt(DbConstants.FILED_EMPLOYEE_AGE));
		employee.setAddress(rs.getString(DbConstants.FILED_EMPLOYEE_ADDRESS));
		return employee;
	}




}
