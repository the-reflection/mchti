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
                <h1><spring:message code='default.button.show.label' text='Show'/> Assignment Prl</h1>
                <ul class='top-links'>
                    <sec:access url='/assignmentPrl/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/assignmentPrl/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/assignmentPrl/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/assignmentPrl/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
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

        <c:if test='${assignmentPrl.code!=null && !assignmentPrl.code.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='code' text='Code'/>: 
                </span>
                <span class='property-value' aria-labelledby='code'>
                    <c:out value='${assignmentPrl.code}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentPrl.employee!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='employee' text='Employee'/>: 
                </span>
                <span class='property-value' aria-labelledby='employee'>
                    <c:out value='${assignmentPrl.employee!=null ? assignmentPrl.employee :"N/A"}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentPrl.startDate!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='startDate' text='Start Date'/>: 
                </span>
                <span class='property-value' aria-labelledby='startDate'>
                    <fmt:formatDate value='${assignmentPrl.startDate}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentPrl.endDate!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='endDate' text='End Date'/>: 
                </span>
                <span class='property-value' aria-labelledby='endDate'>
                    <fmt:formatDate value='${assignmentPrl.endDate}' type='date' pattern='dd/MM/yyyy'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentPrl.basic!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='basic' text='Basic'/>: 
                </span>
                <span class='property-value' aria-labelledby='basic'>
                    <c:out value='${assignmentPrl.basic}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentPrl.houseRent!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='houseRent' text='House Rent'/>: 
                </span>
                <span class='property-value' aria-labelledby='houseRent'>
                    <c:out value='${assignmentPrl.houseRent}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentPrl.medical!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='medical' text='Medical'/>: 
                </span>
                <span class='property-value' aria-labelledby='medical'>
                    <c:out value='${assignmentPrl.medical}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentPrl.convance!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='convance' text='Convance'/>: 
                </span>
                <span class='property-value' aria-labelledby='convance'>
                    <c:out value='${assignmentPrl.convance}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentPrl.otherAllowance!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='otherAllowance' text='Other Allowance'/>: 
                </span>
                <span class='property-value' aria-labelledby='otherAllowance'>
                    <c:out value='${assignmentPrl.otherAllowance}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${assignmentPrl.gross!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='gross' text='Gross'/>: 
                </span>
                <span class='property-value' aria-labelledby='gross'>
                    <c:out value='${assignmentPrl.gross}'/>
                </span>
            </li>
        </c:if>

        </ol>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/assignmentPrl/edit/${assignmentPrl.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/assignmentPrl/copy/${assignmentPrl.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/assignmentPrl/delete/${assignmentPrl.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>