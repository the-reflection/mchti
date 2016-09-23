package org.reflection.controller;

import org.reflection.model.hcm.tl.AssignmentTl;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AssignmentTlNotFoundException;
import org.reflection.service.AssignmentTlService;
import org.reflection.service.EmployeeService;
import org.reflection.model.com.Employee;
import org.reflection.service.ShiftService;
import org.reflection.model.hcm.tl.Shift;
import org.reflection.service.RosterService;
import org.reflection.model.hcm.tl.Roster;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/assignmentTl")
@SessionAttributes({"shifts","rosters"}) 
public class AssignmentTlController extends _BaseController {

    protected static final String MODEL = "assignmentTl";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private AssignmentTlService assignmentTlService;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private RosterService rosterService;




    @ModelAttribute("shifts")
    public Iterable<Shift> shifts() {
        return shiftService.findAll();
    }
    @ModelAttribute("rosters")
    public Iterable<Roster> rosters() {
        return rosterService.findAll();
    }
 

    private void commonPost(AssignmentTl currObject) {
        currObject.setEmployee(employeeService.findByCode(currObject.getEmployee().getCode()));
            try {
                currObject.setShift(shiftService.findById(currObject.getShift().getId()));
            } catch (Exception e) {
                currObject.setShift(null);
            }
            try {
                currObject.setRoster(rosterService.findById(currObject.getRoster().getId()));
            } catch (Exception e) {
                currObject.setRoster(null);
            }

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new AssignmentTl());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid AssignmentTl currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                AssignmentTl assignmentTl = assignmentTlService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, assignmentTl.getCode());
                return "redirect:/" + SHOW + "/" + assignmentTl.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        AssignmentTl assignmentTl = assignmentTlService.findById(id);
        
        if (assignmentTl == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, assignmentTl);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid AssignmentTl currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()){
            try {
                AssignmentTl assignmentTl = assignmentTlService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, assignmentTl.getCode());
                return "redirect:/" + SHOW + "/" + assignmentTl.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        AssignmentTl assignmentTl = assignmentTlService.findById(id);

        if (assignmentTl == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, assignmentTl);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid AssignmentTl currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
               AssignmentTl assignmentTl = assignmentTlService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, assignmentTl.getCode());
               return "redirect:/" + SHOW + "/" + assignmentTl.getId();
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
        List<AssignmentTl> assignmentTls;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            assignmentTls = assignmentTlService.search(searchCriteria);
        } else {
            assignmentTls = assignmentTlService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, assignmentTls);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AssignmentTl> assignmentTls = assignmentTlService.findAll();
        model.addAttribute(MODELS, assignmentTls);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AssignmentTl> assignmentTls = assignmentTlService.findAll(searchCriteria);

        model.addAttribute(MODELS, assignmentTls);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AssignmentTl> assignmentTls = assignmentTlService.findAll();
        model.addAttribute(MODELS, assignmentTls);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        AssignmentTl assignmentTl = assignmentTlService.findById(id);

        if (assignmentTl == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, assignmentTl);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            AssignmentTl deleted = assignmentTlService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (AssignmentTlNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
