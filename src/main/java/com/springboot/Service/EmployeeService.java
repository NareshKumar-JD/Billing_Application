package com.springboot.Service;

import java.util.List;

import com.springboot.Entity.Employee;

public interface EmployeeService {

	public Employee save(Employee emp);
	public Employee getEmpById(int id);
	public List<Employee> getEmployees();
	public Employee updateEmployee(Employee emp);
	public void deleteEmpById(int id);
	
}
