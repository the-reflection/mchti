package org.reflection.repositories;

import org.reflection.model.security.AuthQuestion;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthQuestionRepository extends JpaRepository<AuthQuestion, BigInteger> {

}
