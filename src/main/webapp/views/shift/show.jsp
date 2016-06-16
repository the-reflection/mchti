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
                <h1><spring:message code="default.button.show.label" text="Show"/> Shift</h1>
                <ul class="top-links">
                    <sec:access url="/shift/create">
                        <li>
                            <a href="${pageContext.request.contextPath}/shift/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="New"/></a>
                        </li>
                    </sec:access>
                    <sec:access url="/shift/index">
                        <li>
                            <a href="${pageContext.request.contextPath}/shift/index" class="btn btn-block btn-info btn-xs"><i class="fa fa-reorder"></i> <spring:message code="default.button.list.label" text="List"/></a>
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
    
        <c:if test="${shift.version!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="version" text="Version"/>: 
                </span>
                <span class="property-value" aria-labelledby="version">
                    <c:out value="${shift.version}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.code!=null && !shift.code.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="code" text="Code"/>: 
                </span>
                <span class="property-value" aria-labelledby="code">
                    <c:out value="${shift.code}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.title!=null && !shift.title.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="title" text="Title"/>: 
                </span>
                <span class="property-value" aria-labelledby="title">
                    <c:out value="${shift.title}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.titleBng!=null && !shift.titleBng.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="titleBng" text="Title Bng"/>: 
                </span>
                <span class="property-value" aria-labelledby="titleBng">
                    <c:out value="${shift.titleBng}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.startHr!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="startHr" text="Start Hr"/>: 
                </span>
                <span class="property-value" aria-labelledby="startHr">
                    <c:out value="${shift.startHr}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.endHr!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="endHr" text="End Hr"/>: 
                </span>
                <span class="property-value" aria-labelledby="endHr">
                    <c:out value="${shift.endHr}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.startMin!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="startMin" text="Start Min"/>: 
                </span>
                <span class="property-value" aria-labelledby="startMin">
                    <c:out value="${shift.startMin}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.endMin!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="endMin" text="End Min"/>: 
                </span>
                <span class="property-value" aria-labelledby="endMin">
                    <c:out value="${shift.endMin}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.startBufMin!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="startBufMin" text="Start Buf Min"/>: 
                </span>
                <span class="property-value" aria-labelledby="startBufMin">
                    <c:out value="${shift.startBufMin}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.endBufMin!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="endBufMin" text="End Buf Min"/>: 
                </span>
                <span class="property-value" aria-labelledby="endBufMin">
                    <c:out value="${shift.endBufMin}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.orientationMin!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="orientationMin" text="Orientation Min"/>: 
                </span>
                <span class="property-value" aria-labelledby="orientationMin">
                    <c:out value="${shift.orientationMin}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.shiftType!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="shiftType" text="Shift Type"/>: 
                </span>
                <span class="property-value" aria-labelledby="shiftType">
                    <c:out value="${shift.shiftType}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.remarks!=null && !shift.remarks.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="remarks" text="Remarks"/>: 
                </span>
                <span class="property-value" aria-labelledby="remarks">
                    <c:out value="${shift.remarks}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.isActive!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="isActive" text="Is Active"/>: 
                </span>
                <span class="property-value" aria-labelledby="isActive">
                    <c:if test="${shift.isActive}"><spring:message code="default.boolean.true" text="YES"/></c:if><c:if test="${!shift.isActive}"><spring:message code="default.boolean.false" text="NO"/></c:if>
                </span>
            </li>
        </c:if>

        <c:if test="${shift.slNo!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="slNo" text="Sl No"/>: 
                </span>
                <span class="property-value" aria-labelledby="slNo">
                    <c:out value="${shift.slNo}"/>
                </span>
            </li>
        </c:if>

        </ol>
    
    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class="box-footer">
                            <a href="${pageContext.request.contextPath}/shift/edit/${shift.id}" class="btn btn-primary"><i class="fa fa-edit"></i> <spring:message code="edit.link.label"/></a> 
                            <a href="${pageContext.request.contextPath}/shift/copy/${shift.id}" class="btn btn-warning"><i class="fa fa-clone"></i> <spring:message code="default.button.copy.label"/></a>             
                            <a href="${pageContext.request.contextPath}/shift/delete/${shift.id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete?');"><i class="fa fa-remove"></i> <spring:message code="delete.link.label"/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>