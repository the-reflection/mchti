package org.reflection.service;

import org.reflection.model.hcm.proc.ProcOutRoster;
import org.reflection.exception.ProcOutRosterNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface ProcOutRosterService {



    public ProcOutRoster findById(BigInteger id);
    
    public ProcOutRoster create(ProcOutRoster procOutRoster);
    
    public ProcOutRoster update(ProcOutRoster procOutRoster) throws ProcOutRosterNotFoundException;
    
    public ProcOutRoster copy(ProcOutRoster procOutRoster);
    
    public ProcOutRoster delete(BigInteger id) throws ProcOutRosterNotFoundException;
   
    public List<ProcOutRoster> search(_SearchDTO pageable);
    
    public List<ProcOutRoster> findAll(_SearchDTO pageable);
    
    public List<ProcOutRoster> findAll();
}