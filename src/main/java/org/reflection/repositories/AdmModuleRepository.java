package org.reflection.repositories;

import org.reflection.model.com.AdmModule;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmModuleRepository extends JpaRepository<AdmModule, BigInteger> {

    public AdmModule findByCode(String code);

}
