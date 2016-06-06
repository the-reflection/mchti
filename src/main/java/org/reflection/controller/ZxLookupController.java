package org.reflection.controller;

import org.reflection.model.sample.ZxLookup;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ZxLookupNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
import org.reflection.service.ZxLookupService;

@Controller
@RequestMapping(value = "/zxLookup")
public class ZxLookupController extends _OithController {

    protected static final String MODEL = "zxLookup";

    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private ZxLookupService zxLookupService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new ZxLookup());

        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid ZxLookup currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        if (!bindingResult.hasErrors()) {
            try {
                ZxLookup zxLookup = zxLookupService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, zxLookup.getCode());
                return "redirect:/" + SHOW + "/" + faker(zxLookup.getId());
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }

        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ZxLookup zxLookup = zxLookupService.findById(faker(id));

        if (zxLookup == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        zxLookup.setId(faker(zxLookup.getId()));
        model.addAttribute(MODEL, zxLookup);

        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid ZxLookup currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        if (!bindingResult.hasErrors()) {
            try {
                currObject.setId(faker(currObject.getId()));
                ZxLookup zxLookup = zxLookupService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, zxLookup.getCode());
                return "redirect:/" + SHOW + "/" + faker(zxLookup.getId());
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }

        return EDIT;
    }

    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ZxLookup zxLookup = zxLookupService.findById(faker(id));

        if (zxLookup == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        zxLookup.setId(faker(zxLookup.getId()));
        model.addAttribute(MODEL, zxLookup);

        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid ZxLookup currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        if (!bindingResult.hasErrors()) {
            try {
                currObject.setId(faker(currObject.getId()));
                ZxLookup zxLookup = zxLookupService.copy(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, zxLookup.getCode());
                return "redirect:/" + SHOW + "/" + faker(zxLookup.getId());
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }

        return COPY;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
       
        List<ZxLookup> zxLookups = zxLookupService.findAll();

        for (ZxLookup zxLookup : zxLookups) {
            zxLookup.setId(faker(zxLookup.getId()));
        }

        model.addAttribute(MODELS, zxLookups);

        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {
        ZxLookup zxLookup;
        try {
            zxLookup = zxLookupService.findById(faker(id));
        } catch (Exception e) {
            zxLookup = null;
        }

        if (zxLookup == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
           // return createRedirectViewPath(REQUEST_MAPPING_LIST);
           return "redirect:/" + INDEX;
        }
        zxLookup.setId(id);
        model.addAttribute(MODEL, zxLookup);

        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {

        try {
            ZxLookup deleted = zxLookupService.delete(faker(id));
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (ZxLookupNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }

        return "redirect:/" + INDEX;
    }
}
