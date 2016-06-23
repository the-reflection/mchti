package org.reflection.model.security;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.reflection.model.com.AbstractEntity;

@Entity
@Table(name = "AUTH_MENU")
public class AuthMenu extends AbstractEntity {

    @NotEmpty
    @Column(name = "DISPLAY_NAME", length = 30, nullable = false)
    private String displayName;

    @Column(name = "TOOLTIP", length = 30)
    private String tooltip;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "MENU_TYPE", length = 100)
    private String menuType = "MAIN_MENU";

    @Column(name = "IS_EXTERNAL")
    private Boolean isExternal;
    @Column(name = "IS_OPEN_NEW_TAB")
    private Boolean isOpenNewTab;

    @Column(name = "SL_NO")
    private Integer slNo;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "PARENT_ID")
    private AuthMenu parentAuthMenu;

    @OneToMany(mappedBy = "parentAuthMenu")
    private Set<AuthMenu> childs = new LinkedHashSet<>();

    @NotEmpty
    @Column(name = "URL_PATH", unique = true, length = 255, nullable = false)
    private String urlPath;

    @Column(name = "DISPLAY_ICON_CLASS", length = 128)
    private String displayIconClass;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    public AuthMenu() {
    }

    public AuthMenu(String displayName, String urlPath) {
        this.displayName = displayName;
        this.urlPath = urlPath;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public AuthMenu getParentAuthMenu() {
        return parentAuthMenu;
    }

    public void setParentAuthMenu(AuthMenu parentAuthMenu) {
        this.parentAuthMenu = parentAuthMenu;
    }

    public Set<AuthMenu> getChilds() {
        return childs;
    }

    public void setChilds(Set<AuthMenu> childs) {
        this.childs = childs;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Boolean getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(Boolean isExternal) {
        this.isExternal = isExternal;
    }

    public Boolean getIsOpenNewTab() {
        return isOpenNewTab;
    }

    public void setIsOpenNewTab(Boolean isOpenNewTab) {
        this.isOpenNewTab = isOpenNewTab;
    }

    public Integer getSlNo() {
        return slNo;
    }

    public void setSlNo(Integer slNo) {
        this.slNo = slNo;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getDisplayIconClass() {
        return displayIconClass;
    }

    public void setDisplayIconClass(String displayIconClass) {
        this.displayIconClass = displayIconClass;
    }

}
