package mchti.service;

import mchti.model.sample.ZxEmpEduDtl;
import mchti.dto._SearchDTO;
import mchti.exception.ZxEmpEduDtlNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("zxEmpEduDtlService")
@Transactional(readOnly = true)
public class ZxEmpEduDtlServiceImpl implements ZxEmpEduDtlService {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public ZxEmpEduDtl create(ZxEmpEduDtl zxEmpEduDtl) {
        getCurrentSession().save(zxEmpEduDtl);
        return zxEmpEduDtl;
    }

    @Override
    @Transactional
    public ZxEmpEduDtl findById(BigInteger id) {
        ZxEmpEduDtl zxEmpEduDtl = (ZxEmpEduDtl) getCurrentSession().get(ZxEmpEduDtl.class, id);

        Hibernate.initialize(zxEmpEduDtl.getZxEmp());

        //Hibernate.initialize(zxEmpEduDtl.getHrTlShftDtlId());
        //Hibernate.initialize(zxEmpEduDtl.getPersonEduDtlList());
        return zxEmpEduDtl;
    }

    @Override
    @Transactional(rollbackFor = ZxEmpEduDtlNotFoundException.class)
    public ZxEmpEduDtl delete(BigInteger zxEmpEduDtlId) throws ZxEmpEduDtlNotFoundException {

        ZxEmpEduDtl zxEmpEduDtl = (ZxEmpEduDtl) getCurrentSession().get(ZxEmpEduDtl.class, zxEmpEduDtlId);

        if (zxEmpEduDtl == null) {
            throw new ZxEmpEduDtlNotFoundException();
        }

        getCurrentSession().delete(zxEmpEduDtl);
        return zxEmpEduDtl;
    }

    @Override
    @Transactional
    public List<ZxEmpEduDtl> findAll() {
        List<ZxEmpEduDtl> zxEmpEduDtls = getCurrentSession().createQuery("FROM " + ZxEmpEduDtl.class.getName()).list();

        for (ZxEmpEduDtl zxEmpEduDtl : zxEmpEduDtls) {
            Hibernate.initialize(zxEmpEduDtl.getZxEmp());

            //Hibernate.initialize(zxEmpEduDtl.getHrTlShftDtlId());
            //Hibernate.initialize(zxEmpEduDtl.getPersonEduDtlList());
        }
        return zxEmpEduDtls;
    }

    @Transactional(rollbackFor = ZxEmpEduDtlNotFoundException.class)
    @Override
    public ZxEmpEduDtl update(ZxEmpEduDtl updated) throws ZxEmpEduDtlNotFoundException {

        ZxEmpEduDtl zxEmpEduDtl = (ZxEmpEduDtl) getCurrentSession().get(ZxEmpEduDtl.class, updated.getId());

        if (zxEmpEduDtl == null) {
            throw new ZxEmpEduDtlNotFoundException();
        }

        try {
            PropertyUtils.copyProperties(zxEmpEduDtl, updated);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 ZxEmpEduDtl update: " + e);
        }

        getCurrentSession().save(zxEmpEduDtl);
        return updated;
    }

    @Transactional(rollbackFor = ZxEmpEduDtlNotFoundException.class)
    @Override
    public ZxEmpEduDtl copy(final ZxEmpEduDtl copied) {

        ZxEmpEduDtl zxEmpEduDtl = new ZxEmpEduDtl();//(ZxEmpEduDtl) getCurrentSession().get(ZxEmpEduDtl.class, copied.getId());

        try {
            PropertyUtils.copyProperties(zxEmpEduDtl, copied);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("err: mac must see 144 ZxEmpEduDtl copy: " + e);
        }

        getCurrentSession().save(zxEmpEduDtl);
        return zxEmpEduDtl;
    }

    @Transactional
    @Override
    public List<ZxEmpEduDtl> findAll(_SearchDTO pageable) {

        Session session = getCurrentSession();

        Query query = session.createQuery("FROM ZxEmpEduDtl m");
        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<ZxEmpEduDtl> zxEmpEduDtls = (List<ZxEmpEduDtl>) query.list();
        for (ZxEmpEduDtl zxEmpEduDtl : zxEmpEduDtls) {
            Hibernate.initialize(zxEmpEduDtl.getZxEmp());

            //Hibernate.initialize(zxEmpEduDtl.getHrTlShftDtlId());
            //Hibernate.initialize(zxEmpEduDtl.getPersonEduDtlList());
        }

        Long totRecs = (Long) session.createQuery("SELECT COUNT(m) FROM ZxEmpEduDtl m").uniqueResult();

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);
        return zxEmpEduDtls;

        //Criteria criteria = getCurrentSession().createCriteria(ZxEmpEduDtl.class);
        //criteria.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        //criteria.setMaxResults(pageable.getPageSize());
        //
        //List<ZxEmpEduDtl> zxEmpEduDtls = (List<ZxEmpEduDtl>) criteria.list();
        //
        //int totRecs = 27;
        //
        //pageable.setTotalPages(totRecs / pageable.getPageSize() + 1);
        //pageable.setTotalRecs(totRecs);  
    }

    @Transactional
    @Override
    public List<ZxEmpEduDtl> search(_SearchDTO pageable) {

        String searchTerm = pageable.getSearchTerm().toUpperCase();
        Session session = getCurrentSession();

        //String qu = "FROM ZxEmpEduDtl m WHERE 1=1 AND UPPER(m.title) LIKE UPPER(CONCAT('%',:search,'%'))";
        String qu = "FROM ZxEmpEduDtl m WHERE 1=1 MAC_SEARCHABLE_WHERE_TRUE_AND";

        Query queryCount = session.createQuery("SELECT COUNT(m) " + qu);
        queryCount.setParameter("search", searchTerm);
        Long totRecs = (Long) queryCount.uniqueResult();

        Query query = session.createQuery(qu);
        query.setParameter("search", searchTerm);

        query.setFirstResult((pageable.getPage() - 1) * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<ZxEmpEduDtl> zxEmpEduDtls = (List<ZxEmpEduDtl>) query.list();

        for (ZxEmpEduDtl zxEmpEduDtl : zxEmpEduDtls) {
            Hibernate.initialize(zxEmpEduDtl.getZxEmp());

            //Hibernate.initialize(zxEmpEduDtl.getHrTlShftDtlId());
            //Hibernate.initialize(zxEmpEduDtl.getPersonEduDtlList());
        }

        pageable.setTotalPages((int) (totRecs / pageable.getPageSize() + 1));
        pageable.setTotalRecs(totRecs);

        return zxEmpEduDtls;
    }
}
