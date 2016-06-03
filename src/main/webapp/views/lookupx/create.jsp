<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <title><spring:message code="default.create.label" text="Create" arguments="All Lookup"/></title>
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
                        <spring:message code="default.button.create.label" text="Create"/>
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
                        <sec:access url="/lookupx/index">
                            <li>
                                <a href="${pageContext.request.contextPath}/lookupx/index" class="btn btn-block btn-vimeo btn-xs">
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
                    <div id="create-lookupx" class="box box-primary" role="main">
                        <form:form action="${pageContext.request.contextPath}/lookupx/create" commandName="lookupx" method="POST">
                            <div class="box-body">
                                <jsp:include page="_form.jsp"/>
                            </div><!-- /.box-body -->
                            <div class="box-footer">
                                <fieldset class="buttons">
                                    <div class="btn-group">
                                        <button type="reset" class="btn btn-warning" onclick="return confirm('Are you sure, you want to reset?');">
                                            <i class="fa fa-refresh"></i>
                                            <spring:message code="default.button.reset.label" text="Reset"/>
                                        </button>
                                        <button type="submit" class="btn btn-primary">
                                            <i class="fa fa-save"></i>
                                            <spring:message code="default.button.create.label" text="Create"/>
                                        </button>
                                    </div> 
                                </fieldset>
                            </div><!-- /.box-footer -->
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