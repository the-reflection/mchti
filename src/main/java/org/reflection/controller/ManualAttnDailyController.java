package org.reflection.controller;

import java.beans.PropertyEditorSupport;
import org.reflection.model.hcm.tl.ManualAttnDaily;
import org.reflection.dto._SearchDTO;
import org.reflection.exception.ManualAttnDailyNotFoundException;
import org.reflection.service.ManualAttnDailyService;
import org.reflection.service.AuthUserService;
import org.reflection.service.EmployeeService;
import java.math.BigInteger;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/manualAttnDaily")
public class ManualAttnDailyController extends _BaseController {

    protected static final String MODEL = "manualAttnDaily";

    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private ManualAttnDailyService manualAttnDailyService;

    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private EmployeeService employeeService;

    @InitBinder
    @Override
    public void initBinder(WebDataBinder webDataBinder) {

        webDataBinder.registerCustomEditor(Time.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                System.out.println("dukeche  yy1:>" + text + "<");

                try {
                    Date gg = dateFormat.parse(text);
                    setValue(gg);
                    System.out.println("dukeche  yy2:" + gg);

                } catch (Exception ex) {
                    System.out.println("err time:" + ex);
                    setValue(null);
                }
            }
        });
    }

    private void commonPost(ManualAttnDaily currObject) {
        currObject.setEmployee(employeeService.findByCode(currObject.getEmployee().getCode()));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new ManualAttnDaily());
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid ManualAttnDaily currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        currObject.setEntryBy(authUserService.findById(BigInteger.ONE));
        currObject.setEntryDate(new Date());

        if (!bindingResult.hasErrors()) {
            try {
                ManualAttnDaily manualAttnDaily = manualAttnDailyService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, manualAttnDaily.getId());
                return "redirect:/" + SHOW + "/" + manualAttnDaily.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ManualAttnDaily manualAttnDaily = manualAttnDailyService.findById(id);

        if (manualAttnDaily == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, manualAttnDaily);
        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@ModelAttribute(MODEL) @Valid ManualAttnDaily currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        currObject.setEditBy(authUserService.findById(BigInteger.ONE));
        currObject.setEditDate(new Date());

        if (!bindingResult.hasErrors()) {
            try {
                ManualAttnDaily manualAttnDaily = manualAttnDailyService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, manualAttnDaily.getId());
                return "redirect:/" + SHOW + "/" + manualAttnDaily.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }
        return EDIT;
    }

    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ManualAttnDaily manualAttnDaily = manualAttnDailyService.findById(id);

        if (manualAttnDaily == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, manualAttnDaily);
        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid ManualAttnDaily currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes) {

        commonPost(currObject);

        currObject.setEntryBy(authUserService.findById(BigInteger.ONE));
        currObject.setEditBy(authUserService.findById(BigInteger.ONE));
        currObject.setEntryDate(new Date());
        currObject.setEditDate(new Date());

        if (!bindingResult.hasErrors()) {
            try {
                ManualAttnDaily manualAttnDaily = manualAttnDailyService.copy(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, manualAttnDaily.getId());
                return "redirect:/" + SHOW + "/" + manualAttnDaily.getId();
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
        List<ManualAttnDaily> manualAttnDailys;

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            manualAttnDailys = manualAttnDailyService.search(searchCriteria);
        } else {
            manualAttnDailys = manualAttnDailyService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, manualAttnDailys);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);

        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
         */
        Iterable<ManualAttnDaily> manualAttnDailys = manualAttnDailyService.findAll();
        model.addAttribute(MODELS, manualAttnDailys);
        return INDEX;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        /*_SearchDTO searchCriteria = new _SearchDTO();
        searchCriteria.setPage(1);
        searchCriteria.setPageSize(5);

        List<ManualAttnDaily> manualAttnDailys = manualAttnDailyService.findAll(searchCriteria);

        model.addAttribute(MODELS, manualAttnDailys);
        model.addAttribute(SEARCH_CRITERIA, searchCriteria);

        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= searchCriteria.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
         */
        Iterable<ManualAttnDaily> manualAttnDailys = manualAttnDailyService.findAll();
        model.addAttribute(MODELS, manualAttnDailys);
        return INDEX;
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ManualAttnDaily manualAttnDaily = manualAttnDailyService.findById(id);

        if (manualAttnDaily == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, manualAttnDaily);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {

        try {
            ManualAttnDaily deleted = manualAttnDailyService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getId());
        } catch (ManualAttnDailyNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
