package org.reflection.service;

import org.reflection.model.hcm.cr.AssignmentHr;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AssignmentHrNotFoundException;
import org.reflection.repositories.AssignmentHrRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assignmentHrService")
@Transactional(readOnly = true)
public class AssignmentHrServiceImpl implements AssignmentHrService {

    @Autowired
    private AssignmentHrRepository assignmentHrRepository;



    @Transactional
    @Override
    public AssignmentHr create(AssignmentHr lookup) {
        return assignmentHrRepository.save(lookup);
    }

    @Override
    @Transactional
    public AssignmentHr findById(BigInteger id) {
        AssignmentHr assignmentHr=assignmentHrRepository.findOne(id);
            Hibernate.initialize(assignmentHr.getEmployee());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return assignmentHr;
    }

    @Override
    @Transactional(rollbackFor = AssignmentHrNotFoundException.class)
    public AssignmentHr delete(BigInteger id) throws AssignmentHrNotFoundException {

        AssignmentHr assignmentHr = assignmentHrRepository.findOne(id);

        if (assignmentHr == null) {
            throw new AssignmentHrNotFoundException();
        }
        assignmentHrRepository.delete(id);
        return assignmentHr;
    }

    @Override
    @Transactional
    public Iterable<AssignmentHr> findAll() {
        Iterable<AssignmentHr> assignmentHrs=assignmentHrRepository.findAll();
        
        for (AssignmentHr assignmentHr : assignmentHrs) {
            Hibernate.initialize(assignmentHr.getEmployee());

        //Hibernate.initialize(assignmentHr.getA());
        //Hibernate.initialize(assignmentHr.getZs());
        }
        
        return assignmentHrs;
    }

    @Transactional(rollbackFor = AssignmentHrNotFoundException.class)
    @Override
    public AssignmentHr update(AssignmentHr updated) throws AssignmentHrNotFoundException {

        AssignmentHr assignmentHr = assignmentHrRepository.findOne(updated.getId());

        if (assignmentHr == null) {
            throw new AssignmentHrNotFoundException();
        }

        BeanUtils.copyProperties(updated, assignmentHr);
        return assignmentHrRepository.save(assignmentHr);
    }

    @Transactional(rollbackFor = AssignmentHrNotFoundException.class)
    @Override
    public AssignmentHr copy(final AssignmentHr copied) {

        AssignmentHr assignmentHr = new AssignmentHr();
        BeanUtils.copyProperties(copied, assignmentHr);
        assignmentHr.setId(null);

        return assignmentHrRepository.save(assignmentHr);
    }

    @Transactional
    @Override
    public Iterable<AssignmentHr> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AssignmentHr> search(_SearchDTO pageable) {
        return findAll();
    }
}