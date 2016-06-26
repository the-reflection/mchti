package org.reflection.service;

import org.reflection.model.hcm.tl.Period;
import org.reflection.exception.PeriodNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface PeriodService {



    public Period findById(BigInteger id);
    
    public Period create(Period period);
    
    public Period update(Period period) throws PeriodNotFoundException;
    
    public Period copy(Period period);
    
    public Period delete(BigInteger id) throws PeriodNotFoundException;
   
    public Iterable<Period> search(_SearchDTO pageable);
    
    public Iterable<Period> findAll(_SearchDTO pageable);
    
    public Iterable<Period> findAll();
}