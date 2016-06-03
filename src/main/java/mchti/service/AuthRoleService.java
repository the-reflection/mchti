package mchti.service;

import mchti.dto._SearchDTO;
import mchti.exception.AuthRoleNotFoundException;
import mchti.model.security.AuthRole;
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