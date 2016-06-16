<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    .error {
        color: #ff0000;
    }
    .errorblock {
        color: #000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        padding: 8px;
        margin: 16px;
    }
</style>

<script type="text/javascript">
    function getCodableDTOEmployee(code, lblCaption){
        $.ajax({
            type : "GET",
            url: '${pageContext.request.contextPath}/employee/getCodableDTO',
            data: {code: code},
            success: function(d) {
                //alert("ok:"+d)
                $('#'+lblCaption).text(d)
            },
            error: function(err) {
                //alert("err mac:"+err)
                $('#'+lblCaption).text(err)
            }
        });
    }
</script>

<form:errors path="*" cssClass="errorblock" element="div" />
<form:hidden path="id"/>
<div>  
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label class="required" path="code"><spring:message code="code" text="Code"/></form:label>
            <form:input class="form-control" path="code" type="text" required="true" size="30" maxlength="20"/>
            <form:errors path="code" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="appDate"><spring:message code="appDate" text="App Date"/></form:label>
            <form:input class="form-control date" path="appDate"  placeholder="DD/MM/YYYY" />
            <form:errors path="appDate" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label class="required" path="employee"><spring:message code="employee" text="Employee"/></form:label>
            <form:input class="form-control" path="employee.code" required="true" onkeyup="getCodableDTOEmployee(this.value,'employee_caption')" type="text" size="8"/>
            <label id="employee_caption">${leaveApp.employee.fullName}</label>
            <form:errors path="employee" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label class="required" path="leaveType"><spring:message code="leaveType" text="Leave Type"/></form:label>
            <form:select class="form-control" path="leaveType" name="leaveType" id="leaveType" required="true" >
                <form:option value="CASUAL" label="CASUAL"/>
                <form:option value="EARN" label="EARN"/>
                <form:option value="LWOP" label="LWOP"/>
                <form:option value="LWP" label="LWP"/>
                <form:option value="MATERNITY" label="MATERNITY"/>
                <form:option value="ON_TOUR" label="ON TOUR"/>
                <form:option value="SICK" label="SICK"/>
                <form:option value="SPECIAL_WOP" label="SPECIAL WOP"/>
                <form:option value="SPECIAL_WP" label="SPECIAL WP"/>
            </form:select>
            <form:errors path="leaveType" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="startDate"><spring:message code="startDate" text="Start Date"/></form:label>
            <form:input class="form-control date" path="startDate"  placeholder="DD/MM/YYYY" />
            <form:errors path="startDate" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="endDate"><spring:message code="endDate" text="End Date"/></form:label>
            <form:input class="form-control date" path="endDate"  placeholder="DD/MM/YYYY" />
            <form:errors path="endDate" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="addressDuringLeave"><spring:message code="addressDuringLeave" text="Address During Leave"/></form:label>
            <form:textarea class="form-control" path="addressDuringLeave" type="text" size="30" maxlength="500"/>
            <form:errors path="addressDuringLeave" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="contactNo"><spring:message code="contactNo" text="Contact No"/></form:label>
            <form:textarea class="form-control" path="contactNo" type="text" size="30" maxlength="500"/>
            <form:errors path="contactNo" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="reasonForLeave"><spring:message code="reasonForLeave" text="Reason For Leave"/></form:label>
            <form:textarea class="form-control" path="reasonForLeave" type="text" size="30" maxlength="500"/>
            <form:errors path="reasonForLeave" cssClass="error" element="div"/>
        </div>
    </div>

</div>   