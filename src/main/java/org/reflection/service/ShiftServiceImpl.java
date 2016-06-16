package org.reflection.service;

import org.reflection.model.hcm.tl.Shift;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ShiftNotFoundException;
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

@Service("shiftService")
@Transactional(readOnly = true)
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public Shift create(Shift shift) {
        getCurrentSession().save(shift);
        return shift;
    }
    
    @Override
    @Transactional
    public Shift findById(BigInteger id) {
        Shift shift = (Shift) getCurrentSession().get(Shift.class, id);
        

        //Hibernate.initialize(shift.getHrTlShftDtlId());
        //Hibernate.initialize(shift.getPersonEduDtlList());
        return shift;
    }
    
    @Override
    @Transactional(rollbackFor = ShiftNotFoundException.class)
    public Shift delete(BigInteger shiftId) throws ShiftNotFoundException {
           
        Shift shift = (Shift) getCurrentSession().get(Shift.class, shiftId);
            
        if (shift == null) {
            throw new ShiftNotFoundException();
        }
    
        getCurrentSession().delete(shift);
        return shift;
    }
    
    @Override
    @Transactional
    public List<Shift> findAll() {
        List<Shift> shifts =getCurrentSession().createQuery("FROM " + Shift.class.getName()).list();
            
        for (Shift shift : shifts) {

            //Hibernate.initialize(shift.getHrTlShftDtlId());
            //Hibernate.initialize(shift.getPersonEduDtlList());
        }
        return shifts;
    }
      
    @Transactional(rollbackFor = ShiftNotFoundException.class)
    @Override
    public Shift update(Shift updated) throws ShiftNotFoundException {

        Shift shift = (Shift) getCurrentSession().get(Shift.class, updated.getId());

        if (shift == null) {
            throw new ShiftNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(shift, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Shift update: "+ e);
        }

        getCurrentSession().save(shift);
        return updated;
    }
    
    @Transactional(rollbackFor = ShiftNotFoundException.class)
    @Override
    public Shift copy(final Shift copied) {
    
        Shift shift = new Shift();//(Shift) getCurrentSession().get(Shift.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(shift, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Shift copy: "+ e);
        }
    
        getCurrentSession().save(shift);
        return shift;
    }
    
    @Transactional
    @Override
    public List<Shift> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Shift m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Shift> shifts = (List<Shift>) query.list();
        for (Shift shift : shifts) {

        //Hibernate.initialize(shift.getHrTlShftDtlId());
        //Hibernate.initialize(shift.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM Shift m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return shifts;

    //Criteria criteria = getCurrentSession().createCriteria(Shift.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<Shift> shifts = (List<Shift>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<Shift> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM Shift m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM Shift m WHERE 1=1  AND UPPER(CONCAT(m.remarks, m.title)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Shift> shifts = (List<Shift>) query.list();

        for (Shift shift : shifts) {

            //Hibernate.initialize(shift.getHrTlShftDtlId());
            //Hibernate.initialize(shift.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return shifts;
    }
}
