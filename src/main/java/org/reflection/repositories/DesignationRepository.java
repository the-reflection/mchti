package org.reflection.repositories;

import org.reflection.model.hcm.cr.Designation;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository extends JpaRepository<Designation, BigInteger> {

}
