<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate"/>

<tiles:putAttribute name="header">
    <jsp:include page="../layouts/header.jsp"/>
</tiles:putAttribute>

<tiles:putAttribute name="menu">
    <jsp:include page="../layouts/menu.jsp"/>
</tiles:putAttribute>

<div class="box box-primary">
    <div class="box-body">
<tiles:putAttribute name="body">

    <title><spring:message code="project.title.show" text="Show"/></title>

    <div class="btn-group">
        <a class="btn btn-info" href="${pageContext.request.contextPath}/zxEmpEduDtl/index"><spring:message code="list.link.label"/>&NonBreakingSpace;<spring:message code="zxEmpEduDtl" text="ZxEmp Edu Dtl"/></a> 
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/zxEmpEduDtl/create"><spring:message code="create.link.label"/>&NonBreakingSpace;<spring:message code="zxEmpEduDtl" text="ZxEmp Edu Dtl"/></a>
    </div>

    <h1><spring:message code="show.page.title"/></h1>
    
    <div>
        <form:hidden path="id"/>
        <ol class="property-list hrIrGrdScr">
    
        <c:if test="${zxEmpEduDtl.code!=null && !zxEmpEduDtl.code.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="code" text="Code"/>: 
                </span>
                <span class="property-value" aria-labelledby="code">
                    <c:out value="${zxEmpEduDtl.code}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${zxEmpEduDtl.exam!=null && !zxEmpEduDtl.exam.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="exam" text="Exam"/>: 
                </span>
                <span class="property-value" aria-labelledby="exam">
                    <c:out value="${zxEmpEduDtl.exam}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${zxEmpEduDtl.examOrientDate!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="examOrientDate" text="Exam Orient Date"/>: 
                </span>
                <span class="property-value" aria-labelledby="examOrientDate">
                    <fmt:formatDate value="${zxEmpEduDtl.examOrientDate}" type="date" pattern="dd/MM/yyyy"/>
                </span>
            </li>
        </c:if>

        <c:if test="${zxEmpEduDtl.remarks!=null && !zxEmpEduDtl.remarks.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="remarks" text="Remarks"/>: 
                </span>
                <span class="property-value" aria-labelledby="remarks">
                    <c:out value="${zxEmpEduDtl.remarks}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${zxEmpEduDtl.slNo!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="slNo" text="Sl No"/>: 
                </span>
                <span class="property-value" aria-labelledby="slNo">
                    <c:out value="${zxEmpEduDtl.slNo}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${zxEmpEduDtl.certificateFile!=null && !zxEmpEduDtl.certificateFile.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="certificateFile" text="Certificate File"/>: 
                </span>
                <span class="property-value" aria-labelledby="certificateFile">
                    <c:url var="certificateFile" value="/zxEmpEduDtl/getFile/${zxEmpEduDtl.certificateFile}"/>
<a target="_blank" href="${certificateFile}">${zxEmpEduDtl.certificateFile}</a>
                </span>
            </li>
        </c:if>

        <c:if test="${zxEmpEduDtl.picFile!=null && !zxEmpEduDtl.picFile.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="picFile" text="ZxEmp Who Pic File"/>: 
                </span>
                <span class="property-value" aria-labelledby="picFile">
                    <c:url var="picFile" value="/zxEmpEduDtl/getPhoto/${zxEmpEduDtl.picFile}"/><img height="110px" width="90px" alt="${zxEmpEduDtl.picFile}" src="${picFile}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${zxEmpEduDtl.zxEmp!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="zxEmp" text="ZxEmp Id"/>: 
                </span>
                <span class="property-value" aria-labelledby="zxEmp">
                    <c:out value="${zxEmpEduDtl.zxEmp!=null ? zxEmpEduDtl.zxEmp :'N/A'}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${zxEmpEduDtl.zxEmpWhoCheckedBy!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="zxEmpWhoCheckedBy" text="ZxEmp Who Checked By Id"/>: 
                </span>
                <span class="property-value" aria-labelledby="zxEmpWhoCheckedBy">
                    <c:out value="${zxEmpEduDtl.zxEmpWhoCheckedBy!=null ? zxEmpEduDtl.zxEmpWhoCheckedBy :'N/A'}"/>
                </span>
            </li>
        </c:if>

        </ol>
    
    </div>


    <div class="btn-group">
        <a href="${pageContext.request.contextPath}/zxEmpEduDtl/edit/${zxEmpEduDtl.id}" class="btn btn-primary"><spring:message code="edit.link.label"/></a> 
        <a href="${pageContext.request.contextPath}/zxEmpEduDtl/copy/${zxEmpEduDtl.id}" class="btn btn-warning"><spring:message code="copy.link.label"/></a>             
        <a href="${pageContext.request.contextPath}/zxEmpEduDtl/delete/${zxEmpEduDtl.id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete?');" ><spring:message code="delete.link.label"/></a>
    </div>
                    
</tiles:putAttribute>  

        <div class="box-footer">
            <tiles:putAttribute name="footer">
                <jsp:include page="../layouts/footer.jsp"/>
            </tiles:putAttribute>  
        </div>
    </div> 