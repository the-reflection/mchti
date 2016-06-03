<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate"/>

<tiles:putAttribute name="header">
    <jsp:include page="../layouts/header.jsp"/>
</tiles:putAttribute>

<tiles:putAttribute name="menu">
    <jsp:include page="../layouts/menu.jsp"/>
</tiles:putAttribute>

<tiles:putAttribute name="body">

    <title><spring:message code="reportRunnerPage.title"  text="Report Runner Page"/></title>


    <h1>Download Page</h1>

    <p>Click the download links below:</p>
    <c:url value="/report/xls" var="downloadXls"/>
    <a href="${downloadXls}">Download Excel</a>
    <br/>
    <c:url value="/report/pdf" var="downloadPdf"/>
    <a href="${downloadPdf}">Download PDF</a>
    <br/>
    <c:url value="/report/csv" var="downloadCsv"/>
    <a href="${downloadCsv}">Download CSV</a>
    <br/>
    <c:url value="/report/htm" var="downloadHtm"/>
    <a href="${downloadHtml}">Download HTML</a>
    <br/>
    <c:url value="/report/rtf" var="downloadRtf"/>
    <a href="${downloadRtf}">Download RTF</a>
    <br/>
    <c:url value="/report/txt" var="downloadTxt"/>
    <a href="${downloadTxt}">Download TEXT</a>

</tiles:putAttribute>


<tiles:putAttribute name="footer">
    <jsp:include page="../layouts/footer.jsp"/>
</tiles:putAttribute>