package org.reflection.repositories;

import org.reflection.model.security.AuthRequestMap;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRequestMapRepository extends JpaRepository<AuthRequestMap, BigInteger> {

}
