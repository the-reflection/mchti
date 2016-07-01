package org.reflection.service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
import org.apache.commons.lang.time.DateUtils;
import org.reflection.model.hcm.enums.HolidayType;
import org.reflection.model.hcm.prl.AssignmentPrl;
import org.reflection.model.hcm.proc.ProcOutCalender;
import org.reflection.model.hcm.proc.ProcOutCalenderPK;
import org.reflection.model.hcm.proc.ProcOutRoster;
import org.reflection.model.hcm.tl.CustomizedHolidayApp;
import org.reflection.model.hcm.tl.GeneralHoliday;
import org.reflection.model.hcm.tl.ManualAttnDaily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("procService")
@Transactional(readOnly = true)
public class ProcServiceImpl implements ProcService {

    private static final Format FORMAT_DAY = new SimpleDateFormat("EEEE");

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void refresh() {

        EntityManager em = entityManagerFactory.createEntityManager();

        List<Employee> emps = em.createQuery("SELECT c FROM " + Employee.class.getName() + " c ", Employee.class).getResultList();

        for (Employee emp : emps) {

            ProcOutEmp pp;
            try {

                pp = em.createQuery(
                        "SELECT m FROM " + ProcOutEmp.class.getName() + " m WHERE m.employee=:employee", ProcOutEmp.class)
                        .setParameter("employee", emp)
                        .getSingleResult();

            } catch (Exception e) {
                pp = null;
            }

            if (pp == null) {
                pp = new ProcOutEmp();
            }

            //pp.setId(emp.getId());
            pp.setEmployee(emp);
            pp.setDob(emp.getDob());
            pp.setDoj(emp.getDoj());
            pp.setGender(emp.getGender());
            pp.setCode(emp.getCode());
            pp.setAddress(emp.getAddress());
            pp.setEmail(emp.getEmail());
            pp.setPicFile(emp.getPicFile());
            pp.setFullName(emp.getFullName());

            try {
                AssignmentHr assignmentHr = em.createQuery("FROM " + AssignmentHr.class.getName() + " m WHERE m.employee=:employee", AssignmentHr.class).setParameter("employee", emp).getSingleResult();

                if (assignmentHr != null) {

                    pp.setDepartment(assignmentHr.getDepartment());
                    pp.setDesignation(assignmentHr.getDesignation());
                    pp.setEmpGroup(assignmentHr.getEmpGroup() == null ? EmpGroup.ACTIVE : assignmentHr.getEmpGroup());
                }
            } catch (Exception e) {
                System.out.println("err refsh assignmentHr:" + e);
            }

            try {
                AssignmentTl assignmentTl = em.createQuery("FROM " + AssignmentTl.class.getName() + " m WHERE m.employee=:employee", AssignmentTl.class).setParameter("employee", emp).getSingleResult();

                if (assignmentTl != null) {
                    pp.setRoster(assignmentTl.getRoster());
                    pp.setShift(assignmentTl.getShift());
                    pp.setWeekendShiftOffDay(assignmentTl.getWeekendShiftOffDay());
                    pp.setIsOvertime(assignmentTl.getIsOvertime());
                }
            } catch (Exception e) {
                System.out.println("err refsh assignmentTl:" + e);
            }

            try {
                em.getTransaction().begin();
                em.persist(pp);
                em.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("errr too 76544: " + e);
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            }

            System.out.println("Manik too 2345 agn: " + pp);
        }
        em.close();
    }

