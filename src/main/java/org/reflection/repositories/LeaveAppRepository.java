package org.reflection.repositories;

import org.reflection.model.hcm.tl.LeaveApp;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveAppRepository extends JpaRepository<LeaveApp, BigInteger> {

}
