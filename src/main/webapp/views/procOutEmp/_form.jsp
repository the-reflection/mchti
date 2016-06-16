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
            <form:label path="picFile"><spring:message code="picFile" text="Pic File"/></form:label>
                <c:url var="picFile" value="/procOutEmp/getPhoto/${procOutEmp.picFile}"/>
                <img height="110px" width="90px" alt="${procOutEmp.picFile}" src="${picFile}"/>
                <form:hidden path="picFile"/>
                <input id="picFileOBJ" name="picFileOBJ" type="file" accept="image/*"/>
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
            <form:label path="gender"><spring:message code="gender" text="Gender"/></form:label>
            <form:select class="form-control" path="gender" name="gender" id="gender" >
                <form:option value="MALE" label="Male"/>
                <form:option value="FEMALE" label="Female"/>
                <form:option value="OTHER" label="Other"/>
            </form:select>
            <form:errors path="gender" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="doj"><spring:message code="doj" text="Doj"/></form:label>
            <form:input class="form-control date" path="doj"  placeholder="DD/MM/YYYY" />
            <form:errors path="doj" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dob"><spring:message code="dob" text="Dob"/></form:label>
            <form:input class="form-control date" path="dob"  placeholder="DD/MM/YYYY" />
            <form:errors path="dob" cssClass="error" element="div"/>
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
            <form:label path="department"><spring:message code="department" text="Department"/></form:label>
            <form:select class="form-control" path="department.id" name="department" id="department" >
                <form:option value="${null}" label="--- Select ---"/>
                <form:options items="${departments}" itemValue="id" itemLabel="title"/>
            </form:select>
            <form:errors path="department" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="designation"><spring:message code="designation" text="Designation"/></form:label>
            <form:select class="form-control" path="designation.id" name="designation" id="designation" >
                <form:option value="${null}" label="--- Select ---"/>
                <form:options items="${designations}" itemValue="id" itemLabel="title"/>
            </form:select>
            <form:errors path="designation" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="empGroup"><spring:message code="empGroup" text="Emp Group"/></form:label>
            <form:select class="form-control" path="empGroup" name="empGroup" id="empGroup" >
                <form:option value="ACTIVE" label="ACTIVE"/>
                <form:option value="EXTERNAL" label="EXTERNAL"/>
                <form:option value="RETIRED" label="RETIRED"/>
                <form:option value="TERMINATED" label="TERMINATED"/>
            </form:select>
            <form:errors path="empGroup" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="shiftOffDay"><spring:message code="shiftOffDay" text="Shift Off Day"/></form:label>
            <form:select class="form-control" path="shiftOffDay" name="shiftOffDay" id="shiftOffDay" >
                <form:option value="FRIDAY" label="FRIDAY"/>
                <form:option value="SATURDAY" label="SATURDAY"/>
                <form:option value="SUNDAY" label="SUNDAY"/>
                <form:option value="MONDAY" label="MONDAY"/>
                <form:option value="TUESDAY" label="TUESDAY"/>
                <form:option value="WEDNESDAY" label="WEDNESDAY"/>
                <form:option value="THURSDAY" label="THURSDAY"/>
            </form:select>
            <form:errors path="shiftOffDay" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="shift"><spring:message code="shift" text="Shift"/></form:label>
            <form:select class="form-control" path="shift.id" name="shift" id="shift" >
                <form:option value="${null}" label="--- Select ---"/>
                <form:options items="${shifts}" itemValue="id" itemLabel="title"/>
            </form:select>
            <form:errors path="shift" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="roster"><spring:message code="roster" text="Roster"/></form:label>
            <form:select class="form-control" path="roster.id" name="roster" id="roster" >
                <form:option value="${null}" label="--- Select ---"/>
                <form:options items="${rosters}" itemValue="id" itemLabel="title"/>
            </form:select>
            <form:errors path="roster" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="isOvertime"><spring:message code="isOvertime" text="Is Overtime"/></form:label>
            <br><form:checkbox class="cb" path="isOvertime"/>
            <form:errors path="isOvertime" cssClass="error" element="div"/>
        </div>
    </div>

</div>   