package org.reflection.service;

import org.reflection.model.hcm.tl.AssignmentTl;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AssignmentTlNotFoundException;
import org.reflection.repositories.AssignmentTlRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assignmentTlService")
@Transactional(readOnly = true)
public class AssignmentTlServiceImpl implements AssignmentTlService {

    @Autowired
    private AssignmentTlRepository assignmentTlRepository;



    @Transactional
    @Override
    public AssignmentTl create(AssignmentTl lookup) {
        return assignmentTlRepository.save(lookup);
    }

    @Override
    @Transactional
    public AssignmentTl findById(BigInteger id) {
        AssignmentTl assignmentTl=assignmentTlRepository.findOne(id);
            Hibernate.initialize(assignmentTl.getEmployee());
            Hibernate.initialize(assignmentTl.getShift());
            Hibernate.initialize(assignmentTl.getRoster());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return assignmentTl;
    }

    @Override
    @Transactional(rollbackFor = AssignmentTlNotFoundException.class)
    public AssignmentTl delete(BigInteger id) throws AssignmentTlNotFoundException {

        AssignmentTl assignmentTl = assignmentTlRepository.findOne(id);

        if (assignmentTl == null) {
            throw new AssignmentTlNotFoundException();
        }
        assignmentTlRepository.delete(id);
        return assignmentTl;
    }

    @Override
    @Transactional
    public Iterable<AssignmentTl> findAll() {
        Iterable<AssignmentTl> assignmentTls=assignmentTlRepository.findAll();
        
        for (AssignmentTl assignmentTl : assignmentTls) {
            Hibernate.initialize(assignmentTl.getEmployee());
            Hibernate.initialize(assignmentTl.getShift());
            Hibernate.initialize(assignmentTl.getRoster());

        //Hibernate.initialize(assignmentTl.getA());
        //Hibernate.initialize(assignmentTl.getZs());
        }
        
        return assignmentTls;
    }

    @Transactional(rollbackFor = AssignmentTlNotFoundException.class)
    @Override
    public AssignmentTl update(AssignmentTl updated) throws AssignmentTlNotFoundException {

        AssignmentTl assignmentTl = assignmentTlRepository.findOne(updated.getId());

        if (assignmentTl == null) {
            throw new AssignmentTlNotFoundException();
        }

        BeanUtils.copyProperties(updated, assignmentTl);
        return assignmentTlRepository.save(assignmentTl);
    }

    @Transactional(rollbackFor = AssignmentTlNotFoundException.class)
    @Override
    public AssignmentTl copy(final AssignmentTl copied) {

        AssignmentTl assignmentTl = new AssignmentTl();
        BeanUtils.copyProperties(copied, assignmentTl);
        assignmentTl.setId(null);

        return assignmentTlRepository.save(assignmentTl);
    }

    @Transactional
    @Override
    public Iterable<AssignmentTl> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AssignmentTl> search(_SearchDTO pageable) {
        return findAll();
    }
}