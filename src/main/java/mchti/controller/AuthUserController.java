package mchti.controller;

import mchti.model.security.AuthRole;
import mchti.model.security.AuthUser;
import mchti.service.AuthRoleService;
import mchti.service.AuthUserService;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/authUser")
public class AuthUserController extends _OithController {

    protected static final String MODEL = "authUser";

    protected static final String MODELS = MODEL + "s";
    protected static final String INDEX = MODEL + "/index";
    protected static final String CREATE = MODEL + "/create";
    protected static final String EDIT = MODEL + "/edit";
    protected static final String COPY = MODEL + "/copy";
    protected static final String SHOW = MODEL + "/show";

    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private AuthRoleService authRoleService;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String userList(ModelMap model) {

        List<AuthUser> users = authUserService.findAll();
        model.addAttribute("users", users);
        //model.addAttribute("greeting", "Hi, Welcome to mysite. ");
        // return "index";
        //model.addAttribute("loggedinuser", getPrincipal());
        return "authUser/index";
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {;
        model.addAttribute("user", new AuthUser());
        model.addAttribute("edit", false);
        //model.addAttribute("loggedinuser", getPrincipal());
        return "authUser/registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     *
     * @param user
     * @param result
     * @param model
     */
    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") @Valid AuthUser user, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            return "authUser/registration";
        }

        if (!authUserService.isUsernameUnique(user.getId(), user.getUsername())) {
            FieldError ssoError = new FieldError("user", "username", messageSource.getMessage("non.unique.username", new String[]{user.getUsername()}, Locale.getDefault()));
            result.addError(ssoError);
            return "authUser/registration";
        }

        authUserService.create(user);

        model.addAttribute("success", "User " + user.getUsername() + " registered successfully");
        //model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "authUser/registrationSuccess";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit/{username}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String username, ModelMap model) {
        AuthUser user = authUserService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        //model.addAttribute("loggedinuser", getPrincipal());
        return "authUser/registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit/{username}"}, method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") @Valid AuthUser user, BindingResult result,
            ModelMap model, @PathVariable String username) {

        if (result.hasErrors()) {
            return "authUser/registration";
        }

        try {
            authUserService.update(user);
        } catch (Exception e) {
        }

        model.addAttribute("success", "User " + user.getUsername() + " updated successfully");
        //model.addAttribute("loggedinuser", getPrincipal());
        return "authUser/registrationSuccess";
    }

    /**
     * This method will delete an user by it's username value.
     *
     * @param username
     * @return
     */
    @RequestMapping(value = {"/delete/{username}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable("username") String username) {
        authUserService.deleteByUsername(username);
        return "redirect:/authUser/index";
    }

    /**
     * This method will provide AuthRole list to views
     *
     * @return
     */
    @ModelAttribute("roles")
    public List<AuthRole> initializeProfiles() {
        return authRoleService.findAll();
    }

}
