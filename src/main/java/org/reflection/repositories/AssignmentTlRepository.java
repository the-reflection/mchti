package org.reflection.repositories;

import org.reflection.model.hcm.tl.AssignmentTl;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentTlRepository extends JpaRepository<AssignmentTl, BigInteger> {

}
