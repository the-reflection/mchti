<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate"/>

<tiles:putAttribute name="header">
    <jsp:include page="../layouts/header.jsp"/>
</tiles:putAttribute>

<tiles:putAttribute name="menu">
    <jsp:include page="../layouts/menu.jsp"/>
</tiles:putAttribute>

<tiles:putAttribute name="body">

    <title><spring:message code="project.title.show" text="Show"/></title>

    <div>   
        <a href="${pageContext.request.contextPath}/emp/index"><spring:message code="list.link.label"/>&NonBreakingSpace;<spring:message code="emp" text="Emp"/></a> |
        <a href="${pageContext.request.contextPath}/emp/create"><spring:message code="create.link.label"/>&NonBreakingSpace;<spring:message code="emp" text="Emp"/></a>
    </div>

    <h1><spring:message code="show.page.title"/></h1>
    
    <div>
        <form:hidden path="id"/>
        <ol class="property-list hrIrGrdScr">

            <c:if test="${emp.code!=null && !emp.code.isEmpty()}">
                <li class="fieldcontain first_item">
                    <span id="title" class="property-label">
                        <spring:message code="code" text="Code"/>: 
                    </span>
                    <span class="property-value" aria-labelledby="code">
                        <c:out value="${emp.code}"/>
                    </span>
                </li>
            </c:if>

            <c:if test="${emp.picFile!=null && !emp.picFile.isEmpty()}">
                <li class="fieldcontain first_item">
                    <span id="title" class="property-label">
                        <spring:message code="picFile" text="Pic File"/>: 
                    </span>
                    <span class="property-value" aria-labelledby="picFile">
                        <img height="110px" width="90px" alt="${emp.picFile}" src="<%=request.getContextPath()%>/pics/${emp.picFile}"/>
                    </span>
                </li>
            </c:if>

            <c:if test="${emp.fullName!=null && !emp.fullName.isEmpty()}">
                <li class="fieldcontain first_item">
                    <span id="title" class="property-label">
                        <spring:message code="fullName" text="fullName"/>: 
                    </span>
                    <span class="property-value" aria-labelledby="fullName">
                        <c:out value="${emp.fullName}"/>
                    </span>
                </li>
            </c:if>

            <c:if test="${emp.gender!=null}">
                <li class="fieldcontain first_item">
                    <span id="title" class="property-label">
                        <spring:message code="gender" text="Gender"/>: 
                    </span>
                    <span class="property-value" aria-labelledby="gender">
                        <c:out value="${emp.gender}"/>
                    </span>
                </li>
            </c:if>

            <c:if test="${emp.doj!=null}">
                <li class="fieldcontain first_item">
                    <span id="title" class="property-label">
                        <spring:message code="doj" text="doj Date"/>: 
                    </span>
                    <span class="property-value" aria-labelledby="doj">
                        <fmt:formatDate value="${emp.doj}" type="date" pattern="dd/MM/yyyy"/>
                    </span>
                </li>
            </c:if>

            <c:if test="${emp.dob!=null}">
                <li class="fieldcontain first_item">
                    <span id="title" class="property-label">
                        <spring:message code="dob" text="Dob"/>: 
                    </span>
                    <span class="property-value" aria-labelledby="dob">
                        <fmt:formatDate value="${emp.dob}" type="date" pattern="dd/MM/yyyy"/>
                    </span>
                </li>
            </c:if>

            <c:if test="${emp.salary!=null}">
                <li class="fieldcontain first_item">
                    <span id="title" class="property-label">
                        <spring:message code="salary" text="salary"/>: 
                    </span>
                    <span class="property-value" aria-labelledby="salary">
                        <c:out value="${emp.salary}"/>
                    </span>
                </li>
            </c:if>

            <c:if test="${emp.email!=null && !emp.email.isEmpty()}">
                <li class="fieldcontain first_item">
                    <span id="title" class="property-label">
                        <spring:message code="email" text="Email"/>: 
                    </span>
                    <span class="property-value" aria-labelledby="email">
                        <c:out value="${emp.email}"/>
                    </span>
                </li>
            </c:if>
        </ol>

    </div>

    <a href="${pageContext.request.contextPath}/emp/edit/<c:out value="${emp.id}"/>"><spring:message code="edit.link.label"/></a> |
    <a href="${pageContext.request.contextPath}/emp/delete/<c:out value="${emp.id}"/>" onclick="return confirm('Are you sure to delete?');" ><spring:message code="delete.link.label"/></a>

</tiles:putAttribute>  

<tiles:putAttribute name="footer">
    <jsp:include page="../layouts/footer.jsp"/>
</tiles:putAttribute>    