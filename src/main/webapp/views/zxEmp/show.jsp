<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate"/>

<tiles:putAttribute name="header">
    <%--<jsp:include page="../layouts/header.jsp"/>--%>
</tiles:putAttribute>

<tiles:putAttribute name="menu">
    <%--<jsp:include page="../layouts/menu.jsp"/>--%>
</tiles:putAttribute>

<div class="box box-primary">
    <div class="box-body">
        <tiles:putAttribute name="body">

            <title><spring:message code="project.title.show" text="Show"/></title>

            <div class="btn-group">
                <a class="btn btn-info" href="${pageContext.request.contextPath}/zxEmp/index"><spring:message code="list.link.label"/>&NonBreakingSpace;<spring:message code="zxEmp" text="ZxEmp"/></a> 
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/zxEmp/create"><spring:message code="create.link.label"/>&NonBreakingSpace;<spring:message code="zxEmp" text="ZxEmp"/></a>
            </div>

            <h1><spring:message code="show.page.title"/></h1>

            <div>
                <form:hidden path="id"/>
                <ol class="property-list hrIrGrdScr">

                    <c:if test="${zxEmp.code!=null && !zxEmp.code.isEmpty()}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="code" text="Code"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="code">
                                <c:out value="${zxEmp.code}"/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.picFile!=null && !zxEmp.picFile.isEmpty()}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="picFile" text="Pic File"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="picFile">
                                <c:url var="picFile" value="/zxEmp/getPhoto/${zxEmp.picFile}"/><img height="110px" width="90px" alt="${zxEmp.picFile}" src="${picFile}"/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.fullName!=null && !zxEmp.fullName.isEmpty()}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="fullName" text="Full Name"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="fullName">
                                <c:out value="${zxEmp.fullName}"/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.zxGender!=null}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="zxGender" text="Zx Gender"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="zxGender">
                                <c:out value="${zxEmp.zxGender}"/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.startDate!=null}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="startDate" text="Start Date"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="startDate">
                                <fmt:formatDate value="${zxEmp.startDate}" type="date" pattern="dd/MM/yyyy"/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.dob!=null}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="dob" text="Dob"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="dob">
                                <fmt:formatDate value="${zxEmp.dob}" type="date" pattern="dd/MM/yyyy"/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.sal!=null}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="sal" text="Sal"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="sal">
                                <c:out value="${zxEmp.sal}"/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.taxPaid!=null}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="taxPaid" text="Tax Paid"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="taxPaid">
                                <c:out value="${zxEmp.taxPaid}"/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.email!=null && !zxEmp.email.isEmpty()}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="email" text="Email"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="email">
                                <c:out value="${zxEmp.email}"/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.pin!=null && !zxEmp.pin.isEmpty()}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="pin" text="Pin"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="pin">
                                <c:out value="${zxEmp.pin}"/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.webAddress!=null && !zxEmp.webAddress.isEmpty()}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="webAddress" text="Web Address"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="webAddress">
                                <c:url var="webAddress" value="/zxEmp/getFile/${zxEmp.webAddress}"/>
                                <a target="_blank" href="${webAddress}">${zxEmp.webAddress}</a>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.docFile!=null && !zxEmp.docFile.isEmpty()}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="docFile" text="Doc File"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="docFile">
                                <c:url var="docFile" value="/zxEmp/getFile/${zxEmp.docFile}"/>
                                <a target="_blank" href="${docFile}">${zxEmp.docFile}</a>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.isActive!=null}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="isActive" text="Is Active"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="isActive">
                                <c:if test="${zxEmp.isActive}"><spring:message code="default.boolean.true" text="YES"/></c:if><c:if test="${!zxEmp.isActive}"><spring:message code="default.boolean.false" text="NO"/></c:if>
                                </span>
                            </li>
                    </c:if>

                    <c:if test="${zxEmp.remarks!=null && !zxEmp.remarks.isEmpty()}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="remarks" text="Remarks"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="remarks">
                                <c:out value="${zxEmp.remarks}"/>
                            </span>
                        </li>
                    </c:if>

                    <c:if test="${zxEmp.zxLookupBloodGroup!=null}">
                        <li class="fieldcontain first_item">
                            <span id="title" class="property-label">
                                <spring:message code="zxLookupBloodGroup" text="Zx Lookup Blood Group"/>: 
                            </span>
                            <span class="property-value" aria-labelledby="zxLookupBloodGroup">
                                <c:out value="${zxEmp.zxLookupBloodGroup}"/>
                            </span>
                        </li>
                    </c:if>

                </ol>
                <div><jsp:include page="zxEmpEduDtls.jsp"/></div>

            </div>


            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/zxEmp/edit/${zxEmp.id}" class="btn btn-primary"><spring:message code="edit.link.label"/></a> 
                <a href="${pageContext.request.contextPath}/zxEmp/copy/${zxEmp.id}" class="btn btn-warning"><spring:message code="copy.link.label"/></a>             
                <a href="${pageContext.request.contextPath}/zxEmp/delete/${zxEmp.id}" class="btn btn-danger" onclick="return confirm('Are you sure to delete?');" ><spring:message code="delete.link.label"/></a>
            </div>

        </tiles:putAttribute>  

        <div class="box-footer">
            <tiles:putAttribute name="footer">
                <%--<jsp:include page="../layouts/footer.jsp"/>--%>
            </tiles:putAttribute>  
        </div>
    </div> 