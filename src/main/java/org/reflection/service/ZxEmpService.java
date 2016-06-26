package org.reflection.service;

import org.reflection.model.sample.ZxEmp;
import org.reflection.exception.ZxEmpNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface ZxEmpService {

    public ZxEmp findByCode(String code);

    public ZxEmp findById(BigInteger id);
    
    public ZxEmp create(ZxEmp zxEmp);
    
    public ZxEmp update(ZxEmp zxEmp) throws ZxEmpNotFoundException;
    
    public ZxEmp copy(ZxEmp zxEmp);
    
    public ZxEmp delete(BigInteger id) throws ZxEmpNotFoundException;
   
    public Iterable<ZxEmp> search(_SearchDTO pageable);
    
    public Iterable<ZxEmp> findAll(_SearchDTO pageable);
    
    public Iterable<ZxEmp> findAll();
}