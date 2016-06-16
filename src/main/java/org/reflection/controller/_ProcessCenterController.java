package org.reflection.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import org.reflection.model.sample.ZxLookup;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.reflection.service.ProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class _ProcessCenterController {

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
            @RequestParam(value = "P_ATTN_DATE") String P_ATTN_DATE) throws ServletException, IOException,
            ClassNotFoundException, SQLException, JRException {


//        try {
//            //params.put("P_ATTN_DATE", dateFormat.parse(P_ATTN_DATE));
//        } catch (Exception e) {
//            System.out.println("err in report calling date format : " + e);
//        }
        //procService.daily("08-05-2016");
        if (title.equals("daily")) {
            procService.daily(P_ATTN_DATE);
        } else if (title.equals("refresh")) {
            procService.refresh();
        }

        return "redirect:homeSecure";
    }
}
