package org.reflection.service;

import org.reflection.model.security.AuthMenu;
import org.reflection.exception.AuthMenuNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface AuthMenuService {

    public AuthMenu findById(BigInteger id);
    
    public AuthMenu create(AuthMenu authMenu);
    
    public AuthMenu update(AuthMenu authMenu) throws AuthMenuNotFoundException;
    
    public AuthMenu copy(AuthMenu authMenu);
    
    public AuthMenu delete(BigInteger id) throws AuthMenuNotFoundException;
   
    public List<AuthMenu> search(_SearchDTO pageable);
    
    public List<AuthMenu> findAll(_SearchDTO pageable);
    
    public List<AuthMenu> findAll();
}