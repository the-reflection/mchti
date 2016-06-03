package mchti.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class _OithReportRunnerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(_OithReportRunnerController.class);

    enum ReportOutput {

        PDF, XLS, CSV, RTF, HTM, TXT
    }

    @RequestMapping(value = "/reportRunner", method = RequestMethod.GET)
    public String getDownloadPage() {
        LOGGER.debug("Received request to show download page");

        return "etc/reportRunnerPage";
    }

    @RequestMapping(value = "/report/xls", method = RequestMethod.GET)
    public void doReportXls(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            ClassNotFoundException, SQLException, JRException {

        caller(request, response, _OithReportRunnerController.ReportOutput.XLS);
    }

    @RequestMapping(value = "/report/pdf", method = RequestMethod.GET)
    public void doReportPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            ClassNotFoundException, SQLException, JRException {

        caller(request, response, _OithReportRunnerController.ReportOutput.PDF);
    }

    @RequestMapping(value = "/report/csv", method = RequestMethod.GET)
    public void doReportCsv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            ClassNotFoundException, SQLException, JRException {

        caller(request, response, _OithReportRunnerController.ReportOutput.CSV);
    }

    @RequestMapping(value = "/report/htm", method = RequestMethod.GET)
    public void doReportHtml(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            ClassNotFoundException, SQLException, JRException {

        caller(request, response, _OithReportRunnerController.ReportOutput.HTM);
    }

    @RequestMapping(value = "/report/rtf", method = RequestMethod.GET)
    public void doReportRtf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            ClassNotFoundException, SQLException, JRException {

        caller(request, response, _OithReportRunnerController.ReportOutput.RTF);
    }

    @RequestMapping(value = "/report/txt", method = RequestMethod.GET)
    public void doReportText(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,
            ClassNotFoundException, SQLException, JRException {

        caller(request, response, _OithReportRunnerController.ReportOutput.TXT);
    }

    void caller(HttpServletRequest request, HttpServletResponse response, _OithReportRunnerController.ReportOutput reportOutput) throws ServletException, IOException,
            ClassNotFoundException, SQLException, JRException {
        LOGGER.debug("Received request to download report");

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

        File file = new File(request.getServletContext().getRealPath("/")
                + File.separator
                + "repositories"
                + File.separator
                + "reports"
                + File.separator
                + "oith.jrxml");
        // Create a JasperDesign object from the JRXMl file
        // You can also load the template by directly adding the actual path, i.e.
        JasperDesign jd = JRXmlLoader.load(file);//"\\src\\main\\webapp\\repositories\\reports\\oith.jrxml");
        //JasperDesign jd = JRXmlLoader.load("D:\\_OITH_OUTPUT\\OITH_TEST_SAMPLE\\src\\main\\webapp\\repositories\\reports\\oith.jrxml");

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
        if (reportOutput.equals(_OithReportRunnerController.ReportOutput.PDF)) {
            exportPDF(response, "testoith", jp, baos);
        } else if (reportOutput.equals(_OithReportRunnerController.ReportOutput.XLS)) {
            exportXLS(response, "testoith", jp, baos);
        } else if (reportOutput.equals(_OithReportRunnerController.ReportOutput.HTM)) {
            exportHTM(response, "testoith", jp, baos);
        } else if (reportOutput.equals(_OithReportRunnerController.ReportOutput.CSV)) {
            exportCSV(response, "testoith", jp, baos);
        } else if (reportOutput.equals(_OithReportRunnerController.ReportOutput.RTF)) {
            exportRTF(response, "testoith", jp, baos);
        } else if (reportOutput.equals(_OithReportRunnerController.ReportOutput.TXT)) {
            exportTXT(response, "testoith", jp, baos);
        }


        response.setContentLength(baos.size());

        try {
            // Write to reponse stream
            ServletOutputStream outputStream = response.getOutputStream();
            baos.writeTo(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            LOGGER.error("Unable to write report to the output stream");
        }
    }

    public void exportHTM(HttpServletResponse response, String fileName, JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        // Create a JRXlsExporter instance
        JRAbstractExporter exporter = new JRHtmlExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        // Excel specific parameters
        // Check the Jasper (not DynamicJasper) docs for a description of these settings. Most are
        // self-documenting

        // Retrieve the exported report in XLS format
        exporter.exportReport();

        // Set our response propertiesLOGGER
        // Here you can declare a custom filename

        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".html");
//        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".html");
        // Make sure to set the correct content type
        // Each format has its own content type
        // response.setContentType("application/vnd.ms-excel");
        response.setContentType("text/html");
        // response.setHeader("Content-disposition", "attachment; filename=" + "xyz" + ".pdf");
    }

    public void exportPDF(HttpServletResponse response, String fileName, JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        // Create a JRXlsExporter instance
        JRAbstractExporter exporter = new JRPdfExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        // Retrieve the exported report in XLS format
        exporter.exportReport();

        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".pdf");
        // Make sure to set the correct content type
        // Each format has its own content type
        //response.setContentType("application/pdf");
        response.setContentType("application/x-download");
    }

    public void exportXLS(HttpServletResponse response, String fileName, JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        // Create a JRXlsExporter instance
        JRAbstractExporter exporter = new JRXlsExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        // Excel specific parameters
        // Check the Jasper (not DynamicJasper) docs for a description of these settings. Most are
        // self-documenting
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

        // Retrieve the exported report in XLS format
        exporter.exportReport();

        // Set our response propertiesLOGGER
        // Here you can declare a custom filename

        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
        // Make sure to set the correct content type
        // Each format has its own content type
        response.setContentType("application/vnd.ms-excel");
        // response.setContentType("application/x-pdf");
        // response.setHeader("Content-disposition", "attachment; filename=" + "xyz" + ".pdf");
        // response.setContentType("application/pdf");
    }

    public void exportCSV(HttpServletResponse response, String fileName, JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        // Create a JRXlsExporter instance
        JRAbstractExporter exporter = new JRCsvExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        // Excel specific parameters
        // Check the Jasper (not DynamicJasper) docs for a description of these settings. Most are
        // self-documenting
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

        // Retrieve the exported report in XLS format
        exporter.exportReport();

        // Set our response propertiesLOGGER
        // Here you can declare a custom filename

        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".csv");
        // Make sure to set the correct content type
        // Each format has its own content type
        response.setContentType("application/ms-excel");
        // response.setContentType("application/x-pdf");
        // response.setHeader("Content-disposition", "attachment; filename=" + "xyz" + ".pdf");
        // response.setContentType("application/pdf");
    }

    public void exportRTF(HttpServletResponse response, String fileName, JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        // Create a JRXlsExporter instance
        JRAbstractExporter exporter = new JRRtfExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        // Excel specific parameters
        // Check the Jasper (not DynamicJasper) docs for a description of these settings. Most are
        // self-documenting
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

        // Retrieve the exported report in XLS format
        exporter.exportReport();

        // Set our response propertiesLOGGER
        // Here you can declare a custom filename

        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".rtf");
        // Make sure to set the correct content type
        // Each format has its own content type
        response.setContentType("text/rtf");
        // response.setContentType("application/x-pdf");
        // response.setHeader("Content-disposition", "attachment; filename=" + "xyz" + ".pdf");
        // response.setContentType("application/pdf");
    }

    public void exportTXT(HttpServletResponse response, String fileName, JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
        // Create a JRXlsExporter instance
        JRAbstractExporter exporter = new JRRtfExporter();

        // Here we assign the parameters jp and baos to the exporter
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

        // Excel specific parameters
        // Check the Jasper (not DynamicJasper) docs for a description of these settings. Most are
        // self-documenting
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

        // Retrieve the exported report in XLS format
        exporter.exportReport();

        // Set our response propertiesLOGGER
        // Here you can declare a custom filename

        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".txt");
        // Make sure to set the correct content type
        // Each format has its own content type
        response.setContentType("application/text");
        // response.setContentType("application/x-pdf");
        // response.setHeader("Content-disposition", "attachment; filename=" + "xyz" + ".pdf");
        // response.setContentType("application/pdf");
    }
}