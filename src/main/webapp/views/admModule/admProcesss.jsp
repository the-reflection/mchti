<%@ page contentType='text/html;charset=utf-8' language='java' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<spring:message code='admProcesss' text='Adm Processs'/> | <a href='${pageContext.request.contextPath}/admProcess/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='AdmProcess' text='Adm Process'/></a>

<c:if test='${not empty admModule.admProcesss}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <tr>
                    <td></td>
                    <td><spring:message code='version' text='Version'/></td>
                    <td><spring:message code='code' text='Code'/></td>
                    <td><spring:message code='fullName' text='Full Name'/></td>
     
                    <td><spring:message code='isActive' text='Is Active'/></td>
                    <td><spring:message code='slNo' text='Sl No'/></td>
                    <td><spring:message code='remarks' text='Remarks'/></td>
 
                </tr>
            </thead>
            <tbody>
                <c:forEach items='${admModule.admProcesss}' var='admProcesss'  varStatus='loopStatus'>

                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${pageContext.request.contextPath}/admProcess/show/<c:out value='${admProcesss.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td><c:out value='${admProcesss.version}'/></td>
                        <td><c:out value='${admProcesss.code}'/></td>
                        <td><c:out value='${admProcesss.fullName}'/></td>
             
                        <td><c:if test='${admProcesss.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!admProcesss.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                        <td><c:out value='${admProcesss.slNo}'/></td>
                        <td><c:out value='${admProcesss.remarks}'/></td>

                        <td><a href='${pageContext.request.contextPath}/admProcess/edit/<c:out value='${admProcesss.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${pageContext.request.contextPath}/admProcess/delete/<c:out value='${admProcesss.id}'/>' onclick='return confirm('Are you sure to delete?');' ><spring:message code='delete.link.label'/></a></td>
                    </tr>
                    
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>