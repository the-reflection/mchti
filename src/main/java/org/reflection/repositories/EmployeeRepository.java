package org.reflection.repositories;

import org.reflection.model.com.Employee;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, BigInteger> {
    public Employee findByCode(String code);
}
