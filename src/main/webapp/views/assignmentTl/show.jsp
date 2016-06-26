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
                <h1><spring:message code='default.button.show.label' text='Show'/> Assignment Tl</h1>
                <ul class='top-links'>
                    <sec:access url='/assignmentTl/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/assignmentTl/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/assignmentTl/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/assignmentTl/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
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

        <c:if test='${assignmentTl.code!=null && !assignmentTl.code.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='code' text='Code'/>: 
                </span>
                <span class='property-value' aria-labelledby='code'>
                    <c:out value='${assignmentTl.code}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentTl.employee!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='employee' text='Employee'/>: 
                </span>
                <span class='property-value' aria-labelledby='employee'>
                    <c:out value='${assignmentTl.employee!=null ? assignmentTl.employee :"N/A"}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentTl.startDate!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='startDate' text='Start Date'/>: 
                </span>
                <span class='property-value' aria-labelledby='startDate'>
                    <fmt:formatDate value='${assignmentTl.startDate}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentTl.endDate!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='endDate' text='End Date'/>: 
                </span>
                <span class='property-value' aria-labelledby='endDate'>
                    <fmt:formatDate value='${assignmentTl.endDate}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentTl.weekendShiftOffDay!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='weekendShiftOffDay' text='Weekend Shift Off Day'/>: 
                </span>
                <span class='property-value' aria-labelledby='weekendShiftOffDay'>
                    <c:out value='${assignmentTl.weekendShiftOffDay}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentTl.shift!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='shift' text='Shift'/>: 
                </span>
                <span class='property-value' aria-labelledby='shift'>
                    <c:out value='${assignmentTl.shift}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentTl.roster!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='roster' text='Roster'/>: 
                </span>
                <span class='property-value' aria-labelledby='roster'>
                    <c:out value='${assignmentTl.roster}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentTl.isOvertime!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='isOvertime' text='Is Overtime'/>: 
                </span>
                <span class='property-value' aria-labelledby='isOvertime'>
                    <c:if test='${assignmentTl.isOvertime}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!assignmentTl.isOvertime}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                </span>
            </li>
        </c:if>

        </ol>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/assignmentTl/edit/${assignmentTl.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/assignmentTl/copy/${assignmentTl.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/assignmentTl/delete/${assignmentTl.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>