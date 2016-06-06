package org.reflection.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.reflection.model.com.Employee;
import org.reflection.model.hcm.enums.AttnMode;
import org.reflection.model.hcm.proc.ProcOutAttnDaily;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/excelFileUpload")
public class ExcelFileUploadController {

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getQueryRunnerPage() {
        return "etc/excelFileUpload";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String upload(
            ModelMap model,
            RedirectAttributes attributes,
            MultipartHttpServletRequest request) {

        MultipartFile multipartFile = request.getFile("file");

        Map hhh = null;
        if (multipartFile != null && multipartFile.getSize() > 0) {

            System.out.println("ok file got" + multipartFile);
            try {
                hhh = getMap(multipartFile.getInputStream());
            } catch (Exception e) {
                System.out.println("ok file err" + e);
            }

        }

        model.addAttribute("result", hhh);

        return "etc/excelFileUpload";
    }

    private Map getMap(InputStream fis) {
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

                    String eee = myMap.get("empId").getClass().getSimpleName();
                    String jjj = myMap.get("transactionTime").getClass().getSimpleName();
                    System.out.println("EE: " + eee + " hhh: " + jjj);
                    Transaction tx = session.beginTransaction();
                    //tx.setTimeout(5);

                    Employee emp = null;
                    try {
                        emp = session.get(Employee.class, new java.math.BigInteger(myMap.get("empId").toString()));
//                        emp = session.load(Emp.class, new java.math.BigInteger(myMap.get("empId")));
                    } catch (Exception e) {
                    }

                    Date time = null;
                    try {
                        time = (Date) myMap.get("transactionTime");
                    } catch (Exception e) {
                    }

                    if (emp != null && time != null) {
                        try {
                            ProcOutAttnDaily procOutAttnDaily = new ProcOutAttnDaily(emp, time, AttnMode.FP);

                            //session.refresh(procOutAttnDaily);
                            System.out.println("saving obj: " + procOutAttnDaily);
                            session.save(procOutAttnDaily);
                            tx.commit();
                            pass++;
                            //idx++;
                        } catch (Exception e) {
                            System.out.println(" err db 5456: " + e);
                            tx.rollback();

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
}
