package org.reflection.repository;

import java.util.List;
import org.reflection.model.hcm.tl.LeaveApp;
import java.math.BigInteger;

public interface LeaveAppRepo {

    public LeaveApp findById(BigInteger id);

    public void save(LeaveApp leaveApp);

    public List<LeaveApp> findAll();

}
