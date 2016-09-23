package org.reflection.controller;

import org.reflection.model.com.AdmReport;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AdmReportNotFoundException;
import org.reflection.service.AdmReportService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admReport")
public class AdmReportController extends _BaseController {

    protected static final String MODEL = "admReport";

    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private AdmReportService admReportService;

    private void commonPost(AdmReport currObject) {

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new AdmReport());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid AdmReport currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                AdmReport admReport = admReportService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, admReport.getCode());
                return "redirect:/" + SHOW + "/" + admReport.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        AdmReport admReport = admReportService.findById(id);

        if (admReport == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, admReport);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid AdmReport currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                AdmReport admReport = admReportService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, admReport.getCode());
                return "redirect:/" + SHOW + "/" + admReport.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }

    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        AdmReport admReport = admReportService.findById(id);

        if (admReport == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, admReport);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid AdmReport currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                AdmReport admReport = admReportService.copy(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, admReport.getCode());
                return "redirect:/" + SHOW + "/" + admReport.getId();
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
        List<AdmReport> admReports;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            admReports = admReportService.search(searchCriteria);
        } else {
            admReports = admReportService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, admReports);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
         */
        Iterable<AdmReport> admReports = admReportService.findAll();
        model.addAttribute(MODELS, admReports);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AdmReport> admReports = admReportService.findAll(searchCriteria);

        model.addAttribute(MODELS, admReports);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
         */
        Iterable<AdmReport> admReports = admReportService.findAll();
        model.addAttribute(MODELS, admReports);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        AdmReport admReport = admReportService.findById(id);

        if (admReport == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, admReport);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {

        try {
            AdmReport deleted = admReportService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (AdmReportNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
