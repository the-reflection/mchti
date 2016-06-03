<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="default.edit.label" text="Edit" arguments="All Lookup"/></title>
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
                        <spring:message code="default.button.edit.label" text="Edit"/>
                        <!-- Optional description-->
                        <small>(Consist's in your project)</small>
                    </h1>
                    <ul class="top-links">
                        <sec:access url="/lookupx/create">
                            <li>
                                <a href="${pageContext.request.contextPath}/lookupx/create" class="btn btn-block btn-primary btn-xs">
                                    <i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="Create"/>
                                </a>
                            </li>
                        </sec:access>
                        <sec:access url="/lookupx/index">
                            <li>
                                <a href="${pageContext.request.contextPath}/lookupx/index" class="btn btn-block btn-primary btn-xs">
                                    <i class="fa fa-reorder"></i> <spring:message code="default.button.list.label" text="List"/>
                                </a>
                            </li>
                        </sec:access>
                    </ul>
                </section><!-- /.content-header -->

                <section class="content-messages">
                    <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
                </section><!-- /.flesh-message -->

                <section class="content"><!-- Main content -->
                    <div class="box box-primary">
                        <form:form action="${pageContext.request.contextPath}/lookupx/edit"  commandName="lookupx" method="POST">
                            <div class="box-body">
                                <jsp:include page="_form.jsp"/>
                            </div>
                            <div class="box-footer">
                                <div class="btn-group">
                                    <a href="${pageContext.request.contextPath}/lookupx/show/${lookupx.id}" class="btn btn-primary"><spring:message code="default.button.show.label" text="Show"/></a> 
                                    <input type="submit" class="btn btn-warning" value="<spring:message code="default.button.submit.label" text="Submit"/>"/>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </section><!-- /.content -->
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