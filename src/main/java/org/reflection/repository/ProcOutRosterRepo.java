package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.proc.ProcOutRoster;
import java.math.BigInteger;

public interface ProcOutRosterRepo {

    public ProcOutRoster findById(BigInteger id);

    public void save(ProcOutRoster procOutRoster);

    public List<ProcOutRoster> findAll();

}
