package org.reflection.service;

import org.reflection.model.sample.ZxDesg;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxDesgNotFoundException;
import org.reflection.repositories.ZxDesgRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("zxDesgService")
@Transactional(readOnly = true)
public class ZxDesgServiceImpl implements ZxDesgService {

    @Autowired
    private ZxDesgRepository zxDesgRepository;



    @Transactional
    @Override
    public ZxDesg create(ZxDesg lookup) {
        return zxDesgRepository.save(lookup);
    }

    @Override
    @Transactional
    public ZxDesg findById(BigInteger id) {
        ZxDesg zxDesg=zxDesgRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return zxDesg;
    }

    @Override
    @Transactional(rollbackFor = ZxDesgNotFoundException.class)
    public ZxDesg delete(BigInteger id) throws ZxDesgNotFoundException {

        ZxDesg zxDesg = zxDesgRepository.findOne(id);

        if (zxDesg == null) {
            throw new ZxDesgNotFoundException();
        }
        zxDesgRepository.delete(id);
        return zxDesg;
    }

    @Override
    @Transactional
    public Iterable<ZxDesg> findAll() {
        Iterable<ZxDesg> zxDesgs=zxDesgRepository.findAll();
        
        for (ZxDesg zxDesg : zxDesgs) {

        //Hibernate.initialize(zxDesg.getA());
        //Hibernate.initialize(zxDesg.getZs());
        }
        
        return zxDesgs;
    }

    @Transactional(rollbackFor = ZxDesgNotFoundException.class)
    @Override
    public ZxDesg update(ZxDesg updated) throws ZxDesgNotFoundException {

        ZxDesg zxDesg = zxDesgRepository.findOne(updated.getId());

        if (zxDesg == null) {
            throw new ZxDesgNotFoundException();
        }

        BeanUtils.copyProperties(updated, zxDesg);
        return zxDesgRepository.save(zxDesg);
    }

    @Transactional(rollbackFor = ZxDesgNotFoundException.class)
    @Override
    public ZxDesg copy(final ZxDesg copied) {

        ZxDesg zxDesg = new ZxDesg();
        BeanUtils.copyProperties(copied, zxDesg);
        zxDesg.setId(null);

        return zxDesgRepository.save(zxDesg);
    }

    @Transactional
    @Override
    public Iterable<ZxDesg> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<ZxDesg> search(_SearchDTO pageable) {
        return findAll();
    }
}