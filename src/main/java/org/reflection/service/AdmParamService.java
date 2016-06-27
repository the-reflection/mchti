package org.reflection.service;

import org.reflection.model.com.AdmParam;
import org.reflection.exception.AdmParamNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface AdmParamService {



    public AdmParam findById(BigInteger id);
    
    public AdmParam create(AdmParam admParam);
    
    public AdmParam update(AdmParam admParam) throws AdmParamNotFoundException;
    
    public AdmParam copy(AdmParam admParam);
    
    public AdmParam delete(BigInteger id) throws AdmParamNotFoundException;
   
    public Iterable<AdmParam> search(_SearchDTO pageable);
    
    public Iterable<AdmParam> findAll(_SearchDTO pageable);
    
    public Iterable<AdmParam> findAll();
}