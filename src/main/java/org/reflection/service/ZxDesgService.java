package org.reflection.service;

import org.reflection.model.sample.ZxDesg;
import org.reflection.exception.ZxDesgNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface ZxDesgService {



    public ZxDesg findById(BigInteger id);
    
    public ZxDesg create(ZxDesg zxDesg);
    
    public ZxDesg update(ZxDesg zxDesg) throws ZxDesgNotFoundException;
    
    public ZxDesg copy(ZxDesg zxDesg);
    
    public ZxDesg delete(BigInteger id) throws ZxDesgNotFoundException;
   
    public Iterable<ZxDesg> search(_SearchDTO pageable);
    
    public Iterable<ZxDesg> findAll(_SearchDTO pageable);
    
    public Iterable<ZxDesg> findAll();
}