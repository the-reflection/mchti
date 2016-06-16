package org.reflection.repository;

import java.util.List;
import org.reflection.model.com.Department;
import java.math.BigInteger;

public interface DepartmentRepo {

    public Department findById(BigInteger id);

    public void save(Department department);

    public List<Department> findAll();

}
