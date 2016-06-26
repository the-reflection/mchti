package org.reflection.service;

import org.reflection.model.security.AuthRole;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthRoleNotFoundException;
import org.reflection.repositories.AuthRoleRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authRoleService")
@Transactional(readOnly = true)
public class AuthRoleServiceImpl implements AuthRoleService {

    @Autowired
    private AuthRoleRepository authRoleRepository;



    @Transactional
    @Override
    public AuthRole create(AuthRole lookup) {
        return authRoleRepository.save(lookup);
    }

    @Override
    @Transactional
    public AuthRole findById(BigInteger id) {
        AuthRole authRole=authRoleRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return authRole;
    }

    @Override
    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    public AuthRole delete(BigInteger id) throws AuthRoleNotFoundException {

        AuthRole authRole = authRoleRepository.findOne(id);

        if (authRole == null) {
            throw new AuthRoleNotFoundException();
        }
        authRoleRepository.delete(id);
        return authRole;
    }

    @Override
    @Transactional
    public Iterable<AuthRole> findAll() {
        Iterable<AuthRole> authRoles=authRoleRepository.findAll();
        
        for (AuthRole authRole : authRoles) {

        //Hibernate.initialize(authRole.getA());
        //Hibernate.initialize(authRole.getZs());
        }
        
        return authRoles;
    }

    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    @Override
    public AuthRole update(AuthRole updated) throws AuthRoleNotFoundException {

        AuthRole authRole = authRoleRepository.findOne(updated.getId());

        if (authRole == null) {
            throw new AuthRoleNotFoundException();
        }

        BeanUtils.copyProperties(updated, authRole);
        return authRoleRepository.save(authRole);
    }

    @Transactional(rollbackFor = AuthRoleNotFoundException.class)
    @Override
    public AuthRole copy(final AuthRole copied) {

        AuthRole authRole = new AuthRole();
        BeanUtils.copyProperties(copied, authRole);
        authRole.setId(null);

        return authRoleRepository.save(authRole);
    }

    @Transactional
    @Override
    public Iterable<AuthRole> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AuthRole> search(_SearchDTO pageable) {
        return findAll();
    }
}