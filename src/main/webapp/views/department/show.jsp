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
                <h1><spring:message code="default.button.show.label" text="Show"/> Department</h1>
                <ul class="top-links">
                    <sec:access url="/department/create">
                        <li>
                            <a href="${pageContext.request.contextPath}/department/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="New"/></a>
                        </li>
                    </sec:access>
                    <sec:access url="/department/index">
                        <li>
                            <a href="${pageContext.request.contextPath}/department/index" class="btn btn-block btn-info btn-xs"><i class="fa fa-reorder"></i> <spring:message code="default.button.list.label" text="List"/></a>
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
    
        <c:if test="${department.version!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="version" text="Version"/>: 
                </span>
                <span class="property-value" aria-labelledby="version">
                    <c:out value="${department.version}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${department.code!=null && !department.code.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="code" text="Code"/>: 
                </span>
                <span class="property-value" aria-labelledby="code">
                    <c:out value="${department.code}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${department.title!=null && !department.title.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="title" text="Title"/>: 
                </span>
                <span class="property-value" aria-labelledby="title">
                    <c:out value="${department.title}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${department.titleBng!=null && !department.titleBng.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="titleBng" text="Title Bng"/>: 
                </span>
                <span class="property-value" aria-labelledby="titleBng">
                    <c:out value="${department.titleBng}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${department.remarks!=null && !department.remarks.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="remarks" text="Remarks"/>: 
                </span>
                <span class="property-value" aria-labelledby="remarks">
                    <c:out value="${department.remarks}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${department.isActive!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="isActive" text="Is Active"/>: 
                </span>
                <span class="property-value" aria-labelledby="isActive">
                    <c:if test="${department.isActive}"><spring:message code="default.boolean.true" text="YES"/></c:if><c:if test="${!department.isActive}"><spring:message code="default.boolean.false" text="NO"/></c:if>
                </span>
            </li>
        </c:if>

        <c:if test="${department.slNo!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="slNo" text="Sl No"/>: 
                </span>
                <span class="property-value" aria-labelledby="slNo">
                    <c:out value="${department.slNo}"/>
                </span>
            </li>
        </c:if>

        </ol>
    
    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class="box-footer">
                            <a href="${pageContext.request.contextPath}/department/edit/${department.id}" class="btn btn-primary"><i class="fa fa-edit"></i> <spring:message code="edit.link.label"/></a> 
                            <a href="${pageContext.request.contextPath}/department/copy/${department.id}" class="btn btn-warning"><i class="fa fa-clone"></i> <spring:message code="default.button.copy.label"/></a>             
                            <a href="${pageContext.request.contextPath}/department/delete/${department.id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete?');"><i class="fa fa-remove"></i> <spring:message code="delete.link.label"/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>