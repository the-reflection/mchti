package org.reflection.service;

import org.reflection.model.hcm.tl.Period;
import org.reflection.exception.PeriodNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface PeriodService {



    public Period findById(BigInteger id);
    
    public Period create(Period period);
    
    public Period update(Period period) throws PeriodNotFoundException;
    
    public Period copy(Period period);
    
    public Period delete(BigInteger id) throws PeriodNotFoundException;
   
    public List<Period> search(_SearchDTO pageable);
    
    public List<Period> findAll(_SearchDTO pageable);
    
    public List<Period> findAll();
}