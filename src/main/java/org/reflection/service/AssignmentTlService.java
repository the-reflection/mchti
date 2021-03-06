package org.reflection.service;

import org.reflection.model.hcm.tl.AssignmentTl;
import org.reflection.exception.AssignmentTlNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface AssignmentTlService {



    public AssignmentTl findById(BigInteger id);
    
    public AssignmentTl create(AssignmentTl assignmentTl);
    
    public AssignmentTl update(AssignmentTl assignmentTl) throws AssignmentTlNotFoundException;
    
    public AssignmentTl copy(AssignmentTl assignmentTl);
    
    public AssignmentTl delete(BigInteger id) throws AssignmentTlNotFoundException;
   
    public Iterable<AssignmentTl> search(_SearchDTO pageable);
    
    public Iterable<AssignmentTl> findAll(_SearchDTO pageable);
    
    public Iterable<AssignmentTl> findAll();
}