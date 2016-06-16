package org.reflection.controller;

import org.reflection.model.hcm.proc.ProcOutAttnPeriod;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ProcOutAttnPeriodNotFoundException;
import org.reflection.service.ProcOutAttnPeriodService;
import org.reflection.service.PeriodService;
import org.reflection.model.hcm.tl.Period;
import org.reflection.service.EmployeeService;
import org.reflection.model.com.Employee;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/procOutAttnPeriod")
public class ProcOutAttnPeriodController extends _OithController {

    protected static final String MODEL = "procOutAttnPeriod";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private ProcOutAttnPeriodService procOutAttnPeriodService;

    @Autowired
    private PeriodService periodService;
    @Autowired
    private EmployeeService employeeService;




    private void commonGet(ModelMap model) {
            List<Period> periods = periodService.findAll();
            model.addAttribute("periods", periods);
 
    }
    
    private void commonPost(ProcOutAttnPeriod currObject) {
        try {
currObject.setPeriod(periodService.findById(currObject.getPeriod().getId()));
            } catch (Exception e) {
currObject.setPeriod(null);
}        currObject.setEmployee(employeeService.findByCode(currObject.getEmployee().getCode()));

    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new ProcOutAttnPeriod());
        commonGet(model); 
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid ProcOutAttnPeriod currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                ProcOutAttnPeriod procOutAttnPeriod = procOutAttnPeriodService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, procOutAttnPeriod.getId());
                return "redirect:/" + SHOW + "/" + procOutAttnPeriod.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        commonGet(model);
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        ProcOutAttnPeriod procOutAttnPeriod = procOutAttnPeriodService.findById(id);
        
        if (procOutAttnPeriod == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, procOutAttnPeriod);
        commonGet(model); 
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid ProcOutAttnPeriod currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



        commonPost(currObject);

        if (!bindingResult.hasErrors()){
            try {
                ProcOutAttnPeriod procOutAttnPeriod = procOutAttnPeriodService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, procOutAttnPeriod.getId());
                return "redirect:/" + SHOW + "/" + procOutAttnPeriod.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        commonGet(model); 
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ProcOutAttnPeriod procOutAttnPeriod = procOutAttnPeriodService.findById(id);

        if (procOutAttnPeriod == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, procOutAttnPeriod);
        commonGet(model);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid ProcOutAttnPeriod currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
               ProcOutAttnPeriod procOutAttnPeriod = procOutAttnPeriodService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, procOutAttnPeriod.getId());
               return "redirect:/" + SHOW + "/" + procOutAttnPeriod.getId();
            } catch (Exception e) {
               errorHandler(bindingResult, e);
            }
        }
        commonGet(model); 
        return COPY;
    }
    
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String search(@ModelAttribute(SEARCH_CRITERIA) _SearchDTO searchCriteria, ModelMap model) {
        
        String searchTerm = searchCriteria.getSearchTerm();
        List<ProcOutAttnPeriod> procOutAttnPeriods;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            procOutAttnPeriods = procOutAttnPeriodService.search(searchCriteria);
        } else {
            procOutAttnPeriods = procOutAttnPeriodService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, procOutAttnPeriods);
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
        
        List<ProcOutAttnPeriod> procOutAttnPeriods = procOutAttnPeriodService.findAll(searchCriteria);

        model.addAttribute(MODELS, procOutAttnPeriods);
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
       
        ProcOutAttnPeriod procOutAttnPeriod = procOutAttnPeriodService.findById(id);

        if (procOutAttnPeriod == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, procOutAttnPeriod);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            ProcOutAttnPeriod deleted = procOutAttnPeriodService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
        } catch (ProcOutAttnPeriodNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
