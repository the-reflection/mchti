package org.reflection.controller;

import org.reflection.model.security.AuthUserAuthQuestion;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.AuthUserAuthQuestionNotFoundException;
import org.reflection.service.AuthUserAuthQuestionService;

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
@RequestMapping(value = "/authUserAuthQuestion")
public class AuthUserAuthQuestionController extends _OithController {

    protected static final String MODEL = "authUserAuthQuestion";
    
    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private AuthUserAuthQuestionService authUserAuthQuestionService;





    private void commonGet(ModelMap model) {
 
    }
    
    private void commonPost(AuthUserAuthQuestion currObject) {

    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) { 
        model.addAttribute(MODEL, new AuthUserAuthQuestion());
        commonGet(model); 
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid AuthUserAuthQuestion currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
                AuthUserAuthQuestion authUserAuthQuestion = authUserAuthQuestionService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, authUserAuthQuestion.getId());
                return "redirect:/" + SHOW + "/" + authUserAuthQuestion.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        } 
        commonGet(model);
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
       
        AuthUserAuthQuestion authUserAuthQuestion = authUserAuthQuestionService.findById(id);
        
        if (authUserAuthQuestion == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, authUserAuthQuestion);
        commonGet(model); 
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid AuthUserAuthQuestion currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



        commonPost(currObject);

        if (!bindingResult.hasErrors()){
            try {
                AuthUserAuthQuestion authUserAuthQuestion = authUserAuthQuestionService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, authUserAuthQuestion.getId());
                return "redirect:/" + SHOW + "/" + authUserAuthQuestion.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        commonGet(model); 
        return EDIT;
    }
    
    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        AuthUserAuthQuestion authUserAuthQuestion = authUserAuthQuestionService.findById(id);

        if (authUserAuthQuestion == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, authUserAuthQuestion);
        commonGet(model);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid AuthUserAuthQuestion currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes ) {



        commonPost(currObject);

        if (!bindingResult.hasErrors()) {
            try {
               AuthUserAuthQuestion authUserAuthQuestion = authUserAuthQuestionService.copy(currObject);
               addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, authUserAuthQuestion.getId());
               return "redirect:/" + SHOW + "/" + authUserAuthQuestion.getId();
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
        List<AuthUserAuthQuestion> authUserAuthQuestions;
   
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            authUserAuthQuestions = authUserAuthQuestionService.search(searchCriteria);
        } else {
            authUserAuthQuestions = authUserAuthQuestionService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, authUserAuthQuestions);
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
        
        List<AuthUserAuthQuestion> authUserAuthQuestions = authUserAuthQuestionService.findAll(searchCriteria);

        model.addAttribute(MODELS, authUserAuthQuestions);
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
       
        AuthUserAuthQuestion authUserAuthQuestion = authUserAuthQuestionService.findById(id);

        if (authUserAuthQuestion == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, authUserAuthQuestion);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {
       
        try {
            AuthUserAuthQuestion deleted = authUserAuthQuestionService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
        } catch (AuthUserAuthQuestionNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
