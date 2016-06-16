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
            <form:label class="required" path="employee"><spring:message code="employee" text="Employee"/></form:label>
            <form:input class="form-control" path="employee.code" required="true" onkeyup="getCodableDTOEmployee(this.value,'employee_caption')" type="text" size="8"/>
            <label id="employee_caption">${assignmentPrl.employee.fullName}</label>
            <form:errors path="employee" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="startDate"><spring:message code="startDate" text="Start Date"/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='startDate'  />
             </div>
            <form:errors path="startDate" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="endDate"><spring:message code="endDate" text="End Date"/></form:label>
            <div class='input-group'>
                <div class='input-group-addon'><i class='fa fa-calendar'></i></div>
                <form:input class='form-control dtp-date' path='endDate'  />
             </div>
            <form:errors path="endDate" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="basic"><spring:message code="basic" text="Basic"/></form:label>
            <form:input class="form-control" path="basic" type="number" size="15" maxlength="15"/>
            <form:errors path="basic" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="houseRent"><spring:message code="houseRent" text="House Rent"/></form:label>
            <form:input class="form-control" path="houseRent" type="number" size="15" maxlength="15"/>
            <form:errors path="houseRent" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="medical"><spring:message code="medical" text="Medical"/></form:label>
            <form:input class="form-control" path="medical" type="number" size="15" maxlength="15"/>
            <form:errors path="medical" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="convance"><spring:message code="convance" text="Convance"/></form:label>
            <form:input class="form-control" path="convance" type="number" size="15" maxlength="15"/>
            <form:errors path="convance" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="otherAllowance"><spring:message code="otherAllowance" text="Other Allowance"/></form:label>
            <form:input class="form-control" path="otherAllowance" type="number" size="15" maxlength="15"/>
            <form:errors path="otherAllowance" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="gross"><spring:message code="gross" text="Gross"/></form:label>
            <form:input class="form-control" path="gross" type="number" size="15" maxlength="15"/>
            <form:errors path="gross" cssClass="error" element="div"/>
        </div>
    </div>

</div>   