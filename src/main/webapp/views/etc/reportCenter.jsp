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
                <h1><spring:message code="default.button.report.label" text="Report"/></h1>
            </section><!-- /.content-header -->

            <section class="content-messages">
                <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
            </section><!-- /.flesh-message -->

            <section class="content"><!-- Main content -->
                <div class="box box-primary">
                    <form:form action="${pageContext.request.contextPath}/reportCenter" commandName="rrr" method="POST">
                        <div class="box-body">
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="report.reportName.label" text="Report Name"/>:</label>
                                    <form:select class="form-control" path="title">
                                        <form:options items="${admReports}" itemValue="id"/>
                                    </form:select>


                                </div><!-- /.form-group -->
                            </div><!-- /.col-xs-12 col-sm-6 col-md-6 col-lg-6-->

                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="default.date.labeln" text="Attendance Date"/>:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                        <input name="P_ATTN_DATE" class="form-control dtp-date"/>
                                    </div><!-- /.input group -->
                                </div><!-- /.form-group -->
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="default.date.labelx" text="From Date"/>:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                        <input name="P_FROM_DATE" class="form-control dtp-date"/>
                                    </div><!-- /.input group -->
                                </div><!-- /.form-group -->
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="default.date.labelx" text="To Date"/>:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                        <input name="P_TO_DATE" class="form-control dtp-date"/>
                                    </div><!-- /.input group -->
                                </div><!-- /.form-group -->
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="default.date.labelx" text="Month Date"/>:</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                                        <input name="P_MONTH_DATE" class="form-control dtp-date"/>
                                    </div><!-- /.input group -->
                                </div><!-- /.form-group -->
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="default.date.labelx" text="Employee ID"/>:</label>
                                    <div class="input-group">
                                        <input name="P_EMPLOYEE_CODE" class="form-control"/>
                                    </div><!-- /.input group -->
                                </div><!-- /.form-group -->
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="default.date.labelx" text="Department"/>:</label>
                                    <div class="input-group">

                                        <select class="form-control" name="P_DEPARTMENT_ID" id="P_DEPARTMENT_ID">
                                            <option value='${null}' label='--- Select ---'/>
                                            <c:forEach items='${departments}' var='department'  varStatus='loopStatus'>
                                                <option value='${department.id}' >${department}</option>
                                            </c:forEach>
                                        </select>
                                    </div><!-- /.input group -->
                                </div><!-- /.form-group -->
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                <div class="form-group">
                                    <label><spring:message code="default.date.labelx" text="Designation"/>:</label>
                                    <div class="input-group">

                                        <select class="form-control" name="P_DESIGNATION_ID" id="P_DESIGNATION_ID">
                                            <option value='${null}' >--- Select ---</option>
                                            <c:forEach items='${designations}' var='designation'  varStatus='loopStatus'>
                                                <option value='${designation.id}' >${designation}</option>
                                            </c:forEach>
                                        </select>
                                    </div><!-- /.input group -->
                                </div><!-- /.form-group -->
                            </div>
                            <%--
                                <c:url value="/reportCenter/all" var="downloadXls"/>
                                <a href="${downloadXls}">Download All</a>
                                <br/>
                                <c:url value="/reportCenter/all" var="downloadPdf"/>
                                <a href="${downloadPdf}">Download Monthly</a>
                                <br/>
                                <c:url value="/reportCenter/all" var="downloadCsv"/>
                                <a href="${downloadCsv}">Download Daily</a>
                            --%>
                        </div><!-- /.box-body -->

                        <div class="box-footer">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <!--<sec:access url="/reportCenter">-->
                                <button type="submit" class="btn btn-primary"><i class="fa fa-info"></i> <spring:message code="default.button.report.label" text="Report"/></button>
                                <!--</sec:access>-->
                            </div>
                        </div><!-- /.box-footer -->
                    </form:form>
                </div><!-- /.create-zxLookup -->
            </section><!-- /.content -->
        </div><!-- /.content-wrapper -->
    </tiles:putAttribute>

</tiles:insertDefinition>

