package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.proc.ProcOutAttnDaily;
import java.math.BigInteger;

public interface ProcOutAttnDailyRepo {

    public ProcOutAttnDaily findById(BigInteger id);

    public void save(ProcOutAttnDaily procOutAttnDaily);

    public List<ProcOutAttnDaily> findAll();

}
