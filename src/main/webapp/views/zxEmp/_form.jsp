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

<form:errors path="*" cssClass="errorblock" element="div"/>
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
            <form:label path="picFile"><spring:message code="picFile" text="Pic File"/></form:label>
            <c:url var="picFile" value="/zxEmp/getPhoto/${zxEmp.picFile}"/>
            <img height="110px" width="90px" alt="${zxEmp.picFile}" src="${picFile}"/>
            <form:hidden path="picFile"/>
            <%--<form:input type="file" path="picFile" id="picFile"/>--%>
            <input id="picFileOBJ" name="picFileOBJ" type="file" class="form-control input-sm" accept="image/*"/>
            <form:errors path="picFile" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label class="required" path="fullName"><spring:message code="fullName" text="Full Name"/></form:label>
            <form:input class="form-control" path="fullName" type="text" required="true" size="30" maxlength="100"/>
            <form:errors path="fullName" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="zxGender"><spring:message code="zxGender" text="Zx Gender"/></form:label>
            <form:select class="form-control" path="zxGender" name="zxGender" id="zxGender" >
                <form:option value="MALE" label="Male"/>
                <form:option value="FEMALE" label="Female"/>
                <form:option value="OTHER" label="Other"/>
            </form:select>
            <form:errors path="zxGender" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="startDate"><spring:message code="startDate" text="Start Date"/></form:label>
            <form:input class="form-control date" path="startDate"  placeholder="DD/MM/YYYY"/>
            <form:errors path="startDate" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dob"><spring:message code="dob" text="Dob"/></form:label>
            <form:input class="form-control date" path="dob"  placeholder="DD/MM/YYYY"/>
            <form:errors path="dob" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="sal"><spring:message code="sal" text="Sal"/></form:label>
            <form:input class="form-control" path="sal" type="number" size="15" maxlength="15"/>
            <form:errors path="sal" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="taxPaid"><spring:message code="taxPaid" text="Tax Paid"/></form:label>
            <form:input class="form-control" path="taxPaid" type="number" size="15" maxlength="15"/>
            <form:errors path="taxPaid" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="email"><spring:message code="email" text="Email"/></form:label>
            <form:input class="form-control" path="email" type="email" size="30" maxlength="30"/>
            <form:errors path="email" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="pin"><spring:message code="pin" text="Pin"/></form:label>
            <form:password class="form-control" path="pin" showPassword="true" size="30" maxlength="30"/>
            <form:errors path="pin" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="webAddress"><spring:message code="webAddress" text="Web Address"/></form:label>
            <form:input class="form-control" path="webAddress" type="text" size="30" maxlength="20"/>
            <form:errors path="webAddress" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="docFile"><spring:message code="docFile" text="Doc File"/></form:label>
            <c:url var="docFile" value="/zxEmp/getFile/${zxEmp.docFile}"/>
            <a target="_blank" href="${docFile}">${zxEmp.docFile}</a>
            <form:hidden path="docFile"/>
            <input id="docFileOBJ" name="docFileOBJ" type="file"/>
            <form:errors path="docFile" cssClass="error" element="div"/>
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
            <form:label path="remarks"><spring:message code="remarks" text="Remarks"/></form:label>
            <form:textarea class="form-control" path="remarks" type="text" size="30" maxlength="2000"/>
            <form:errors path="remarks" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="zxLookupBloodGroup"><spring:message code="zxLookupBloodGroup" text="Zx Lookup Blood Group"/></form:label>
            <form:select class="form-control" path="zxLookupBloodGroup.id" name="zxLookupBloodGroup" id="zxLookupBloodGroup" >
                <form:option value="${null}" label="--- Select ---"/>
                <form:options items="${zxLookupBloodGroups}" itemValue="id" itemLabel="title"/>
            </form:select>
            <form:errors path="zxLookupBloodGroup" cssClass="error" element="div"/>
        </div>
    </div>

</div>   