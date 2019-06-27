package com.poc.employeerecruitment.dao;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.poc.employeerecruitment.dao.mapper.EmployeeRowMapper;
import com.poc.employeerecruitment.entity.Employee;

import static com.poc.employeerecruitment.constants.DbConstants.*;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	/* ------------ QUERY CONSTANTS --------------- */
	/**
	 * Query for get all employees form employee table.
	 */
	private static final String QUERY_SELECT_ALL_EMPLOYEES = "select * from " + TABLE_EMPLOYEE;
	
	/**
	 * Query for add employee.
	 */
	private static final String QUERY_ADD_EMPLOYEE = String.format(
			"insert into %1$s(%2$s, %3$s, %4$s, %5$s) values(:%2$s, :%3$s, :%4$s, :%5$s)", TABLE_EMPLOYEE,
			FILED_EMPLOYEE_ID, FILED_EMPLOYEE_NAME, FILED_EMPLOYEE_AGE, FILED_EMPLOYEE_ADDRESS);
	
	private static final String QUERY_UPDATE_EMPLOYEES = String.format(
			"update %1$s set %2$s=:%2$s, %3$s=:%3$s, %4$s=:%4$s where %5$s=:%5$s", TABLE_EMPLOYEE, FILED_EMPLOYEE_NAME,
			FILED_EMPLOYEE_AGE, FILED_EMPLOYEE_ADDRESS, FILED_EMPLOYEE_ID);
	
	private static final String QUERY_DELETE_EMPLOYEE = String.format(
			"delete from %1$s where %2$s=:%2$s", TABLE_EMPLOYEE,
			FILED_EMPLOYEE_ID);

	/*--------------- GLOBAL VARIABLES -------------------*/
	private NamedParameterJdbcTemplate template;
	
	
	
	private SqlParameterSource getEmpolyeeSqlParams(Employee employee) {
		return new MapSqlParameterSource()
				.addValue(FILED_EMPLOYEE_ID, employee.getId())
				.addValue(FILED_EMPLOYEE_NAME, employee.getName())
				.addValue(FILED_EMPLOYEE_AGE, employee.getAge())
				.addValue(FILED_EMPLOYEE_ADDRESS, employee.getAddress());
		
	} 

	public EmployeeDaoImpl(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	@Override
	public List<Employee> findAll() {
		return template.query(QUERY_SELECT_ALL_EMPLOYEES, new EmployeeRowMapper());
	}

	@Override
	public void insertEmployee(Employee emp) {
		template.update(QUERY_ADD_EMPLOYEE, getEmpolyeeSqlParams(emp), new GeneratedKeyHolder());

	}

	@Override
	public void updateEmployee(Employee emp) {
		template.update(QUERY_UPDATE_EMPLOYEES, getEmpolyeeSqlParams(emp), new GeneratedKeyHolder());

	}

	@Override
	public void executeUpdateEmployee(Employee emp) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FILED_EMPLOYEE_ID, emp.getId());
		map.put(FILED_EMPLOYEE_NAME, emp.getName());
		map.put(FILED_EMPLOYEE_AGE, emp.getAge());
		map.put(FILED_EMPLOYEE_ADDRESS, emp.getAddress());
		
		template.execute(QUERY_UPDATE_EMPLOYEES,map,(PreparedStatement ps)-> ps.executeUpdate());
	}

	@Override
	public void deleteEmployee(Employee emp) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FILED_EMPLOYEE_ID, emp.getId());

		template.execute(QUERY_DELETE_EMPLOYEE, map, (PreparedStatement ps) -> ps.executeUpdate());

	}

}
