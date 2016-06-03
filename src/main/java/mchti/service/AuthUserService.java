package mchti.service;

import mchti.model.security.AuthUser;

import mchti.dto._SearchDTO;
import mchti.exception.AuthUserNotFoundException;
import java.math.BigInteger;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserService extends UserDetailsService{

    public AuthUser findById(BigInteger id);

    public AuthUser findByUsername(String username);

    public AuthUser create(AuthUser emp);

    public AuthUser update(AuthUser emp) throws AuthUserNotFoundException;

    public AuthUser delete(BigInteger id) throws AuthUserNotFoundException;

    public void deleteByUsername(String username);

    public List<AuthUser> search(_SearchDTO pageable);

    public List<AuthUser> findAll(_SearchDTO pageable);

    public List<AuthUser> findAll();

    public boolean isUsernameUnique(BigInteger id, String username);
}
