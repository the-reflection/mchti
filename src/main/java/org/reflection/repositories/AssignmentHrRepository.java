package org.reflection.repositories;

import org.reflection.model.hcm.cr.AssignmentHr;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentHrRepository extends JpaRepository<AssignmentHr, BigInteger> {

}
