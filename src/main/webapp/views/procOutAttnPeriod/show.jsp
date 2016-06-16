<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="main" >

    <tiles:putAttribute name="body">
        
        <div class="content-wrapper"><!-- Content Wrapper. Contains page content -->
            <section class="content-header"><!-- Content Header (Page header) -->
                <h1><spring:message code="default.button.show.label" text="Show"/> ProcOutAttnPeriod</h1>
                <ul class="top-links">
                    <sec:access url="/procOutAttnPeriod/create">
                        <li>
                            <a href="${pageContext.request.contextPath}/procOutAttnPeriod/create" class="btn btn-block btn-primary btn-xs"><i class="fa fa-plus-circle"></i> <spring:message code="default.button.create.label" text="New"/></a>
                        </li>
                    </sec:access>
                    <sec:access url="/procOutAttnPeriod/index">
                        <li>
                            <a href="${pageContext.request.contextPath}/procOutAttnPeriod/index" class="btn btn-block btn-info btn-xs"><i class="fa fa-reorder"></i> <spring:message code="default.button.list.label" text="List"/></a>
                        </li>
                    </sec:access>
                </ul>
            </section><!-- /.content-header -->

            <section class="content-messages">
                <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
            </section><!-- /.flesh-message -->

            <section class="content"><!-- Main content -->
                <div class="box box-primary">
                    <div class="box-body">
                        <fieldset class="show-page">
                             <form:hidden path="id"/>
        <ol class="property-list hrIrGrdScr">
    
        <c:if test="${procOutAttnPeriod.genType!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="genType" text="Gen Type"/>: 
                </span>
                <span class="property-value" aria-labelledby="genType">
                    <c:out value="${procOutAttnPeriod.genType}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.attnSts!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="attnSts" text="Attn Sts"/>: 
                </span>
                <span class="property-value" aria-labelledby="attnSts">
                    <c:out value="${procOutAttnPeriod.attnSts}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.period!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="period" text="Period"/>: 
                </span>
                <span class="property-value" aria-labelledby="period">
                    <c:out value="${procOutAttnPeriod.period}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.employee!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="employee" text="Employee"/>: 
                </span>
                <span class="property-value" aria-labelledby="employee">
                    <c:out value="${procOutAttnPeriod.employee!=null ? procOutAttnPeriod.employee :'N/A'}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.dayPresent!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dayPresent" text="Day Present"/>: 
                </span>
                <span class="property-value" aria-labelledby="dayPresent">
                    <c:out value="${procOutAttnPeriod.dayPresent}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.dayAbsent!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dayAbsent" text="Day Absent"/>: 
                </span>
                <span class="property-value" aria-labelledby="dayAbsent">
                    <c:out value="${procOutAttnPeriod.dayAbsent}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.dayLate!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dayLate" text="Day Late"/>: 
                </span>
                <span class="property-value" aria-labelledby="dayLate">
                    <c:out value="${procOutAttnPeriod.dayLate}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.dayEarlyOut!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dayEarlyOut" text="Day Early Out"/>: 
                </span>
                <span class="property-value" aria-labelledby="dayEarlyOut">
                    <c:out value="${procOutAttnPeriod.dayEarlyOut}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.dayLateInEarlyOut!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dayLateInEarlyOut" text="Day Late In Early Out"/>: 
                </span>
                <span class="property-value" aria-labelledby="dayLateInEarlyOut">
                    <c:out value="${procOutAttnPeriod.dayLateInEarlyOut}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.dayCl!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dayCl" text="Day Cl"/>: 
                </span>
                <span class="property-value" aria-labelledby="dayCl">
                    <c:out value="${procOutAttnPeriod.dayCl}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.daySl!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="daySl" text="Day Sl"/>: 
                </span>
                <span class="property-value" aria-labelledby="daySl">
                    <c:out value="${procOutAttnPeriod.daySl}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.dayEl!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dayEl" text="Day El"/>: 
                </span>
                <span class="property-value" aria-labelledby="dayEl">
                    <c:out value="${procOutAttnPeriod.dayEl}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.dayMl!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dayMl" text="Day Ml"/>: 
                </span>
                <span class="property-value" aria-labelledby="dayMl">
                    <c:out value="${procOutAttnPeriod.dayMl}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.dayLwp!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dayLwp" text="Day Lwp"/>: 
                </span>
                <span class="property-value" aria-labelledby="dayLwp">
                    <c:out value="${procOutAttnPeriod.dayLwp}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.otHourNormal!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="otHourNormal" text="Ot Hour Normal"/>: 
                </span>
                <span class="property-value" aria-labelledby="otHourNormal">
                    <c:out value="${procOutAttnPeriod.otHourNormal}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.otHourSro!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="otHourSro" text="Ot Hour Sro"/>: 
                </span>
                <span class="property-value" aria-labelledby="otHourSro">
                    <c:out value="${procOutAttnPeriod.otHourSro}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.otHourExtra!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="otHourExtra" text="Ot Hour Extra"/>: 
                </span>
                <span class="property-value" aria-labelledby="otHourExtra">
                    <c:out value="${procOutAttnPeriod.otHourExtra}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.otHourHoliday!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="otHourHoliday" text="Ot Hour Holiday"/>: 
                </span>
                <span class="property-value" aria-labelledby="otHourHoliday">
                    <c:out value="${procOutAttnPeriod.otHourHoliday}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.dayHolidayWork!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dayHolidayWork" text="Day Holiday Work"/>: 
                </span>
                <span class="property-value" aria-labelledby="dayHolidayWork">
                    <c:out value="${procOutAttnPeriod.dayHolidayWork}"/>
                </span>
            </li>
        </c:if>

        <c:if test="${procOutAttnPeriod.dayHoliday!=null}">
            <li class="fieldcontain first_item">
                <span id="title" class="property-label">
                    <spring:message code="dayHoliday" text="Day Holiday"/>: 
                </span>
                <span class="property-value" aria-labelledby="dayHoliday">
                    <c:out value="${procOutAttnPeriod.dayHoliday}"/>
                </span>
            </li>
        </c:if>

        </ol>
    
    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class="box-footer">
                            <a href="${pageContext.request.contextPath}/procOutAttnPeriod/edit/${procOutAttnPeriod.id}" class="btn btn-primary"><i class="fa fa-edit"></i> <spring:message code="edit.link.label"/></a> 
                            <a href="${pageContext.request.contextPath}/procOutAttnPeriod/copy/${procOutAttnPeriod.id}" class="btn btn-warning"><i class="fa fa-clone"></i> <spring:message code="default.button.copy.label"/></a>             
                            <a href="${pageContext.request.contextPath}/procOutAttnPeriod/delete/${procOutAttnPeriod.id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete?');"><i class="fa fa-remove"></i> <spring:message code="delete.link.label"/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>