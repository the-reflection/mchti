package org.reflection.repositories;

import org.reflection.model.hcm.tl.GeneralHoliday;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralHolidayRepository extends JpaRepository<GeneralHoliday, BigInteger> {

}
