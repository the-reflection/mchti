package org.reflection.repositories;

import org.reflection.model.com.Transacsion;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacsionRepository extends JpaRepository<Transacsion, BigInteger> {

}
