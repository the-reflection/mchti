package org.reflection.service;

import org.reflection.model.hcm.tl.Shift;
import org.reflection.exception.ShiftNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface ShiftService {



    public Shift findById(BigInteger id);
    
    public Shift create(Shift shift);
    
    public Shift update(Shift shift) throws ShiftNotFoundException;
    
    public Shift copy(Shift shift);
    
    public Shift delete(BigInteger id) throws ShiftNotFoundException;
   
    public List<Shift> search(_SearchDTO pageable);
    
    public List<Shift> findAll(_SearchDTO pageable);
    
    public List<Shift> findAll();
}