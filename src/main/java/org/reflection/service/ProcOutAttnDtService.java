package org.reflection.service;

import org.reflection.model.hcm.proc.ProcOutAttnDt;
import org.reflection.exception.ProcOutAttnDtNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface ProcOutAttnDtService {



    public ProcOutAttnDt findById(BigInteger id);
    
    public ProcOutAttnDt create(ProcOutAttnDt procOutAttnDt);
    
    public ProcOutAttnDt update(ProcOutAttnDt procOutAttnDt) throws ProcOutAttnDtNotFoundException;
    
    public ProcOutAttnDt copy(ProcOutAttnDt procOutAttnDt);
    
    public ProcOutAttnDt delete(BigInteger id) throws ProcOutAttnDtNotFoundException;
   
    public List<ProcOutAttnDt> search(_SearchDTO pageable);
    
    public List<ProcOutAttnDt> findAll(_SearchDTO pageable);
    
    public List<ProcOutAttnDt> findAll();
}