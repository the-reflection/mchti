package org.reflection.controller;

import org.reflection.model.com.AdmReportDetail;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmReportDetailNotFoundException;
import org.reflection.service.AdmReportDetailService;
import org.reflection.service.AdmReportService;
import org.reflection.model.com.AdmReport;
import org.reflection.service.AdmParamService;
import org.reflection.model.com.AdmParam;

import java.math.BigInteger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admReportDetail")
@SessionAttributes({"admReports", "admParams"})
public class AdmReportDetailController extends _BaseController {

    protected static final String MODEL = "admReportDetail";

    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private AdmReportDetailService admReportDetailService;

    @Autowired
    private AdmReportService admReportService;
    @Autowired
    private AdmParamService admParamService;

    @ModelAttribute("admReports")
    public Iterable<AdmReport> admReports() {
        return admReportService.findAll();
    }

    @ModelAttribute("admParams")
    public Iterable<AdmParam> admParams() {
        return admParamService.findAll();
    }

    private void commonPost(AdmReportDetail currObject) {
        try {
            currObject.setAdmReport(admReportService.findById(currObject.getAdmReport().getId()));
        } catch (Exception e) {
            currObject.setAdmReport(null);
        }
        try {
            currObject.setAdmParam(admParamService.findById(currObject.getAdmParam().getId()));
        } catch (Exception e) {
            currObject.setAdmParam(null);
        }

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new AdmReportDetail());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid AdmReportDetail currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                AdmReportDetail admReportDetail = admReportDetailService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, admReportDetail.getId());
                return "redirect:/" + SHOW + "/" + admReportDetail.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        AdmReportDetail admReportDetail = admReportDetailService.findById(id);

        if (admReportDetail == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, admReportDetail);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid AdmReportDetail currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                AdmReportDetail admReportDetail = admReportDetailService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, admReportDetail.getId());
                return "redirect:/" + SHOW + "/" + admReportDetail.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }

    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        AdmReportDetail admReportDetail = admReportDetailService.findById(id);

        if (admReportDetail == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, admReportDetail);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid AdmReportDetail currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                AdmReportDetail admReportDetail = admReportDetailService.copy(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, admReportDetail.getId());
                return "redirect:/" + SHOW + "/" + admReportDetail.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return COPY;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String search(@ModelAttribute(SEARCH_CRITERIA) _SearchDTO searchCriteria, ModelMap model) {
        /*
        String searchTerm = searchCriteria.getSearchTerm();
        List<AdmReportDetail> admReportDetails;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            admReportDetails = admReportDetailService.search(searchCriteria);
        } else {
            admReportDetails = admReportDetailService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, admReportDetails);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
         */
        Iterable<AdmReportDetail> admReportDetails = admReportDetailService.findAll();
        model.addAttribute(MODELS, admReportDetails);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AdmReportDetail> admReportDetails = admReportDetailService.findAll(searchCriteria);

        model.addAttribute(MODELS, admReportDetails);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
         */
        Iterable<AdmReportDetail> admReportDetails = admReportDetailService.findAll();
        model.addAttribute(MODELS, admReportDetails);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        AdmReportDetail admReportDetail = admReportDetailService.findById(id);

        if (admReportDetail == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, admReportDetail);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {

        try {
            AdmReportDetail deleted = admReportDetailService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
        } catch (AdmReportDetailNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
