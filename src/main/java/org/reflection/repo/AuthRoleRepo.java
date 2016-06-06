package org.reflection.repo;

import java.util.List;

import org.reflection.model.security.AuthRole;

public interface AuthRoleRepo {

    List<AuthRole> findAll();

    AuthRole findByAuthority(String authority);

    AuthRole findById(Long id);
}
