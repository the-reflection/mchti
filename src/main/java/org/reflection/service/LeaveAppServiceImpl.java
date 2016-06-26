package org.reflection.service;

import org.reflection.model.hcm.tl.LeaveApp;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.LeaveAppNotFoundException;
import org.reflection.repositories.LeaveAppRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("leaveAppService")
@Transactional(readOnly = true)
public class LeaveAppServiceImpl implements LeaveAppService {

    @Autowired
    private LeaveAppRepository leaveAppRepository;



    @Transactional
    @Override
    public LeaveApp create(LeaveApp lookup) {
        return leaveAppRepository.save(lookup);
    }

    @Override
    @Transactional
    public LeaveApp findById(BigInteger id) {
        LeaveApp leaveApp=leaveAppRepository.findOne(id);
            Hibernate.initialize(leaveApp.getEmployee());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return leaveApp;
    }

    @Override
    @Transactional(rollbackFor = LeaveAppNotFoundException.class)
    public LeaveApp delete(BigInteger id) throws LeaveAppNotFoundException {

        LeaveApp leaveApp = leaveAppRepository.findOne(id);

        if (leaveApp == null) {
            throw new LeaveAppNotFoundException();
        }
        leaveAppRepository.delete(id);
        return leaveApp;
    }

    @Override
    @Transactional
    public Iterable<LeaveApp> findAll() {
        Iterable<LeaveApp> leaveApps=leaveAppRepository.findAll();
        
        for (LeaveApp leaveApp : leaveApps) {
            Hibernate.initialize(leaveApp.getEmployee());

        //Hibernate.initialize(leaveApp.getA());
        //Hibernate.initialize(leaveApp.getZs());
        }
        
        return leaveApps;
    }

    @Transactional(rollbackFor = LeaveAppNotFoundException.class)
    @Override
    public LeaveApp update(LeaveApp updated) throws LeaveAppNotFoundException {

        LeaveApp leaveApp = leaveAppRepository.findOne(updated.getId());

        if (leaveApp == null) {
            throw new LeaveAppNotFoundException();
        }

        BeanUtils.copyProperties(updated, leaveApp);
        return leaveAppRepository.save(leaveApp);
    }

    @Transactional(rollbackFor = LeaveAppNotFoundException.class)
    @Override
    public LeaveApp copy(final LeaveApp copied) {

        LeaveApp leaveApp = new LeaveApp();
        BeanUtils.copyProperties(copied, leaveApp);
        leaveApp.setId(null);

        return leaveAppRepository.save(leaveApp);
    }

    @Transactional
    @Override
    public Iterable<LeaveApp> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<LeaveApp> search(_SearchDTO pageable) {
        return findAll();
    }
}