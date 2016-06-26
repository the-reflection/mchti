package org.reflection.service;

import org.reflection.model.hcm.tl.Roster;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.RosterNotFoundException;
import org.reflection.repositories.RosterRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("rosterService")
@Transactional(readOnly = true)
public class RosterServiceImpl implements RosterService {

    @Autowired
    private RosterRepository rosterRepository;



    @Transactional
    @Override
    public Roster create(Roster lookup) {
        return rosterRepository.save(lookup);
    }

    @Override
    @Transactional
    public Roster findById(BigInteger id) {
        Roster roster=rosterRepository.findOne(id);

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return roster;
    }

    @Override
    @Transactional(rollbackFor = RosterNotFoundException.class)
    public Roster delete(BigInteger id) throws RosterNotFoundException {

        Roster roster = rosterRepository.findOne(id);

        if (roster == null) {
            throw new RosterNotFoundException();
        }
        rosterRepository.delete(id);
        return roster;
    }

    @Override
    @Transactional
    public Iterable<Roster> findAll() {
        Iterable<Roster> rosters=rosterRepository.findAll();
        
        for (Roster roster : rosters) {

        //Hibernate.initialize(roster.getA());
        //Hibernate.initialize(roster.getZs());
        }
        
        return rosters;
    }

    @Transactional(rollbackFor = RosterNotFoundException.class)
    @Override
    public Roster update(Roster updated) throws RosterNotFoundException {

        Roster roster = rosterRepository.findOne(updated.getId());

        if (roster == null) {
            throw new RosterNotFoundException();
        }

        BeanUtils.copyProperties(updated, roster);
        return rosterRepository.save(roster);
    }

    @Transactional(rollbackFor = RosterNotFoundException.class)
    @Override
    public Roster copy(final Roster copied) {

        Roster roster = new Roster();
        BeanUtils.copyProperties(copied, roster);
        roster.setId(null);

        return rosterRepository.save(roster);
    }

    @Transactional
    @Override
    public Iterable<Roster> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<Roster> search(_SearchDTO pageable) {
        return findAll();
    }
}