package org.reflection.service;

import org.reflection.model.hcm.tl.CustomizedHolidayApp;
import org.reflection.exception.CustomizedHolidayAppNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface CustomizedHolidayAppService {



    public CustomizedHolidayApp findById(BigInteger id);
    
    public CustomizedHolidayApp create(CustomizedHolidayApp customizedHolidayApp);
    
    public CustomizedHolidayApp update(CustomizedHolidayApp customizedHolidayApp) throws CustomizedHolidayAppNotFoundException;
    
    public CustomizedHolidayApp copy(CustomizedHolidayApp customizedHolidayApp);
    
    public CustomizedHolidayApp delete(BigInteger id) throws CustomizedHolidayAppNotFoundException;
   
    public Iterable<CustomizedHolidayApp> search(_SearchDTO pageable);
    
    public Iterable<CustomizedHolidayApp> findAll(_SearchDTO pageable);
    
    public Iterable<CustomizedHolidayApp> findAll();
}