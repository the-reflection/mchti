package org.reflection.service;

import org.reflection.model.sample.ZxEmpEduDtl;
import org.reflection.exception.ZxEmpEduDtlNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface ZxEmpEduDtlService {



    public ZxEmpEduDtl findById(BigInteger id);
    
    public ZxEmpEduDtl create(ZxEmpEduDtl zxEmpEduDtl);
    
    public ZxEmpEduDtl update(ZxEmpEduDtl zxEmpEduDtl) throws ZxEmpEduDtlNotFoundException;
    
    public ZxEmpEduDtl copy(ZxEmpEduDtl zxEmpEduDtl);
    
    public ZxEmpEduDtl delete(BigInteger id) throws ZxEmpEduDtlNotFoundException;
   
    public Iterable<ZxEmpEduDtl> search(_SearchDTO pageable);
    
    public Iterable<ZxEmpEduDtl> findAll(_SearchDTO pageable);
    
    public Iterable<ZxEmpEduDtl> findAll();
}