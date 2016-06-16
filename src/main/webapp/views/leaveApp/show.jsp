<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="main" >

    <tiles:putAttribute name="body">
        
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->
            <section class="content-header"><!-- Content Header (Page header) -->
                <h1><spring:message code="default.button.show.label" text="Show"/> LeaveApp</h1>
                <ul class="top-links">
                    <sec:access url="/leaveApp/create">
                        <li>
                            <a href="${pageContext.request.contextPath}/leaveApp/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="New"/></a>
                        </li>
                    </sec:access>
                    <sec:access url="/leaveApp/index">
                        <li>
                            <a href="${pageContext.request.contextPath}/leaveApp/index" class="btn btn-block btn-info btn-xs"><i class="fa fa-reorder"></i> <spring:message code="default.button.list.label" text="List"/></a>
                        </li>
                    </sec:access>
                </ul>
            </section><!-- /.content-header -->

            <section class="content-messages">
                <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
            </section><!-- /.flesh-message -->

            <section class="content"><!-- Main content -->
                <div class="box box-primary">
                    <div class="box-body">
                        <fieldset class="show-page">
                             <form:hidden path="id"/>
        <ol class="property-list hrIrGrdScr">
    
        <c:if test="${leaveApp.version!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="version" text="Version"/>: 
                </span>
                <span class="property-value" aria-labelledby="version">
                    <c:out value="${leaveApp.version}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${leaveApp.code!=null && !leaveApp.code.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="code" text="Code"/>: 
                </span>
                <span class="property-value" aria-labelledby="code">
                    <c:out value="${leaveApp.code}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${leaveApp.appDate!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="appDate" text="App Date"/>: 
                </span>
                <span class="property-value" aria-labelledby="appDate">
                    <fmt:formatDate value="${leaveApp.appDate}" type="date" pattern="dd/MM/yyyy"/>
                </span>
            </li>
        </c:if>

        <c:if test="${leaveApp.employee!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="employee" text="Employee"/>: 
                </span>
                <span class="property-value" aria-labelledby="employee">
                    <c:out value="${leaveApp.employee!=null ? leaveApp.employee :'N/A'}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${leaveApp.leaveType!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="leaveType" text="Leave Type"/>: 
                </span>
                <span class="property-value" aria-labelledby="leaveType">
                    <c:out value="${leaveApp.leaveType}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${leaveApp.startDate!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="startDate" text="Start Date"/>: 
                </span>
                <span class="property-value" aria-labelledby="startDate">
                    <fmt:formatDate value="${leaveApp.startDate}" type="date" pattern="dd/MM/yyyy"/>
                </span>
            </li>
        </c:if>

        <c:if test="${leaveApp.endDate!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="endDate" text="End Date"/>: 
                </span>
                <span class="property-value" aria-labelledby="endDate">
                    <fmt:formatDate value="${leaveApp.endDate}" type="date" pattern="dd/MM/yyyy"/>
                </span>
            </li>
        </c:if>

        <c:if test="${leaveApp.addressDuringLeave!=null && !leaveApp.addressDuringLeave.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="addressDuringLeave" text="Address During Leave"/>: 
                </span>
                <span class="property-value" aria-labelledby="addressDuringLeave">
                    <c:out value="${leaveApp.addressDuringLeave}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${leaveApp.contactNo!=null && !leaveApp.contactNo.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="contactNo" text="Contact No"/>: 
                </span>
                <span class="property-value" aria-labelledby="contactNo">
                    <c:out value="${leaveApp.contactNo}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${leaveApp.reasonForLeave!=null && !leaveApp.reasonForLeave.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="reasonForLeave" text="Reason For Leave"/>: 
                </span>
                <span class="property-value" aria-labelledby="reasonForLeave">
                    <c:out value="${leaveApp.reasonForLeave}"/>
                </span>
            </li>
        </c:if>

        </ol>
    
    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class="box-footer">
                            <a href="${pageContext.request.contextPath}/leaveApp/edit/${leaveApp.id}" class="btn btn-primary"><i class="fa fa-edit"></i> <spring:message code="edit.link.label"/></a> 
                            <a href="${pageContext.request.contextPath}/leaveApp/copy/${leaveApp.id}" class="btn btn-warning"><i class="fa fa-clone"></i> <spring:message code="copy.link.label"/></a>             
                            <a href="${pageContext.request.contextPath}/leaveApp/delete/${leaveApp.id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete?');"><i class="fa fa-remove"></i> <spring:message code="delete.link.label"/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>