    @Override
    public void daily(Date fromDate, Date toDate) {
        long diff = Math.abs(toDate.getTime() - fromDate.getTime());
        long diffDays = diff / (24 * 60 * 60 * 1000);
        try {
            for (int i = 0; i < diffDays + 1; i++) {
                Date xDate = addDays(fromDate, i);
                daily(xDate);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void daily(Date attnDate) {

        EntityManager em = entityManagerFactory.createEntityManager();

        List<ProcOutEmp> emps = em.createQuery("FROM " + ProcOutEmp.class.getName() + " m", ProcOutEmp.class).getResultList();

        for (ProcOutEmp poe : emps) {

            Shift shift = poe.getShift();//(Shift) session.get(Shift.class, new BigInteger("1"));

            try {
                ProcOutRoster hh = em.createQuery("FROM " + ProcOutRoster.class.getName() + " m WHERE m.procOutRosterPK.employee=:employee AND TRUNC(m.procOutRosterPK.calcDate)=:calcDate", ProcOutRoster.class)
                        .setParameter("employee", poe.getEmployee())
                        .setParameter("calcDate", attnDate, TemporalType.DATE)
                        .getSingleResult();
                shift = hh.getShift();//(Shift) session.get(Shift.class, new BigInteger("1"));

            } catch (Exception e) {
                System.out.println("err roster shift: " + e);
            }

            if (shift == null) {
                System.out.println("warning shift not assigned: " + poe.getFullName());
                continue;
            }

            Date inTime = null;
            Date outTime = null;

            String remarks = "";
            ManualAttnDaily manualAttnDaily = null;
            try {

                manualAttnDaily = em
                        .createQuery("SELECT m FROM "
                                + ManualAttnDaily.class.getName()
                                + " m WHERE m.employee=:employee AND TRUNC(m.attnDate)=:attnDate", ManualAttnDaily.class)
                        .setParameter("employee", poe.getEmployee())
                        .setParameter("attnDate", attnDate, TemporalType.DATE)
                        .getSingleResult();

                inTime = new Date(attnDate.getTime() + manualAttnDaily.getInTime().getTime() + 6 * 60 * 60 * 1000);
                outTime = new Date(attnDate.getTime() + manualAttnDaily.getOutTime().getTime() + 6 * 60 * 60 * 1000);

            } catch (Exception e) {
                System.out.println("show manual entry err: " + e);
            }

            Long punchCount = null;

            if (manualAttnDaily != null && inTime != null && outTime != null) {
                remarks += "Manual IN/OUT given By " + manualAttnDaily.getEntryBy();
            } else {

                try {

                    Object[] ppx = (Object[]) em
                            .createQuery("SELECT MIN(m.procOutAttnDailyPK.transactionTime), MAX(m.procOutAttnDailyPK.transactionTime), COUNT(m.procOutAttnDailyPK.transactionTime) FROM "
                                    + ProcOutAttnDaily.class.getName()
                                    + " m WHERE m.procOutAttnDailyPK.employee=:employee AND TRUNC(m.procOutAttnDailyPK.transactionTime)=:transactionTime")
                            .setParameter("employee", poe.getEmployee())
                            .setParameter("transactionTime", attnDate, TemporalType.DATE)
                            .getSingleResult();

                    if (inTime == null) {
                        inTime = (Date) ppx[0];
                    } else {
                        remarks += "Manual IN given By " + manualAttnDaily.getEntryBy();
                    }
                    if (outTime == null) {
                        outTime = (Date) ppx[1];
                    } else {
                        remarks += "Manual OUT given By " + manualAttnDaily.getEntryBy();
                    }
                    punchCount = (Long) ppx[2];
                } catch (Exception e) {
                    System.out.println("show mac err: " + e);
                }
            }

            DtAttnType dtAttnType;
            if (inTime == null && outTime == null) {
                dtAttnType = getDtAttnTypeOnNoPunch(em, poe, attnDate);// DtAttnType.ABSENT;
            } else if (punchCount != null && punchCount == 1) {//inTime.equals(outTime)
                dtAttnType = DtAttnType.UNDEFINED;
            } else {
                dtAttnType = getDtAttnTypeAnyOn(shift, attnDate, inTime, outTime);// DtAttnType.ABSENT;
            }

            ProcOutAttnDt pp;
            try {
                pp = em
                        .createQuery("FROM " + ProcOutAttnDt.class.getName() + " m WHERE m.procOutAttnDtPK.employee=:employee AND m.procOutAttnDtPK.attnDate=:attnDate", ProcOutAttnDt.class)
                        .setParameter("employee", poe.getEmployee())
                        .setParameter("attnDate", attnDate, TemporalType.DATE)
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
            pp.setRemarks(remarks);

            try {
                em.getTransaction().begin();
                em.persist(pp);
                em.getTransaction().commit();
            } catch (Exception e) {
                System.out.println(" err db 8345: " + e + " hhh: " + pp);
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            }
        }
        em.close();
    }

    private DtAttnType getDtAttnTypeOnNoPunch(EntityManager em, ProcOutEmp procOutEmp, Date attnDate) {
        String sss = null;
        try {
            sss = FORMAT_DAY.format(attnDate).trim().toUpperCase();
        } catch (Exception e) {
            System.out.println("err self weekend or offday: " + e);
        }

        if (sss != null && procOutEmp.getWeekendShiftOffDay().toString().equals(sss)) {
            return DtAttnType.WEEKEND;
        }
//        if (procOutEmp.getWeekendShiftOffDay().toString().equals(sss)) {
//            //  mmm   return DtAttnType.SHIFT_OFF_DAY;
//        }

        ProcOutCalender pp = null;
        try {
            pp = em
                    .createQuery("SELECT m FROM " + ProcOutCalender.class.getName() + " m WHERE m.isApplicable=true AND trunc(m.procOutCalenderPK.calDate)=:calDate", ProcOutCalender.class)
                    .setParameter("calDate", attnDate, TemporalType.DATE)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("err in calender: " + e);
        }
        if (pp != null) {

            if (null != pp.getHolidayType()) {
                switch (pp.getHolidayType()) {
                    case GOVERNMENT:
                        return DtAttnType.GOVERNMENT_HOLIDAY;
                    case INTERNATIONAL:
                        return DtAttnType.INTERNATIONAL_HOLIDAY;
                    case OFFICIAL:
                        return DtAttnType.OFFICIAL_HOLIDAY;
                    default:
                        break;
                }
            }
        }

        List<LeaveApp> ppx = null;
        try {
            ppx = em
                    .createQuery("SELECT m FROM " + LeaveApp.class.getName() + " m WHERE m.employee=:employee AND :attnDate BETWEEN trunc(m.startDate) AND trunc(m.endDate)", LeaveApp.class)
                    .setParameter("employee", procOutEmp.getEmployee())
                    .setParameter("attnDate", attnDate, TemporalType.DATE)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("err in leave app: " + e);
        }

        if (ppx != null && !ppx.isEmpty()) {
            return DtAttnType.LEAVE;
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

    @Override
    public void empCascadeDeleteWithCode(String empCode) {

        if (empCode == null || empCode.isEmpty()) {
            return;
        }

        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            Employee emp = em.createQuery("SELECT x FROM " + Employee.class.getName() + " x WHERE x.code=:code", Employee.class).setParameter("code", empCode).getSingleResult();

            int deletedCount1 = em.createQuery("DELETE FROM " + AssignmentTl.class.getName() + " x WHERE x.employee=:employee").setParameter("employee", emp).executeUpdate();
            int deletedCount2 = em.createQuery("DELETE FROM " + AssignmentPrl.class.getName() + " x WHERE x.employee=:employee").setParameter("employee", emp).executeUpdate();
            int deletedCount3 = em.createQuery("DELETE FROM " + AssignmentHr.class.getName() + " x WHERE x.employee=:employee").setParameter("employee", emp).executeUpdate();
            int deletedCount4 = em.createQuery("DELETE FROM " + ProcOutRoster.class.getName() + " x WHERE x.procOutRosterPK.employee=:employee").setParameter("employee", emp).executeUpdate();
            int deletedCount5 = em.createQuery("DELETE FROM " + ProcOutAttnDaily.class.getName() + " x WHERE x.procOutAttnDailyPK.employee=:employee").setParameter("employee", emp).executeUpdate();
            int deletedCount6 = em.createQuery("DELETE FROM " + ProcOutAttnDt.class.getName() + " x WHERE x.procOutAttnDtPK.employee=:employee").setParameter("employee", emp).executeUpdate();
            int deletedCount7 = em.createQuery("DELETE FROM " + ProcOutEmp.class.getName() + " x WHERE x.employee=:employee").setParameter("employee", emp).executeUpdate();

            //7 table del
            //AssignmentTl, AssignmentPrl, AssignmentHr, ProcOutRoster, ProcOutEmp, ProcOutAttnDt, ProcOutAttnDaily
            em.remove(emp);

            em.getTransaction().commit();

            System.out.println("emp del ok: " + empCode);
        } catch (Exception e) {
            System.out.println("err del emp: " + e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }

    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    @Override
    public void genCalender(Date fromDate, Date toDate) {

        EntityManager em = entityManagerFactory.createEntityManager();

        long diff = Math.abs(toDate.getTime() - fromDate.getTime());
        long diffDays = diff / (24 * 60 * 60 * 1000);
        try {
            em.getTransaction().begin();
            for (int i = 0; i < diffDays + 1; i++) {
                Date xDate = addDays(fromDate, i);

                ProcOutCalender mm;
                try {
                    mm = em.createQuery(
                            "SELECT m FROM " + ProcOutCalender.class.getName() + " m WHERE m.procOutCalenderPK.calDate=:xDate", ProcOutCalender.class)
                            .setParameter("xDate", xDate)
                            .getSingleResult();
                } catch (Exception e) {
                    mm = null;
                }

                if (mm == null) {
                    mm = new ProcOutCalender(new ProcOutCalenderPK(xDate));
                }

                try {
                    CustomizedHolidayApp ccc = em
                            .createQuery("SELECT x FROM " + CustomizedHolidayApp.class.getName() + " x WHERE :xDate BETWEEN x.startDate AND x.endDate", CustomizedHolidayApp.class)
                            .setParameter("xDate", xDate)
                            .getSingleResult();
                    System.out.println("in date lopp ccc " + ccc + " kkk " + ccc);
                    if (ccc != null) {
                        mm.setHolidayType(ccc.getHolidayType());
                        mm.setIsApplicable(Boolean.TRUE);
                    }
                } catch (Exception e) {
                }

                if (mm.getIsApplicable() == null) {
                    try {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(xDate);
                        int onDay = cal.get(Calendar.DAY_OF_MONTH);
                        int onMonth = cal.get(Calendar.MONTH) + 1;
                        GeneralHoliday gh = em
                                .createQuery("SELECT x FROM " + GeneralHoliday.class.getName() + " x WHERE x.onDay=:onDay AND x.onMonth=:onMonth AND x.isActive=true", GeneralHoliday.class)
                                .setParameter("onDay", onDay)
                                .setParameter("onMonth", onMonth)
                                .getSingleResult();
                        if (gh != null) {
                            mm.setHolidayType(gh.getHolidayType());
                            mm.setIsApplicable(Boolean.TRUE);
                        }
                    } catch (Exception e) {
                    }
                }
                em.persist(mm);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("err genCalender: " + e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }

    }

}
