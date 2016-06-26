package org.reflection.service;

import org.reflection.model.security.AuthUser;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthUserNotFoundException;
import java.math.BigInteger;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserService extends UserDetailsService{

    public AuthUser findById(BigInteger id);

    public AuthUser findByUsername(String username);

    public AuthUser create(AuthUser emp);

    public AuthUser update(AuthUser emp) throws AuthUserNotFoundException;
    
    public AuthUser copy(AuthUser authUser);

    public AuthUser delete(BigInteger id) throws AuthUserNotFoundException;

    public void deleteByUsername(String username);

    public Iterable<AuthUser> search(_SearchDTO pageable);

    public Iterable<AuthUser> findAll(_SearchDTO pageable);

    public Iterable<AuthUser> findAll();

    public boolean isUsernameUnique(BigInteger id, String username);
}
