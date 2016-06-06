<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title><spring:message code="project.title" text="OITH Framework"/></title>

        <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/resources/images/favicon.ico"/>

        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/plugins/bootstrap/bootstrap-3.3.6/css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/plugins/jquery/dataTables-1.10.10/media/css/jquery.dataTables.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/plugins/jquery/dataTables-1.10.10/media/css/dataTables.bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/plugins/font-awesome-4.6.3/css/font-awesome.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/plugins/reflection-1.0.0/reflection.css"/>
    </head>
    <body>
        <fieldset>
            <div id="header" style="padding-left: 15px; padding-right: 15px; padding-top: 5px">
                <div>
                    <a href="<%=request.getContextPath()%>">
                        <img alt="oith_logo_left" align="left" src="<%=request.getContextPath()%>/resources/images/oith_logo_left.png"/>
                    </a>
                </div>
                <div id="flags"  style="float: right">
                    <a href="?lang=en">
                        <img alt="en" title="English" src="<%=request.getContextPath()%>/resources/images/flags/united-kingdom_14x11.png"/>
                    </a>
                    <a href="?lang=bn_BD">
                        <img alt="bn" title="Bangladesh" src="<%=request.getContextPath()%>/resources/images/flags/bangladesh_14x11.png"/>
                    </a>
                    <a href="?lang=it">
                        <img alt="it" title="Italy" src="<%=request.getContextPath()%>/resources/images/flags/italy_14x11.png"/>
                    </a>
                    <a href="?lang=hi_IN">
                        <img alt="hi" title="India" src="<%=request.getContextPath()%>/resources/images/flags/india_14x11.png"/>
                    </a>
                </div>
            </div>    
        </fieldset>

        <script src="<%=request.getContextPath()%>/resources/plugins/bootstrap/bootstrap-3.3.6/js/bootstrap.js"></script>
        <script src="<%=request.getContextPath()%>/resources/plugins/jquery/jquery-1.11.3/jquery-1.11.3.js"></script>
        <script src="<%=request.getContextPath()%>/resources/plugins/jquery/dataTables-1.10.10/media/js/jquery.dataTables.js"></script>
        <script src="<%=request.getContextPath()%>/resources/plugins/iCheck/icheck.min.js"></script>
        <script src="<%=request.getContextPath()%>/resources/plugins/reflection-1.0.0/reflection.js"></script>

        <script type="text/javascript">
            var contextPath = "<%=request.getContextPath()%>";
            jQuery(document).ready(function () {
                alert('hello from reflection-1.0.0: ' + contextPath);
            });
        </script>

    </body>
    <footer class="footer">
        <jsp:include page="../layouts/footer.jsp"/>
    </footer>
</html>