<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="default.show.label" text="Show" arguments="All Lookup"/></title>
        <jsp:include page="../layouts/_css.jsp"/>
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <!-- Main Header -->
            <jsp:include page="../layouts/_header.jsp"/>

            <!-- Left side column. contains the logo and sidebar -->
            <jsp:include page="../layouts/_sideBarLeft.jsp"/>

            <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->
                <section class="content-header"><!-- Content Header (Page header) -->
                    <h1>
                        <!-- Page Header -->
                        <spring:message code="default.button.show.label" text="Show"/>
                        <!-- Optional description-->
                        <small>(Consist's in your project)</small>
                    </h1>
                    <!--      
                    <ol class="breadcrumb">
                        <li><a href="${pageContext.request.contextPath}"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/lookupx/index">
                                <i class="fa fa-reorder"></i> <spring:message code="default.button.list.label" text="List"/>
                            </a>
                        </li>
                    </ol>
                    -->
                    <ul class="top-links">
                        <sec:access url="/lookupx/create">
                            <li><a href="${pageContext.request.contextPath}/lookupx/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="Create"/></a></li>
                        </sec:access>
                        <sec:access url="/lookupx/index">
                            <li><a href="${pageContext.request.contextPath}/lookupx/index" class="btn btn-block btn-info btn-xs"><i class="fa fa-reorder"></i> <spring:message code="default.button.list.label" text="List"/></a></li>
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
                                <div class="form-gorup">
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                        <spring:message code="code" text="Code"/>
                                    </div>
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                        <c:out value="${lookupx.code}"/>
                                    </div>
                                </div>
                                <div class="form-gorup">
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                        <spring:message code="isActive" text="Is Active"/>
                                    </div>
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                        <c:if test="${lookupx.isActive}"><spring:message code="default.boolean.true" text="YES"/></c:if><c:if test="${!lookupx.isActive}"><spring:message code="default.boolean.false" text="NO"/></c:if>
                                    </div>
                                </div>
                                <div class="form-gorup">
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                        <spring:message code="slNo" text="Sl No"/>
                                    </div>
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                        <c:out value="${lookupx.slNo}"/>
                                    </div>
                                </div>
                                <div class="form-gorup">
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                        <spring:message code="title" text="Title"/>
                                    </div>
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                        <c:out value="${lookupx.title}"/>
                                    </div>
                                </div>
                                <div class="form-gorup">
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                        <spring:message code="titleBng" text="Title Bng"/>
                                    </div>
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                        <c:out value="${lookupx.titleBng}"/>
                                    </div>
                                </div>
                                <div class="form-gorup">
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                        <spring:message code="keyword" text="Keyword"/>
                                    </div>
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">
                                        <c:out value="${lookupx.keyword}"/>
                                    </div>
                                </div>
                                <div class="form-gorup">
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2 bold">
                                        <spring:message code="remarks" text="Remarks"/> 
                                    </div>
                                    <div class="col-sx-12 col-sm-6 col-md-3 col-lg-2">                              
                                        <c:out value="${lookupx.remarks}"/>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                        <div class="box-footer">
                            <div class="btn-group">
                                <a href="${pageContext.request.contextPath}/lookupx/edit/${lookupx.id}" class="btn btn-primary"><i class="fa fa-edit"></i> <spring:message code="edit.link.label"/></a> 
                                <a href="${pageContext.request.contextPath}/lookupx/copy/${lookupx.id}" class="btn btn-warning"><i class="fa fa-copy"></i> <spring:message code="copy.link.label"/></a>             
                                <a href="${pageContext.request.contextPath}/lookupx/delete/${lookupx.id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete?');"><i class="fa fa-remove"></i> <spring:message code="delete.link.label"/></a>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

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