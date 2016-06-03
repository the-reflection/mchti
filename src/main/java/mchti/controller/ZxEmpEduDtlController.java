package mchti.controller;

import mchti.model.sample.ZxEmpEduDtl;
import mchti.dto._SearchDTO;
import mchti.exception.ZxEmpEduDtlNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import mchti.service.ZxEmpEduDtlService;
import mchti.service.ZxEmpService;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/zxEmpEduDtl")
public class ZxEmpEduDtlController extends _OithController {

    protected static final String MODEL = "zxEmpEduDtl";

    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private ZxEmpEduDtlService zxEmpEduDtlService;
    @Autowired
    private ZxEmpService zxEmpService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new ZxEmpEduDtl());

        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid ZxEmpEduDtl currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes, MultipartHttpServletRequest request) {

        String certificateFile = multipartFileHandler(request, "certificateFile");
        if (certificateFile != null && !certificateFile.isEmpty()) {
            currObject.setCertificateFile(certificateFile);
        }
        String picFile = multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }

        currObject.setZxEmp(zxEmpService.findByCode(currObject.getZxEmp().getCode()));
        currObject.setZxEmpWhoCheckedBy(zxEmpService.findByCode(currObject.getZxEmpWhoCheckedBy().getCode()));

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmpEduDtl zxEmpEduDtl = zxEmpEduDtlService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, zxEmpEduDtl.getCode());
                return "redirect:/" + SHOW + "/" + zxEmpEduDtl.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }

        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ZxEmpEduDtl zxEmpEduDtl = zxEmpEduDtlService.findById(id);

        if (zxEmpEduDtl == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, zxEmpEduDtl);

        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid ZxEmpEduDtl currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes, MultipartHttpServletRequest request) {

        String certificateFile = multipartFileHandler(request, "certificateFile");
        if (certificateFile != null && !certificateFile.isEmpty()) {
            currObject.setCertificateFile(certificateFile);
        }
        String picFile = multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }

        currObject.setZxEmp(zxEmpService.findByCode(currObject.getZxEmp().getCode()));
        currObject.setZxEmpWhoCheckedBy(zxEmpService.findByCode(currObject.getZxEmpWhoCheckedBy().getCode()));

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmpEduDtl zxEmpEduDtl = zxEmpEduDtlService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, zxEmpEduDtl.getCode());
                return "redirect:/" + SHOW + "/" + zxEmpEduDtl.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }

        return EDIT;
    }

    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ZxEmpEduDtl zxEmpEduDtl = zxEmpEduDtlService.findById(id);

        if (zxEmpEduDtl == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, zxEmpEduDtl);

        return COPY;
    }

    @RequestMapping(value = "/copy/{id}", method = RequestMethod.POST)
    public String copied(@PathVariable("id") BigInteger id, @ModelAttribute(MODEL) @Valid ZxEmpEduDtl currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes, MultipartHttpServletRequest request) {

        String certificateFile = multipartFileHandler(request, "certificateFile");
        if (certificateFile != null && !certificateFile.isEmpty()) {
            currObject.setCertificateFile(certificateFile);
        }
        String picFile = multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }

        currObject.setZxEmp(zxEmpService.findByCode(currObject.getZxEmp().getCode()));
        currObject.setZxEmpWhoCheckedBy(zxEmpService.findByCode(currObject.getZxEmpWhoCheckedBy().getCode()));

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmpEduDtl zxEmpEduDtl = zxEmpEduDtlService.copy(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, zxEmpEduDtl.getCode());
                return "redirect:/" + SHOW + "/" + zxEmpEduDtl.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }

        return COPY;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String search(@ModelAttribute(SEARCH_CRITERIA) _SearchDTO searchCriteria, ModelMap model) {

        String searchTerm = searchCriteria.getSearchTerm();
        List<ZxEmpEduDtl> zxEmpEduDtls;

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            zxEmpEduDtls = zxEmpEduDtlService.search(searchCriteria);
        } else {
            zxEmpEduDtls = zxEmpEduDtlService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, zxEmpEduDtls);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);

        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        _SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);

        List<ZxEmpEduDtl> zxEmpEduDtls = zxEmpEduDtlService.findAll(searchCriteria);

        model.addAttribute(MODELS, zxEmpEduDtls);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);

        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ZxEmpEduDtl zxEmpEduDtl = zxEmpEduDtlService.findById(id);

        if (zxEmpEduDtl == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, zxEmpEduDtl);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {

        try {
            ZxEmpEduDtl deleted = zxEmpEduDtlService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (ZxEmpEduDtlNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
