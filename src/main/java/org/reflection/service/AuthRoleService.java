package org.reflection.service;

import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthRoleNotFoundException;
import org.reflection.model.security.AuthRole;
import java.util.List;

public interface AuthRoleService {

    public AuthRole findById(Long id);
    
    public AuthRole create(AuthRole emp);
    
    public AuthRole update(AuthRole emp) throws AuthRoleNotFoundException;
    
    public AuthRole delete(Long id) throws AuthRoleNotFoundException;
   
    public List<AuthRole> search(_SearchDTO pageable);
    
    public List<AuthRole> findAll(_SearchDTO pageable);
    
    public List<AuthRole> findAll();
}