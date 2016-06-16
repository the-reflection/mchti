package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.proc.ProcOutAttnDt;
import java.math.BigInteger;

public interface ProcOutAttnDtRepo {

    public ProcOutAttnDt findById(BigInteger id);

    public void save(ProcOutAttnDt procOutAttnDt);

    public List<ProcOutAttnDt> findAll();

}
