<%-- 
    Document   : login
    Created on : May 22, 2016, 1:46:14 PM
    Author     : hoshen.mahmud
--%>
<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ page isELIgnored="false" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://www.springframework.org/tags' prefix='spring'%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='sec'%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Reflection | Log in</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <jsp:include page="_css.jsp"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="hold-transition login-page">
        <div class="login-box">
            <div class="login-logo">
                <!--<a href="../../samplePages/home/index2.jsp"><b>Reflection</b></a>-->
                <a href="${pageContext.request.contextPath}/"><b>Reflection</b></a>
            </div>
            <!-- /.login-logo -->
            <div class="login-box-body">

                <c:url var="loginUrl" value="/login"/>
                <form action="${loginUrl}" method="post" class="form-horizontal">

                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p><spring:message code="default.message.invalidLogin.label" text="Invalid username or password."/></p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            <p><spring:message code="default.message.logoutSuccess.label" text="You have been logged out successfully."/></p>
                        </div>
                    </c:if>


                    <div class="input-group input-sm p-l-0 p-r-0">
                        <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
                    </div>
                    <div class="input-group input-sm p-l-0 p-r-0">
                        <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                    </div>

                    <div class="row p-t-15">
                        <fieldset>
                            <div class="col-xs-8">
                                <div class="checkbox icheck">
                                    <label>
                                        <input id="rememberMe" name="rememberMe" type="checkbox"> 
                                        <spring:message code="default.message.rememberMe.label" text="Remember Me"/>
                                    </label>
                                </div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <!-- /.col -->
                            <div class="col-xs-4" style="text-align: right">
                                <button type="submit" class="btn btn-primary"><spring:message code="default.message.logIn.label" text="Log In"/></button>
                            </div>
                            <!-- /.col -->
                        </fieldset>
                    </div>
                </form>
                <div class="text-center p-t-15">
                    <a class="btn btn-sm btn-dropbox" href="#"><spring:message code="default.message.forgotPassword.label" text="I forgot my password"/></a>
                    <a class="btn btn-sm btn-info text-center" href="#"><spring:message code="default.message.register.label" text="Register as a new member"/></a>
                </div>
            </div>
            <!-- /.login-box-body -->
        </div>
        <!-- /.login-box -->

        <jsp:include page="_js.jsp"/>

        <script>
            $(function () {
                $('input').iCheck({
                    checkboxClass: 'icheckbox_square-blue',
                    radioClass: 'iradio_square-blue',
                    increaseArea: '20%' // optional
                });
            });
        </script>
    </body>
</html>