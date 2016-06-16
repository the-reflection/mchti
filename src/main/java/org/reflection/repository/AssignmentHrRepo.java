package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.cr.AssignmentHr;
import java.math.BigInteger;

public interface AssignmentHrRepo {

    public AssignmentHr findById(BigInteger id);

    public void save(AssignmentHr assignmentHr);

    public List<AssignmentHr> findAll();

}
