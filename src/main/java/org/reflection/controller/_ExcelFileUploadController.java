package org.reflection.controller;

import java.util.Map;
import org.reflection.service.ProcServiceDef;
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
public class _ExcelFileUploadController {

    @Autowired
    private ProcServiceDef procServiceDef;

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
                hhh = procServiceDef.doUploadExcelFile(multipartFile.getInputStream());
            } catch (Exception e) {
                System.out.println("ok file err" + e);
            }
        }

        model.addAttribute("result", hhh);
        return "etc/excelFileUpload";
    }
}
