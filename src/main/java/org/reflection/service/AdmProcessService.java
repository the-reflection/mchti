package org.reflection.service;

import org.reflection.model.com.AdmProcess;
import org.reflection.exception.AdmProcessNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface AdmProcessService {



    public AdmProcess findById(BigInteger id);
    
    public AdmProcess create(AdmProcess admProcess);
    
    public AdmProcess update(AdmProcess admProcess) throws AdmProcessNotFoundException;
    
    public AdmProcess copy(AdmProcess admProcess);
    
    public AdmProcess delete(BigInteger id) throws AdmProcessNotFoundException;
   
    public Iterable<AdmProcess> search(_SearchDTO pageable);
    
    public Iterable<AdmProcess> findAll(_SearchDTO pageable);
    
    public Iterable<AdmProcess> findAll();
}