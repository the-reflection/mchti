package org.reflection.service;

import org.reflection.model.hcm.tl.LeaveApp;
import org.reflection.exception.LeaveAppNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface LeaveAppService {



    public LeaveApp findById(BigInteger id);
    
    public LeaveApp create(LeaveApp leaveApp);
    
    public LeaveApp update(LeaveApp leaveApp) throws LeaveAppNotFoundException;
    
    public LeaveApp copy(LeaveApp leaveApp);
    
    public LeaveApp delete(BigInteger id) throws LeaveAppNotFoundException;
   
    public Iterable<LeaveApp> search(_SearchDTO pageable);
    
    public Iterable<LeaveApp> findAll(_SearchDTO pageable);
    
    public Iterable<LeaveApp> findAll();
}