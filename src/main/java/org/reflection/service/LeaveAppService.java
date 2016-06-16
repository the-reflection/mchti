package org.reflection.service;

import org.reflection.model.hcm.tl.LeaveApp;
import org.reflection.exception.LeaveAppNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface LeaveAppService {



    public LeaveApp findById(BigInteger id);
    
    public LeaveApp create(LeaveApp leaveApp);
    
    public LeaveApp update(LeaveApp leaveApp) throws LeaveAppNotFoundException;
    
    public LeaveApp copy(LeaveApp leaveApp);
    
    public LeaveApp delete(BigInteger id) throws LeaveAppNotFoundException;
   
    public List<LeaveApp> search(_SearchDTO pageable);
    
    public List<LeaveApp> findAll(_SearchDTO pageable);
    
    public List<LeaveApp> findAll();
}