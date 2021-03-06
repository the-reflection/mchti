package org.reflection.service;

import org.reflection.model.hcm.cr.Designation;
import org.reflection.exception.DesignationNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface DesignationService {



    public Designation findById(BigInteger id);
    
    public Designation create(Designation designation);
    
    public Designation update(Designation designation) throws DesignationNotFoundException;
    
    public Designation copy(Designation designation);
    
    public Designation delete(BigInteger id) throws DesignationNotFoundException;
   
    public Iterable<Designation> search(_SearchDTO pageable);
    
    public Iterable<Designation> findAll(_SearchDTO pageable);
    
    public Iterable<Designation> findAll();
}