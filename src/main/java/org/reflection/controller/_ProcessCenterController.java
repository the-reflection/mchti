package org.reflection.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import net.sf.jasperreports.engine.JRException;
import org.reflection.dto._DualDTO;
import org.reflection.model.com.AdmProcess;
import org.reflection.model.com.AdmProcessDetail;
import org.reflection.model.com.AdmZoneType;
import org.reflection.service.AdmModuleService;
import org.reflection.service.AdmProcessService;
import org.reflection.service.AdmUtilProcess;
import org.reflection.service.ProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.reflection.service.ProcServiceDef;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/processCenter")
public class _ProcessCenterController extends _BaseController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private ProcService procService;
    @Autowired
    private ProcServiceDef procServiceDef;
    @Autowired
    private AdmProcessService admProcessService;
    @Autowired
    private AdmModuleService admModuleService;

    @GetMapping("/{code}")
    public String indexCode(@PathVariable("code") String code, ModelMap model) {
        AdmProcess bbb = admProcessService.findByCode(code);
        model.addAttribute("processFullName", bbb.getFullName());
        model.addAttribute("processId", bbb.getId());
        return "etc/dynamicFormProcess";
    }

    @GetMapping()
    public String index(ModelMap model) {

        List<_DualDTO> kk = new ArrayList();
        for (AdmProcess bbb : admProcessService.findAll()) {
            _DualDTO bbbm = new _DualDTO(bbb.getId(), bbb.getAdmModule() + "-" + bbb);
            kk.add(bbbm);
        }

        model.addAttribute("admModules", admModuleService.findAll());
        model.addAttribute("admProcesss", kk);
        return "etc/processCenter";
    }

    @GetMapping(value = "/getProcess", produces = "text/html;charset=utf-8")
    public @ResponseBody
    ResponseEntity<String> getProcess(@RequestParam(value = "module") BigInteger moduleId, final Locale locale) {

        System.out.println("finding getProcess: code: >" + moduleId + "<");

        StringBuilder sb = new StringBuilder();
        String uuuu = messageSource.getMessage("default.select.null", null, locale);

        sb.append("<option value='${null}'>").append(uuuu).append("</option>");

        if (moduleId == null) {
            for (AdmProcess bbb : admProcessService.findAll()) {
                sb.append("<option value='").append(bbb.getId()).append("'>").append(bbb.getAdmModule()).append('-').append(bbb).append("</option>");
            }
        } else {
            for (AdmProcess bbb : admProcessService.findAll()) {
                if (bbb.getAdmModule().getId().equals(moduleId)) {
                    sb.append("<option value='").append(bbb.getId()).append("'>").append(bbb).append("</option>");
                }
            }
        }
        //final HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(sb.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/getDynamicContent")
    public @ResponseBody
    ResponseEntity<String> getDynamicContent(@RequestParam(value = "processId") BigInteger processId) {

        System.out.println("finding getDynamicContent: code: >" + processId + "<");

        Map<String, String> allMap;

        allMap = AdmUtilProcess.getProcessPageMap(admProcessService.findById(processId));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return new ResponseEntity<>(objectMapper.writeValueAsString(allMap), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/search")
    public @ResponseBody
    ResponseEntity<String> search(
            @RequestParam(value = "strKeyVal") String strKeyVal,
            @RequestParam(value = "processId") BigInteger processId
    ) {

        System.out.println("finding search 789: code: " + processId);

        Map<String, String> allMap;
        Map<String, String> searcherIds;

        AdmProcess admProcess = admProcessService.findById(processId);

        allMap = AdmUtilProcess.getProcessPageMap(admProcess);
//        } catch (Exception e) {
//            System.out.println("err 849: " + e);
//            allMap = new LinkedHashMap();
//            allMap.put("error", "Process not found. " + e);
//        }

        String uuuu = allMap.get("searcherIds");

        Map user1 = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            user1 = objectMapper.readValue(uuuu, Map.class);
        } catch (Exception e) {
        }

        System.err.println("serchar 957 : " + user1 + " uuuu:" + uuuu);

        searcherIds = new LinkedHashMap<>(user1);

        Map<String, String> tblMap;

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> c = null;
        try {
            c = mapper.readValue(strKeyVal, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
        }
        //Map c = (Map) JSON.parse(strKeyVal);

        Map<String, Object> cc = new LinkedHashMap(c);

        for (String kss : searcherIds.keySet()) {
            Object kv = searcherIds.get(kss);
            if (kv.equals("DATE")) {

                SimpleDateFormat klkl = new SimpleDateFormat("dd/MM/yyyy");
                Object vvv;
                try {
                    vvv = klkl.parse((String) cc.get(kss));
                } catch (Exception eee) {
                    vvv = null;
                }
                cc.put(kss, vvv);
            }
        }

        tblMap = AdmUtilProcess.getTableOnly(admProcess, cc);

        // final HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.TEXT_HTML);
        // return new ResponseEntity<>(tblMap.toString(), headers, HttpStatus.CREATED);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return new ResponseEntity<>(objectMapper.writeValueAsString(tblMap), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/executeProcess")
    public @ResponseBody
    ResponseEntity<String> executeProcess(
            @RequestParam(value = "strKeyVal") String strKeyVal,
            @RequestParam(value = "porcTitleMApx") String porcTitleMApx,
            @RequestParam(value = "processId") BigInteger processId) {

        System.out.println("uuuuuuuu 252pm chk strKeyVal:" + strKeyVal);
        System.out.println("uuuuuuuu 252pm chk porcTitleMApx:" + porcTitleMApx);
        System.out.println("uuuuuuuu 252pm chk processId:" + processId);

        String porcTitleNw = "";
        String errMsg = "";
        String errMsgShow = "";
        Map<String, String> outMsg = new LinkedHashMap();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> c = null;
        try {
            c = mapper.readValue(strKeyVal, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
        }

        Map<String, Object> cothers = null;
        try {
            cothers = mapper.readValue(porcTitleMApx, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            System.out.println("err 345:" + e);
        }

        //Map c = (Map) JSON.parse(strKeyVal);
        //Map cothers = (Map) JSON.parse(porcTitleMApx);
        Map FIXED_PARAMs = (Map) cothers.get("FIXED_PARAM_VAL");
        String QU_PARAMs = (String) cothers.get("QU_PARAM_VAL");
        String PROC_BTN_ID = (String) cothers.get("PROC_BTN_ID");

        List FIXED_PARAMs_sl_by = new ArrayList();
        String FIXED_PARAMs_sl_bySCRPT = "";
        List QU_PARAMs_sl_by = new ArrayList();

        if (!QU_PARAMs.equals("")) {
            try {
                QU_PARAMs_sl_by.addAll(Arrays.asList(QU_PARAMs.split(",")));
            } catch (Exception ee) {
            }
        }

        AdmProcess admProcMst = admProcessService.findById(processId);// AdmProcess.executeQuery("FROM AdmProcess a WHERE a.id=" + processId);

        for (AdmProcessDetail nnnx : admProcMst.getAdmProcessDetails()) {

            String nnncmd = nnnx.getAdmParam().getCmd();
            String nnnwidgetIdentity = nnnx.getAdmParam().getParamName();
            //AdmWidgetType nnnwidgetType = nnnx.getAdmParam().getAdmWidgetType();

            if (nnnwidgetIdentity.equalsIgnoreCase(PROC_BTN_ID)) {
                porcTitleNw = nnncmd;
            }

            if (nnnx.getAdmZoneType() == AdmZoneType.PARAM_FIXED) {

                if (66 == 66) {

                    try {
                        Object jjj = FIXED_PARAMs.get(nnnwidgetIdentity);
                        FIXED_PARAMs_sl_by.add(jjj);
                        if (jjj.toString().isEmpty()) {
                            FIXED_PARAMs_sl_bySCRPT += "null,";
                        } else {
                            FIXED_PARAMs_sl_bySCRPT += "'" + jjj + "',";
                        }
                    } catch (Exception ee) {
                        System.out.println("err 81205: " + ee);
                    }
                }
            }
        }

        System.out.println("jjjjj FIXED_PARAMs_sl_bySCRPT: " + FIXED_PARAMs_sl_bySCRPT);

        int countsPass = 0;
        int countsFail = 0;

        long runById = -1L;//getCurrAdmUser().getId();

        String procLink = "<a href=" + " AdmProcess.findById(processId)" + "" + " target='_blank'>Process Output Link</a><br>";

        if (admProcMst.getQuery() == null || admProcMst.getQuery().trim().isEmpty()) {

            String title = admProcMst.getCmd();

            System.out.println("FIXED_PARAMs map:" + FIXED_PARAMs);
            Object[] para = new Object[3];
            int iiip = 0;
            for (Object object : FIXED_PARAMs.keySet()) {
                if (object.toString().endsWith("DATE")) {
                    try {
                        para[iiip] = dateFormat.parse(FIXED_PARAMs.get(object).toString());
                    } catch (Exception e) {
                        System.out.println("err in report calling date format : " + e);
                    }
                } else {
                    para[iiip] = FIXED_PARAMs.get(object);
                }
                iiip++;
            }

            if (title.equals("daily")) {
                procService.daily((Date) para[0]);//attnDate
            } else if (title.equals("dailyRange")) {
                procService.daily((Date) para[0], (Date) para[1]);//fromDate, toDate
            } else if (title.equals("refresh")) {
                procService.refresh();
            } else if (title.equals("delEmp")) {
                procService.empCascadeDeleteWithCode((String) para[0]);//P_EMP_CODE
            } else if (title.equals("genCal")) {
                procService.genCalender((Date) para[0], (Date) para[1]);////fromDate, toDate
            }

            if (11 == 12) {

                List lstObj = new ArrayList();
                lstObj.add(runById);
                lstObj.addAll(FIXED_PARAMs_sl_by);

                //lstObj.add(Sql.out(Sql.INTEGER.type));
                //lstObj.add(Sql.VARCHAR);
                try {

                    //	Thread th=new Thread(new Runnable(){
                    //	public void run(){
                    System.out.println("ibx-423 NO CMD MODE porcTitle: " + porcTitleNw + " param: " + lstObj);
//                    sql.call("{call " + porcTitleNw + "}",
//                            lstObj,
//                    {result1, result2 ->
//                        if (result1.toString().toInteger() == 1) {
//                            countsPass++
//                        }
//                    })
                    //	}
                    //	});
                    //th.start()

                } catch (Exception ex) {
                    countsFail++;

                    errMsgShow = errMsgShow + strErrOk(ex.toString()) + "<br>";
                    errMsg = errMsg + ex + "<br>";
                }

            }
            //outMsg.put("errMsg", errMsgShow + "<br><br> Must be off: " + "<br>" + errMsg);

            //outMsg.put("countsPass", countsPass + "");
            //outMsg.put("countsFail", countsFail + "");
            //outMsg.put("procOutLink", procLink);
        } else {

            for (Object obj : c.keySet()) {

                Map inrMapQu = (Map) c.get(obj);

                List lstObj = new ArrayList();

                lstObj.add(runById);
                lstObj.addAll(FIXED_PARAMs_sl_by);

                for (Object jkk : QU_PARAMs_sl_by) {
                    lstObj.add(inrMapQu.get(jkk));
                }

                //lstObj.add(Sql.out(Sql.INTEGER.type));
                //lstObj.add(Sql.VARCHAR);
                try {

                    //	Thread th=new Thread(new Runnable(){
                    //	public void run(){
                    System.out.println("ibx-423 porcTitle: " + porcTitleNw + " param: " + lstObj);
//                    sql.call("{call " + porcTitleNw + "}",
//                            lstObj,
//                    {result1, result2 ->
//                        if (result1.toString().toInteger() == 1) {
                    countsPass++;
//                        }
//                    })
                    //	}
                    //	});
                    //th.start()

                } catch (Exception ex) {
                    countsFail++;

                    errMsgShow = errMsgShow + strErrOk(ex.toString()) + "<br>";
                    errMsg = errMsg + ex + "<br>";
                }
            }

            outMsg.put("errMsg", errMsgShow + "<br><br>Must be off: " + "<br>" + errMsg);

            outMsg.put("countsPass", countsPass + "");
            outMsg.put("countsFail", countsFail + "");
            outMsg.put("procOutLink", procLink);
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return new ResponseEntity<>(objectMapper.writeValueAsString(outMsg), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("")
    public String processCenterPost(@RequestParam(value = "title") String title,
            @RequestParam(value = "P_ATTN_DATE") String P_ATTN_DATE) throws ServletException, IOException,
            ClassNotFoundException, SQLException, JRException {

        if (title.equals("daily")) {
            //procService.daily(P_ATTN_DATE);
        } else if (title.equals("refresh")) {
            //procService.refresh();
        }

        return "redirect:main";
    }

    String strErrOk(String hhh) {
        try {
            int innx = hhh.indexOf(":", hhh.indexOf("ORA-"));
            int innb = hhh.indexOf("ORA-", innx);
            String sss = hhh.substring(innx + 2, innb - 1);
            return sss;
        } catch (Exception ee) {
            return "mac: ERR: " + ee + ": " + hhh;
        }
    }
}
