package org.reflection.service;

import org.reflection.model.security.AuthRole;
import org.reflection.exception.AuthRoleNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface AuthRoleService {



    public AuthRole findById(BigInteger id);
    
    public AuthRole create(AuthRole authRole);
    
    public AuthRole update(AuthRole authRole) throws AuthRoleNotFoundException;
    
    public AuthRole copy(AuthRole authRole);
    
    public AuthRole delete(BigInteger id) throws AuthRoleNotFoundException;
   
    public List<AuthRole> search(_SearchDTO pageable);
    
    public List<AuthRole> findAll(_SearchDTO pageable);
    
    public List<AuthRole> findAll();
}