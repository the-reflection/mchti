<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="default.list.label" text="List" arguments="All Lookup"/></title>
        <jsp:include page="../layouts/_css.jsp"/>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <!-- Main Header -->
            <jsp:include page="../layouts/_header.jsp"/>

            <!-- Left side column. contains the logo and sidebar -->
            <jsp:include page="../layouts/_sideBarLeft.jsp"/>

            <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        <!-- Page Header -->
                        <spring:message code="default.button.list.label" text="List"/>
                        <!-- Optional description-->
                        <small>(Consist's in your project)</small>
                    </h1>
                    <!--      
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/lookupx/create">
                                <i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="Create"/>
                            </a>
                        </li>
                    </ol>
                    -->
                    <ul class="top-links">
                        <sec:access url="/lookupx/create">
                            <li>
                                <a href="${pageContext.request.contextPath}/lookupx/create" class="btn btn-block btn-primary btn-xs">
                                    <i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="Create"/>
                                </a>
                            </li>
                        </sec:access>
                    </ul>
                </section>

                <section class="content-messages">
                    <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
                </section><!-- /.flesh-message -->

                <section class="content"><!-- Main content -->
                    <div class="box box-primary">
                        <div class="box-body">
                            <table class="dt-default table table-bordered table-striped table-hover table-condensed th-center">
                                <thead>
                                    <tr>
                                        <th><spring:message code="code" text="Code"/></th>
                                        <th><spring:message code="isActive" text="Is Active"/></th>
                                        <th><spring:message code="slNo" text="Sl No"/></th>
                                        <th><spring:message code="title" text="Title"/></th>
                                        <th><spring:message code="titleBng" text="Title Bng"/></th>
                                        <th><spring:message code="remarks" text="Remarks"/></th> 
                                        <th><spring:message code="keyword" text="Keyword"/></th>                  
                                        <th><spring:message code="default.button.action.label" text="Action"/></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:choose>
                                        <c:when test="${lookupxs.size()!=0}">
                                            <c:forEach items="${lookupxs}" var="lookupx"  varStatus="loopStatus">
                                                <tr>
                                                    <td><c:out value="${lookupx.code}"/></td>
                                                    <td><c:if test="${lookupx.isActive}"><spring:message code="default.boolean.true" text="YES"/></c:if><c:if test="${!lookupx.isActive}"><spring:message code="default.boolean.false" text="NO"/></c:if></td>
                                                    <td><c:out value="${lookupx.slNo}"/></td>
                                                    <td><c:out value="${lookupx.title}"/></td>
                                                    <td><c:out value="${lookupx.titleBng}"/></td>
                                                    <td><c:out value="${lookupx.remarks}"/></td>
                                                    <td><c:out value="${lookupx.keyword}"/></td>
                                                    <td class="center nowrap">
                                                        <sec:access url="/lookupx/show">
                                                            <a class="btn btn-info btn-xs" href="${pageContext.request.contextPath}/lookupx/show/${lookupx.id}">
                                                                <i class="fa fa-envelope"></i> <spring:message code="show.link.label"/>
                                                            </a>
                                                        </sec:access>
                                                        <sec:access url="/lookupx/edit">
                                                            <a class="btn btn-primary btn-xs" href="${pageContext.request.contextPath}/lookupx/edit/${lookupx.id}">
                                                                <i class="fa fa-edit"></i> <spring:message code="edit.link.label"/>
                                                            </a>
                                                        </sec:access>
                                                        <sec:access url="/lookupx/copy">
                                                            <a class="btn btn-warning btn-xs" href="${pageContext.request.contextPath}/lookupx/copy/${lookupx.id}">
                                                                <i class="fa fa-copy"></i> <spring:message code="copy.link.label"/>
                                                            </a>
                                                        </sec:access>
                                                        <sec:access url="/lookupx/delete">
                                                            <a class="btn btn-danger btn-xs" href="${pageContext.request.contextPath}/lookupx/delete/${lookupx.id}" onclick="return confirm('Are you sure to delete?');">
                                                                <i class="fa fa-remove"></i> <spring:message code="delete.link.label"/>
                                                            </a>
                                                        </sec:access>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td colspan="9" class="bold center">
                                                    <spring:message code="default.noRecordFound.label" text="No Record Found...!"/>
                                                </td>
                                            </tr>
                                        </c:otherwise> 
                                    </c:choose>
                                </tbody>
                            </table>
                            <!--<div class="pagination">-->
                                <!--<g:paginate total="{testInstanceCount ?: 0}"/>-->
                            <!--</div>-->
                        </div><!-- /.box-body table-responsive no-padding -->
                    </div><!-- /.box box-primary -->
                </section><!-- /.content -->
            </div><!-- /.content-wrapper -->

            <!-- Main Footer-->
            <jsp:include page="../layouts/_footer.jsp"/>

            <!-- Control Sidebar -->
            <jsp:include page="../layouts/_sideBarRight.jsp"/>

            <!-- 
            Add the sidebar's background. This div must be placed
            immediately after the control sidebar 
            -->
            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->
        <jsp:include page="../layouts/_js.jsp"/>
    </body>
</html>