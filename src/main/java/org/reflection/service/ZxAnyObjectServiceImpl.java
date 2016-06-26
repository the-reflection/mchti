package org.reflection.service;

import org.reflection.model.sample.ZxAnyObject;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxAnyObjectNotFoundException;
import org.reflection.repositories.ZxAnyObjectRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("zxAnyObjectService")
@Transactional(readOnly = true)
public class ZxAnyObjectServiceImpl implements ZxAnyObjectService {

    @Autowired
    private ZxAnyObjectRepository zxAnyObjectRepository;



    @Transactional
    @Override
    public ZxAnyObject create(ZxAnyObject lookup) {
        return zxAnyObjectRepository.save(lookup);
    }

    @Override
    @Transactional
    public ZxAnyObject findById(BigInteger id) {
        ZxAnyObject zxAnyObject=zxAnyObjectRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return zxAnyObject;
    }

    @Override
    @Transactional(rollbackFor = ZxAnyObjectNotFoundException.class)
    public ZxAnyObject delete(BigInteger id) throws ZxAnyObjectNotFoundException {

        ZxAnyObject zxAnyObject = zxAnyObjectRepository.findOne(id);

        if (zxAnyObject == null) {
            throw new ZxAnyObjectNotFoundException();
        }
        zxAnyObjectRepository.delete(id);
        return zxAnyObject;
    }

    @Override
    @Transactional
    public Iterable<ZxAnyObject> findAll() {
        Iterable<ZxAnyObject> zxAnyObjects=zxAnyObjectRepository.findAll();
        
        for (ZxAnyObject zxAnyObject : zxAnyObjects) {

        //Hibernate.initialize(zxAnyObject.getA());
        //Hibernate.initialize(zxAnyObject.getZs());
        }
        
        return zxAnyObjects;
    }

    @Transactional(rollbackFor = ZxAnyObjectNotFoundException.class)
    @Override
    public ZxAnyObject update(ZxAnyObject updated) throws ZxAnyObjectNotFoundException {

        ZxAnyObject zxAnyObject = zxAnyObjectRepository.findOne(updated.getId());

        if (zxAnyObject == null) {
            throw new ZxAnyObjectNotFoundException();
        }

        BeanUtils.copyProperties(updated, zxAnyObject);
        return zxAnyObjectRepository.save(zxAnyObject);
    }

    @Transactional(rollbackFor = ZxAnyObjectNotFoundException.class)
    @Override
    public ZxAnyObject copy(final ZxAnyObject copied) {

        ZxAnyObject zxAnyObject = new ZxAnyObject();
        BeanUtils.copyProperties(copied, zxAnyObject);
        zxAnyObject.setId(null);

        return zxAnyObjectRepository.save(zxAnyObject);
    }

    @Transactional
    @Override
    public Iterable<ZxAnyObject> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxAnyObject> search(_SearchDTO pageable) {
        return findAll();
    }
}