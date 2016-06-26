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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.reflection.model.hcm.proc.ProcOutCalender;
import org.reflection.model.hcm.proc.ProcOutRoster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("procService")
@Transactional(readOnly = true)
public class ProcServiceImpl implements ProcService {

    private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void refresh() {

        EntityManager em = entityManagerFactory.createEntityManager();

     List<Employee> emps =   em.createQuery("SELECT c FROM " + Employee.class.getName() + " c ", Employee.class).getResultList();

        for (Employee emp : emps) {
            ProcOutEmp pp;
            try {
                
               pp = (ProcOutEmp)   em.createQuery(
    "SELECT m FROM " + ProcOutEmp.class.getName() + " m WHERE m.employee=:employee")
    .setParameter("employee", emp)
    .getResultList().get(0);

            } catch (Exception e) {
                pp = null;
            }

            if (pp == null) {
                pp = new ProcOutEmp();
            }

            //pp.setId(emp.getId());
            pp.setEmployee( emp);
            pp.setDob(emp.getDob());
            pp.setDoj(emp.getDoj());
            pp.setGender(emp.getGender());
            pp.setCode(emp.getCode());
            pp.setAddress(emp.getAddress());
            pp.setEmail(emp.getEmail());
            pp.setPicFile(emp.getPicFile());
            pp.setFullName(emp.getFullName());

            try {
                AssignmentHr assignmentHr = em.createQuery("FROM " + AssignmentHr.class.getName() + " m WHERE m.employee=:employee",AssignmentHr.class).setParameter("employee", emp).getSingleResult();//uniqueResult();//list().get(0);
              //  AssignmentHr assignmentHr = (AssignmentHr) session.createQuery("FROM " + AssignmentHr.class.getName() + " m WHERE m.employee=:employee").setParameter("employee", emp).uniqueResult();//list().get(0);

                if (assignmentHr != null) {

                    pp.setDepartment(assignmentHr.getDepartment());
                    pp.setDesignation(assignmentHr.getDesignation());
                    pp.setEmpGroup(assignmentHr.getEmpGroup() == null ? EmpGroup.ACTIVE : assignmentHr.getEmpGroup());
                }
            } catch (Exception e) {
                System.out.println("uuuuuuuuuuuu36545:" + e);
            }

            try {
                AssignmentTl assignmentTl = em.createQuery("FROM " + AssignmentTl.class.getName() + " m WHERE m.employee=:employee",AssignmentTl.class).setParameter("employee", emp).getSingleResult();//list().get(0);

                if (assignmentTl != null) {
                    pp.setRoster(assignmentTl.getRoster());
                    pp.setShift(assignmentTl.getShift());
                    pp.setWeekendShiftOffDay(assignmentTl.getWeekendShiftOffDay());
                    pp.setIsOvertime(assignmentTl.getIsOvertime());
                }
            } catch (Exception e) {
            }

             EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx.begin();
                em.merge(pp);
                tx.commit();
            } catch (Exception e) {
                System.out.println("errr too 76544: " + e);
                if (tx != null) {
                    tx.rollback();
                }
            }

            System.out.println("Manik too 2345: " + pp);
        }
        em.close();
    }

    @Override
    public void daily(String attnDateStr) {

        Date attnDate = null;
        try {
            //attnDate = SIMPLE_DATE_FORMAT.parse("09/05/2016");
            attnDate = SIMPLE_DATE_FORMAT.parse(attnDateStr);
        } catch (Exception e) {
        }

        EntityManager em = entityManagerFactory.createEntityManager();

        List<ProcOutEmp> emps = em.createQuery("FROM " + ProcOutEmp.class.getName() + " m",ProcOutEmp.class).getResultList();

        for (ProcOutEmp poe : emps) {

            Date inTime = null;
            Date outTime = null;
            Long punchCount = null;
            Shift shift = poe.getShift();//(Shift) session.get(Shift.class, new BigInteger("1"));

            try {
                ProcOutRoster hh =  em.createQuery("FROM " + ProcOutRoster.class.getName() + " m WHERE m.procOutRosterPK.employee=:employee AND TRUNC(m.procOutRosterPK.calcDate)=:calcDate",ProcOutRoster.class)
                        .setParameter("employee", poe.getEmployee())
                        .setParameter("calcDate", attnDate)
                        .getSingleResult();
                shift = hh.getShift();//(Shift) session.get(Shift.class, new BigInteger("1"));

            } catch (Exception e) {
                System.out.println("err roster shift: " + e);
            }

            if (shift == null) {
                System.out.println("warning shift not assigned: " + poe.getFullName());
                continue;
            }

            try {

                Object[] ppx = (Object[]) em
                        .createQuery("SELECT MIN(m.procOutAttnDailyPK.transactionTime), MAX(m.procOutAttnDailyPK.transactionTime), COUNT(m.procOutAttnDailyPK.transactionTime) FROM "
                                + ProcOutAttnDaily.class.getName()
                                + " m WHERE m.procOutAttnDailyPK.employee=:employee AND TRUNC(m.procOutAttnDailyPK.transactionTime)=:transactionTime")
                        .setParameter("employee", poe.getEmployee())
                        .setParameter("transactionTime", attnDate)
                        .getSingleResult();
                inTime = (Date) ppx[0];
                outTime = (Date) ppx[1];
                punchCount = (Long) ppx[2];
            } catch (Exception e) {
                System.out.println("show mac err: " + e);
            }

            DtAttnType dtAttnType;
            if (inTime == null && outTime == null) {
                dtAttnType = getDtAttnTypeOnNoPunch(em,poe.getEmployee(), attnDate);// DtAttnType.ABSENT;
            } else if (punchCount == 1) {//inTime.equals(outTime)
                dtAttnType = DtAttnType.UNDEFINED;
            } else {
                dtAttnType = getDtAttnTypeAnyOn(shift, attnDate, inTime, outTime);// DtAttnType.ABSENT;
            }

            ProcOutAttnDt pp;
            try {
                pp = em
                        .createQuery("FROM " + ProcOutAttnDt.class.getName() + " m WHERE m.employee=:employee AND m.attnDate=:attnDate",ProcOutAttnDt.class)
                        .setParameter("employee", poe.getEmployee())
                        .setParameter("attnDate", attnDate)
                        .getSingleResult();
            } catch (Exception e) {
                pp = null;
            }

            if (pp == null) {
                pp = new ProcOutAttnDt(poe.getEmployee(), attnDate);
            }

            pp.setDtAttnType(dtAttnType);
            pp.setInTime(inTime);
            pp.setOutTime(outTime);
            pp.setShift(shift);

            EntityTransaction tx = null;
            try {
                tx = em.getTransaction();
                tx .begin();
                //ProcOutRoster dfd=new ProcOutRoster(poe.getEmployee(), attnDate);
                //session.saveOrUpdate(dfd);
                em.merge(pp);
                tx.commit();
            } catch (Exception e) {
                System.out.println(" err db 8345: " + e);
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        em.close();
    }

    private DtAttnType getDtAttnTypeOnNoPunch(EntityManager em, Employee employee, Date attnDate) {

        try {
            ProcOutCalender pp =  em
                    .createQuery("FROM " + ProcOutCalender.class.getName() + " m WHERE trunc(m.procOutCalenderPK.calDate)=:calDate",ProcOutCalender.class)
                    .setParameter("calDate", attnDate)
                    .getSingleResult();

            if (pp != null && pp.getIsApplicable() != null && Objects.equals(pp.getIsApplicable(), Boolean.TRUE)) {
                return DtAttnType.OFF_DAY;
            }
        } catch (Exception e) {
            System.out.println("err in calender: " + e);
        }

        try {
            List<LeaveApp> pp =  em
                    .createQuery("FROM " + LeaveApp.class.getName() + " m WHERE m.employee=:employee AND :attnDate BETWEEN trunc(m.startDate) AND trunc(m.endDate)",LeaveApp.class)
                    .setParameter("employee", employee)
                    .setParameter("attnDate", attnDate)
                    .getResultList();

            if (pp != null && !pp.isEmpty()) {
                return DtAttnType.LEAVE;
            }
        } catch (Exception e) {
            System.out.println("err in leave app: " + e);
        }

        return DtAttnType.ABSENT;
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
