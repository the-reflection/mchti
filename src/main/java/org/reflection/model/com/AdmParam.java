package org.reflection.model.com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(catalog = "MCHTI", name = "ADM_PARAM")
public class AdmParam extends AbstractEntity {

    @NotNull
    @Column(name = "TITLE", nullable = false, length = 30)
    private String title;
    @NotNull
    @Column(name = "PARAM_NAME", nullable = false, unique = true, length = 30)
    private String paramName;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ADM_WIDGET_TYPE", length = 30, nullable = false)
    private AdmWidgetType admWidgetType;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "IS_MANDATORY")
    private Boolean isMandatory;
    @Column(name = "SL_NO")
    private Integer slNo;
    @Column(name = "CMD", length = 500)
    private String cmd;
    @Column(name = "DEFAULT_VAL")
    private String defaultVal;
    @Column(name = "HELP_TEXT")
    private String helpText;

    public AdmParam() {
    }

    public AdmParam(String title, String paramName, AdmWidgetType admWidgetType, Boolean isActive, Boolean isMandatory, Integer slNo, String cmd, String defaultVal, String helpText) {
        this.title = title;
        this.paramName = paramName;
        this.admWidgetType = admWidgetType;
        this.isActive = isActive;
        this.isMandatory = isMandatory;
        this.slNo = slNo;
        this.cmd = cmd;
        this.defaultVal = defaultVal;
        this.helpText = helpText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Integer getSlNo() {
        return slNo;
    }

    public void setSlNo(Integer slNo) {
        this.slNo = slNo;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getDefaultVal() {
        return defaultVal;
    }

    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public AdmWidgetType getAdmWidgetType() {
        return admWidgetType;
    }

    public void setAdmWidgetType(AdmWidgetType admWidgetType) {
        this.admWidgetType = admWidgetType;
    }

    @Override
    public String toString() {
        return title;
    }

}
