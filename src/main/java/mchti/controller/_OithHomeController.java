package mchti.controller;

import mchti.model.security.AuthUser;
import mchti.service.ProcService;
import java.math.BigInteger;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
public class _OithHomeController {

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    private ProcService proc;

    private SortedSet<String> list = new TreeSet<>();

    @RequestMapping(value = {"/proc-refresh"}, method = RequestMethod.GET)
    public String procRefresh(ModelMap model) {
        proc.refresh();
        return "index";
    }

    @RequestMapping(value = {"/proc-daily/{day}"}, method = RequestMethod.GET)
    public String procDaily(@PathVariable("day") String day, ModelMap model) {
        proc.daily(day);
        return "index";
    }

    @RequestMapping(value = {"/proc-daily"}, method = RequestMethod.GET)
    public String procDaily(ModelMap model) {
        proc.daily("09-05-2016");
        proc.daily("21-05-2016");
        proc.daily("23-05-2016");
        return "index";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "index";
    }

    @RequestMapping(value = {"/homeSecure"}, method = RequestMethod.GET)
    public String homeSecure(ModelMap model) {

        model.addAttribute("controllerList", list);
        //model.addAttribute("greeting", "Hi, Welcome to mysite. ");
        // return "index";
        model.addAttribute("loggedinuser", getPrincipal());
        return "homeSecure";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "admin";
    }

    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "dba";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/index";
        }
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
//        return "redirect:/index?logout";
        return "redirect:/login?logout";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof AuthUser) {
            userName = ((AuthUser) principal).getFullName();
        }

        return userName;
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }

    @PostConstruct
    public void init() {

        Map<RequestMappingInfo, HandlerMethod> handlerMethods
                = this.requestMappingHandlerMapping.getHandlerMethods();

        // index(null, null);
        for (Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
            RequestMappingInfo mapping = item.getKey();
            HandlerMethod method = item.getValue();

            for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
                //System.out.println(method.getBeanType().getName() + "#" + method.getMethod().getName()+ " <-- " + urlPattern);

                if (urlPattern.endsWith("index")
                        || urlPattern.endsWith("reportRunner")
                        || urlPattern.endsWith("procRunner")
                        || urlPattern.endsWith("queryRunner")) {
                    System.out.println(method.getBeanType().getName() + "#" + method.getMethod().getName() + " <-- " + urlPattern);

                    list.add(urlPattern);
                }
                //if (urlPattern.equals("some specific url")) {
                //add to list of matching METHODS
                //}
            }
        }
    }
}
