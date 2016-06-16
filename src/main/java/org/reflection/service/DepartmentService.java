package org.reflection.service;

import org.reflection.model.com.Department;
import org.reflection.exception.DepartmentNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface DepartmentService {



    public Department findById(BigInteger id);
    
    public Department create(Department department);
    
    public Department update(Department department) throws DepartmentNotFoundException;
    
    public Department copy(Department department);
    
    public Department delete(BigInteger id) throws DepartmentNotFoundException;
   
    public List<Department> search(_SearchDTO pageable);
    
    public List<Department> findAll(_SearchDTO pageable);
    
    public List<Department> findAll();
}