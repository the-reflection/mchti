package org.reflection.repository;

import java.util.List;
import org.reflection.model.security.AuthQuestion;
import java.math.BigInteger;

public interface AuthQuestionRepo {

    public AuthQuestion findById(BigInteger id);

    public void save(AuthQuestion authQuestion);

    public List<AuthQuestion> findAll();

}
