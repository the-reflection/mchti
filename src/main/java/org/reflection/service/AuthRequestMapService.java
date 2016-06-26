package org.reflection.service;

import org.reflection.model.security.AuthRequestMap;
import org.reflection.exception.AuthRequestMapNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface AuthRequestMapService {



    public AuthRequestMap findById(BigInteger id);
    
    public AuthRequestMap create(AuthRequestMap authRequestMap);
    
    public AuthRequestMap update(AuthRequestMap authRequestMap) throws AuthRequestMapNotFoundException;
    
    public AuthRequestMap copy(AuthRequestMap authRequestMap);
    
    public AuthRequestMap delete(BigInteger id) throws AuthRequestMapNotFoundException;
   
    public Iterable<AuthRequestMap> search(_SearchDTO pageable);
    
    public Iterable<AuthRequestMap> findAll(_SearchDTO pageable);
    
    public Iterable<AuthRequestMap> findAll();
}