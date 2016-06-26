package org.reflection.controller;

import org.reflection.model.com.Employee;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.EmployeeNotFoundException;
import org.reflection.service.EmployeeService;

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
@RequestMapping(value = "/employee")
 
public class EmployeeController extends _OithController {

    protected static final String MODEL = "employee";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private EmployeeService employeeService;



    @RequestMapping(value = "/getCodableDTO", method = RequestMethod.GET)
    public @ResponseBody
    String getCodableDTO(@RequestParam(value="code") String code) {
        Employee codable = employeeService.findByCode(code);
       
        if (codable != null) {
            return codable.getFullName();
        }
        return "Not Found";
    }

 

    private void commonPost(Employee currObject) {

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new Employee());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid Employee currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

        String picFile=multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }


    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                Employee employee = employeeService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, employee.getCode());
                return "redirect:/" + SHOW + "/" + employee.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        Employee employee = employeeService.findById(id);
        
        if (employee == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, employee);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid Employee currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

        String picFile=multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }


    commonPost(currObject);

        if (!bindingResult.hasErrors()){
            try {
                Employee employee = employeeService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, employee.getCode());
                return "redirect:/" + SHOW + "/" + employee.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        Employee employee = employeeService.findById(id);

        if (employee == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, employee);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid Employee currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes , MultipartHttpServletRequest request) {

        String picFile=multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }


    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
               Employee employee = employeeService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, employee.getCode());
               return "redirect:/" + SHOW + "/" + employee.getId();
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
        List<Employee> employees;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            employees = employeeService.search(searchCriteria);
        } else {
            employees = employeeService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, employees);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<Employee> employees = employeeService.findAll();
        model.addAttribute(MODELS, employees);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<Employee> employees = employeeService.findAll(searchCriteria);

        model.addAttribute(MODELS, employees);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<Employee> employees = employeeService.findAll();
        model.addAttribute(MODELS, employees);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, employee);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            Employee deleted = employeeService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (EmployeeNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
