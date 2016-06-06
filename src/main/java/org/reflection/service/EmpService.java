package org.reflection.service;

import org.reflection.model.com.Employee;
import org.reflection.exception.EmpNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;

public interface EmpService {

    public Employee findByCode(String code);

    public Employee findById(Long id);
    
    public Employee create(Employee emp);
    
    public Employee update(Employee emp) throws EmpNotFoundException;
    
    public Employee delete(Long id) throws EmpNotFoundException;
   
    public List<Employee> search(_SearchDTO pageable);
    
    public List<Employee> findAll(_SearchDTO pageable);
    
    public List<Employee> findAll();
}