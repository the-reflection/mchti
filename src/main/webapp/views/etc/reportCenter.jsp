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
                <h1>Report</h1>
                
            </section><!-- /.content-header -->

            <section class="content-messages">
                <%--<jsp:include page="../layouts/_flashMessage.jsp"/>--%>
            </section><!-- /.flesh-message -->

            <section class="content"><!-- Main content -->
                <div id="create-zxLookup" class="box box-primary" role="main">
                    <form:form action="${pageContext.request.contextPath}/reportCenter" commandName="zxLookup" method="POST">
                        <div class="box-body">

                            <p>Click the download links below:</p>
                            <c:url value="/reportCenter/all" var="downloadXls"/>
                            <a href="${downloadXls}">Download All</a>
                            <br/>
                            <c:url value="/reportCenter/all" var="downloadPdf"/>
                            <a href="${downloadPdf}">Download Monthly</a>
                            <br/>
                            <c:url value="/reportCenter/all" var="downloadCsv"/>
                            <a href="${downloadCsv}">Download Daily</a>
                
                        </div><!-- /.box-body -->
                        <div class="box-footer">
<!--                            <button type="reset" class="btn btn-danger">
                                <i class="fa fa-refresh"></i> < spring:message code="default.button.reset.label" text="Reset"/>
                            </button>
                            <sec:access url="/zxLookup/create">
                                <button type="submit" class="btn btn-primary">
                                    <i class="fa fa-save"></i> < spring:message code="default.button.save.label" text="Save"/>
                                </button>
                            </sec:access>-->
                        </div><!-- /.box-footer -->
                    </form:form>
                </div><!-- /.create-zxLookup -->
            </section><!-- /.content -->
        </div><!-- /.content-wrapper -->
    </tiles:putAttribute>

</tiles:insertDefinition>