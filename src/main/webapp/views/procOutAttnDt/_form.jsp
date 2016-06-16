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
            <form:label class="required" path="dtAttnType"><spring:message code="dtAttnType" text="Dt Attn Type"/></form:label>
            <form:select class="form-control" path="dtAttnType" name="dtAttnType" id="dtAttnType" required="true" >
                <form:option value="UNDEFINED" label="UNDEFINED"/>
                <form:option value="PRESENT" label="PRESENT"/>
                <form:option value="ABSENT" label="ABSENT"/>
                <form:option value="LATE" label="LATE"/>
                <form:option value="EARLY_OUT" label="EARLY OUT"/>
                <form:option value="LATE_COME_EARLY_OUT" label="LATE COME EARLY OUT"/>
                <form:option value="LEAVE" label="LEAVE"/>
                <form:option value="REST" label="REST"/>
                <form:option value="OFF_DAY" label="OFF DAY"/>
                <form:option value="OFF_DAY_DUTY" label="OFF DAY DUTY"/>
            </form:select>
            <form:errors path="dtAttnType" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label class="required" path="shift"><spring:message code="shift" text="Shift"/></form:label>
            <form:select class="form-control" path="shift.id" name="shift" id="shift" required="true" >
                <form:options items="${shifts}" itemValue="id" itemLabel="title"/>
            </form:select>
            <form:errors path="shift" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="inTime"><spring:message code="inTime" text="In Time"/></form:label>
            <form:input class="form-control date" path="inTime"  placeholder="DD/MM/YYYY" />
            <form:errors path="inTime" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="outTime"><spring:message code="outTime" text="Out Time"/></form:label>
            <form:input class="form-control date" path="outTime"  placeholder="DD/MM/YYYY" />
            <form:errors path="outTime" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="ot"><spring:message code="ot" text="Ot"/></form:label>
            <form:input class="form-control" path="ot" type="number" size="15" maxlength="15"/>
            <form:errors path="ot" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="remarks"><spring:message code="remarks" text="Remarks"/></form:label>
            <form:textarea class="form-control" path="remarks" type="text" size="30" maxlength="500"/>
            <form:errors path="remarks" cssClass="error" element="div"/>
        </div>
    </div>

</div>   