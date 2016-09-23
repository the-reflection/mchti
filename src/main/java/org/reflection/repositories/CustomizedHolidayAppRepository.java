package org.reflection.repositories;

import java.math.BigInteger;
import org.reflection.model.hcm.tl.CustomizedHolidayApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizedHolidayAppRepository extends JpaRepository<CustomizedHolidayApp, BigInteger> {

}
