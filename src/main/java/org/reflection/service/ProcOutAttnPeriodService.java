package org.reflection.service;

import org.reflection.model.hcm.proc.ProcOutAttnPeriod;
import org.reflection.exception.ProcOutAttnPeriodNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface ProcOutAttnPeriodService {



    public ProcOutAttnPeriod findById(BigInteger id);
    
    public ProcOutAttnPeriod create(ProcOutAttnPeriod procOutAttnPeriod);
    
    public ProcOutAttnPeriod update(ProcOutAttnPeriod procOutAttnPeriod) throws ProcOutAttnPeriodNotFoundException;
    
    public ProcOutAttnPeriod copy(ProcOutAttnPeriod procOutAttnPeriod);
    
    public ProcOutAttnPeriod delete(BigInteger id) throws ProcOutAttnPeriodNotFoundException;
   
    public Iterable<ProcOutAttnPeriod> search(_SearchDTO pageable);
    
    public Iterable<ProcOutAttnPeriod> findAll(_SearchDTO pageable);
    
    public Iterable<ProcOutAttnPeriod> findAll();
}