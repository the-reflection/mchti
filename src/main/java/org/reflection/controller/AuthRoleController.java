package org.reflection.controller;

import org.reflection.model.security.AuthRole;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthRoleNotFoundException;
import org.reflection.service.AuthRoleService;

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
@RequestMapping(value = "/authRole")
 
public class AuthRoleController extends _BaseController {

    protected static final String MODEL = "authRole";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private AuthRoleService authRoleService;





 

    private void commonPost(AuthRole currObject) {

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new AuthRole());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid AuthRole currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                AuthRole authRole = authRoleService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, authRole.getId());
                return "redirect:/" + SHOW + "/" + authRole.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        AuthRole authRole = authRoleService.findById(id);
        
        if (authRole == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, authRole);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid AuthRole currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()){
            try {
                AuthRole authRole = authRoleService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, authRole.getId());
                return "redirect:/" + SHOW + "/" + authRole.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        AuthRole authRole = authRoleService.findById(id);

        if (authRole == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, authRole);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid AuthRole currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
               AuthRole authRole = authRoleService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, authRole.getId());
               return "redirect:/" + SHOW + "/" + authRole.getId();
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
        List<AuthRole> authRoles;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            authRoles = authRoleService.search(searchCriteria);
        } else {
            authRoles = authRoleService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, authRoles);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AuthRole> authRoles = authRoleService.findAll();
        model.addAttribute(MODELS, authRoles);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AuthRole> authRoles = authRoleService.findAll(searchCriteria);

        model.addAttribute(MODELS, authRoles);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AuthRole> authRoles = authRoleService.findAll();
        model.addAttribute(MODELS, authRoles);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        AuthRole authRole = authRoleService.findById(id);

        if (authRole == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, authRole);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            AuthRole deleted = authRoleService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
        } catch (AuthRoleNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
