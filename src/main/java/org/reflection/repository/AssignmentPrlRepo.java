package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.prl.AssignmentPrl;
import java.math.BigInteger;

public interface AssignmentPrlRepo {

    public AssignmentPrl findById(BigInteger id);

    public void save(AssignmentPrl assignmentPrl);

    public List<AssignmentPrl> findAll();

}
