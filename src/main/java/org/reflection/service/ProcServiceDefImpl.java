package org.reflection.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.reflection.model.security.AuthGender;
import org.reflection.model.security.AuthMenu;
import org.reflection.model.security.AuthRole;
import org.reflection.model.security.AuthUser;
import java.util.SortedSet;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.reflection.model.com.Employee;
import org.reflection.model.hcm.enums.AttnMode;
import org.reflection.model.hcm.proc.ProcOutAttnDaily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("procServiceDef")
@Transactional(readOnly = true)
public class ProcServiceDefImpl implements ProcServiceDef {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void fastMenuGen(SortedSet<String> list) {

        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();

            for (String string : list) {
                String dn = null;
                try {
                    dn = string.substring(1, string.lastIndexOf("index") - 1);
                } catch (Exception e) {
                    //System.out.println("hhhhhhhhhhhhhhhh" + e + " bb: " + string);
                }

                if (dn == null || dn.isEmpty()) {
                    continue;
                }

                dn = getShowTitle(dn);
                AuthMenu authMenu = new AuthMenu(dn, string);
                authMenu.setDisplayIconClass("fa fa-list");
                authMenu.setIsActive(Boolean.TRUE);
                em.persist(authMenu);
            }

            tx.commit();
        } catch (Exception e) {
            System.out.println("macsay: err db menu create: " + e);
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }

    }

    @Override
    public void dummyUserData() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = null;

        try {
            tx = em.getTransaction();
            tx.begin();

            AuthRole authRoleAdmin = new AuthRole("ADMIN");
            em.persist(authRoleAdmin);
            AuthRole authRoleUser = new AuthRole("USER");
            em.persist(authRoleUser);

            Set<AuthRole> authRoles = new LinkedHashSet<>();
            authRoles.add(authRoleAdmin);
            authRoles.add(authRoleUser);

            AuthUser authUser1 = new AuthUser();
            authUser1.setPassword("123");
            authUser1.setUsername("mac");
            authUser1.setPicFile("aaaa.jpg");
            authUser1.setEnabled(true);
            authUser1.setAccountNonExpired(false);
            authUser1.setAccountNonLocked(false);
            authUser1.setCredentialsNonExpired(false);
            authUser1.setAuthRoles(authRoles);
            authUser1.setDisplayName("Manik");
            authUser1.setGender(AuthGender.MALE);
            authUser1.setFullName("Mohammad Badiuzzaman");
            authUser1.setEmail("manikmonir@gmail.com");
            em.persist(authUser1);

            authRoles = new LinkedHashSet<>();
            authRoles.add(authRoleUser);

            AuthUser authUser2 = new AuthUser();
            authUser2.setPassword("123");
            authUser2.setUsername("saif_hmk");
            authUser2.setPicFile("bbbb.jpg");
            authUser2.setEnabled(true);
            authUser2.setAccountNonExpired(false);
            authUser2.setAccountNonLocked(false);
            authUser2.setCredentialsNonExpired(false);
            authUser2.setAuthRoles(authRoles);
            authUser2.setDisplayName("Saif");
            authUser2.setGender(AuthGender.MALE);
            authUser2.setFullName("MD. HOSHEN MAHMUD KHAN");
            authUser2.setEmail("saif_hmk@live.com");
            em.persist(authUser2);

            AuthUser authUser3 = new AuthUser();
            authUser3.setPassword("123");
            authUser3.setUsername("anis");
            authUser3.setPicFile("cccc.jpg");
            authUser3.setEnabled(true);
            authUser3.setAccountNonExpired(false);
            authUser3.setAccountNonLocked(false);
            authUser3.setCredentialsNonExpired(false);
            authUser3.setAuthRoles(authRoles);
            authUser3.setDisplayName("Anis");
            authUser3.setGender(AuthGender.MALE);
            authUser3.setFullName("Mohammad Anisur Rahman Khan");
            authUser3.setEmail("anis.object@gmail.com");
            em.persist(authUser3);

            tx.commit();
        } catch (Exception e) {
            System.out.println("macsay: err db users create: " + e);
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }

    }

    private static String getShowTitle(String fdf) {

        if (fdf == null) {
            return "";
        }

        String uuu = "";
        for (int i = 0; i < fdf.length(); i++) {
            char hhh = fdf.charAt(i);

            if (i == 0) {
                uuu += ("" + hhh).toUpperCase();
                continue;
            }

            if (hhh >= 65 && hhh <= (65 + 26)) {
                uuu += " " + hhh;
            } else {
                uuu += "" + hhh;
            }
        }

        return uuu;
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

            EntityManager em = entityManagerFactory.createEntityManager();

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
                        emp = em.find(Employee.class, new java.math.BigInteger(myMap.get("acNo").toString()));
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
                        EntityTransaction tx = null;
                        try {
                            tx = em.getTransaction();//beginTransaction();
                            tx.begin();
                            ProcOutAttnDaily procOutAttnDaily = new ProcOutAttnDaily(emp, time, AttnMode.FP);
                            System.out.println("saving obj: " + procOutAttnDaily);
                            //session.save(procOutAttnDaily);
                            //session.update(procOutAttnDaily);
                            em.merge(procOutAttnDaily);
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
            em.close();
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
}
