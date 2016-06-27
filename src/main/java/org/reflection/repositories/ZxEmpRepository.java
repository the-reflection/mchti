package org.reflection.repositories;

import org.reflection.model.sample.ZxEmp;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZxEmpRepository extends JpaRepository<ZxEmp, BigInteger> {
    public ZxEmp findByCode(String code);
}
