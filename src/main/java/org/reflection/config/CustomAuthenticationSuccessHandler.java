package org.reflection.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.reflection.model.security.AuthMenu;
import org.reflection.model.security.AuthUser;
import org.reflection.service.AuthMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements
        AuthenticationSuccessHandler {

    @Autowired
    private AuthMenuService authMenuService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {

        String contextPath = request.getContextPath();

        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // session.setAttribute("uname", authUser.getUsername());  
        // session.setAttribute("authorities", authentication.getAuthorities()); 

        String sss = "";
        for (AuthMenu authMenu : authMenuService.findAll()) {
            sss += "<li><a href='" + contextPath + authMenu.getUrlPath() + "'><i class='" + authMenu.getDisplayIconClass() + "'></i> <span>" + authMenu.getDisplayName() + "</span></a></li>";
        }

        authUser.setCompleteMenu(sss);
        /*Set target URL to redirect*/
        //String targetUrl = determineTargetUrl(authentication);
        redirectStrategy.sendRedirect(request, response, "/homeSecure");
    }

//    protected String determineTargetUrl(Authentication authentication) {
//        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//        if (authorities.contains("ROLE_ADMIN")) {
//            return "/admin";
//        } else if (authorities.contains("ROLE_USER")) {
//            return "/user";
//        } else {
//            throw new IllegalStateException();
//        }
//    }
    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}

//import org.springframework.web.context.request.RequestContextHolder
/*
@Transactional
class MenuService {

    def grailsApplication
    def springSecurityService
    def webInvocationPrivilegeEvaluator
    def menuSession = 'appMenu'
    LinkGenerator grailsLinkGenerator

    def getMenu(String menuType) {
        def session = RequestContextHolder.currentRequestAttributes().getSession()
        def menu
        if (session.getAttribute(menuSession)) {
            menu = session.getAttribute(menuSession)
        } else {
            def menuString = new StringBuilder()
            menu = getChildMenu(null, menuType, 0, menuString)
            session.setAttribute(menuSession, menu)
        }
        return menu
    }

    def getChildMenu(Menu parentMenu, String menuType, Integer level, StringBuilder menuString) {
        def menuInstanceList = Menu.createCriteria().list {
            if (parentMenu)
                eq("parentMenu", parentMenu)
            else
                isNull("parentMenu")
            eq("menuType", menuType)
            eq("isActive", true)
            order('sortOrder', 'asc')
            order('title', 'asc')
        }

        if (menuInstanceList) {
            def ul = level == 0 ? '<ul class="sidebar-menu">' : '<ul class="treeview-menu">'
            menuString.append(ul)
            for (Menu menuInstance in menuInstanceList) {
                def linkProperties = menuInstance.isOpenNewTab ? 'target="_blank"' : 'target="_self"'
                def menuLink = menuInstance.isExternal ? menuInstance?.urlPath : grailsLinkGenerator.link(uri: menuInstance?.urlPath, absolute: false)
                def menuClass = menuInstance?.menuClass ? menuInstance?.menuClass : 'fa fa-circle-o'
                def menuTitle = level == 0 ? "<span>" + menuInstance?.title + "</span>" : menuInstance?.title
                def countChild = countChildMenu(menuInstance)
                if(countChild > 0){
                    menuTitle = menuTitle + "<i class=\"fa fa-angle-left pull-right\"></i>"
                }
                if (grailsApplication.config.grails.plugin.springsecurity.active == true && webInvocationPrivilegeEvaluator.isAllowed(menuInstance?.urlPath, springSecurityService.getAuthentication())) {
                        menuString.append('<li class="treeview"><a href="' + menuLink + '" ' + linkProperties + '><i class="' + menuClass + '"></i>' + menuTitle + '</a>')
                        getChildMenu(menuInstance, menuType, level + 1, menuString)
                        menuString.append('</li>')
                }
            }
            menuString.append('</ul>')
        }

        return menuString
    }

    def countChildMenu(Menu menuInstance){
        return Menu.countByParentMenuAndIsActive(menuInstance, true)
    }

    def getHierarchicalMenuList() {
        def menuInstanceList = _getHierarchicalMenuList(new ArrayList(), null, null)
        return menuInstanceList
    }

    def _getHierarchicalMenuList(List menuList, Menu parentMenu, String menuPrefix) {
        def menuInstanceList = Menu.createCriteria().list {
            if (parentMenu)
                eq("parentMenu", parentMenu)
            else
                isNull("parentMenu")
            eq("isActive", true)
            order('sortOrder', 'asc')
            order('title', 'asc')
        }
        if (menuInstanceList) {
            for (Menu menuInstance in menuInstanceList) {
                def title = menuPrefix ? menuPrefix + " > " + menuInstance.title : menuInstance.title
                menuList.add([id: menuInstance.id, title: title])
                _getHierarchicalMenuList(menuList, menuInstance, title)
            }
        }
        return menuList
    }

    def clearSession() {
        def session = RequestContextHolder.currentRequestAttributes().getSession()
        return session.removeAttribute(menuSession)
    }
}
 */
