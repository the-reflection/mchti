package org.reflection.service;

import org.reflection.model.hcm.tl.AssignmentTl;
import org.reflection.exception.AssignmentTlNotFoundException;
import org.reflection.dto._SearchDTO;
import java.util.List;
import java.math.BigInteger;

public interface AssignmentTlService {



    public AssignmentTl findById(BigInteger id);
    
    public AssignmentTl create(AssignmentTl assignmentTl);
    
    public AssignmentTl update(AssignmentTl assignmentTl) throws AssignmentTlNotFoundException;
    
    public AssignmentTl copy(AssignmentTl assignmentTl);
    
    public AssignmentTl delete(BigInteger id) throws AssignmentTlNotFoundException;
   
    public List<AssignmentTl> search(_SearchDTO pageable);
    
    public List<AssignmentTl> findAll(_SearchDTO pageable);
    
    public List<AssignmentTl> findAll();
}