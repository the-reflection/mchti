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
                <h1><spring:message code='default.button.show.label' text='Show'/> Assignment Hr</h1>
                <ul class='top-links'>
                    <sec:access url='/assignmentHr/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/assignmentHr/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/assignmentHr/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/assignmentHr/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
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

        <c:if test='${assignmentHr.code!=null && !assignmentHr.code.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='code' text='Code'/>: 
                </span>
                <span class='property-value' aria-labelledby='code'>
                    <c:out value='${assignmentHr.code}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentHr.employee!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='employee' text='Employee'/>: 
                </span>
                <span class='property-value' aria-labelledby='employee'>
                    <c:out value='${assignmentHr.employee!=null ? assignmentHr.employee :"N/A"}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentHr.startDate!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='startDate' text='Start Date'/>: 
                </span>
                <span class='property-value' aria-labelledby='startDate'>
                    <fmt:formatDate value='${assignmentHr.startDate}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentHr.endDate!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='endDate' text='End Date'/>: 
                </span>
                <span class='property-value' aria-labelledby='endDate'>
                    <fmt:formatDate value='${assignmentHr.endDate}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentHr.department!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='department' text='Department'/>: 
                </span>
                <span class='property-value' aria-labelledby='department'>
                    <c:out value='${assignmentHr.department}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentHr.designation!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='designation' text='Designation'/>: 
                </span>
                <span class='property-value' aria-labelledby='designation'>
                    <c:out value='${assignmentHr.designation}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentHr.empGroup!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='empGroup' text='Emp Group'/>: 
                </span>
                <span class='property-value' aria-labelledby='empGroup'>
                    <c:out value='${assignmentHr.empGroup}'/>
                </span>
            </li>
        </c:if>

        </ol>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/assignmentHr/edit/${assignmentHr.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/assignmentHr/copy/${assignmentHr.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/assignmentHr/delete/${assignmentHr.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>