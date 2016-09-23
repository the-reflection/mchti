package org.reflection.service;

import org.reflection.model.hcm.cr.Department;
import org.reflection.exception.DepartmentNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface DepartmentService {



    public Department findById(BigInteger id);
    
    public Department create(Department department);
    
    public Department update(Department department) throws DepartmentNotFoundException;
    
    public Department copy(Department department);
    
    public Department delete(BigInteger id) throws DepartmentNotFoundException;
   
    public Iterable<Department> search(_SearchDTO pageable);
    
    public Iterable<Department> findAll(_SearchDTO pageable);
    
    public Iterable<Department> findAll();
}