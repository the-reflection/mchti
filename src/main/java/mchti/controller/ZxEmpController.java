package mchti.controller;

import mchti.model.sample.ZxEmp;
import mchti.dto._SearchDTO;
import mchti.exception.ZxEmpNotFoundException;
import mchti.model.sample.ZxLookup;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import mchti.service.ZxEmpService;
import mchti.service.ZxLookupService;
import java.math.BigInteger;

@Controller
@RequestMapping(value = "/zxEmp")
public class ZxEmpController extends _OithController {

    protected static final String MODEL = "zxEmp";

    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private ZxEmpService zxEmpService;
    @Autowired
    private ZxLookupService zxLookupService;

    @ModelAttribute("zxLookupBloodGroups")
    public List< ZxLookup> initializeProfiles() {
        return zxLookupService.findAll();
    }

    @RequestMapping(value = "/getCodableDTO", method = RequestMethod.GET)
    public @ResponseBody
    String getCodableDTO(@RequestParam(value = "code") String code) {
        ZxEmp codable = zxEmpService.findByCode(code);

        if (codable != null) {
            return codable.getFullName();
        }
        return "Not Found";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute(MODEL, new ZxEmp());

        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute(MODEL) @Valid ZxEmp currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes attributes,
            MultipartHttpServletRequest request) {

        String picFile = multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }
        String docFile = multipartFileHandler(request, "docFile");
        if (docFile != null && !docFile.isEmpty()) {
            currObject.setDocFile(docFile);
        }

        if (currObject.getZxLookupBloodGroup().getId() == null) {
            currObject.setZxLookupBloodGroup(null);
        }

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmp zxEmp = zxEmpService.create(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CREATED, zxEmp.getCode());
                return "redirect:/" + SHOW + "/" + zxEmp.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }

        return CREATE;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ZxEmp zxEmp = zxEmpService.findById(id);

        if (zxEmp == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, zxEmp);

        return EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(
            @ModelAttribute(MODEL) @Valid ZxEmp currObject,
            BindingResult bindingResult,
            ModelMap model,
            RedirectAttributes attributes,
            MultipartHttpServletRequest request
    //@RequestParam("picFileOBJ") MultipartFile file

    ) {

        System.out.println("jjjj 1048:" + request.getFileMap());

        String picFile = multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }
        String docFile = multipartFileHandler(request, "docFile");
        if (docFile != null && !docFile.isEmpty()) {
            currObject.setDocFile(docFile);
        }

        if (currObject.getZxLookupBloodGroup().getId() == null) {
            currObject.setZxLookupBloodGroup(null);
        }

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmp zxEmp = zxEmpService.update(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, zxEmp.getCode());
                return "redirect:/" + SHOW + "/" + currObject.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }

        return EDIT;
    }

    @RequestMapping(value = "/copy/{id}", method = RequestMethod.GET)
    public String copy(@PathVariable("id") BigInteger id, ModelMap model, RedirectAttributes attributes) {

        ZxEmp zxEmp = zxEmpService.findById(id);

        if (zxEmp == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, zxEmp);

        return COPY;
    }

    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public String copied(@ModelAttribute(MODEL) @Valid ZxEmp currObject, BindingResult bindingResult, ModelMap model, RedirectAttributes attributes, MultipartHttpServletRequest request) {

        String picFile = multipartImageFileHandler(request, "picFile");
        if (picFile != null && !picFile.isEmpty()) {
            currObject.setPicFile(picFile);
        }
        String docFile = multipartFileHandler(request, "docFile");
        if (docFile != null && !docFile.isEmpty()) {
            currObject.setDocFile(docFile);
        }

        if (currObject.getZxLookupBloodGroup().getId() == null) {
            currObject.setZxLookupBloodGroup(null);
        }

        if (!bindingResult.hasErrors()) {
            try {
                ZxEmp zxEmp = zxEmpService.copy(currObject);
                addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EDITED, zxEmp.getCode());
                return "redirect:/" + SHOW + "/" + zxEmp.getId();
            } catch (Exception e) {
                errorHandler(bindingResult, e);
            }
        }

        return COPY;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String search(@ModelAttribute(SEARCH_CRITERIA) _SearchDTO searchCriteria, ModelMap model) {

        String searchTerm = searchCriteria.getSearchTerm();
        List<ZxEmp> zxEmps;

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            zxEmps = zxEmpService.search(searchCriteria);
        } else {
            zxEmps = zxEmpService.findAll(searchCriteria);
        }
        model.addAttribute(MODELS, zxEmps);
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

        List<ZxEmp> zxEmps = zxEmpService.findAll(searchCriteria);

        model.addAttribute(MODELS, zxEmps);
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

        ZxEmp zxEmp = zxEmpService.findById(id);

        if (zxEmp == null) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);
        }
        model.addAttribute(MODEL, zxEmp);
        return SHOW;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") BigInteger id, RedirectAttributes attributes) {

        try {
            ZxEmp deleted = zxEmpService.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getCode());
        } catch (ZxEmpNotFoundException e) {
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_WAS_NOT_FOUND);
        }
        return "redirect:/" + INDEX;
    }
}
