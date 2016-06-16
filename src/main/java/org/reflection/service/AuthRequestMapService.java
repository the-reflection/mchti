package org.reflection.service;

import org.reflection.model.security.AuthRequestMap;
import org.reflection.exception.AuthRequestMapNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface AuthRequestMapService {



    public AuthRequestMap findById(BigInteger id);
    
    public AuthRequestMap create(AuthRequestMap authRequestMap);
    
    public AuthRequestMap update(AuthRequestMap authRequestMap) throws AuthRequestMapNotFoundException;
    
    public AuthRequestMap copy(AuthRequestMap authRequestMap);
    
    public AuthRequestMap delete(BigInteger id) throws AuthRequestMapNotFoundException;
   
    public List<AuthRequestMap> search(_SearchDTO pageable);
    
    public List<AuthRequestMap> findAll(_SearchDTO pageable);
    
    public List<AuthRequestMap> findAll();
}