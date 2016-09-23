package org.reflection.controller;

import org.reflection.model.hcm.cr.AssignmentHr;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AssignmentHrNotFoundException;
import org.reflection.service.AssignmentHrService;
import org.reflection.service.EmployeeService;
import org.reflection.model.com.Employee;
import org.reflection.service.DepartmentService;
import org.reflection.model.hcm.cr.Department;
import org.reflection.service.DesignationService;
import org.reflection.model.hcm.cr.Designation;

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
@RequestMapping(value = "/assignmentHr")
@SessionAttributes({"departments","designations"}) 
public class AssignmentHrController extends _BaseController {

    protected static final String MODEL = "assignmentHr";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private AssignmentHrService assignmentHrService;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DesignationService designationService;




    @ModelAttribute("departments")
    public Iterable<Department> departments() {
        return departmentService.findAll();
    }
    @ModelAttribute("designations")
    public Iterable<Designation> designations() {
        return designationService.findAll();
    }
 

    private void commonPost(AssignmentHr currObject) {
        currObject.setEmployee(employeeService.findByCode(currObject.getEmployee().getCode()));
            try {
                currObject.setDepartment(departmentService.findById(currObject.getDepartment().getId()));
            } catch (Exception e) {
                currObject.setDepartment(null);
            }
            try {
                currObject.setDesignation(designationService.findById(currObject.getDesignation().getId()));
            } catch (Exception e) {
                currObject.setDesignation(null);
            }

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new AssignmentHr());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid AssignmentHr currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                AssignmentHr assignmentHr = assignmentHrService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, assignmentHr.getCode());
                return "redirect:/" + SHOW + "/" + assignmentHr.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        AssignmentHr assignmentHr = assignmentHrService.findById(id);
        
        if (assignmentHr == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, assignmentHr);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid AssignmentHr currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()){
            try {
                AssignmentHr assignmentHr = assignmentHrService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, assignmentHr.getCode());
                return "redirect:/" + SHOW + "/" + assignmentHr.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        AssignmentHr assignmentHr = assignmentHrService.findById(id);

        if (assignmentHr == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, assignmentHr);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid AssignmentHr currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
               AssignmentHr assignmentHr = assignmentHrService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, assignmentHr.getCode());
               return "redirect:/" + SHOW + "/" + assignmentHr.getId();
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
        List<AssignmentHr> assignmentHrs;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            assignmentHrs = assignmentHrService.search(searchCriteria);
        } else {
            assignmentHrs = assignmentHrService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, assignmentHrs);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AssignmentHr> assignmentHrs = assignmentHrService.findAll();
        model.addAttribute(MODELS, assignmentHrs);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AssignmentHr> assignmentHrs = assignmentHrService.findAll(searchCriteria);

        model.addAttribute(MODELS, assignmentHrs);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AssignmentHr> assignmentHrs = assignmentHrService.findAll();
        model.addAttribute(MODELS, assignmentHrs);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        AssignmentHr assignmentHr = assignmentHrService.findById(id);

        if (assignmentHr == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, assignmentHr);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            AssignmentHr deleted = assignmentHrService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (AssignmentHrNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
