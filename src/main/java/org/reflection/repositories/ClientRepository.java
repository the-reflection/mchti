package org.reflection.repositories;

import org.reflection.model.com.Client;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, BigInteger> {
    public Client findByCode(String code);
}
