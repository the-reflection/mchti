package org.reflection.service;

import org.reflection.model.sample.ZxDept;
import org.reflection.exception.ZxDeptNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface ZxDeptService {



    public ZxDept findById(BigInteger id);
    
    public ZxDept create(ZxDept zxDept);
    
    public ZxDept update(ZxDept zxDept) throws ZxDeptNotFoundException;
    
    public ZxDept copy(ZxDept zxDept);
    
    public ZxDept delete(BigInteger id) throws ZxDeptNotFoundException;
   
    public Iterable<ZxDept> search(_SearchDTO pageable);
    
    public Iterable<ZxDept> findAll(_SearchDTO pageable);
    
    public Iterable<ZxDept> findAll();
}