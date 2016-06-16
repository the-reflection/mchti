package org.reflection.service;

import org.reflection.model.hcm.tl.GeneralHoliday;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.GeneralHolidayNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import java.math.BigInteger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("generalHolidayService")
@Transactional(readOnly = true)
public class GeneralHolidayServiceImpl implements GeneralHolidayService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public GeneralHoliday create(GeneralHoliday generalHoliday) {
        getCurrentSession().save(generalHoliday);
        return generalHoliday;
    }
    
    @Override
    @Transactional
    public GeneralHoliday findById(BigInteger id) {
        GeneralHoliday generalHoliday = (GeneralHoliday) getCurrentSession().get(GeneralHoliday.class, id);
        

        //Hibernate.initialize(generalHoliday.getHrTlShftDtlId());
        //Hibernate.initialize(generalHoliday.getPersonEduDtlList());
        return generalHoliday;
    }
    
    @Override
    @Transactional(rollbackFor = GeneralHolidayNotFoundException.class)
    public GeneralHoliday delete(BigInteger generalHolidayId) throws GeneralHolidayNotFoundException {
           
        GeneralHoliday generalHoliday = (GeneralHoliday) getCurrentSession().get(GeneralHoliday.class, generalHolidayId);
            
        if (generalHoliday == null) {
            throw new GeneralHolidayNotFoundException();
        }
    
        getCurrentSession().delete(generalHoliday);
        return generalHoliday;
    }
    
    @Override
    @Transactional
    public List<GeneralHoliday> findAll() {
        List<GeneralHoliday> generalHolidays =getCurrentSession().createQuery("FROM " + GeneralHoliday.class.getName()).list();
            
        for (GeneralHoliday generalHoliday : generalHolidays) {

            //Hibernate.initialize(generalHoliday.getHrTlShftDtlId());
            //Hibernate.initialize(generalHoliday.getPersonEduDtlList());
        }
        return generalHolidays;
    }
      
    @Transactional(rollbackFor = GeneralHolidayNotFoundException.class)
    @Override
    public GeneralHoliday update(GeneralHoliday updated) throws GeneralHolidayNotFoundException {

        GeneralHoliday generalHoliday = (GeneralHoliday) getCurrentSession().get(GeneralHoliday.class, updated.getId());

        if (generalHoliday == null) {
            throw new GeneralHolidayNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(generalHoliday, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 GeneralHoliday update: "+ e);
        }

        getCurrentSession().save(generalHoliday);
        return updated;
    }
    
    @Transactional(rollbackFor = GeneralHolidayNotFoundException.class)
    @Override
    public GeneralHoliday copy(final GeneralHoliday copied) {
    
        GeneralHoliday generalHoliday = new GeneralHoliday();//(GeneralHoliday) getCurrentSession().get(GeneralHoliday.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(generalHoliday, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 GeneralHoliday copy: "+ e);
        }
    
        getCurrentSession().save(generalHoliday);
        return generalHoliday;
    }
    
    @Transactional
    @Override
    public List<GeneralHoliday> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM GeneralHoliday m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<GeneralHoliday> generalHolidays = (List<GeneralHoliday>) query.list();
        for (GeneralHoliday generalHoliday : generalHolidays) {

        //Hibernate.initialize(generalHoliday.getHrTlShftDtlId());
        //Hibernate.initialize(generalHoliday.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM GeneralHoliday m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return generalHolidays;

    //Criteria criteria = getCurrentSession().createCriteria(GeneralHoliday.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<GeneralHoliday> generalHolidays = (List<GeneralHoliday>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<GeneralHoliday> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM GeneralHoliday m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM GeneralHoliday m WHERE 1=1  AND UPPER(CONCAT(m.title)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<GeneralHoliday> generalHolidays = (List<GeneralHoliday>) query.list();

        for (GeneralHoliday generalHoliday : generalHolidays) {

            //Hibernate.initialize(generalHoliday.getHrTlShftDtlId());
            //Hibernate.initialize(generalHoliday.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return generalHolidays;
    }
}
