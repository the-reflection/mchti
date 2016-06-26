package org.reflection.service;

import org.reflection.model.security.AuthRequestMap;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthRequestMapNotFoundException;
import org.reflection.repositories.AuthRequestMapRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("authRequestMapService")
@Transactional(readOnly = true)
public class AuthRequestMapServiceImpl implements AuthRequestMapService {

    @Autowired
    private AuthRequestMapRepository authRequestMapRepository;



    @Transactional
    @Override
    public AuthRequestMap create(AuthRequestMap lookup) {
        return authRequestMapRepository.save(lookup);
    }

    @Override
    @Transactional
    public AuthRequestMap findById(BigInteger id) {
        AuthRequestMap authRequestMap=authRequestMapRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return authRequestMap;
    }

    @Override
    @Transactional(rollbackFor = AuthRequestMapNotFoundException.class)
    public AuthRequestMap delete(BigInteger id) throws AuthRequestMapNotFoundException {

        AuthRequestMap authRequestMap = authRequestMapRepository.findOne(id);

        if (authRequestMap == null) {
            throw new AuthRequestMapNotFoundException();
        }
        authRequestMapRepository.delete(id);
        return authRequestMap;
    }

    @Override
    @Transactional
    public Iterable<AuthRequestMap> findAll() {
        Iterable<AuthRequestMap> authRequestMaps=authRequestMapRepository.findAll();
        
        for (AuthRequestMap authRequestMap : authRequestMaps) {

        //Hibernate.initialize(authRequestMap.getA());
        //Hibernate.initialize(authRequestMap.getZs());
        }
        
        return authRequestMaps;
    }

    @Transactional(rollbackFor = AuthRequestMapNotFoundException.class)
    @Override
    public AuthRequestMap update(AuthRequestMap updated) throws AuthRequestMapNotFoundException {

        AuthRequestMap authRequestMap = authRequestMapRepository.findOne(updated.getId());

        if (authRequestMap == null) {
            throw new AuthRequestMapNotFoundException();
        }

        BeanUtils.copyProperties(updated, authRequestMap);
        return authRequestMapRepository.save(authRequestMap);
    }

    @Transactional(rollbackFor = AuthRequestMapNotFoundException.class)
    @Override
    public AuthRequestMap copy(final AuthRequestMap copied) {

        AuthRequestMap authRequestMap = new AuthRequestMap();
        BeanUtils.copyProperties(copied, authRequestMap);
        authRequestMap.setId(null);

        return authRequestMapRepository.save(authRequestMap);
    }

    @Transactional
    @Override
    public Iterable<AuthRequestMap> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AuthRequestMap> search(_SearchDTO pageable) {
        return findAll();
    }
}