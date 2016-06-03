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
                <h1><spring:message code="default.button.show.label" text="Show"/> Lookup</h1>
                <ul class="top-links">
                    <sec:access url="/zxLookup/create">
                        <li>
                            <a href="${pageContext.request.contextPath}/zxLookup/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="New"/></a>
                        </li>
                    </sec:access>
                    <sec:access url="/zxLookup/index">
                        <li>
                            <a href="${pageContext.request.contextPath}/zxLookup/index" class="btn btn-block btn-info btn-xs"><i class="fa fa-reorder"></i> <spring:message code="default.button.list.label" text="List"/></a>
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
                            <div class="form-gorup">
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                    <spring:message code="code" text="Code"/>
                                </div>
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                    <c:out value="${zxLookup.code}"/>&nbsp;
                                </div>
                            </div>
                            <div class="form-gorup">
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                    <spring:message code="isActive" text="Is Active"/>
                                </div>
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                    <c:if test="${zxLookup.isActive}"><spring:message code="default.boolean.true" text="YES"/></c:if><c:if test="${!zxLookup.isActive}"><spring:message code="default.boolean.false" text="NO"/></c:if>&nbsp;
                                    </div>
                                </div>
                                <div class="form-gorup">
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                    <spring:message code="slNo" text="Sl No"/>
                                </div>
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                    <c:out value="${zxLookup.slNo}"/>&nbsp;
                                </div>
                            </div>
                            <div class="form-gorup">
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                    <spring:message code="title" text="Title"/>
                                </div>
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                    <c:out value="${zxLookup.title}"/>&nbsp;
                                </div>
                            </div>
                            <div class="form-gorup">
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                    <spring:message code="titleBng" text="Title Bng"/>
                                </div>
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                    <c:out value="${zxLookup.titleBng}"/>&nbsp;
                                </div>
                            </div>

                            <div class="form-gorup">
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                    <spring:message code="zxLookupKeyword" text="Keyword"/>
                                </div>
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                    <c:out value="${zxLookup.zxLookupKeyword}"/>&nbsp;
                                </div>
                            </div>

                            <div class="form-gorup">
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                    <spring:message code="remarks" text="Remarks"/> 
                                </div>
                                <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">                              
                                    <c:out value="${zxLookup.remarks}"/>&nbsp;
                                </div>
                            </div>
                        </fieldset>     <!--.show-page-->
                    </div>      <!--.box-body-->

                    <div class="box-footer">
                        <a href="${pageContext.request.contextPath}/zxLookup/edit/${zxLookup.id}" class="btn btn-primary"><i class="fa fa-edit"></i> <spring:message code="edit.link.label"/></a> 
                        <a href="${pageContext.request.contextPath}/zxLookup/copy/${zxLookup.id}" class="btn btn-warning"><i class="fa fa-clone"></i> <spring:message code="copy.link.label"/></a>             
                        <a href="${pageContext.request.contextPath}/zxLookup/delete/${zxLookup.id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete?');"><i class="fa fa-remove"></i> <spring:message code="delete.link.label"/></a>
                    </div>      <!--.box-footer-->
                </div>      <!--.box .box-primary-->
            </section>      <!--.content-->
        </div>      <!--.content-wrapper-->
    </tiles:putAttribute>
</tiles:insertDefinition>