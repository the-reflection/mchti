package org.reflection.repository;

import java.util.List;
import org.reflection.model.com.Lookup;
import java.math.BigInteger;

public interface LookupRepo {

    public Lookup findById(BigInteger id);

    public void save(Lookup lookup);

    public List<Lookup> findAll();

}
