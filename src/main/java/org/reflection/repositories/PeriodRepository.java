package org.reflection.repositories;

import org.reflection.model.hcm.tl.Period;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepository extends JpaRepository<Period, BigInteger> {

}
