package org.reflection.service;

import org.reflection.model.hcm.tl.Roster;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.RosterNotFoundException;
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

@Service("rosterService")
@Transactional(readOnly = true)
public class RosterServiceImpl implements RosterService {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional
    @Override
    public Roster create(Roster roster) {
        getCurrentSession().save(roster);
        return roster;
    }
    
    @Override
    @Transactional
    public Roster findById(BigInteger id) {
        Roster roster = (Roster) getCurrentSession().get(Roster.class, id);
        

        //Hibernate.initialize(roster.getHrTlShftDtlId());
        //Hibernate.initialize(roster.getPersonEduDtlList());
        return roster;
    }
    
    @Override
    @Transactional(rollbackFor = RosterNotFoundException.class)
    public Roster delete(BigInteger rosterId) throws RosterNotFoundException {
           
        Roster roster = (Roster) getCurrentSession().get(Roster.class, rosterId);
            
        if (roster == null) {
            throw new RosterNotFoundException();
        }
    
        getCurrentSession().delete(roster);
        return roster;
    }
    
    @Override
    @Transactional
    public List<Roster> findAll() {
        List<Roster> rosters =getCurrentSession().createQuery("FROM " + Roster.class.getName()).list();
            
        for (Roster roster : rosters) {

            //Hibernate.initialize(roster.getHrTlShftDtlId());
            //Hibernate.initialize(roster.getPersonEduDtlList());
        }
        return rosters;
    }
      
    @Transactional(rollbackFor = RosterNotFoundException.class)
    @Override
    public Roster update(Roster updated) throws RosterNotFoundException {

        Roster roster = (Roster) getCurrentSession().get(Roster.class, updated.getId());

        if (roster == null) {
            throw new RosterNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(roster, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Roster update: "+ e);
        }

        getCurrentSession().save(roster);
        return updated;
    }
    
    @Transactional(rollbackFor = RosterNotFoundException.class)
    @Override
    public Roster copy(final Roster copied) {
    
        Roster roster = new Roster();//(Roster) getCurrentSession().get(Roster.class, copied.getId());
    
        try {
            PropertyUtils.copyProperties(roster, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 Roster copy: "+ e);
        }
    
        getCurrentSession().save(roster);
        return roster;
    }
    
    @Transactional
    @Override
    public List<Roster> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM Roster m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Roster> rosters = (List<Roster>) query.list();
        for (Roster roster : rosters) {

        //Hibernate.initialize(roster.getHrTlShftDtlId());
        //Hibernate.initialize(roster.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT count(m) FROM Roster m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return rosters;

    //Criteria criteria = getCurrentSession().createCriteria(Roster.class);
    //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
    //criteria.setMaxResults(pageable.getPageSize());
    //
    //List<Roster> rosters = (List<Roster>) criteria.list();
    //
    //int totRecs = 27;
    //
    //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
    //pageable.setTotalRecs(totRecs);  
    }
    
    @Transactional
    @Override
    public List<Roster> search(_SearchDTO pageable) {
        
        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM Roster m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM Roster m WHERE 1=1  AND UPPER(CONCAT(m.remarks, m.title)) LIKE CONCAT('%',:search,'%')";

        Query queryCount = session.createQuery("SELECT count(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();
        
        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Roster> rosters = (List<Roster>) query.list();

        for (Roster roster : rosters) {

            //Hibernate.initialize(roster.getHrTlShftDtlId());
            //Hibernate.initialize(roster.getPersonEduDtlList());
        }
        
        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return rosters;
    }
}
