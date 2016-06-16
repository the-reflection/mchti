package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.tl.AssignmentTl;
import java.math.BigInteger;

public interface AssignmentTlRepo {

    public AssignmentTl findById(BigInteger id);

    public void save(AssignmentTl assignmentTl);

    public List<AssignmentTl> findAll();

}
