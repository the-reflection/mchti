package org.reflection.repository;

import java.util.List;
import org.reflection.model.security.AuthUserAuthQuestion;
import java.math.BigInteger;

public interface AuthUserAuthQuestionRepo {

    public AuthUserAuthQuestion findById(BigInteger id);

    public void save(AuthUserAuthQuestion authUserAuthQuestion);

    public List<AuthUserAuthQuestion> findAll();

}
