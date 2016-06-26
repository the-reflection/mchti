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
                <h1><spring:message code='default.button.show.label' text='Show'/> Supplier Company</h1>
                <ul class='top-links'>
                    <sec:access url='/supplierCompany/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/supplierCompany/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/supplierCompany/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/supplierCompany/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
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

        <c:if test='${supplierCompany.code!=null && !supplierCompany.code.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='code' text='Code'/>: 
                </span>
                <span class='property-value' aria-labelledby='code'>
                    <c:out value='${supplierCompany.code}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${supplierCompany.picFile!=null && !supplierCompany.picFile.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='picFile' text='Pic File'/>: 
                </span>
                <span class='property-value' aria-labelledby='picFile'>
                    <c:url var='picFile' value='/supplierCompany/getPhoto/${supplierCompany.picFile}'/>
<img height="110px" width="90px" alt='${supplierCompany.picFile}' src='${picFile}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${supplierCompany.fullName!=null && !supplierCompany.fullName.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='fullName' text='Full Name'/>: 
                </span>
                <span class='property-value' aria-labelledby='fullName'>
                    <c:out value='${supplierCompany.fullName}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${supplierCompany.orientationDate!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='orientationDate' text='Orientation Date'/>: 
                </span>
                <span class='property-value' aria-labelledby='orientationDate'>
                    <fmt:formatDate value='${supplierCompany.orientationDate}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${supplierCompany.email!=null && !supplierCompany.email.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='email' text='Email'/>: 
                </span>
                <span class='property-value' aria-labelledby='email'>
                    <c:out value='${supplierCompany.email}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${supplierCompany.address!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='address' text='Address'/>: 
                </span>
                <span class='property-value' aria-labelledby='address'>
                    <c:out value='${supplierCompany.address}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${supplierCompany.companyType!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='companyType' text='Company Type'/>: 
                </span>
                <span class='property-value' aria-labelledby='companyType'>
                    <c:out value='${supplierCompany.companyType}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${supplierCompany.web!=null && !supplierCompany.web.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='web' text='Web'/>: 
                </span>
                <span class='property-value' aria-labelledby='web'>
                    <c:out value='${supplierCompany.web}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${supplierCompany.regNo!=null && !supplierCompany.regNo.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='regNo' text='Reg No'/>: 
                </span>
                <span class='property-value' aria-labelledby='regNo'>
                    <c:out value='${supplierCompany.regNo}'/>
                </span>
            </li>
        </c:if>

        </ol>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/supplierCompany/edit/${supplierCompany.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/supplierCompany/copy/${supplierCompany.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/supplierCompany/delete/${supplierCompany.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>