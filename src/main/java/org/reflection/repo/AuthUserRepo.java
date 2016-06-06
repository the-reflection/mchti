package org.reflection.repo;

import java.util.List;

import org.reflection.model.security.AuthUser;
import java.math.BigInteger;

public interface AuthUserRepo {

    AuthUser findById(BigInteger id);

    AuthUser findByUsername(String username);

    void save(AuthUser user);

    void deleteByUsername(String username);

    List<AuthUser> findAllAuthUsers();

}
