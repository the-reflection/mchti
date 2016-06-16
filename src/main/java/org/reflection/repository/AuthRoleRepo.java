package org.reflection.repository;

import java.util.List;
import org.reflection.model.security.AuthRole;
import java.math.BigInteger;

public interface AuthRoleRepo {

    public AuthRole findById(BigInteger id);

    public void save(AuthRole authRole);

    public List<AuthRole> findAll();

}
