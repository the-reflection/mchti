<%@ page contentType='text/html;charset=UTF-8' language='java' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<spring:message code='admReportDetails' text='Adm Report Details'/> | <a href='${pageContext.request.contextPath}/admReportDetail/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='AdmReportDetail' text='Adm Report Detail'/></a>

<c:if test='${not empty admReport.admReportDetails}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <tr>
                    <td></td>
                    <td><spring:message code='version' text='Version'/></td>
                    <td><spring:message code='slNo' text='Sl No'/></td>
                    <td><spring:message code='admParam' text='Adm Param'/></td>
 
                </tr>
            </thead>
            <tbody>
                <c:forEach items='${admReport.admReportDetails}' var='admReportDetails'  varStatus='loopStatus'>

                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${pageContext.request.contextPath}/admReportDetail/show/<c:out value='${admReportDetails.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td><c:out value='${admReportDetails.version}'/></td>
                        <td><c:out value='${admReportDetails.slNo}'/></td>
                        <td><c:out value='${admReportDetails.admParam}'/></td>

                        <td><a href='${pageContext.request.contextPath}/admReportDetail/edit/<c:out value='${admReportDetails.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${pageContext.request.contextPath}/admReportDetail/delete/<c:out value='${admReportDetails.id}'/>' onclick='return confirm('Are you sure to delete?');' ><spring:message code='delete.link.label'/></a></td>
                    </tr>
                    
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>