<page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="main" >

    <tiles:putAttribute name="body">

        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->
            <section class="content-header"><!-- Content Header (Page header) -->
                <h1><spring:message code="default.button.list.label" text="List"/> Proc Out Attn Period</h1>
                <ul class="top-links">
                    <sec:access url="/procOutAttnPeriod/create">
                        <li>
                            <a href="${pageContext.request.contextPath}/procOutAttnPeriod/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="New"/></a>
                        </li>
                    </sec:access>
                </ul>
            </section><!-- /.content-header -->

            <section class="content-messages">
                <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
            </section><!-- /.flesh-message -->

            <section class="content"><!-- Main content -->
                <div class="box box-primary">   
                    <div class="box-body" style="overflow-x: auto">
                        <table class="dt-default table table-bordered table-striped table-hover table-condensed">

                            <!--<table class="table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center">-->
                            <thead>
                        <th class='center bold'><spring:message code="genType" text="Gen Type"/></th>
                    <th class='center bold'><spring:message code="attnSts" text="Attn Sts"/></th>
                    <th class='center bold'><spring:message code="period" text="Period"/></th>
                    <th class='center bold'><spring:message code="employee" text="Employee"/></th>
                    <th class='center bold'><spring:message code="dayPresent" text="Day Present"/></th>
                    <th class='center bold'><spring:message code="dayAbsent" text="Day Absent"/></th>
                    <th class='center bold'><spring:message code="dayLate" text="Day Late"/></th>
                    <th class='center bold'><spring:message code="dayEarlyOut" text="Day Early Out"/></th>
                    <th class='center bold'><spring:message code="dayLateInEarlyOut" text="Day Late In Early Out"/></th>
                    <th class='center bold'><spring:message code="dayCl" text="Day Cl"/></th>
                    <th class='center bold'><spring:message code="daySl" text="Day Sl"/></th>
                    <th class='center bold'><spring:message code="dayEl" text="Day El"/></th>
                    <th class='center bold'><spring:message code="dayMl" text="Day Ml"/></th>
                    <th class='center bold'><spring:message code="dayLwp" text="Day Lwp"/></th>
                    <th class='center bold'><spring:message code="otHourNormal" text="Ot Hour Normal"/></th>
                    <th class='center bold'><spring:message code="otHourSro" text="Ot Hour Sro"/></th>
                    <th class='center bold'><spring:message code="otHourExtra" text="Ot Hour Extra"/></th>
                    <th class='center bold'><spring:message code="otHourHoliday" text="Ot Hour Holiday"/></th>
                    <th class='center bold'><spring:message code="dayHolidayWork" text="Day Holiday Work"/></th>
                    <th class='center bold'><spring:message code="dayHoliday" text="Day Holiday"/></th>

    <th class='center bold'><spring:message code="default.button.action.label" text="Action"/></th> 
            </thead>
            <tbody>
            <c:if test="${not empty procOutAttnPeriods}">
                <c:forEach items="${procOutAttnPeriods}" var="procOutAttnPeriod"  varStatus="loopStatus">
                    <tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
                        <td><c:out value="${procOutAttnPeriod.genType}"/></td>
                        <td><c:out value="${procOutAttnPeriod.attnSts}"/></td>
                        <td><c:out value="${procOutAttnPeriod.period}"/></td>
                        <td><c:out value="${procOutAttnPeriod.employee!=null ? procOutAttnPeriod.employee :'N/A'}"/></td>
                        <td><c:out value="${procOutAttnPeriod.dayPresent}"/></td>
                        <td><c:out value="${procOutAttnPeriod.dayAbsent}"/></td>
                        <td><c:out value="${procOutAttnPeriod.dayLate}"/></td>
                        <td><c:out value="${procOutAttnPeriod.dayEarlyOut}"/></td>
                        <td><c:out value="${procOutAttnPeriod.dayLateInEarlyOut}"/></td>
                        <td><c:out value="${procOutAttnPeriod.dayCl}"/></td>
                        <td><c:out value="${procOutAttnPeriod.daySl}"/></td>
                        <td><c:out value="${procOutAttnPeriod.dayEl}"/></td>
                        <td><c:out value="${procOutAttnPeriod.dayMl}"/></td>
                        <td><c:out value="${procOutAttnPeriod.dayLwp}"/></td>
                        <td><c:out value="${procOutAttnPeriod.otHourNormal}"/></td>
                        <td><c:out value="${procOutAttnPeriod.otHourSro}"/></td>
                        <td><c:out value="${procOutAttnPeriod.otHourExtra}"/></td>
                        <td><c:out value="${procOutAttnPeriod.otHourHoliday}"/></td>
                        <td><c:out value="${procOutAttnPeriod.dayHolidayWork}"/></td>
                        <td><c:out value="${procOutAttnPeriod.dayHoliday}"/></td>

                           <td class="center action">
                            <ul class="top-links">
                            <sec:access url="/procOutAttnPeriod/show">
                                <li>
                                <a class="btn btn-block btn-info btn-xs" href="${pageContext.request.contextPath}/procOutAttnPeriod/show/${procOutAttnPeriod.id}">
                                    <i class="fa fa-info-circle"></i> <spring:message code="show.link.label"/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url="/procOutAttnPeriod/edit">
                                <li>
                                <a class="btn btn-block btn-primary btn-xs" href="${pageContext.request.contextPath}/procOutAttnPeriod/edit/${procOutAttnPeriod.id}">
                                    <i class="fa fa-edit"></i> <spring:message code="edit.link.label"/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url="/procOutAttnPeriod/copy">
                                <li>
                                <a class="btn btn-block btn-warning btn-xs" href="${pageContext.request.contextPath}/procOutAttnPeriod/copy/${procOutAttnPeriod.id}">
                                    <i class="fa fa-clone" aria-hidden="true"></i> <spring:message code="default.button.copy.label"/>
                                </a>
                                </li>
                            </sec:access>
                            <sec:access url="/procOutAttnPeriod/delete">
                                <li>
                                <a class="btn btn-block btn-danger btn-xs" href="${pageContext.request.contextPath}/procOutAttnPeriod/delete/${procOutAttnPeriod.id}" onclick="return confirm('Are you sure to delete?');">
                                    <i class="fa fa-remove"></i> <spring:message code="delete.link.label"/>
                                </a>
                                </li>
                            </sec:access>
                            </ul>
                        </td>
                        </tr>
                    </c:forEach>
                    </c:if> 
                </tbody>
            </table>
            <div class="pagination">
                <!--<g:paginate total="{testInstanceCount ?: 0}"/>-->
            </div>
            </div><!-- /.box-body table-responsive no-padding -->
        </div><!-- /.box box-primary -->
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    </tiles:putAttribute>
                
</tiles:insertDefinition>