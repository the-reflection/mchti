<%@ page contentType='text/html;charset=UTF-8' language='java' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<spring:message code='admProcessDetails' text='Adm Process Details'/> | <a href='${pageContext.request.contextPath}/admProcessDetail/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='AdmProcessDetail' text='Adm Process Detail'/></a>

<c:if test='${not empty admProcess.admProcessDetails}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <tr>
                    <td></td>
                    <td><spring:message code='version' text='Version'/></td>
                    <td><spring:message code='slNo' text='Sl No'/></td>
                    <td><spring:message code='admParam' text='Adm Param'/></td>
                    <td><spring:message code='admZoneType' text='Adm Zone Type'/></td>

                </tr>
            </thead>
            <tbody>
                <c:forEach items='${admProcess.admProcessDetails}' var='admProcessDetails'  varStatus='loopStatus'>

                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${pageContext.request.contextPath}/admProcessDetail/show/<c:out value='${admProcessDetails.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td><c:out value='${admProcessDetails.version}'/></td>
                        <td><c:out value='${admProcessDetails.slNo}'/></td>
                        <td><c:out value='${admProcessDetails.admParam}'/></td>
                        <td><c:out value='${admProcessDetails.admZoneType}'/></td>

                        <td><a href='${pageContext.request.contextPath}/admProcessDetail/edit/<c:out value='${admProcessDetails.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${pageContext.request.contextPath}/admProcessDetail/delete/<c:out value='${admProcessDetails.id}'/>' onclick="return confirm('Are you sure...? you want to delete this record...!');" ><spring:message code='delete.link.label'/></a></td>
                    </tr>

                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>