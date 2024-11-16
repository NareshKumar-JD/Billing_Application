package com.springboot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Entity.Employee;
import com.springboot.Service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeService serviceem;
			
	@PostMapping("/employee")
	public ResponseEntity<Employee> save(@RequestBody Employee emp)
	{
		return ResponseEntity.ok().body(serviceem.save(emp));	
	}
	
	@GetMapping("/employee/{emp_id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable int emp_id)
	{
		return ResponseEntity.ok().body(serviceem.getEmpById(emp_id));
	}
	
	@GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees()
    {
		return ResponseEntity.ok().body(serviceem.getEmployees());
    }
	
	@PutMapping("/employee/{emp_id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int emp_id,
             @RequestBody Employee emp) 
	{
		emp.setEmp_id(emp_id); 
		return ResponseEntity.ok().body(serviceem.updateEmployee(emp));
 	
    }
    @DeleteMapping("/employee/{emp_id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int emp_id) 
    {
    	serviceem.deleteEmpById(emp_id);
    	return ResponseEntity.ok().body("Record has been deleted");
    	   
    }   

}
