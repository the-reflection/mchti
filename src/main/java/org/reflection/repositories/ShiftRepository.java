package org.reflection.repositories;

import org.reflection.model.hcm.tl.Shift;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, BigInteger> {

}
