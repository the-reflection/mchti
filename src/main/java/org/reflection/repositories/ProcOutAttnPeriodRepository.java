package org.reflection.repositories;

import org.reflection.model.hcm.proc.ProcOutAttnPeriod;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcOutAttnPeriodRepository extends JpaRepository<ProcOutAttnPeriod, BigInteger> {

}
