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
            <form:label class="required" path="period"><spring:message code="period" text="Period"/></form:label>
            <form:select class="form-control" path="period.id" name="period" id="period" required="true" >
                <form:options items="${periods}" itemValue="id" itemLabel="title"/>
            </form:select>
            <form:errors path="period" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label class="required" path="roster"><spring:message code="roster" text="Roster"/></form:label>
            <form:select class="form-control" path="roster.id" name="roster" id="roster" required="true" >
                <form:options items="${rosters}" itemValue="id" itemLabel="title"/>
            </form:select>
            <form:errors path="roster" cssClass="error" element="div"/>
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

</div>   