<%@ page contentType='text/html;charset=UTF-8' language='java' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<spring:message code='zxEmpEduDtls' text='Zx Emp Edu Dtls'/> | <a href='${pageContext.request.contextPath}/zxEmpEduDtl/create'><spring:message code='create.link.label'/>&NonBreakingSpace;<spring:message code='ZxEmpEduDtl' text='Zx Emp Edu Dtl'/></a>

<c:if test='${not empty zxEmp.zxEmpEduDtls}'>
    <div class='box-body table-responsive no-padding'>
        <table class='table table-bordered table-striped table-hover table-condensed dt-responsive display nowrap th-center'>
            <thead>
                <tr>
                    <td></td>
                    <td><spring:message code='version' text='Version'/></td>
                    <td><spring:message code='exam' text='Exam'/></td>
                    <td><spring:message code='examOrientDate' text='Exam Orient Date'/></td>
                    <td><spring:message code='remarks' text='Remarks'/></td>
                    <td><spring:message code='slNo' text='Sl No'/></td>
                    <td><spring:message code='certificateFile' text='Certificate File'/></td>
                    <td><spring:message code='picFile' text='Pic File'/></td>
                    <td><spring:message code='zxEmpWhoCheckedBy' text='Zx Emp Who Checked By'/></td>
 
                </tr>
            </thead>
            <tbody>
                <c:forEach items='${zxEmp.zxEmpEduDtls}' var='zxEmpEduDtls'  varStatus='loopStatus'>

                    <tr class='${loopStatus.index % 2 == 0 ? 'odd' : 'even'}'>
                        <td><a href='${pageContext.request.contextPath}/zxEmpEduDtl/show/<c:out value='${zxEmpEduDtls.id}'/>'><spring:message code='show.link.label'/></a></td>
                        <td><c:out value='${zxEmpEduDtls.version}'/></td>
                        <td><c:out value='${zxEmpEduDtls.exam}'/></td>
                        <td><fmt:formatDate value='${zxEmpEduDtls.examOrientDate}' type='date' pattern='dd/MM/yyyy'/></td>
                        <td><c:out value='${zxEmpEduDtls.remarks}'/></td>
                        <td><c:out value='${zxEmpEduDtls.slNo}'/></td>
                        <td><c:url var='certificateFile' value='/zxEmpEduDtls/getFile/${zxEmpEduDtls.certificateFile}'/>
<a target='_blank' href='${certificateFile}'>${zxEmpEduDtls.certificateFile}</a></td>
                        <td><c:url var='picFile' value='/zxEmpEduDtls/getPhotoTumb/${zxEmpEduDtls.picFile}'/>
<img alt='${zxEmpEduDtls.picFile}' src='${picFile}'/></td>
                        <td><c:out value='${zxEmpEduDtls.zxEmpWhoCheckedBy!=null ? zxEmpEduDtls.zxEmpWhoCheckedBy :"N/A"}'/></td>

                        <td><a href='${pageContext.request.contextPath}/zxEmpEduDtl/edit/<c:out value='${zxEmpEduDtls.id}'/>'><spring:message code='edit.link.label'/></a></td>
                        <td><a href='${pageContext.request.contextPath}/zxEmpEduDtl/delete/<c:out value='${zxEmpEduDtls.id}'/>' onclick='return confirm('Are you sure to delete?');' ><spring:message code='delete.link.label'/></a></td>
                    </tr>
                    
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>