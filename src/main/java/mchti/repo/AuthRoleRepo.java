package mchti.repo;

import java.util.List;

import mchti.model.security.AuthRole;

public interface AuthRoleRepo {

    List<AuthRole> findAll();

    AuthRole findByAuthority(String authority);

    AuthRole findById(Long id);
}
