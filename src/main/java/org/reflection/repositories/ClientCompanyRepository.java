package org.reflection.repositories;

import org.reflection.model.com.ClientCompany;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientCompanyRepository extends JpaRepository<ClientCompany, BigInteger> {
    public ClientCompany findByCode(String code);
}
