package org.reflection.service;

import org.reflection.model.com.AdmModule;
import org.reflection.exception.AdmModuleNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface AdmModuleService {

    public AdmModule findByCode(String code);

    public AdmModule findById(BigInteger id);
    
    public AdmModule create(AdmModule admModule);
    
    public AdmModule update(AdmModule admModule) throws AdmModuleNotFoundException;
    
    public AdmModule copy(AdmModule admModule);
    
    public AdmModule delete(BigInteger id) throws AdmModuleNotFoundException;
   
    public Iterable<AdmModule> search(_SearchDTO pageable);
    
    public Iterable<AdmModule> findAll(_SearchDTO pageable);
    
    public Iterable<AdmModule> findAll();
}