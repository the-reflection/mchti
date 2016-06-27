package org.reflection.repositories;

import org.reflection.model.com.Supplier;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, BigInteger> {
    public Supplier findByCode(String code);
}
