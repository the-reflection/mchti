package org.reflection.service;

import org.reflection.model.hcm.cr.Designation;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.DesignationNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import java.math.BigInteger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("designationService")
@Transactional(readOnly = true)
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public Designation create(Designation designation) {
        getCurrentSession().save(designation);
        return designation;
    }
    
    @Override
    @Transactional
    public Designation findById(BigInteger id) {
        Designation designation = (Designation) getCurrentSession().get(Designation.class, id);
        

        //Hibernate.initialize(designation.getHrTlShftDtlId());
        //Hibernate.initialize(designation.getPersonEduDtlList());
        return designation;
    }
    
    @Override
    @Transactional(rollbackFor = DesignationNotFoundException.class)
    public Designation delete(BigInteger designationId) throws DesignationNotFoundException {
           
        Designation designation = (Designation) getCurrentSession().get(Designation.class, designationId);
            
        if (designation == null) {
            throw new DesignationNotFoundException();
        }
    
        getCurrentSession().delete(designation);
        return designation;
    }
    
    @Override
    @Transactional
    public List<Designation> findAll() {
        List<Designation> designations =getCurrentSession().createQuery("FROM " + Designation.class.getName()).list();
            
        for (Designation designation : designations) {

            //Hibernate.initialize(designation.getHrTlShftDtlId());
            //Hibernate.initialize(designation.getPersonEduDtlList());
        }
        return designations;
    }
      
    @Transactional(rollbackFor = DesignationNotFoundException.class)
    @Override
    public Designation update(Designation updated) throws DesignationNotFoundException {

        Designation designation = (Designation) getCurrentSession().get(Designation.class, updated.getId());

        if (designation == null) {
            throw new DesignationNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(designation, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Designation update: "+ e);
        }

        getCurrentSession().save(designation);
        return updated;
    }
    
    @Transactional(rollbackFor = DesignationNotFoundException.class)
    @Override
    public Designation copy(final Designation copied) {
    
        Designation designation = new Designation();//(Designation) getCurrentSession().get(Designation.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(designation, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Designation copy: "+ e);
        }
    
        getCurrentSession().save(designation);
        return designation;
    }
    
    @Transactional
    @Override
    public List<Designation> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Designation m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Designation> designations = (List<Designation>) query.list();
        for (Designation designation : designations) {

        //Hibernate.initialize(designation.getHrTlShftDtlId());
        //Hibernate.initialize(designation.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM Designation m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return designations;

    //Criteria criteria = getCurrentSession().createCriteria(Designation.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<Designation> designations = (List<Designation>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<Designation> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM Designation m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM Designation m WHERE 1=1  AND UPPER(CONCAT(m.remarks, m.title)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Designation> designations = (List<Designation>) query.list();

        for (Designation designation : designations) {

            //Hibernate.initialize(designation.getHrTlShftDtlId());
            //Hibernate.initialize(designation.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return designations;
    }
}
