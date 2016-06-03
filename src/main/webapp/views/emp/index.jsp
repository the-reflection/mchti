<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="defaultTemplate"/>

<tiles:putAttribute name="header">
    <jsp:include page="../layouts/header.jsp"/>
</tiles:putAttribute>

<tiles:putAttribute name="menu">
    <jsp:include page="../layouts/menu.jsp"/>
</tiles:putAttribute>

<tiles:putAttribute name="body">

    <title><spring:message code="project.title.index" text="Index"/></title>

    <div>   
        <a href="${pageContext.request.contextPath}/emp/create"><spring:message code="create.link.label"/>&NonBreakingSpace;<spring:message code="emp" text="Emp"/></a>
    </div>

    <h1><spring:message code="list.page.title"/></h1>

    <form:form action="${pageContext.request.contextPath}/emp/index" commandName="searchCriteria" method="POST">

        <table>
            <tr>
                <td><form:label path="searchTerm"><spring:message code="label.searchTerm"/>:</form:label></td>
                <td><form:input path="searchTerm" type="text" id="txtSearch" size="20"/></td>
                <td><form:label path="pageSize">Found ${searchCriteria.totalRecs} Rec.</form:label></td>
                </tr>

                <tr>
                    <td><form:label path="pageSize"><spring:message code="label.pageSize"/>:</form:label></td>
                <td><form:select path="pageSize">
                        <%--<form:option value="2" label="2"/>--%>
                        <%--<form:option value="3" label="3"/>--%>
                        <form:option value="5" label="5"/>
                        <form:option value="10" label="10"/>
                        <form:option value="20" label="20"/>
                        <form:option value="50" label="50"/>
                        <form:option value="100" label="100"/>
                        <form:option value="200" label="200"/>
                        <form:option value="500" label="500"/>
                        <form:option value="1000" label="1000"/>
                        <%--   <form:options items="${pageSize}"/>--%>
                    </form:select></td>
            </tr>
            <tr>
                <td><form:label path="page"><spring:message code="label.page"/>:</form:label></td>
                <td><form:select path="page">
                        <%--<form:option value="-1" label="...Select..."/>--%>
                        <form:options items="${pages}"/>
                    </form:select></td>
            </tr>
        </table>

        <div>
            <input type="submit" value="<spring:message code="search.form.submit.label"/>"/>
        </div>
    </form:form>

    <c:if test="${emps.size()!=0}">
        <div style="width: 995px; margin: auto; overflow-x: scroll;">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="style-table">
                <thead>
                    <tr>
                        <td></td>
                        <td><spring:message code="code" text="Code"/></td>
                        <td><spring:message code="picFile" text="Pic File"/></td>
                        <td><spring:message code="fullName" text="fullName"/></td>
                        <td><spring:message code="gender" text="Gender"/></td>
                        <td><spring:message code="doj" text="Join Date"/></td>
                        <td><spring:message code="dob" text="Dob"/></td>
                        <td><spring:message code="salary" text="Salary"/></td>
                        <td><spring:message code="email" text="Email"/></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${emps}" var="emp"  varStatus="loopStatus">

                        <tr class="${loopStatus.index % 2 == 0 ? 'odd' : 'even'}">
                            <td><a href="${pageContext.request.contextPath}/emp/show/<c:out value="${emp.id}"/>"><spring:message code="show.link.label"/></a></td>
                            <td><c:out value="${emp.code}"/></td>
                            <td><img height="55px" width="45px" alt="${emp.picFile}" src="<%=request.getContextPath()%>/tumb_pics/${emp.picFile}"/></td>
                            <td><c:out value="${emp.fullName}"/></td>
                            <td><c:out value="${emp.gender}"/></td>
                            <td><fmt:formatDate value="${emp.doj}" type="date" pattern="dd/MM/yyyy"/></td>
                            <td><fmt:formatDate value="${emp.dob}" type="date" pattern="dd/MM/yyyy"/></td>
                            <td><c:out value="${emp.salary}"/></td>
                            <td><c:out value="${emp.email}"/></td>

                            <td><a href="${pageContext.request.contextPath}/emp/edit/<c:out value="${emp.id}"/>"><spring:message code="edit.link.label"/></a></td>
                            <td><a href="${pageContext.request.contextPath}/emp/delete/<c:out value="${emp.id}"/>" onclick="return confirm('Are you sure to delete?');" ><spring:message code="delete.link.label"/></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!--<div class="row-fluid">-->
        <!--    <div>
            <util:pagination thispage="${emps}"></util:pagination>
        </div>-->
    </c:if>
    <c:if test="${empty emps}">
        <p>
            <spring:message code="list.page.label.no.data.found" text="No data found"/>
        </p>
    </c:if>

</tiles:putAttribute>  

<tiles:putAttribute name="footer">
    <jsp:include page="../layouts/footer.jsp"/>
</tiles:putAttribute>    
