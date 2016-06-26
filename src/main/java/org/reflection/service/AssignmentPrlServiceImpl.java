package org.reflection.service;

import org.reflection.model.hcm.prl.AssignmentPrl;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AssignmentPrlNotFoundException;
import org.reflection.repositories.AssignmentPrlRepository;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assignmentPrlService")
@Transactional(readOnly = true)
public class AssignmentPrlServiceImpl implements AssignmentPrlService {

    @Autowired
    private AssignmentPrlRepository assignmentPrlRepository;



    @Transactional
    @Override
    public AssignmentPrl create(AssignmentPrl lookup) {
        return assignmentPrlRepository.save(lookup);
    }

    @Override
    @Transactional
    public AssignmentPrl findById(BigInteger id) {
        AssignmentPrl assignmentPrl=assignmentPrlRepository.findOne(id);
            Hibernate.initialize(assignmentPrl.getEmployee());

        //Hibernate.initialize(lookup.getPersonEduDtlList());
        return assignmentPrl;
    }

    @Override
    @Transactional(rollbackFor = AssignmentPrlNotFoundException.class)
    public AssignmentPrl delete(BigInteger id) throws AssignmentPrlNotFoundException {

        AssignmentPrl assignmentPrl = assignmentPrlRepository.findOne(id);

        if (assignmentPrl == null) {
            throw new AssignmentPrlNotFoundException();
        }
        assignmentPrlRepository.delete(id);
        return assignmentPrl;
    }

    @Override
    @Transactional
    public Iterable<AssignmentPrl> findAll() {
        Iterable<AssignmentPrl> assignmentPrls=assignmentPrlRepository.findAll();
        
        for (AssignmentPrl assignmentPrl : assignmentPrls) {
            Hibernate.initialize(assignmentPrl.getEmployee());

        //Hibernate.initialize(assignmentPrl.getA());
        //Hibernate.initialize(assignmentPrl.getZs());
        }
        
        return assignmentPrls;
    }

    @Transactional(rollbackFor = AssignmentPrlNotFoundException.class)
    @Override
    public AssignmentPrl update(AssignmentPrl updated) throws AssignmentPrlNotFoundException {

        AssignmentPrl assignmentPrl = assignmentPrlRepository.findOne(updated.getId());

        if (assignmentPrl == null) {
            throw new AssignmentPrlNotFoundException();
        }

        BeanUtils.copyProperties(updated, assignmentPrl);
        return assignmentPrlRepository.save(assignmentPrl);
    }

    @Transactional(rollbackFor = AssignmentPrlNotFoundException.class)
    @Override
    public AssignmentPrl copy(final AssignmentPrl copied) {

        AssignmentPrl assignmentPrl = new AssignmentPrl();
        BeanUtils.copyProperties(copied, assignmentPrl);
        assignmentPrl.setId(null);

        return assignmentPrlRepository.save(assignmentPrl);
    }

    @Transactional
    @Override
    public Iterable<AssignmentPrl> findAll(_SearchDTO pageable) {
        return findAll();
    }

    @Transactional
    @Override
    public Iterable<AssignmentPrl> search(_SearchDTO pageable) {
        return findAll();
    }
}