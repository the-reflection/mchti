package org.reflection.repositories;

import org.reflection.model.hcm.tl.Roster;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RosterRepository extends JpaRepository<Roster, BigInteger> {

}
