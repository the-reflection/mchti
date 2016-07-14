<%@ page contentType='text/html;charset=UTF-8' language='java' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<spring:message code='childs' text='Childs'/> | <a href='${pageContext.request.contextPath}/authMenu/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='AuthMenu' text='Auth Menu'/></a>

<c:if test='${not empty authMenu.childs}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <tr>
                    <td></td>
                    <td><spring:message code='version' text='Version'/></td>
                    <td><spring:message code='displayName' text='Display Name'/></td>
                    <td><spring:message code='tooltip' text='Tooltip'/></td>
                    <td><spring:message code='description' text='Description'/></td>
                    <td><spring:message code='menuType' text='Menu Type'/></td>
                    <td><spring:message code='isExternal' text='Is External'/></td>
                    <td><spring:message code='isOpenNewTab' text='Is Open New Tab'/></td>
                    <td><spring:message code='slNo' text='Sl No'/></td>
                    <td><spring:message code='urlPath' text='Url Path'/></td>
                    <td><spring:message code='displayIconClass' text='Display Icon Class'/></td>
                    <td><spring:message code='isActive' text='Is Active'/></td>
 
                </tr>
            </thead>
            <tbody>
                <c:forEach items='${authMenu.childs}' var='childs'  varStatus='loopStatus'>

                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${pageContext.request.contextPath}/authMenu/show/<c:out value='${childs.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td><c:out value='${childs.version}'/></td>
                        <td><c:out value='${childs.displayName}'/></td>
                        <td><c:out value='${childs.tooltip}'/></td>
                        <td><c:out value='${childs.description}'/></td>
                        <td><c:out value='${childs.menuType}'/></td>
                        <td><c:if test='${childs.isExternal}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!childs.isExternal}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                        <td><c:if test='${childs.isOpenNewTab}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!childs.isOpenNewTab}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>
                        <td><c:out value='${childs.slNo}'/></td>
                        <td><c:out value='${childs.urlPath}'/></td>
                        <td><c:out value='${childs.displayIconClass}'/></td>
                        <td><c:if test='${childs.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!childs.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if></td>

                        <td><a href='${pageContext.request.contextPath}/authMenu/edit/<c:out value='${childs.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${pageContext.request.contextPath}/authMenu/delete/<c:out value='${childs.id}'/>' onclick="return confirm('Are you sure...? you want to delete this record...!');" ><spring:message code='delete.link.label'/></a></td>
                    </tr>
                    
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>