package org.reflection.controller;

import org.reflection.model.hcm.cr.Department;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.DepartmentNotFoundException;
import org.reflection.service.DepartmentService;

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
@RequestMapping(value = "/department")
 
public class DepartmentController extends _BaseController {

    protected static final String MODEL = "department";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private DepartmentService departmentService;





 

    private void commonPost(Department currObject) {

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new Department());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid Department currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                Department department = departmentService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, department.getCode());
                return "redirect:/" + SHOW + "/" + department.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        Department department = departmentService.findById(id);
        
        if (department == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, department);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid Department currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()){
            try {
                Department department = departmentService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, department.getCode());
                return "redirect:/" + SHOW + "/" + department.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        Department department = departmentService.findById(id);

        if (department == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, department);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid Department currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
               Department department = departmentService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, department.getCode());
               return "redirect:/" + SHOW + "/" + department.getId();
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
        List<Department> departments;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            departments = departmentService.search(searchCriteria);
        } else {
            departments = departmentService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, departments);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<Department> departments = departmentService.findAll();
        model.addAttribute(MODELS, departments);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<Department> departments = departmentService.findAll(searchCriteria);

        model.addAttribute(MODELS, departments);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<Department> departments = departmentService.findAll();
        model.addAttribute(MODELS, departments);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        Department department = departmentService.findById(id);

        if (department == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, department);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            Department deleted = departmentService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (DepartmentNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
