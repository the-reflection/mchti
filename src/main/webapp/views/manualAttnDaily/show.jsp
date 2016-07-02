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
                <h1><spring:message code='default.button.show.label' text='Show'/> Manual Attn Daily</h1>
                <ul class='top-links'>
                    <sec:access url='/manualAttnDaily/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/manualAttnDaily/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/manualAttnDaily/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/manualAttnDaily/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
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



                                <c:if test='${manualAttnDaily.employee!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='employee' text='Employee'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='employee'>
                                            <c:out value='${manualAttnDaily.employee!=null ? manualAttnDaily.employee :"N/A"}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${manualAttnDaily.attnDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='attnDate' text='Attn Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='attnDate'>
                                            <fmt:formatDate value='${manualAttnDaily.attnDate}' type='date' pattern='dd/MM/yyyy'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${manualAttnDaily.inTime!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='inTime' text='In Time'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='inTime'>
                                            <c:out value='${manualAttnDaily.inTime}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${manualAttnDaily.outTime!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='outTime' text='Out Time'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='outTime'>
                                            <c:out value='${manualAttnDaily.outTime}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${manualAttnDaily.remarks!=null && !manualAttnDaily.remarks.isEmpty()}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='remarks' text='Remarks'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='remarks'>
                                            <c:out value='${manualAttnDaily.remarks}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${manualAttnDaily.entryBy!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='entryBy' text='Entry By'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='entryBy'>
                                            <c:out value='${manualAttnDaily.entryBy}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${manualAttnDaily.entryDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='entryDate' text='Entry Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='entryDate'>
                                            <fmt:formatDate value='${manualAttnDaily.entryDate}' type='date' pattern='dd/MM/yyyy HH:mm:ss'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${manualAttnDaily.editBy!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='editBy' text='Edit By'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='editBy'>
                                            <c:out value='${manualAttnDaily.editBy}'/>
                                        </span>
                                    </li>
                                </c:if>

                                <c:if test='${manualAttnDaily.editDate!=null}'>
                                    <li class='fieldcontain first_item'>
                                        <span id='title' class='property-label'>
                                            <spring:message code='editDate' text='Edit Date'/>: 
                                        </span>
                                        <span class='property-value' aria-labelledby='editDate'>
                                            <fmt:formatDate value='${manualAttnDaily.editDate}' type='date' pattern='dd/MM/yyyy HH:mm:ss'/>
                                        </span>
                                    </li>
                                </c:if>
                            </ol>


                        </fieldset>     <!--.show-page-->
                    </div>      <!--.box-body-->

                    <div class='box-footer'>
                        <a href='${pageContext.request.contextPath}/manualAttnDaily/edit/${manualAttnDaily.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                        <a href='${pageContext.request.contextPath}/manualAttnDaily/copy/${manualAttnDaily.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                        <a href='${pageContext.request.contextPath}/manualAttnDaily/delete/${manualAttnDaily.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                    </div>      <!--.box-footer-->
                </div>      <!--.box .box-primary-->
            </section>      <!--.content-->
        </div>      <!--.content-wrapper-->
    </tiles:putAttribute>
</tiles:insertDefinition>