package org.reflection.service;

import org.reflection.model.hcm.cr.AssignmentHr;
import org.reflection.exception.AssignmentHrNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface AssignmentHrService {



    public AssignmentHr findById(BigInteger id);
    
    public AssignmentHr create(AssignmentHr assignmentHr);
    
    public AssignmentHr update(AssignmentHr assignmentHr) throws AssignmentHrNotFoundException;
    
    public AssignmentHr copy(AssignmentHr assignmentHr);
    
    public AssignmentHr delete(BigInteger id) throws AssignmentHrNotFoundException;
   
    public Iterable<AssignmentHr> search(_SearchDTO pageable);
    
    public Iterable<AssignmentHr> findAll(_SearchDTO pageable);
    
    public Iterable<AssignmentHr> findAll();
}