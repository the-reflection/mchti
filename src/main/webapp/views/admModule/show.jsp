<%@ page language='java' contentType='text/html;charset=utf-8' pageEncoding='UTF-8'%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://www.springframework.org/tags' prefix='spring'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@ taglib uri='http://www.springframework.org/tags/form' prefix='form'%>

<section class='content-header'><!-- Content Header (Page header) -->
    <h1><spring:message code='default.button.show.label' text='Show'/> Adm Module</h1>
    <ul class='top-links'>
        <sec:access url='/admModule/create'>
            <li>
                <a href='${pageContext.request.contextPath}/admModule/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
            </li>
        </sec:access>
        <sec:access url='/admModule/index'>
            <li>
                <a href='${pageContext.request.contextPath}/admModule/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
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

                    <c:if test='${admModule.code!=null && !admModule.code.isEmpty()}'>
                        <li class='fieldcontain first_item'>
                            <span id='title' class='property-label'>
                                <spring:message code='code' text='Code'/>: 
                            </span>
                            <span class='property-value' aria-labelledby='code'>
                                <c:out value='${admModule.code}'/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test='${admModule.fullName!=null && !admModule.fullName.isEmpty()}'>
                        <li class='fieldcontain first_item'>
                            <span id='title' class='property-label'>
                                <spring:message code='fullName' text='Full Name'/>: 
                            </span>
                            <span class='property-value' aria-labelledby='fullName'>
                                <c:out value='${admModule.fullName}'/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test='${admModule.isActive!=null}'>
                        <li class='fieldcontain first_item'>
                            <span id='title' class='property-label'>
                                <spring:message code='isActive' text='Is Active'/>: 
                            </span>
                            <span class='property-value' aria-labelledby='isActive'>
                                <c:if test='${admModule.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admModule.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                                </span>
                            </li>
                    </c:if>

                    <c:if test='${admModule.slNo!=null}'>
                        <li class='fieldcontain first_item'>
                            <span id='title' class='property-label'>
                                <spring:message code='slNo' text='Sl No'/>: 
                            </span>
                            <span class='property-value' aria-labelledby='slNo'>
                                <c:out value='${admModule.slNo}'/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test='${admModule.remarks!=null && !admModule.remarks.isEmpty()}'>
                        <li class='fieldcontain first_item'>
                            <span id='title' class='property-label'>
                                <spring:message code='remarks' text='Remarks'/>: 
                            </span>
                            <span class='property-value' aria-labelledby='remarks'>
                                <c:out value='${admModule.remarks}'/>
                            </span>
                        </li>
                    </c:if>

                </ol>
                <div><jsp:include page='admProcesss.jsp' /></div>
                <div><jsp:include page='admReports.jsp' /></div>


            </fieldset>     <!--.show-page-->
        </div>      <!--.box-body-->

        <div class='box-footer'>
            <a href='${pageContext.request.contextPath}/admModule/edit/${admModule.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
            <a href='${pageContext.request.contextPath}/admModule/copy/${admModule.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
            <a href='${pageContext.request.contextPath}/admModule/delete/${admModule.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
        </div>      <!--.box-footer-->
    </div>      <!--.box .box-primary-->
</section>      <!--.content-->
