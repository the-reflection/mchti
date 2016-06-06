package org.reflection.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class _OithReportRunnerController {

    @RequestMapping(value = "/reportRunner", method = RequestMethod.GET)
    public String getDownloadPage() {

        return "etc/reportRunnerPage";
    }

    @RequestMapping(value = "/report/pdf", method = RequestMethod.GET)
    public void doReportPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            ClassNotFoundException, SQLException, JRException {

        List<Map<String, String>> collDS = new ArrayList<>();//.findAll();
        for (int i = 0; i < 10; i++) {
            Map<String, String> m1 = new HashMap<>();
            m1.put("code", "00" + i);
            m1.put("title", "oith title " + i);
            collDS.add(m1);
        }

        JRDataSource ds = new JRBeanCollectionDataSource(collDS);

        // params is used for passing extra parameters
        Map params = new HashMap();

        // Create a JasperDesign object from the JRXMl file
        // You can also load the template by directly adding the actual path, i.e.
        JasperDesign jd = JRXmlLoader.load("/reports/tl_daily_all_emp_punch.jrxml");

        // Compile our report layout
        JasperReport jr = JasperCompileManager.compileReport(jd);

        // It needs a JasperReport layout and a datasource
        JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);

        // Create our output byte stream
        // This is the stream where the data will be written
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

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

        response.setHeader("Content-disposition", "attachment; filename=oith.pdf");
        // Make sure to set the correct content type
        // Each format has its own content type
        //response.setContentType("application/pdf");
        response.setContentType("application/x-download");

        response.setContentLength(baos.size());

        try {
            // Write to reponse stream
            ServletOutputStream outputStream = response.getOutputStream();
            baos.writeTo(outputStream);
            outputStream.flush();
        } catch (Exception e) {

        }
    }
}
