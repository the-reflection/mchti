package org.reflection.service;

import org.reflection.model.com.Lookup;
import org.reflection.exception.LookupNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface LookupService {



    public Lookup findById(BigInteger id);
    
    public Lookup create(Lookup lookup);
    
    public Lookup update(Lookup lookup) throws LookupNotFoundException;
    
    public Lookup copy(Lookup lookup);
    
    public Lookup delete(BigInteger id) throws LookupNotFoundException;
   
    public List<Lookup> search(_SearchDTO pageable);
    
    public List<Lookup> findAll(_SearchDTO pageable);
    
    public List<Lookup> findAll();
}