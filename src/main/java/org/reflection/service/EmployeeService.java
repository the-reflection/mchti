package org.reflection.service;

import org.reflection.model.com.Employee;
import org.reflection.exception.EmployeeNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface EmployeeService {

    public Employee findByCode(String code);

    public Employee findById(BigInteger id);
    
    public Employee create(Employee employee);
    
    public Employee update(Employee employee) throws EmployeeNotFoundException;
    
    public Employee copy(Employee employee);
    
    public Employee delete(BigInteger id) throws EmployeeNotFoundException;
   
    public List<Employee> search(_SearchDTO pageable);
    
    public List<Employee> findAll(_SearchDTO pageable);
    
    public List<Employee> findAll();
}