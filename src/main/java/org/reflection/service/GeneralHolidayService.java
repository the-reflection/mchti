package org.reflection.service;

import org.reflection.model.hcm.tl.GeneralHoliday;
import org.reflection.exception.GeneralHolidayNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface GeneralHolidayService {



    public GeneralHoliday findById(BigInteger id);
    
    public GeneralHoliday create(GeneralHoliday generalHoliday);
    
    public GeneralHoliday update(GeneralHoliday generalHoliday) throws GeneralHolidayNotFoundException;
    
    public GeneralHoliday copy(GeneralHoliday generalHoliday);
    
    public GeneralHoliday delete(BigInteger id) throws GeneralHolidayNotFoundException;
   
    public Iterable<GeneralHoliday> search(_SearchDTO pageable);
    
    public Iterable<GeneralHoliday> findAll(_SearchDTO pageable);
    
    public Iterable<GeneralHoliday> findAll();
}