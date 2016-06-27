package org.reflection.repositories;

import org.reflection.model.com.Department;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, BigInteger> {

}
