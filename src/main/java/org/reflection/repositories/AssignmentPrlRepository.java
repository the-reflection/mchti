package org.reflection.repositories;

import org.reflection.model.hcm.prl.AssignmentPrl;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentPrlRepository extends JpaRepository<AssignmentPrl, BigInteger> {

}
