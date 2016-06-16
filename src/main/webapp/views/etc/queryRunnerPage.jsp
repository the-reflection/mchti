<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate"/>

<tiles:putAttribute name="header">
    <%--<jsp:include page="../layouts/header.jsp"/>--%>
</tiles:putAttribute>

<tiles:putAttribute name="menu">
    <%--<jsp:include page="../layouts/menu.jsp"/>--%>
</tiles:putAttribute>

<tiles:putAttribute name="body">

    <title><spring:message code="queryRunnerPage.title"  text="Query Runner Page"/></title>


    <script>
        function execNativeQuery(){
        
            var elem = document.getElementById("query");
            var query = elem.value

            $.ajax({
                type : "POST",
                url: '${pageContext.request.contextPath}/queryRunner/execNativeQuery',
                data: ({query : query}), 
                success: function(d) {
                    //  alert("ok:"+d.id)
                    document.getElementById('datatab').innerHTML = d
                },
                error: function(err) {
                    //alert("err mac:"+err)
                    document.getElementById('datatab').innerHTML = err
                }
            });
        } 
        function execOrmQuery(){
        
            var elem = document.getElementById("query");
            var query = elem.value

            $.ajax({
                type : "POST",
                url: '${pageContext.request.contextPath}/queryRunner/execOrmQuery',
                data: ({query : query}), 
                success: function(d) {
                    //  alert("ok:"+d.id)
                    document.getElementById('datatab').innerHTML = d
                },
                error: function(err) {
                    // alert("query"+err+">>>")
                    //alert("err mac:"+err)
                    document.getElementById('datatab').innerHTML = err
                }
            });
        } 
    </script>

    <h1>Query Runner</h1>
    <table>
        <tr>
            <td>
                Query:
            </td>
            <td>   
                <textarea id="query" rows="4" cols="50">SELECT m.code, m.fullName FROM Emp m</textarea>
            </td>
            <td>
                <button type="button" onclick="execOrmQuery()">Execute ORM</button>
                <br>
                <button type="button" onclick="execNativeQuery()">Execute Native</button>
            </td>
        </tr>

    </table>

    <div style="height: 400px; width: 995px; margin: auto; overflow-x: auto;">
        <p id="datatab"></p>
    </div>

</tiles:putAttribute>


<tiles:putAttribute name="footer">
    <%--<jsp:include page="../layouts/footer.jsp"/>--%>
</tiles:putAttribute>