package org.reflection.service;

import org.reflection.model.com.Lookup;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.LookupNotFoundException;
import org.reflection.repositories.LookupRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("lookupService")
@Transactional(readOnly = true)
public class LookupServiceImpl implements LookupService {

    @Autowired
    private LookupRepository lookupRepository;



    @Transactional
    @Override
    public Lookup create(Lookup lookup) {
        return lookupRepository.save(lookup);
    }

    @Override
    @Transactional
    public Lookup findById(BigInteger id) {
        Lookup lookup=lookupRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return lookup;
    }

    @Override
    @Transactional(rollbackFor = LookupNotFoundException.class)
    public Lookup delete(BigInteger id) throws LookupNotFoundException {

        Lookup lookup = lookupRepository.findOne(id);

        if (lookup == null) {
            throw new LookupNotFoundException();
        }
        lookupRepository.delete(id);
        return lookup;
    }

    @Override
    @Transactional
    public Iterable<Lookup> findAll() {
        Iterable<Lookup> lookups=lookupRepository.findAll();
        
        for (Lookup lookup : lookups) {

        //Hibernate.initialize(lookup.getA());
        //Hibernate.initialize(lookup.getZs());
        }
        
        return lookups;
    }

    @Transactional(rollbackFor = LookupNotFoundException.class)
    @Override
    public Lookup update(Lookup updated) throws LookupNotFoundException {

        Lookup lookup = lookupRepository.findOne(updated.getId());

        if (lookup == null) {
            throw new LookupNotFoundException();
        }

        BeanUtils.copyProperties(updated, lookup);
        return lookupRepository.save(lookup);
    }

    @Transactional(rollbackFor = LookupNotFoundException.class)
    @Override
    public Lookup copy(final Lookup copied) {

        Lookup lookup = new Lookup();
        BeanUtils.copyProperties(copied, lookup);
        lookup.setId(null);

        return lookupRepository.save(lookup);
    }

    @Transactional
    @Override
    public Iterable<Lookup> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Lookup> search(_SearchDTO pageable) {
        return findAll();
    }
}