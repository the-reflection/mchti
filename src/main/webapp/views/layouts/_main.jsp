<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>JSP Page</title>-->
        <!--<meta charset="utf-8">-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Reflection | Home</title>
        <!-- favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/resources/images/favicon.ico"/>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    </head>
    <body class="hold-transition skin-blue sidebar-mini sidebar-collapse fixed">
        <div class="wrapper">

            <div class="styleSheet">
                <tiles:insertAttribute name="styleSheet"/>
            </div>

            <div class="header">
                <tiles:insertAttribute name="header"/>
            </div>

            <div class="sideBarLeft">
                <tiles:insertAttribute name="sideBarLeft"/>
            </div>
            
            <!-- Content Wrapper. Contains page content -->
            <div class="body">
                <tiles:insertAttribute name="body"/>
                <div id="spinner" class="spinner" style="display:none;"><g:message code="default.message.loading" default="Loading&hellip;"/></div>
            </div>
                
            <div class="sideBarRight">
                <tiles:insertAttribute name="sideBarRight"/>
            </div>
            
            <div class="control-sidebar-bg"></div>
        </div>
        <!-- ./wrapper -->

        <div class="script">
            <tiles:insertAttribute name="script"/>
        </div>
        <div class="footer">
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>
