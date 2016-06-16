package org.reflection.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import org.reflection.model.sample.ZxLookup;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class _ReportCenterController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = "/reportCenter", method = RequestMethod.GET)
    public String reportCenter(ModelMap model) {

        // model.addAttribute(MODEL, new ZxLookup());
        // Map<String, String> jjj = new LinkedHashMap<>();
        // jjj.put("title", "");
        model.addAttribute("rrr", new ZxLookup());
        // model.addAttribute("reportName", "");
        return "etc/reportCenter";
    }

    @RequestMapping(value = "/reportCenter", method = RequestMethod.POST)
    public void doReportPdf(@RequestParam(value = "title") String title,
            @RequestParam(value = "P_ATTN_DATE") String P_ATTN_DATE, HttpServletRequest request, HttpServletResponse response) {

        //JRDataSource ds = new JRBeanCollectionDataSource(collDS);
        // params is used for passing extra parameters
        File file = new File(request.getServletContext().getRealPath("/"));

        Map params = new HashMap();

        params.put("REPORT_PATH", file + "/reports/");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            params.put("P_ATTN_DATE", dateFormat.parse(P_ATTN_DATE));
        } catch (Exception e) {

            System.out.println("err in report calling date format : " + e);
        }

        System.out.println("report path yaaa: " + file + " bbb:" + title);

        ByteArrayOutputStream baos = null;
        try {

            // Create a JasperDesign object from the JRXMl file
            // You can also load the template by directly adding the actual path, i.e.
            JasperDesign jd = JRXmlLoader.load(file + "/reports/" + title + ".jrxml");

            // Compile our report layout
            JasperReport jr = JasperCompileManager.compileReport(jd);

            // It needs a JasperReport layout and a datasource
            JasperPrint jp = JasperFillManager.fillReport(jr, params, dataSource.getConnection());

            // Create our output byte stream
            // This is the stream where the data will be written
            baos = new ByteArrayOutputStream();

            // Export to output stream
            // The data will be exported to the ByteArrayOutputStream baos
            // We delegate the exporting  to a custom Exporter instance
            // The Exporter is a wrapper class I made. Feel free to remove or modify it
            // Create a JRXlsExporter instance
            JRAbstractExporter exporter = new JRPdfExporter();

            // Here we assign the parameters jp and baos to the exporter
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

            // Retrieve the exported report in XLS format
            exporter.exportReport();

            response.setHeader("Content-disposition", "attachment; filename=" + title + ".pdf");
            // Make sure to set the correct content type
            // Each format has its own content type
            //response.setContentType("application/pdf");
            response.setContentType("application/x-download");

            response.setContentLength(baos.size());
        } catch (Exception e) {
        }

        if (baos != null) {
            try {
                // Write to reponse stream
                ServletOutputStream outputStream = response.getOutputStream();
                baos.writeTo(outputStream);
                outputStream.flush();
            } catch (Exception e) {

            }
        }
    }
}
