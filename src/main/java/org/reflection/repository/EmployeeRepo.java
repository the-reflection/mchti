package org.reflection.repository;

import java.util.List;
import org.reflection.model.com.Employee;
import java.math.BigInteger;

public interface EmployeeRepo {

    public Employee findById(BigInteger id);

    public void save(Employee employee);

    public List<Employee> findAll();

}
