<%@ page contentType='text/html; charset=UTF-8' language='java' %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>

<style>
    .error {
        color: #ff0000;
    }
    .errorblock {
        color: #000;
        background-color: #ffEEEE;
        border: 3px solid #ff0000;
        padding: 8px;
        margin: 16px;
    }
</style>

<script type='text/javascript'>

</script>

<form:errors path='*' cssClass='errorblock' element='div' />
<form:hidden path='id'/>
<div>  
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='configAttribute'><spring:message code='configAttribute' text='Config Attribute'/></form:label>
            <form:textarea class='form-control' path='configAttribute' type='text' size='30' maxlength='255'/>
            <form:errors path='configAttribute' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='httpMethod'><spring:message code='httpMethod' text='Http Method'/></form:label>
            <form:select class='form-control' path='httpMethod' name='httpMethod' id='httpMethod' >
                <form:option value='GET' label='GET'/>
                <form:option value='HEAD' label='HEAD'/>
                <form:option value='POST' label='POST'/>
                <form:option value='PUT' label='PUT'/>
                <form:option value='PATCH' label='PATCH'/>
                <form:option value='DELETE' label='DELETE'/>
                <form:option value='OPTIONS' label='OPTIONS'/>
                <form:option value='TRACE' label='TRACE'/>
            </form:select>
            <form:errors path='httpMethod' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='url'><spring:message code='url' text='Url'/></form:label>
            <form:textarea class='form-control' path='url' type='text' size='30' maxlength='255'/>
            <form:errors path='url' cssClass='error' element='div'/>
        </div>
    </div>

</div>   