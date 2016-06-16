package org.reflection.service;

import org.reflection.model.hcm.proc.ProcOutCalender;
import org.reflection.exception.ProcOutCalenderNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface ProcOutCalenderService {



    public ProcOutCalender findById(BigInteger id);
    
    public ProcOutCalender create(ProcOutCalender procOutCalender);
    
    public ProcOutCalender update(ProcOutCalender procOutCalender) throws ProcOutCalenderNotFoundException;
    
    public ProcOutCalender copy(ProcOutCalender procOutCalender);
    
    public ProcOutCalender delete(BigInteger id) throws ProcOutCalenderNotFoundException;
   
    public List<ProcOutCalender> search(_SearchDTO pageable);
    
    public List<ProcOutCalender> findAll(_SearchDTO pageable);
    
    public List<ProcOutCalender> findAll();
}