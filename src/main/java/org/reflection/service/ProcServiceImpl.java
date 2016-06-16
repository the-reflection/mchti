package org.reflection.service;

import java.io.IOException;
import java.io.InputStream;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.reflection.model.hcm.enums.AttnMode;
import org.reflection.model.hcm.proc.ProcOutCalender;
import org.reflection.model.hcm.proc.ProcOutRoster;
import org.reflection.model.security.AuthGender;
import org.reflection.model.security.AuthRole;
import org.reflection.model.security.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("procService")
@Transactional(readOnly = true)
public class ProcServiceImpl implements ProcService {

    private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

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
                AssignmentTl assignmentTl = (AssignmentTl) session.createQuery("FROM " + AssignmentTl.class.getName() + " m WHERE m.employee=:employee").setParameter("employee", emp).uniqueResult();//list().get(0);

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
                session.saveOrUpdate(pp);
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
            Long punchCount = null;
            Shift shift = poe.getShift();//(Shift) session.get(Shift.class, new BigInteger("1"));

            try {
                ProcOutRoster hh = (ProcOutRoster) session.createQuery("FROM " + ProcOutRoster.class.getName() + " m WHERE m.procOutRosterPK.employee=:employee AND TRUNC(m.procOutRosterPK.calcDate)=:calcDate")
                        .setParameter("employee", poe.getEmployee())
                        .setParameter("calcDate", attnDate)
                        .uniqueResult();
                shift = hh.getShift();//(Shift) session.get(Shift.class, new BigInteger("1"));

            } catch (Exception e) {
                System.out.println("err roster shift: " + e);
            }

            if (shift == null) {
                System.out.println("warning shift not assigned: " + poe.getFullName());
                continue;
            }
            
            try {

                Object[] ppx = (Object[]) session
                        .createQuery("SELECT MIN(m.procOutAttnDailyPK.transactionTime), MAX(m.procOutAttnDailyPK.transactionTime), COUNT(m.procOutAttnDailyPK.transactionTime) FROM "
                                + ProcOutAttnDaily.class.getName()
                                + " m WHERE m.procOutAttnDailyPK.employee=:employee AND TRUNC(m.procOutAttnDailyPK.transactionTime)=:transactionTime")
                        .setParameter("employee", poe.getEmployee())
                        .setParameter("transactionTime", attnDate)
                        .uniqueResult();
                inTime = (Date) ppx[0];
                outTime = (Date) ppx[1];
                punchCount = (Long) ppx[2];
            } catch (Exception e) {
                System.out.println("show mac err: " + e);
            }

            DtAttnType dtAttnType;
            if (inTime == null && outTime == null) {
                dtAttnType = getDtAttnTypeOnNoPunch(poe.getEmployee(), attnDate);// DtAttnType.ABSENT;
            } else if (punchCount == 1) {//inTime.equals(outTime)
                dtAttnType = DtAttnType.UNDEFINED;
            } else {
                dtAttnType = getDtAttnTypeAnyOn(shift, attnDate, inTime, outTime);// DtAttnType.ABSENT;
            }

            ProcOutAttnDt pp;
            try {
                pp = (ProcOutAttnDt) session
                        .createQuery("FROM " + ProcOutAttnDt.class.getName() + " m WHERE m.employee=:employee AND m.attnDate=:attnDate")
                        .setParameter("employee", poe.getEmployee())
                        .setParameter("attnDate", attnDate)
                        .uniqueResult();
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

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                //ProcOutRoster dfd=new ProcOutRoster(poe.getEmployee(), attnDate);
                //session.saveOrUpdate(dfd);
                session.saveOrUpdate(pp);
                tx.commit();
            } catch (Exception e) {
                System.out.println(" err db 8345: " + e);
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
        session.close();
    }

