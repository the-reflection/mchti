package org.reflection.controller;

import org.reflection.model.security.AuthMenu;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthMenuNotFoundException;
import org.reflection.service.AuthMenuService;

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
@RequestMapping(value = "/authMenu")
@SessionAttributes({"parentAuthMenus"}) 
public class AuthMenuController extends _BaseController {

    protected static final String MODEL = "authMenu";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private AuthMenuService authMenuService;





    @ModelAttribute("parentAuthMenus")
    public Iterable<AuthMenu> parentAuthMenus() {
        return authMenuService.findAll();
    }
 

    private void commonPost(AuthMenu currObject) {
            try {
                currObject.setParentAuthMenu(authMenuService.findById(currObject.getParentAuthMenu().getId()));
            } catch (Exception e) {
                currObject.setParentAuthMenu(null);
            }

    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new AuthMenu());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid AuthMenu currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                AuthMenu authMenu = authMenuService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, authMenu.getId());
                return "redirect:/" + SHOW + "/" + authMenu.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        AuthMenu authMenu = authMenuService.findById(id);
        
        if (authMenu == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, authMenu);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid AuthMenu currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()){
            try {
                AuthMenu authMenu = authMenuService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, authMenu.getId());
                return "redirect:/" + SHOW + "/" + authMenu.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        AuthMenu authMenu = authMenuService.findById(id);

        if (authMenu == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, authMenu);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid AuthMenu currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



    commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
               AuthMenu authMenu = authMenuService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, authMenu.getId());
               return "redirect:/" + SHOW + "/" + authMenu.getId();
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
        List<AuthMenu> authMenus;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            authMenus = authMenuService.search(searchCriteria);
        } else {
            authMenus = authMenuService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, authMenus);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
        
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AuthMenu> authMenus = authMenuService.findAll();
        model.addAttribute(MODELS, authMenus);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);
        
        List<AuthMenu> authMenus = authMenuService.findAll(searchCriteria);

        model.addAttribute(MODELS, authMenus);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);
    
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        */
        Iterable<AuthMenu> authMenus = authMenuService.findAll();
        model.addAttribute(MODELS, authMenus);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        AuthMenu authMenu = authMenuService.findById(id);

        if (authMenu == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, authMenu);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            AuthMenu deleted = authMenuService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
        } catch (AuthMenuNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
