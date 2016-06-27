package org.reflection.repositories;

import org.reflection.model.sample.ZxLookup;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZxLookupRepository extends JpaRepository<ZxLookup, BigInteger> {

}
