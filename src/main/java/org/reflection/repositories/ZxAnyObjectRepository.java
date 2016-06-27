package org.reflection.repositories;

import org.reflection.model.sample.ZxAnyObject;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZxAnyObjectRepository extends JpaRepository<ZxAnyObject, BigInteger> {

}
