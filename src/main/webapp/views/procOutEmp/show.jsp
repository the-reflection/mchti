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
                <h1><spring:message code="default.button.show.label" text="Show"/> ProcOutEmp</h1>
                <ul class="top-links">
                    <sec:access url="/procOutEmp/create">
                        <li>
                            <a href="${pageContext.request.contextPath}/procOutEmp/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="New"/></a>
                        </li>
                    </sec:access>
                    <sec:access url="/procOutEmp/index">
                        <li>
                            <a href="${pageContext.request.contextPath}/procOutEmp/index" class="btn btn-block btn-info btn-xs"><i class="fa fa-reorder"></i> <spring:message code="default.button.list.label" text="List"/></a>
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
                             <form:hidden path="employee"/>
        <ol class="property-list hrIrGrdScr">
    
        <c:if test="${procOutEmp.version!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="version" text="Version"/>: 
                </span>
                <span class="property-value" aria-labelledby="version">
                    <c:out value="${procOutEmp.version}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.code!=null && !procOutEmp.code.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="code" text="Code"/>: 
                </span>
                <span class="property-value" aria-labelledby="code">
                    <c:out value="${procOutEmp.code}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.picFile!=null && !procOutEmp.picFile.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="picFile" text="Pic File"/>: 
                </span>
                <span class="property-value" aria-labelledby="picFile">
                    <c:url var="picFile" value="/procOutEmp/getPhoto/${procOutEmp.picFile}"/>
<img height="110px" width="90px" alt="${procOutEmp.picFile}" src="${picFile}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.fullName!=null && !procOutEmp.fullName.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="fullName" text="Full Name"/>: 
                </span>
                <span class="property-value" aria-labelledby="fullName">
                    <c:out value="${procOutEmp.fullName}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.gender!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="gender" text="Gender"/>: 
                </span>
                <span class="property-value" aria-labelledby="gender">
                    <c:out value="${procOutEmp.gender}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.doj!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="doj" text="Doj"/>: 
                </span>
                <span class="property-value" aria-labelledby="doj">
                    <fmt:formatDate value="${procOutEmp.doj}" type="date" pattern="dd/MM/yyyy"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.dob!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dob" text="Dob"/>: 
                </span>
                <span class="property-value" aria-labelledby="dob">
                    <fmt:formatDate value="${procOutEmp.dob}" type="date" pattern="dd/MM/yyyy"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.email!=null && !procOutEmp.email.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="email" text="Email"/>: 
                </span>
                <span class="property-value" aria-labelledby="email">
                    <c:out value="${procOutEmp.email}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.address!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="address" text="Address"/>: 
                </span>
                <span class="property-value" aria-labelledby="address">
                    <c:out value="${procOutEmp.address}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.department!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="department" text="Department"/>: 
                </span>
                <span class="property-value" aria-labelledby="department">
                    <c:out value="${procOutEmp.department}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.designation!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="designation" text="Designation"/>: 
                </span>
                <span class="property-value" aria-labelledby="designation">
                    <c:out value="${procOutEmp.designation}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.empGroup!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="empGroup" text="Emp Group"/>: 
                </span>
                <span class="property-value" aria-labelledby="empGroup">
                    <c:out value="${procOutEmp.empGroup}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.shiftOffDay!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="shiftOffDay" text="Shift Off Day"/>: 
                </span>
                <span class="property-value" aria-labelledby="shiftOffDay">
                    <c:out value="${procOutEmp.shiftOffDay}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.shift!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="shift" text="Shift"/>: 
                </span>
                <span class="property-value" aria-labelledby="shift">
                    <c:out value="${procOutEmp.shift}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.roster!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="roster" text="Roster"/>: 
                </span>
                <span class="property-value" aria-labelledby="roster">
                    <c:out value="${procOutEmp.roster}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutEmp.isOvertime!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="isOvertime" text="Is Overtime"/>: 
                </span>
                <span class="property-value" aria-labelledby="isOvertime">
                    <c:if test="${procOutEmp.isOvertime}"><spring:message code="default.boolean.true" text="YES"/></c:if><c:if test="${!procOutEmp.isOvertime}"><spring:message code="default.boolean.false" text="NO"/></c:if>
                </span>
            </li>
        </c:if>

        </ol>
    
    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class="box-footer">
                            <a href="${pageContext.request.contextPath}/procOutEmp/edit/${procOutEmp.id}" class="btn btn-primary"><i class="fa fa-edit"></i> <spring:message code="edit.link.label"/></a> 
                            <a href="${pageContext.request.contextPath}/procOutEmp/copy/${procOutEmp.id}" class="btn btn-warning"><i class="fa fa-clone"></i> <spring:message code="copy.link.label"/></a>             
                            <a href="${pageContext.request.contextPath}/procOutEmp/delete/${procOutEmp.id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete?');"><i class="fa fa-remove"></i> <spring:message code="delete.link.label"/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>