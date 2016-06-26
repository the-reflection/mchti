package org.reflection.service;

import org.reflection.model.com.Transacsion;
import org.reflection.exception.TransacsionNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface TransacsionService {



    public Transacsion findById(BigInteger id);
    
    public Transacsion create(Transacsion transacsion);
    
    public Transacsion update(Transacsion transacsion) throws TransacsionNotFoundException;
    
    public Transacsion copy(Transacsion transacsion);
    
    public Transacsion delete(BigInteger id) throws TransacsionNotFoundException;
   
    public Iterable<Transacsion> search(_SearchDTO pageable);
    
    public Iterable<Transacsion> findAll(_SearchDTO pageable);
    
    public Iterable<Transacsion> findAll();
}