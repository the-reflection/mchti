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
    function getCodableDTOZxEmp(code, lblCaption) {
        $.ajax({
            type: "GET",
            url: '${pageContext.request.contextPath}/zxEmp/getCodableDTO',
            data: {code: code},
            success: function (d) {
                //alert("ok:"+d)
                $('#' + lblCaption).text(d)
            },
            error: function (err) {
                //alert("err mac:"+err)
                $('#' + lblCaption).text(err)
            }
        });
    }
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
            <form:label class="required" path="exam"><spring:message code="exam" text="Exam"/></form:label>
            <form:input class="form-control" path="exam" type="text" required="true" size="30" maxlength="20"/>
            <form:errors path="exam" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="examOrientDate"><spring:message code="examOrientDate" text="Exam Orient Date"/></form:label>
            <form:input class="form-control date" path="examOrientDate"  placeholder="DD/MM/YYYY"/>
            <form:errors path="examOrientDate" cssClass="error" element="div"/>
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
            <form:label path="slNo"><spring:message code="slNo" text="Sl No"/></form:label>
            <form:input class="form-control" path="slNo" type="number" size="15" maxlength="15"/>
            <form:errors path="slNo" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="certificateFile"><spring:message code="certificateFile" text="Certificate File"/></form:label>
            <c:url var="certificateFile" value="/zxEmpEduDtl/getFile/${zxEmpEduDtl.certificateFile}"/>
            <a target="_blank" href="${certificateFile}">${zxEmpEduDtl.certificateFile}</a>
            <form:hidden path="certificateFile"/>
            <input id="certificateFileOBJ" name="certificateFileOBJ" type="file"/>

            <form:errors path="certificateFile" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="picFile"><spring:message code="picFile" text="ZxEmp Who Pic File"/></form:label>
            <c:url var="picFile" value="/zxEmpEduDtl/getPhoto/${zxEmpEduDtl.picFile}"/>
            <img height="110px" width="90px" alt="${zxEmpEduDtl.picFile}" src="${picFile}"/>
            <form:hidden path="picFile"/>
            <input id="picFileOBJ" name="picFileOBJ" type="file" accept="image/*"/>
            <form:errors path="picFile" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label class="required" path="zxEmp"><spring:message code="zxEmp" text="Zx Emp"/></form:label>
            <form:input class="form-control" path="zxEmp.code" required="true" onkeyup="getCodableDTOZxEmp(this.value,'zxEmp_caption')" type="text" size="8"/>
            <label id="zxEmp_caption">${zxEmpEduDtl.zxEmp.fullName}</label>
            <form:errors path="zxEmp" cssClass="error" element="div"/>
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
        <div class="form-group">
            <form:label path="zxEmpWhoCheckedBy"><spring:message code="zxEmpWhoCheckedBy" text="Zx Emp Who Checked By"/></form:label>
            <form:input class="form-control" path="zxEmpWhoCheckedBy.code" onkeyup="getCodableDTOZxEmp(this.value,'zxEmpWhoCheckedBy_caption')" type="text" size="8"/>
            <label id="zxEmpWhoCheckedBy_caption">${zxEmpEduDtl.zxEmpWhoCheckedBy.fullName}</label>
            <form:errors path="zxEmpWhoCheckedBy" cssClass="error" element="div"/>
        </div>
    </div>

</div>   