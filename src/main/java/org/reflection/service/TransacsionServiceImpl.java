package org.reflection.service;

import org.reflection.model.com.Transacsion;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.TransacsionNotFoundException;
import org.reflection.repositories.TransacsionRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("transacsionService")
@Transactional(readOnly = true)
public class TransacsionServiceImpl implements TransacsionService {

    @Autowired
    private TransacsionRepository transacsionRepository;



    @Transactional
    @Override
    public Transacsion create(Transacsion lookup) {
        return transacsionRepository.save(lookup);
    }

    @Override
    @Transactional
    public Transacsion findById(BigInteger id) {
        Transacsion transacsion=transacsionRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return transacsion;
    }

    @Override
    @Transactional(rollbackFor = TransacsionNotFoundException.class)
    public Transacsion delete(BigInteger id) throws TransacsionNotFoundException {

        Transacsion transacsion = transacsionRepository.findOne(id);

        if (transacsion == null) {
            throw new TransacsionNotFoundException();
        }
        transacsionRepository.delete(id);
        return transacsion;
    }

    @Override
    @Transactional
    public Iterable<Transacsion> findAll() {
        Iterable<Transacsion> transacsions=transacsionRepository.findAll();
        
        for (Transacsion transacsion : transacsions) {

        //Hibernate.initialize(transacsion.getA());
        //Hibernate.initialize(transacsion.getZs());
        }
        
        return transacsions;
    }

    @Transactional(rollbackFor = TransacsionNotFoundException.class)
    @Override
    public Transacsion update(Transacsion updated) throws TransacsionNotFoundException {

        Transacsion transacsion = transacsionRepository.findOne(updated.getId());

        if (transacsion == null) {
            throw new TransacsionNotFoundException();
        }

        BeanUtils.copyProperties(updated, transacsion);
        return transacsionRepository.save(transacsion);
    }

    @Transactional(rollbackFor = TransacsionNotFoundException.class)
    @Override
    public Transacsion copy(final Transacsion copied) {

        Transacsion transacsion = new Transacsion();
        BeanUtils.copyProperties(copied, transacsion);
        transacsion.setId(null);

        return transacsionRepository.save(transacsion);
    }

    @Transactional
    @Override
    public Iterable<Transacsion> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Transacsion> search(_SearchDTO pageable) {
        return findAll();
    }
}