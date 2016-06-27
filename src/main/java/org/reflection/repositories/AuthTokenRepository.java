package org.reflection.repositories;

import org.reflection.model.security.AuthToken;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenRepository extends JpaRepository<AuthToken, BigInteger> {

}
