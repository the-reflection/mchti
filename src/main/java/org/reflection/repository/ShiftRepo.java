package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.tl.Shift;
import java.math.BigInteger;

public interface ShiftRepo {

    public Shift findById(BigInteger id);

    public void save(Shift shift);

    public List<Shift> findAll();

}
