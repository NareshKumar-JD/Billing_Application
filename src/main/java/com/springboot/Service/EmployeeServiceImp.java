package com.springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Entity.Employee;
import com.springboot.Error.RecordNotFoundException;
import com.springboot.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImp implements EmployeeService{
	@Autowired
	EmployeeRepository repoe;

	@Override
	public Employee save(Employee emp) {
		// TODO Auto-generated method stub
		return repoe.save(emp);
	}

	@Override
	public Employee getEmpById(int id) {
	Optional<Employee> empDb = repoe.findById(id);
	if(empDb.isPresent())
	   {
		Employee employee = empDb.get();
		return employee;
		}
		else
		{
			throw new RecordNotFoundException("Record not found");
			
		}
	}	

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return repoe.findAll();
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		Optional<Employee> empDb = repoe.findById(emp.getEmp_id());
		if(empDb.isPresent())
		{
			Employee employee = empDb.get();
			employee.setEmail(emp.getEmail());
			employee.setPhone(emp.getPhone());
			repoe.save(employee);
			return employee;
		}		
		else
		{
			throw new RecordNotFoundException("Record not found");
			
		}
	}

	@Override
	public void deleteEmpById(int id) {
		Optional<Employee> empDb = repoe.findById(null);
		if(empDb.isPresent())
		{
			Employee employee = empDb.get();
			repoe.delete(employee);
		}
		else
		{
			throw new RecordNotFoundException("Record not found");
		}
	}

	}

