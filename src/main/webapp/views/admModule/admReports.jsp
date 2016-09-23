<%@ page contentType='text/html;charset=utf-8' language='java' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<spring:message code='admReports' text='Adm Reports'/> | <a href='${pageContext.request.contextPath}/admReport/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='AdmReport' text='Adm Report'/></a>

<c:if test='${not empty admModule.admReports}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <tr>
                    <td></td>
                    <td><spring:message code='version' text='Version'/></td>
                    <td><spring:message code='code' text='Code'/></td>
                    <td><spring:message code='fullName' text='Full Name'/></td>
                    <td><spring:message code='fileName' text='File Name'/></td>
                    <td><spring:message code='isActive' text='Is Active'/></td>
                    <td><spring:message code='slNo' text='Sl No'/></td>
                    <td><spring:message code='remarks' text='Remarks'/></td>
 
                </tr>
            </thead>
            <tbody>
                <c:forEach items='${admModule.admReports}' var='admReports'  varStatus='loopStatus'>

                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${pageContext.request.contextPath}/admReport/show/<c:out value='${admReports.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td><c:out value='${admReports.version}'/></td>
                        <td><c:out value='${admReports.code}'/></td>
                        <td><c:out value='${admReports.fullName}'/></td>
                        <td><c:out value='${admReports.fileName}'/></td>
                        <td><c:if test='${admReports.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admReports.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                        <td><c:out value='${admReports.slNo}'/></td>
                        <td><c:out value='${admReports.remarks}'/></td>

                        <td><a href='${pageContext.request.contextPath}/admReport/edit/<c:out value='${admReports.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${pageContext.request.contextPath}/admReport/delete/<c:out value='${admReports.id}'/>' onclick='return confirm('Are you sure to delete?');' ><spring:message code='delete.link.label'/></a></td>
                    </tr>
                    
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>