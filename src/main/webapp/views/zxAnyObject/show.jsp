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
        <a class="btn btn-info" href="${pageContext.request.contextPath}/anyObject/index"><spring:message code="list.link.label"/>&NonBreakingSpace;<spring:message code="anyObject" text="Any Object"/></a> 
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/anyObject/create"><spring:message code="create.link.label"/>&NonBreakingSpace;<spring:message code="anyObject" text="Any Object"/></a>
    </div>

    <h1><spring:message code="show.page.title"/></h1>
    
    <div>
        <form:hidden path="id"/>
        <ol class="property-list hrIrGrdScr">
    
        <c:if test="${anyObject.code!=null && !anyObject.code.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="code" text="Code"/>: 
                </span>
                <span class="property-value" aria-labelledby="code">
                    <c:out value="${anyObject.code}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${anyObject.title!=null && !anyObject.title.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="title" text="Title"/>: 
                </span>
                <span class="property-value" aria-labelledby="title">
                    <c:out value="${anyObject.title}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${anyObject.remarks!=null && !anyObject.remarks.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="remarks" text="Remarks"/>: 
                </span>
                <span class="property-value" aria-labelledby="remarks">
                    <c:out value="${anyObject.remarks}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${anyObject.startDate!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="startDate" text="Start Date"/>: 
                </span>
                <span class="property-value" aria-labelledby="startDate">
                    <fmt:formatDate value="${anyObject.startDate}" type="date" pattern="dd/MM/yyyy"/>
                </span>
            </li>
        </c:if>

        <c:if test="${anyObject.isActive!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="isActive" text="Is Active"/>: 
                </span>
                <span class="property-value" aria-labelledby="isActive">
                    <c:if test="${anyObject.isActive}"><spring:message code="default.boolean.true" text="YES"/></c:if><c:if test="${!anyObject.isActive}"><spring:message code="default.boolean.false" text="NO"/></c:if>
                </span>
            </li>
        </c:if>

        <c:if test="${anyObject.slNo!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="slNo" text="Sl No"/>: 
                </span>
                <span class="property-value" aria-labelledby="slNo">
                    <c:out value="${anyObject.slNo}"/>
                </span>
            </li>
        </c:if>

        </ol>
    
    </div>


    <div class="btn-group">
        <a href="${pageContext.request.contextPath}/anyObject/edit/${anyObject.id}" class="btn btn-primary"><spring:message code="edit.link.label"/></a> 
        <a href="${pageContext.request.contextPath}/anyObject/copy/${anyObject.id}" class="btn btn-warning"><spring:message code="copy.link.label"/></a>             
        <a href="${pageContext.request.contextPath}/anyObject/delete/${anyObject.id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete?');" ><spring:message code="delete.link.label"/></a>
    </div>
                    
</tiles:putAttribute>  

        <div class="box-footer">
            <tiles:putAttribute name="footer">
                <jsp:include page="../layouts/footer.jsp"/>
            </tiles:putAttribute>  
        </div>
    </div> 