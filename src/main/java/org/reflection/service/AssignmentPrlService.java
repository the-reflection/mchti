package org.reflection.service;

import org.reflection.model.hcm.prl.AssignmentPrl;
import org.reflection.exception.AssignmentPrlNotFoundException;
import org.reflection.dto._SearchDTO;
import java.math.BigInteger;

public interface AssignmentPrlService {



    public AssignmentPrl findById(BigInteger id);
    
    public AssignmentPrl create(AssignmentPrl assignmentPrl);
    
    public AssignmentPrl update(AssignmentPrl assignmentPrl) throws AssignmentPrlNotFoundException;
    
    public AssignmentPrl copy(AssignmentPrl assignmentPrl);
    
    public AssignmentPrl delete(BigInteger id) throws AssignmentPrlNotFoundException;
   
    public Iterable<AssignmentPrl> search(_SearchDTO pageable);
    
    public Iterable<AssignmentPrl> findAll(_SearchDTO pageable);
    
    public Iterable<AssignmentPrl> findAll();
}