package org.reflection.repository;

import java.util.List;
import org.reflection.model.security.AuthRequestMap;
import java.math.BigInteger;

public interface AuthRequestMapRepo {

    public AuthRequestMap findById(BigInteger id);

    public void save(AuthRequestMap authRequestMap);

    public List<AuthRequestMap> findAll();

}
