package org.reflection.service;

import org.reflection.model.sample.ZxLookup;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxLookupNotFoundException;
import org.reflection.repositories.ZxLookupRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("zxLookupService")
@Transactional(readOnly = true)
public class ZxLookupServiceImpl implements ZxLookupService {

    @Autowired
    private ZxLookupRepository zxLookupRepository;



    @Transactional
    @Override
    public ZxLookup create(ZxLookup lookup) {
        return zxLookupRepository.save(lookup);
    }

    @Override
    @Transactional
    public ZxLookup findById(BigInteger id) {
        ZxLookup zxLookup=zxLookupRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return zxLookup;
    }

    @Override
    @Transactional(rollbackFor = ZxLookupNotFoundException.class)
    public ZxLookup delete(BigInteger id) throws ZxLookupNotFoundException {

        ZxLookup zxLookup = zxLookupRepository.findOne(id);

        if (zxLookup == null) {
            throw new ZxLookupNotFoundException();
        }
        zxLookupRepository.delete(id);
        return zxLookup;
    }

    @Override
    @Transactional
    public Iterable<ZxLookup> findAll() {
        Iterable<ZxLookup> zxLookups=zxLookupRepository.findAll();
        
        for (ZxLookup zxLookup : zxLookups) {

        //Hibernate.initialize(zxLookup.getA());
        //Hibernate.initialize(zxLookup.getZs());
        }
        
        return zxLookups;
    }

    @Transactional(rollbackFor = ZxLookupNotFoundException.class)
    @Override
    public ZxLookup update(ZxLookup updated) throws ZxLookupNotFoundException {

        ZxLookup zxLookup = zxLookupRepository.findOne(updated.getId());

        if (zxLookup == null) {
            throw new ZxLookupNotFoundException();
        }

        BeanUtils.copyProperties(updated, zxLookup);
        return zxLookupRepository.save(zxLookup);
    }

    @Transactional(rollbackFor = ZxLookupNotFoundException.class)
    @Override
    public ZxLookup copy(final ZxLookup copied) {

        ZxLookup zxLookup = new ZxLookup();
        BeanUtils.copyProperties(copied, zxLookup);
        zxLookup.setId(null);

        return zxLookupRepository.save(zxLookup);
    }

    @Transactional
    @Override
    public Iterable<ZxLookup> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxLookup> search(_SearchDTO pageable) {
        return findAll();
    }
}