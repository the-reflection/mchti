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
                <h1><spring:message code="default.button.show.label" text="Show"/> ProcOutAttnDt</h1>
                <ul class="top-links">
                    <sec:access url="/procOutAttnDt/create">
                        <li>
                            <a href="${pageContext.request.contextPath}/procOutAttnDt/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="New"/></a>
                        </li>
                    </sec:access>
                    <sec:access url="/procOutAttnDt/index">
                        <li>
                            <a href="${pageContext.request.contextPath}/procOutAttnDt/index" class="btn btn-block btn-info btn-xs"><i class="fa fa-reorder"></i> <spring:message code="default.button.list.label" text="List"/></a>
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
    
        <c:if test="${procOutAttnDt.procOutAttnDtPK!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="procOutAttnDtPK" text="Proc Out Attn Dt P K"/>: 
                </span>
                <span class="property-value" aria-labelledby="procOutAttnDtPK">
                    <c:out value="${procOutAttnDt.procOutAttnDtPK}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnDt.dtAttnType!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dtAttnType" text="Dt Attn Type"/>: 
                </span>
                <span class="property-value" aria-labelledby="dtAttnType">
                    <c:out value="${procOutAttnDt.dtAttnType}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnDt.shift!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="shift" text="Shift"/>: 
                </span>
                <span class="property-value" aria-labelledby="shift">
                    <c:out value="${procOutAttnDt.shift}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnDt.inTime!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="inTime" text="In Time"/>: 
                </span>
                <span class="property-value" aria-labelledby="inTime">
                    <fmt:formatDate value="${procOutAttnDt.inTime}" type="date" pattern="dd/MM/yyyy"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnDt.outTime!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="outTime" text="Out Time"/>: 
                </span>
                <span class="property-value" aria-labelledby="outTime">
                    <fmt:formatDate value="${procOutAttnDt.outTime}" type="date" pattern="dd/MM/yyyy"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnDt.ot!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="ot" text="Ot"/>: 
                </span>
                <span class="property-value" aria-labelledby="ot">
                    <c:out value="${procOutAttnDt.ot}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnDt.remarks!=null && !procOutAttnDt.remarks.isEmpty()}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="remarks" text="Remarks"/>: 
                </span>
                <span class="property-value" aria-labelledby="remarks">
                    <c:out value="${procOutAttnDt.remarks}"/>
                </span>
            </li>
        </c:if>

        </ol>
    
    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class="box-footer">
                            <a href="${pageContext.request.contextPath}/procOutAttnDt/edit/${procOutAttnDt.id}" class="btn btn-primary"><i class="fa fa-edit"></i> <spring:message code="edit.link.label"/></a> 
                            <a href="${pageContext.request.contextPath}/procOutAttnDt/copy/${procOutAttnDt.id}" class="btn btn-warning"><i class="fa fa-clone"></i> <spring:message code="copy.link.label"/></a>             
                            <a href="${pageContext.request.contextPath}/procOutAttnDt/delete/${procOutAttnDt.id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete?');"><i class="fa fa-remove"></i> <spring:message code="delete.link.label"/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>