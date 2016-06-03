<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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



//function getCodableDTO(obj1, lblEmployeeName,lblEmployeeID){
//    var code = obj1; 
//   
//    $.ajax({
//        type : "GET",
//        url: '/MAC_CONTEXT_PATH/emp/getCodableDTOemp',
//        data: ({code : code}),
//        success: function(d) {
//            //alert("ok:"+d.id)
//            $('#'+lblEmployeeID).val(d.id)
//            $('#'+lblEmployeeName).text(d.caption)
//        },
//        error: function(err) {
//            //alert("err gg:"+err)
//            $('#'+lblEmployeeName).text(err.err)
//        }
//    });
//} 
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

            <%--                <img class="form-control" height="110px" width="90px" alt="${emp.picFile}" src="<%=request.getContextPath()%>/pics/${emp.picFile}"/>
            --%>            
            <form:hidden path="picFile"/>
            <input class="form-control" id="picFileOBJ" name="picFileOBJ" type="file" accept="image/*"/>

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
            <form:input class="form-control date" path="doj"   />
            <form:errors path="doj" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="dob"><spring:message code="dob" text="Dob"/></form:label>
            <form:input class="form-control" path="dob"  placeholder="DD/MM/YYYY"/>
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
            <form:label path="address"><spring:message code="address" text="Address"/></form:label>
            <form:textarea class="form-control" path="address" type="text" size="30" maxlength="1000"/>
            <form:errors path="address" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="salary"><spring:message code="salary" text="Salary"/></form:label>
            <form:input class="form-control" path="salary" type="number" size="15" maxlength="15"/>
            <form:errors path="salary" cssClass="error" element="div"/>
        </div>
    </div>

</div>   