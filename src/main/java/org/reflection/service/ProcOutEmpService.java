package org.reflection.service;

import org.reflection.model.hcm.proc.ProcOutEmp;
import org.reflection.exception.ProcOutEmpNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface ProcOutEmpService {



    public ProcOutEmp findById(BigInteger id);
    
    public ProcOutEmp create(ProcOutEmp procOutEmp);
    
    public ProcOutEmp update(ProcOutEmp procOutEmp) throws ProcOutEmpNotFoundException;
    
    public ProcOutEmp copy(ProcOutEmp procOutEmp);
    
    public ProcOutEmp delete(BigInteger id) throws ProcOutEmpNotFoundException;
   
    public List<ProcOutEmp> search(_SearchDTO pageable);
    
    public List<ProcOutEmp> findAll(_SearchDTO pageable);
    
    public List<ProcOutEmp> findAll();
}