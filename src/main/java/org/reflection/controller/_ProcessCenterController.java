package org.reflection.controller;

import java.io.IOException;
import org.reflection.model.sample.ZxLookup;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import net.sf.jasperreports.engine.JRException;
import org.reflection.service.ProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class _ProcessCenterController {

    private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private ProcService procService;

    @RequestMapping(value = "/processCenter", method = RequestMethod.GET)
    public String reportCenter(ModelMap model) {

        // model.addAttribute(MODEL, new ZxLookup());
        // Map<String, String> jjj = new LinkedHashMap<>();
        // jjj.put("title", "");
        model.addAttribute("rrr", new ZxLookup());
        // model.addAttribute("reportName", "");
        return "etc/processCenter";
    }

    @RequestMapping(value = "/processCenter", method = RequestMethod.POST)
    public String doReportPdf(@RequestParam(value = "title") String title,
            @RequestParam(value = "P_ATTN_DATE") String P_ATTN_DATE,
            @RequestParam(value = "P_EMP_CODE") String P_EMP_CODE,
            @RequestParam(value = "P_FROM_DATE") String P_FROM_DATE,
            @RequestParam(value = "P_TO_DATE") String P_TO_DATE
    ) throws ServletException, IOException,
            ClassNotFoundException, SQLException, JRException {

        Date attnDate = null;
        try {
            attnDate = SIMPLE_DATE_FORMAT.parse(P_ATTN_DATE);
        } catch (Exception e) {
        }
        Date fromDate = null;
        try {
            fromDate = SIMPLE_DATE_FORMAT.parse(P_FROM_DATE);
        } catch (Exception e) {
        }
        Date toDate = null;
        try {
            toDate = SIMPLE_DATE_FORMAT.parse(P_TO_DATE);
        } catch (Exception e) {
        }

        if (title.equals("daily")) {
            procService.daily(attnDate);
        } else if (title.equals("dailyRange")) {
            procService.daily(fromDate, toDate);
        } else if (title.equals("refresh")) {
            procService.refresh();
        } else if (title.equals("delEmp")) {
            procService.empCascadeDeleteWithCode(P_EMP_CODE);
        } else if (title.equals("genCal")) {
            procService.genCalender(fromDate, toDate);
        }

        return "redirect:processCenter";
    }
}
