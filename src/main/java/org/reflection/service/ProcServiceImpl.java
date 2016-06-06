package org.reflection.service;

import org.reflection.model.hcm.cr.AssignmentHr;
import org.reflection.model.com.Employee;
import org.reflection.model.hcm.enums.DtAttnType;
import org.reflection.model.hcm.enums.EmpGroup;
import org.reflection.model.hcm.proc.ProcOutAttnDaily;
import org.reflection.model.hcm.proc.ProcOutAttnDt;
import org.reflection.model.hcm.proc.ProcOutEmp;
import org.reflection.model.hcm.tl.AssignmentTl;
import org.reflection.model.hcm.tl.LeaveApp;
import org.reflection.model.hcm.tl.Shift;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.reflection.model.com.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("procService")
@Transactional(readOnly = true)
public class ProcServiceImpl implements ProcService {

    private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void refresh() {

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("FROM " + Employee.class.getName() + " m");
        List<Employee> emps = (List<Employee>) query.list();

        for (Employee emp : emps) {

            ProcOutEmp pp;
            try {
                pp = (ProcOutEmp) session
                        .createQuery("FROM " + ProcOutEmp.class.getName() + " m WHERE m.employee=:employee")
                        .setParameter("employee", emp)
                        .list().get(0);

            } catch (Exception e) {
                pp = null;
            }

            if (pp == null) {
                pp = new ProcOutEmp();
                pp.setId(emp.getId());
                pp.setEmployee(emp);
                pp.setDob(emp.getDob());
                pp.setDoj(emp.getDoj());
                pp.setGender(emp.getGender());
                pp.setCode(emp.getCode());
                pp.setAddress(emp.getAddress());
                pp.setEmail(emp.getEmail());
                pp.setPicFile(emp.getPicFile());
                pp.setFullName(emp.getFullName());
            }

            try {
                AssignmentHr assignmentHr = (AssignmentHr) session.createQuery("FROM " + AssignmentHr.class.getName() + " m WHERE m.employee=:employee").setParameter("employee", emp).uniqueResult();//list().get(0);

                if (assignmentHr != null) {

                    pp.setDepartment(assignmentHr.getDepartment());
                    pp.setDesignation(assignmentHr.getDesignation());
                    pp.setEmpGroup(assignmentHr.getEmpGroup() == null ? EmpGroup.ACTIVE : assignmentHr.getEmpGroup());
                }
            } catch (Exception e) {
                System.out.println("uuuuuuuuuuuu36545:" + e);
            }

            try {
                AssignmentTl assignmentTl = (AssignmentTl) session.createQuery("FROM " + AssignmentTl.class.getName() + " m WHERE m.employee=:employee").setParameter("employee", emp).list().get(0);

                if (assignmentTl != null) {
                    pp.setRoster(assignmentTl.getRoster());
                    pp.setShift(assignmentTl.getShift());
                    pp.setShiftOffDay(assignmentTl.getShiftOffDay());
                    pp.setIsOvertime(assignmentTl.getIsOvertime());
                }
            } catch (Exception e) {
            }

            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(pp);
                tx.commit();
            } catch (Exception e) {
                System.out.println("errr too 76544: " + e);
                if (tx != null) {
                    tx.rollback();
                }
            }

            System.out.println("Manik too 2345: " + pp);
        }
        session.close();
    }

    @Override
    public void daily(String attnDateStr) {

        Date attnDate = null;
        try {
            //attnDate = SIMPLE_DATE_FORMAT.parse("09/05/2016");
            attnDate = SIMPLE_DATE_FORMAT.parse(attnDateStr);
        } catch (Exception e) {
        }

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("FROM " + ProcOutEmp.class.getName() + " m");
        List<ProcOutEmp> emps = (List<ProcOutEmp>) query.list();

        for (ProcOutEmp poe : emps) {

            Date inTime = null;
            Date outTime = null;
            int punchCount = 0;
            Shift shift = poe.getShift();//(Shift) session.get(Shift.class, new BigInteger("1"));

            if (shift == null) {
                shift = (Shift) session.get(Shift.class, new BigInteger("1"));
            }

            try {
                Object[] ppx = (Object[]) session
                        .createQuery("SELECT MIN(m.transactionDate), MAX(m.transactionDate), COUNT(m.transactionDate) FROM " + ProcOutAttnDaily.class.getName() + " m WHERE m.employee=:emp AND DATE(m.transactionDate)=:transactionDate")
                        .setParameter("emp", poe.getEmployee())
                        .setParameter("transactionDate", attnDate)
                        .list().get(0);

                inTime = (Date) ppx[0];
                outTime = (Date) ppx[1];
                punchCount = (Integer) ppx[2];
            } catch (Exception e) {
            }
            DtAttnType dtAttnType;
            if (inTime == null && outTime == null) {
                dtAttnType = getDtAttnTypeLeaveOn(poe.getEmployee(), attnDate);// DtAttnType.ABSENT;
            } else if (punchCount == 1) {//inTime.equals(outTime)
                dtAttnType = DtAttnType.UNDEFINED;
            } else {
                dtAttnType = getDtAttnTypeAnyOn(shift, attnDate, inTime, outTime);// DtAttnType.ABSENT;
            }

            ProcOutAttnDt pp;
            try {

                pp = (ProcOutAttnDt) session
                        .createQuery("FROM " + ProcOutAttnDt.class.getName() + " m WHERE m.employee=:emp AND m.attnDate=:attnDate")
                        .setParameter("emp", poe.getEmployee())
                        .setParameter("attnDate", attnDate)
                        .list().get(0);
            } catch (Exception e) {
                pp = null;
            }

            if (pp == null) {
                pp = new ProcOutAttnDt();
                pp.setEmployee(poe.getEmployee());
                pp.setAttnDate(attnDate);
            }

            pp.setDtAttnType(dtAttnType);
            pp.setInTime(inTime);
            pp.setOutTime(outTime);
            pp.setShift(shift);

            //Session session = getCurrentSession();//sessionFactory.openSession();
            //Session session = sessionFactory.openSession();
            //tx.setTimeout(5);
            //doSomething(session);
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(pp);
                tx.commit();
            } catch (Exception e) {
                System.out.println(" err db 8345: " + e);
                if (tx != null) {
                    tx.rollback();
                }

            }

            System.out.println("rrrgf rajib too 2345: " + pp);
        }
        session.close();
    }

    private DtAttnType getDtAttnTypeLeaveOn(Employee emp, Date attnDate) {

        DtAttnType ret = DtAttnType.ABSENT;

        LeaveApp pp;
        try {
            pp = (LeaveApp) sessionFactory.getCurrentSession()
                    .createQuery("FROM " + LeaveApp.class.getName() + " m WHERE m.employee=:emp AND :attnDate BETWEEN m.startDate AND m.endDate")
                    .setParameter("emp", emp)
                    .setParameter("attnDate", attnDate)
                    .list().get(0);

        } catch (Exception e) {
            pp = null;
        }

        if (pp != null) {
            ret = DtAttnType.LEAVE;
        }

        return ret;
    }

    private DtAttnType getDtAttnTypeAnyOn(Shift shift, Date attnDate, Date inTime, Date outTime) {
        DtAttnType ret = DtAttnType.ABSENT;

        Date inOfShift = null;

        try {
            inOfShift = new Date(attnDate.getTime() + 60000 * (shift.getStartHr() * 60 + (shift.getStartMin() + shift.getStartBufMin())));
            //SIMPLE_TS_FORMAT.parse(SIMPLE_DATE_FORMAT.format(attnDate) + " " + shift.getStartHr() + ":" + (shift.getStartMin()+shift.getStartBufMin()) + ":00");
        } catch (Exception e) {
        }

        Date outOfShift = null;

        try {
            outOfShift = new Date(attnDate.getTime() + 60000 * (shift.getEndHr() * 60 + shift.getEndMin()) - shift.getEndBufMin() * 60000);

            //outOfShift = SIMPLE_TS_FORMAT.parse(SIMPLE_DATE_FORMAT.format(attnDate) + " " + shift.getEndHr() + ":" + (shift.getEndMin() - shift.getEndBufMin()) + ":00");
        } catch (Exception e) {
        }

        if (inTime.before(inOfShift) && outTime.after(outOfShift)) {
            ret = DtAttnType.PRESENT;
        } else if (inTime.after(inOfShift) && outTime.after(outOfShift)) {
            ret = DtAttnType.LATE;
        } else if (inTime.before(inOfShift) && outTime.before(outOfShift)) {
            ret = DtAttnType.EARLY_OUT;
        } else if (inTime.after(inOfShift) && outTime.before(outOfShift)) {
            ret = DtAttnType.LATE_COME_EARLY_OUT;
        }

        return ret;
    }
}
