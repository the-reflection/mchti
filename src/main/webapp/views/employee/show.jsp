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
                <h1><spring:message code='default.button.show.label' text='Show'/> Employee</h1>
                <ul class='top-links'>
                    <sec:access url='/employee/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/employee/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/employee/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/employee/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
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

        <c:if test='${employee.code!=null && !employee.code.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='code' text='Code'/>: 
                </span>
                <span class='property-value' aria-labelledby='code'>
                    <c:out value='${employee.code}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${employee.picFile!=null && !employee.picFile.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='picFile' text='Pic File'/>: 
                </span>
                <span class='property-value' aria-labelledby='picFile'>
                    <c:url var='picFile' value='/employee/getPhoto/${employee.picFile}'/>
<img height="110px" width="90px" alt='${employee.picFile}' src='${picFile}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${employee.fullName!=null && !employee.fullName.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='fullName' text='Full Name'/>: 
                </span>
                <span class='property-value' aria-labelledby='fullName'>
                    <c:out value='${employee.fullName}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${employee.gender!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='gender' text='Gender'/>: 
                </span>
                <span class='property-value' aria-labelledby='gender'>
                    <c:out value='${employee.gender}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${employee.dob!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='dob' text='Dob'/>: 
                </span>
                <span class='property-value' aria-labelledby='dob'>
                    <fmt:formatDate value='${employee.dob}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${employee.email!=null && !employee.email.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='email' text='Email'/>: 
                </span>
                <span class='property-value' aria-labelledby='email'>
                    <c:out value='${employee.email}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${employee.address!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='address' text='Address'/>: 
                </span>
                <span class='property-value' aria-labelledby='address'>
                    <c:out value='${employee.address}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${employee.doj!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='doj' text='Doj'/>: 
                </span>
                <span class='property-value' aria-labelledby='doj'>
                    <fmt:formatDate value='${employee.doj}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        </ol>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/employee/edit/${employee.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/employee/copy/${employee.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/employee/delete/${employee.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>