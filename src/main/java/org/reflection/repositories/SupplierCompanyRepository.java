package org.reflection.repositories;

import org.reflection.model.com.SupplierCompany;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierCompanyRepository extends JpaRepository<SupplierCompany, BigInteger> {
    public SupplierCompany findByCode(String code);
}
