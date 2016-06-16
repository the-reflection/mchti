package org.reflection.service;

import org.reflection.model.hcm.proc.ProcOutAttnDaily;
import org.reflection.exception.ProcOutAttnDailyNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface ProcOutAttnDailyService {



    public ProcOutAttnDaily findById(BigInteger id);
    
    public ProcOutAttnDaily create(ProcOutAttnDaily procOutAttnDaily);
    
    public ProcOutAttnDaily update(ProcOutAttnDaily procOutAttnDaily) throws ProcOutAttnDailyNotFoundException;
    
    public ProcOutAttnDaily copy(ProcOutAttnDaily procOutAttnDaily);
    
    public ProcOutAttnDaily delete(BigInteger id) throws ProcOutAttnDailyNotFoundException;
   
    public List<ProcOutAttnDaily> search(_SearchDTO pageable);
    
    public List<ProcOutAttnDaily> findAll(_SearchDTO pageable);
    
    public List<ProcOutAttnDaily> findAll();
}