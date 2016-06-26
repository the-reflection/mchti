<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>

<%@ taglib uri='http://tiles.apache.org/tags-tiles' prefix='tiles'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://www.springframework.org/tags' prefix='spring'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@ taglib uri='http://www.springframework.org/tags/form' prefix='form'%>

<tiles:insertDefinition name='main' >

    <tiles:putAttribute name='body'>
        
        <div class='content-wrapper'><!-- Content Wrapper. Contains page content -->
            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code='default.button.show.label' text='Show'/> Auth User</h1>
                <ul class='top-links'>
                    <sec:access url='/authUser/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/authUser/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/authUser/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/authUser/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
                        </li>
                    </sec:access>
                </ul>
            </section><!-- /.content-header -->

            <section class='content-messages'>
                <%--<jsp:include page='../layouts/_flashMessage.jsp'/>--%>
            </section><!-- /.flesh-message -->

            <section class='content'><!-- Main content -->
                <div class='box box-primary'>
                    <div class='box-body'>
                        <fieldset class='show-page'>
                             <form:hidden path='id'/>
        <ol class='property-list'>

        <c:if test='${authUser.username!=null && !authUser.username.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='username' text='Username'/>: 
                </span>
                <span class='property-value' aria-labelledby='username'>
                    <c:out value='${authUser.username}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.password!=null && !authUser.password.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='password' text='Password'/>: 
                </span>
                <span class='property-value' aria-labelledby='password'>
                    <c:out value='${authUser.password}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.displayName!=null && !authUser.displayName.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='displayName' text='Display Name'/>: 
                </span>
                <span class='property-value' aria-labelledby='displayName'>
                    <c:out value='${authUser.displayName}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.fullName!=null && !authUser.fullName.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='fullName' text='Full Name'/>: 
                </span>
                <span class='property-value' aria-labelledby='fullName'>
                    <c:out value='${authUser.fullName}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.gender!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='gender' text='Gender'/>: 
                </span>
                <span class='property-value' aria-labelledby='gender'>
                    <c:out value='${authUser.gender}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.dob!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='dob' text='Dob'/>: 
                </span>
                <span class='property-value' aria-labelledby='dob'>
                    <fmt:formatDate value='${authUser.dob}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.doj!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='doj' text='Doj'/>: 
                </span>
                <span class='property-value' aria-labelledby='doj'>
                    <fmt:formatDate value='${authUser.doj}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.email!=null && !authUser.email.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='email' text='Email'/>: 
                </span>
                <span class='property-value' aria-labelledby='email'>
                    <c:out value='${authUser.email}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.picFile!=null && !authUser.picFile.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='picFile' text='Pic File'/>: 
                </span>
                <span class='property-value' aria-labelledby='picFile'>
                    <c:url var='picFile' value='/authUser/getPhoto/${authUser.picFile}'/>
<img height="110px" width="90px" alt='${authUser.picFile}' src='${picFile}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.authRoles!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='authRoles' text='Auth Roles'/>: 
                </span>
                <span class='property-value' aria-labelledby='authRoles'>
                    <c:out value='${authUser.authRoles}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.enabled!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='enabled' text='Enabled'/>: 
                </span>
                <span class='property-value' aria-labelledby='enabled'>
                    <c:if test='${authUser.enabled}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.enabled}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.accountNonExpired!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='accountNonExpired' text='Account Non Expired'/>: 
                </span>
                <span class='property-value' aria-labelledby='accountNonExpired'>
                    <c:if test='${authUser.accountNonExpired}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.accountNonExpired}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.accountNonLocked!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='accountNonLocked' text='Account Non Locked'/>: 
                </span>
                <span class='property-value' aria-labelledby='accountNonLocked'>
                    <c:if test='${authUser.accountNonLocked}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.accountNonLocked}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                </span>
            </li>
        </c:if>

        <c:if test='${authUser.credentialsNonExpired!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='credentialsNonExpired' text='Credentials Non Expired'/>: 
                </span>
                <span class='property-value' aria-labelledby='credentialsNonExpired'>
                    <c:if test='${authUser.credentialsNonExpired}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authUser.credentialsNonExpired}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                </span>
            </li>
        </c:if>

        </ol>
    <div><jsp:include page='authUserAuthQuestions.jsp' /></div>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/authUser/edit/${authUser.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/authUser/copy/${authUser.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/authUser/delete/${authUser.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>