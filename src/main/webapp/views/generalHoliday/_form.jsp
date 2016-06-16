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
            <form:label class="required" path="title"><spring:message code="title" text="Title"/></form:label>
            <form:input class="form-control" path="title" type="text" required="true" size="30" maxlength="100"/>
            <form:errors path="title" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label class="required" path="holidayType"><spring:message code="holidayType" text="Holiday Type"/></form:label>
            <form:select class="form-control" path="holidayType" name="holidayType" id="holidayType" required="true" >
                <form:option value="WEEKEND" label="WEEKEND"/>
                <form:option value="GOVERNMENT" label="GOVERNMENT"/>
                <form:option value="INTERNATIONAL" label="INTERNATIONAL"/>
                <form:option value="OFFICIAL" label="OFFICIAL"/>
            </form:select>
            <form:errors path="holidayType" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label class="required" path="onDay"><spring:message code="onDay" text="On Day"/></form:label>
            <form:input class="form-control" path="onDay" type="number" required="true" size="15" maxlength="15"/>
            <form:errors path="onDay" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label class="required" path="onMonth"><spring:message code="onMonth" text="On Month"/></form:label>
            <form:input class="form-control" path="onMonth" type="number" required="true" size="15" maxlength="15"/>
            <form:errors path="onMonth" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="isActive"><spring:message code="isActive" text="Is Active"/></form:label>
            <br><form:checkbox class='cb' path='isActive'/>
            <form:errors path="isActive" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="remarks"><spring:message code="remarks" text="Remarks"/></form:label>
            <form:input class="form-control" path="remarks" type="text" size="30" maxlength="100"/>
            <form:errors path="remarks" cssClass="error" element="div"/>
        </div>
    </div>

</div>   