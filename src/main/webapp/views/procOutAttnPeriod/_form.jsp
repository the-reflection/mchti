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
            <form:label class="required" path="genType"><spring:message code="genType" text="Gen Type"/></form:label>
            <form:select class="form-control" path="genType" name="genType" id="genType" required="true" >
                <form:option value="AUTO" label="AUTO"/>
                <form:option value="MANUAL" label="MANUAL"/>
                <form:option value="AUTO_TO_MANUAL" label="AUTO TO MANUAL"/>
            </form:select>
            <form:errors path="genType" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label class="required" path="attnSts"><spring:message code="attnSts" text="Attn Sts"/></form:label>
            <form:select class="form-control" path="attnSts" name="attnSts" id="attnSts" required="true" >
                <form:option value="NEW" label="NEW"/>
                <form:option value="REGULAR" label="REGULAR"/>
                <form:option value="RESIGN" label="RESIGN"/>
                <form:option value="ABSENT" label="ABSENT"/>
            </form:select>
            <form:errors path="attnSts" cssClass="error" element="div"/>
        </div>
    </div>
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
            <form:label class="required" path="employee"><spring:message code="employee" text="Employee"/></form:label>
            <form:input class="form-control" path="employee.code" required="true" onkeyup="getCodableDTOEmployee(this.value,'employee_caption')" type="text" size="8"/>
            <label id="employee_caption">${procOutAttnPeriod.employee.fullName}</label>
            <form:errors path="employee" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dayPresent"><spring:message code="dayPresent" text="Day Present"/></form:label>
            <form:input class="form-control" path="dayPresent" type="number" size="15" maxlength="15"/>
            <form:errors path="dayPresent" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dayAbsent"><spring:message code="dayAbsent" text="Day Absent"/></form:label>
            <form:input class="form-control" path="dayAbsent" type="number" size="15" maxlength="15"/>
            <form:errors path="dayAbsent" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dayLate"><spring:message code="dayLate" text="Day Late"/></form:label>
            <form:input class="form-control" path="dayLate" type="number" size="15" maxlength="15"/>
            <form:errors path="dayLate" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dayEarlyOut"><spring:message code="dayEarlyOut" text="Day Early Out"/></form:label>
            <form:input class="form-control" path="dayEarlyOut" type="number" size="15" maxlength="15"/>
            <form:errors path="dayEarlyOut" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dayLateInEarlyOut"><spring:message code="dayLateInEarlyOut" text="Day Late In Early Out"/></form:label>
            <form:input class="form-control" path="dayLateInEarlyOut" type="number" size="15" maxlength="15"/>
            <form:errors path="dayLateInEarlyOut" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dayCl"><spring:message code="dayCl" text="Day Cl"/></form:label>
            <form:input class="form-control" path="dayCl" type="number" size="15" maxlength="15"/>
            <form:errors path="dayCl" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="daySl"><spring:message code="daySl" text="Day Sl"/></form:label>
            <form:input class="form-control" path="daySl" type="number" size="15" maxlength="15"/>
            <form:errors path="daySl" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dayEl"><spring:message code="dayEl" text="Day El"/></form:label>
            <form:input class="form-control" path="dayEl" type="number" size="15" maxlength="15"/>
            <form:errors path="dayEl" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dayMl"><spring:message code="dayMl" text="Day Ml"/></form:label>
            <form:input class="form-control" path="dayMl" type="number" size="15" maxlength="15"/>
            <form:errors path="dayMl" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dayLwp"><spring:message code="dayLwp" text="Day Lwp"/></form:label>
            <form:input class="form-control" path="dayLwp" type="number" size="15" maxlength="15"/>
            <form:errors path="dayLwp" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="otHourNormal"><spring:message code="otHourNormal" text="Ot Hour Normal"/></form:label>
            <form:input class="form-control" path="otHourNormal" type="number" size="15" maxlength="15"/>
            <form:errors path="otHourNormal" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="otHourSro"><spring:message code="otHourSro" text="Ot Hour Sro"/></form:label>
            <form:input class="form-control" path="otHourSro" type="number" size="15" maxlength="15"/>
            <form:errors path="otHourSro" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="otHourExtra"><spring:message code="otHourExtra" text="Ot Hour Extra"/></form:label>
            <form:input class="form-control" path="otHourExtra" type="number" size="15" maxlength="15"/>
            <form:errors path="otHourExtra" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="otHourHoliday"><spring:message code="otHourHoliday" text="Ot Hour Holiday"/></form:label>
            <form:input class="form-control" path="otHourHoliday" type="number" size="15" maxlength="15"/>
            <form:errors path="otHourHoliday" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dayHolidayWork"><spring:message code="dayHolidayWork" text="Day Holiday Work"/></form:label>
            <form:input class="form-control" path="dayHolidayWork" type="number" size="15" maxlength="15"/>
            <form:errors path="dayHolidayWork" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dayHoliday"><spring:message code="dayHoliday" text="Day Holiday"/></form:label>
            <form:input class="form-control" path="dayHoliday" type="number" size="15" maxlength="15"/>
            <form:errors path="dayHoliday" cssClass="error" element="div"/>
        </div>
    </div>

</div>   