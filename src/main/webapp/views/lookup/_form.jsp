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
            <form:label path="isActive"><spring:message code="isActive" text="Is Active"/></form:label>
            <br><form:checkbox class="cb" path="isActive"/>
            <form:errors path="isActive" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="slNo"><spring:message code="slNo" text="Sl No"/></form:label>
            <form:input class="form-control" path="slNo" type="number" size="15" maxlength="15"/>
            <form:errors path="slNo" cssClass="error" element="div"/>
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
            <form:label path="titleBng"><spring:message code="titleBng" text="Title Bng"/></form:label>
            <form:textarea class="form-control" path="titleBng" type="text" size="30" maxlength="500"/>
            <form:errors path="titleBng" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="remarks"><spring:message code="remarks" text="Remarks"/></form:label>
            <form:textarea class="form-control" path="remarks" type="text" size="30" maxlength="500"/>
            <form:errors path="remarks" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="keyword"><spring:message code="keyword" text="Keyword"/></form:label>
            <form:select class="form-control" path="keyword" name="keyword" id="keyword" >
                <form:option value="EDUCATION" label="EDUCATION"/>
                <form:option value="BLOOD_GROUP" label="BLOOD GROUP"/>
                <form:option value="PROFESSION" label="PROFESSION"/>
            </form:select>
            <form:errors path="keyword" cssClass="error" element="div"/>
        </div>
    </div>

</div>   