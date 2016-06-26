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
            <form:label class='required' path='displayName'><spring:message code='displayName' text='Display Name'/></form:label>
            <form:input class='form-control' path='displayName' type='text' required='true' size='30' maxlength='30'/>
            <form:errors path='displayName' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='tooltip'><spring:message code='tooltip' text='Tooltip'/></form:label>
            <form:input class='form-control' path='tooltip' type='text' size='30' maxlength='30'/>
            <form:errors path='tooltip' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='description'><spring:message code='description' text='Description'/></form:label>
            <form:input class='form-control' path='description' type='text' size='30' maxlength='100'/>
            <form:errors path='description' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='menuType'><spring:message code='menuType' text='Menu Type'/></form:label>
            <form:input class='form-control' path='menuType' type='text' size='30' maxlength='100'/>
            <form:errors path='menuType' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='isExternal'><spring:message code='isExternal' text='Is External'/></form:label>
            <br><form:checkbox class='cb' path='isExternal'/>
            <form:errors path='isExternal' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='isOpenNewTab'><spring:message code='isOpenNewTab' text='Is Open New Tab'/></form:label>
            <br><form:checkbox class='cb' path='isOpenNewTab'/>
            <form:errors path='isOpenNewTab' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='slNo'><spring:message code='slNo' text='Sl No'/></form:label>
            <form:input class='form-control' path='slNo' type='number' size='15' maxlength='15'/>
            <form:errors path='slNo' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='parentAuthMenu'><spring:message code='parentAuthMenu' text='Parent Auth Menu'/></form:label>
            <form:select class='form-control' path='parentAuthMenu.id' name='parentAuthMenu' id='parentAuthMenu' >
                <form:option value='${null}' label='--- Select ---'/>
                <form:options items='${parentAuthMenus}' itemValue='id'/>
            </form:select>
            <form:errors path='parentAuthMenu' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label class='required' path='urlPath'><spring:message code='urlPath' text='Url Path'/></form:label>
            <form:textarea class='form-control' path='urlPath' type='text' required='true' size='30' maxlength='255'/>
            <form:errors path='urlPath' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='displayIconClass'><spring:message code='displayIconClass' text='Display Icon Class'/></form:label>
            <form:textarea class='form-control' path='displayIconClass' type='text' size='30' maxlength='128'/>
            <form:errors path='displayIconClass' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-3'>
        <div class='form-group'>
            <form:label path='isActive'><spring:message code='isActive' text='Is Active'/></form:label>
            <br><form:checkbox class='cb' path='isActive'/>
            <form:errors path='isActive' cssClass='error' element='div'/>
        </div>
    </div>

</div>   