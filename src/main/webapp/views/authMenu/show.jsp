<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>

<%@ taglib uri='http://tiles.apache.org/tags-tiles' prefix='tiles'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri='http://www.springframework.org/tags' prefix='spring'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions' prefix='fn'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/fmt' prefix='fmt'%>
<%@ taglib uri='http://www.springframework.org/tags/form' prefix='form'%>

<tiles:insertDefinition name='main' >

    <tiles:putAttribute name='body'>
        
        <div class='content-wrapper'><!-- Content Wrapper. Contains page content -->
            <section class='content-header'><!-- Content Header (Page header) -->
                <h1><spring:message code='default.button.show.label' text='Show'/> Auth Menu</h1>
                <ul class='top-links'>
                    <sec:access url='/authMenu/create'>
                        <li>
                            <a href='${pageContext.request.contextPath}/authMenu/create' class='btn btn-block btn-primary btn-xs'><i class='fa fa-plus-circle'></i> <spring:message code='default.button.create.label' text='New'/></a>
                        </li>
                    </sec:access>
                    <sec:access url='/authMenu/index'>
                        <li>
                            <a href='${pageContext.request.contextPath}/authMenu/index' class='btn btn-block btn-info btn-xs'><i class='fa fa-reorder'></i> <spring:message code='default.button.list.label' text='List'/></a>
                        </li>
                    </sec:access>
                </ul>
            </section><!-- /.content-header -->

            <section class='content-messages'>
                <%--<jsp:include page='../layouts/_flashMessage.jsp'/>--%>
            </section><!-- /.flesh-message -->

            <section class='content'><!-- Main content -->
                <div class='box box-primary'>
                    <div class='box-body'>
                        <fieldset class='show-page'>
                             <form:hidden path='id'/>
        <ol class='property-list'>

        <c:if test='${authMenu.displayName!=null && !authMenu.displayName.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='displayName' text='Display Name'/>: 
                </span>
                <span class='property-value' aria-labelledby='displayName'>
                    <c:out value='${authMenu.displayName}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authMenu.tooltip!=null && !authMenu.tooltip.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='tooltip' text='Tooltip'/>: 
                </span>
                <span class='property-value' aria-labelledby='tooltip'>
                    <c:out value='${authMenu.tooltip}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authMenu.description!=null && !authMenu.description.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='description' text='Description'/>: 
                </span>
                <span class='property-value' aria-labelledby='description'>
                    <c:out value='${authMenu.description}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authMenu.menuType!=null && !authMenu.menuType.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='menuType' text='Menu Type'/>: 
                </span>
                <span class='property-value' aria-labelledby='menuType'>
                    <c:out value='${authMenu.menuType}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authMenu.isExternal!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='isExternal' text='Is External'/>: 
                </span>
                <span class='property-value' aria-labelledby='isExternal'>
                    <c:if test='${authMenu.isExternal}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authMenu.isExternal}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                </span>
            </li>
        </c:if>

        <c:if test='${authMenu.isOpenNewTab!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='isOpenNewTab' text='Is Open New Tab'/>: 
                </span>
                <span class='property-value' aria-labelledby='isOpenNewTab'>
                    <c:if test='${authMenu.isOpenNewTab}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authMenu.isOpenNewTab}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                </span>
            </li>
        </c:if>

        <c:if test='${authMenu.slNo!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='slNo' text='Sl No'/>: 
                </span>
                <span class='property-value' aria-labelledby='slNo'>
                    <c:out value='${authMenu.slNo}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authMenu.parentAuthMenu!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='parentAuthMenu' text='Parent Auth Menu'/>: 
                </span>
                <span class='property-value' aria-labelledby='parentAuthMenu'>
                    <c:out value='${authMenu.parentAuthMenu}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authMenu.urlPath!=null && !authMenu.urlPath.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='urlPath' text='Url Path'/>: 
                </span>
                <span class='property-value' aria-labelledby='urlPath'>
                    <c:out value='${authMenu.urlPath}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authMenu.displayIconClass!=null && !authMenu.displayIconClass.isEmpty()}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='displayIconClass' text='Display Icon Class'/>: 
                </span>
                <span class='property-value' aria-labelledby='displayIconClass'>
                    <c:out value='${authMenu.displayIconClass}'/>
                </span>
            </li>
        </c:if>

        <c:if test='${authMenu.isActive!=null}'>
            <li class='fieldcontain first_item'>
                <span id='title' class='property-label'>
                    <spring:message code='isActive' text='Is Active'/>: 
                </span>
                <span class='property-value' aria-labelledby='isActive'>
                    <c:if test='${authMenu.isActive}'><spring:message code='default.boolean.true' text='YES'/></c:if><c:if test='${!authMenu.isActive}'><spring:message code='default.boolean.false' text='NO'/></c:if>
                </span>
            </li>
        </c:if>

        </ol>
    <div><jsp:include page='childs.jsp' /></div>

    
      </fieldset>     <!--.show-page-->
                        </div>      <!--.box-body-->
    
                        <div class='box-footer'>
                            <a href='${pageContext.request.contextPath}/authMenu/edit/${authMenu.id}' class='btn btn-primary'><i class='fa fa-edit'></i> <spring:message code='edit.link.label'/></a> 
                            <a href='${pageContext.request.contextPath}/authMenu/copy/${authMenu.id}' class='btn btn-warning'><i class='fa fa-clone'></i> <spring:message code='default.button.copy.label'/></a>             
                            <a href='${pageContext.request.contextPath}/authMenu/delete/${authMenu.id}' class='btn btn-danger' onclick='return confirm('Are you sure to delete?');'><i class='fa fa-remove'></i> <spring:message code='delete.link.label'/></a>
                        </div>      <!--.box-footer-->
                    </div>      <!--.box .box-primary-->
                </section>      <!--.content-->
            </div>      <!--.content-wrapper-->
        </tiles:putAttribute>
</tiles:insertDefinition>