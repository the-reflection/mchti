package org.reflection.service;

import org.reflection.model.hcm.tl.ManualAttnDaily;
import org.reflection.exception.ManualAttnDailyNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface ManualAttnDailyService {



    public ManualAttnDaily findById(BigInteger id);
    
    public ManualAttnDaily create(ManualAttnDaily manualAttnDaily);
    
    public ManualAttnDaily update(ManualAttnDaily manualAttnDaily) throws ManualAttnDailyNotFoundException;
    
    public ManualAttnDaily copy(ManualAttnDaily manualAttnDaily);
    
    public ManualAttnDaily delete(BigInteger id) throws ManualAttnDailyNotFoundException;
   
    public Iterable<ManualAttnDaily> search(_SearchDTO pageable);
    
    public Iterable<ManualAttnDaily> findAll(_SearchDTO pageable);
    
    public Iterable<ManualAttnDaily> findAll();
}