    private DtAttnType getDtAttnTypeOnNoPunch(Employee employee, Date attnDate) {

        try {
            ProcOutCalender pp = (ProcOutCalender) sessionFactory.getCurrentSession()
                    .createQuery("FROM " + ProcOutCalender.class.getName() + " m WHERE trunc(m.procOutCalenderPK.calDate)=:calDate")
                    .setParameter("calDate", attnDate)
                    .uniqueResult();

            if (pp != null && pp.getIsApplicable() != null && Objects.equals(pp.getIsApplicable(), Boolean.TRUE)) {
                return DtAttnType.OFF_DAY;
            }
        } catch (Exception e) {
            System.out.println("err in calender: " + e);
        }

        try {
            List<LeaveApp> pp = (List<LeaveApp>) sessionFactory.getCurrentSession()
                    .createQuery("FROM " + LeaveApp.class.getName() + " m WHERE m.employee=:employee AND :attnDate BETWEEN trunc(m.startDate) AND trunc(m.endDate)")
                    .setParameter("employee", employee)
                    .setParameter("attnDate", attnDate)
                    .list();

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

    @Override
    public Map doUploadExcelFile(InputStream fis) {
        Map<Integer, String> map = new LinkedHashMap<>();
        int pass = 0;
        int fail = 0;
        try {

            // FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<Row> rowIterator = mySheet.iterator();

            Session session = sessionFactory.openSession();

            int rowIndx = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // For each row, iterate through each columns
                Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
                int colIndx = 0;

                Map<String, Object> myMap = new LinkedHashMap<>();
                //int idx = 54500;
                while (cellIterator.hasNext()) {

                    org.apache.poi.ss.usermodel.Cell cell = cellIterator.next();

                    Object val = null; //   System.out.print("kkkkkkkkkkkk " + "\t" + cell.getCellType() + "\t" + cell.getCellStyle().dataFormatString + "\tcolx: " + map.get(colIndx) + "\t");

                    switch (cell.getCellType()) {

                        case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
                            val = cell.getBooleanCellValue();
                            break;

                        case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
                            String formt = cell.getCellStyle().getDataFormatString();

                            if (formt.indexOf('/') != -1) {
                                val = cell.getDateCellValue();
                            } else if (formt.equals("General") || formt.equals("@") || formt.equals("0")
                                    || formt.indexOf(".") == -1) {
//                            val = cell.getNumericCellValue()
                                val = (long) cell.getNumericCellValue();
                            } else {
                                val = cell.getNumericCellValue();
                            }
                            break;
                        case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
                            val = cell.getStringCellValue();
                            break;
                        default:
                            val = "";
                    }

                    if (rowIndx == 0) {
                        val = getCustomsColumnHeader(val);
                        map.put(colIndx, val.toString());
                    } else {
                        String ioi = map.get(colIndx);
                        myMap.put(ioi, val);
                    }
                    colIndx++;
                }

                if (rowIndx == 0) {
                    System.out.println(" head " + map);
                } else {

                    //String eee = myMap.get("empId").getClass().getSimpleName();
                    //String jjj = myMap.get("transactionTime").getClass().getSimpleName();
                    //System.out.println("EE: " + eee + " hhh: " + jjj);
                    Employee emp = null;
                    try {
                        emp = session.get(Employee.class, new java.math.BigInteger(myMap.get("acNo").toString()));
//                        emp = session.get(Employee.class, new java.math.BigInteger(myMap.get("empId").toString()));
//                        emp = session.load(Emp.class, new java.math.BigInteger(myMap.get("empId")));
                    } catch (Exception e) {
                    }

                    Date time = null;
                    try {
//                        time = (Date) myMap.get("transactionTime");
                        time = (Date) myMap.get("stime");
                    } catch (Exception e) {
                    }

                    if (emp != null && time != null) {
                        Transaction tx = null;
                        try {
                            tx = session.beginTransaction();

                            ProcOutAttnDaily procOutAttnDaily = new ProcOutAttnDaily(emp, time, AttnMode.FP);
                            System.out.println("saving obj: " + procOutAttnDaily);
                            //session.save(procOutAttnDaily);
                            //session.update(procOutAttnDaily);
                            session.saveOrUpdate(procOutAttnDaily);
                            tx.commit();
                            pass++;
                            //idx++;
                        } catch (Exception e) {
                            System.out.println(" err db 5456: " + e);
                            if (tx != null) {
                                tx.rollback();
                            }

                            fail++;
                        }
                    }

                    System.out.println(" data " + myMap);
                }
                rowIndx++;
            }
            session.close();
        } catch (IOException | HibernateException e) {
            System.out.println("ok file  rrr err" + e);
        }
        Map<String, String> mapx = new LinkedHashMap<>();
        mapx.put("Pass", pass + "");
        mapx.put("Fail", fail + "");
        return mapx;
    }

    private String getCustomsColumnHeader(final Object excelCellValue) {//ALl     LooKUP(-GENDER~,ID, //  allLookupGenderId

        if (excelCellValue == null || excelCellValue.toString().trim().isEmpty()) {
            return "";
        }

        String xuy = excelCellValue.toString().toLowerCase();
        StringTokenizer st = new StringTokenizer(xuy, " -~/\\()_,.");
        String ret = st.nextToken(); //all

        while (st.hasMoreTokens()) {
            String tt = st.nextToken();
            String fch = tt.charAt(0) + "";
            ret += fch.toUpperCase() + tt.substring(1);
        }
        return ret;
    }

    @Override
    public void dummyUserData() {
        Session session = sessionFactory.openSession();
       
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            AuthRole authRole = new AuthRole("ADMIN");
            //authRole.setId(new BigInteger("1"));
            session.save(authRole);

            Set<AuthRole> authRoles = new LinkedHashSet<>();
            authRoles.add(authRole);
//
            AuthUser authUser1 = new AuthUser();
            //authUser1.setId(new BigInteger("1"));
            authUser1.setPassword("123");
            authUser1.setUsername("mac");
            authUser1.setEnabled(true);
            authUser1.setAccountNonExpired(false);
            authUser1.setAccountNonLocked(false);
            authUser1.setCredentialsNonExpired(false);
            authUser1.setAuthRoles(authRoles);
            authUser1.setDisplayName("Manik");
            authUser1.setGender(AuthGender.MALE);
            authUser1.setFullName("Mohammad Badiuzzaman");
            authUser1.setEmail("manikmonir@gmail.com");
            session.save(authUser1);

            AuthUser authUser2 = new AuthUser();
            authUser2.setPassword("123");
            authUser2.setUsername("saif_hmk");
            authUser2.setEnabled(true);
            authUser2.setAccountNonExpired(false);
            authUser2.setAccountNonLocked(false);
            authUser2.setCredentialsNonExpired(false);
            authUser2.setAuthRoles(authRoles);
            authUser2.setDisplayName("Saif");
            authUser2.setGender(AuthGender.MALE);
            authUser2.setFullName("MD. HOSHEN MAHMUD KHAN");
            authUser2.setEmail("saif_hmk@live.com");
            session.saveOrUpdate(authUser2);

            AuthUser authUser3 = new AuthUser();
            authUser3.setPassword("123");
            authUser3.setUsername("anis");
            authUser3.setEnabled(true);
            authUser3.setAccountNonExpired(false);
            authUser3.setAccountNonLocked(false);
            authUser3.setCredentialsNonExpired(false);
            authUser3.setAuthRoles(authRoles);
            authUser3.setDisplayName("Anis");
            authUser3.setGender(AuthGender.MALE);
            authUser3.setFullName("Mohammad Anisur Rahman Khan");
            authUser3.setEmail("anis.object@gmail.com");
            session.saveOrUpdate(authUser3);

            tx.commit();
        } catch (Exception e) {
            System.out.println(" err db usr crete: " + e);
            if (tx != null) {
                tx.rollback();
            }
        }
       
        session.close();
    }
}
