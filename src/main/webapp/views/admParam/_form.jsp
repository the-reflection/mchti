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
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-4'>
        <div class='form-group'>
            <form:label path='slNo'><spring:message code='slNo' text='Sl No'/></form:label>
            <form:input class='form-control' path='slNo' type='number' size='15' maxlength='15'/>
            <form:errors path='slNo' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-4'>
        <div class='form-group'>
            <form:label class='required' path='title'><spring:message code='title' text='Title'/></form:label>
            <form:input class='form-control' path='title' type='text' required='true' size='30' maxlength='30'/>
            <form:errors path='title' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-4'>
        <div class='form-group'>
            <form:label class='required' path='paramName'><spring:message code='paramName' text='Param Name'/></form:label>
            <form:input class='form-control' path='paramName' type='text' required='true' size='30' maxlength='30'/>
            <form:errors path='paramName' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-4'>
        <div class='form-group'>
            <form:label class='required' path='admWidgetType'><spring:message code='admWidgetType' text='Adm Widget Type'/></form:label>
            <form:select class='form-control' path='admWidgetType' name='admWidgetType' id='admWidgetType' required='true' >
                <form:option value='${null}' label='Select One'/>
                <form:option value='TEXT' label='TEXT'/>
                <form:option value='PASSWORD' label='PASSWORD'/>
                <form:option value='NUMBER' label='NUMBER'/>
                <form:option value='DATE' label='DATE'/>
                <form:option value='LIST' label='LIST'/>
                <form:option value='LIST_FIXED' label='LIST FIXED'/>
                <form:option value='HIDE' label='HIDE'/>
                <form:option value='INPUT' label='INPUT'/>
                <form:option value='MODAL' label='MODAL'/>
                <form:option value='UUID' label='UUID'/>
                <form:option value='BUTTON' label='BUTTON'/>
            </form:select>
            <form:errors path='admWidgetType' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-4 col-lg-4'>
        <div class='form-group'>
            <form:label path='cmd'><spring:message code='cmd' text='Cmd'/></form:label>
            <form:input class='form-control' path='cmd' type='text' size='30' maxlength='100'/>
            <form:errors path='cmd' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-6 col-sm-3 col-md-2 col-lg-2'>
        <div class='form-group'>
            <form:label path='isActive'><spring:message code='isActive' text='Is Active'/></form:label>
            <br><form:checkbox class='cb' path='isActive'/>
            <form:errors path='isActive' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-6 col-sm-3 col-md-2 col-lg-2'>
        <div class='form-group'>
            <form:label path='isMandatory'><spring:message code='isMandatory' text='Is Mandatory'/></form:label>
            <br><form:checkbox class='cb' path='isMandatory'/>
            <form:errors path='isMandatory' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'>
        <div class='form-group'>
            <form:label path='defaultVal'><spring:message code='defaultVal' text='Default Val'/></form:label>
            <form:textarea class='form-control' path='defaultVal' type='text' size='30' maxlength='255'/>
            <form:errors path='defaultVal' cssClass='error' element='div'/>
        </div>
    </div>
    <div class='col-xs-12 col-sm-6 col-md-6 col-lg-6'>
        <div class='form-group'>
            <form:label path='helpText'><spring:message code='helpText' text='Help Text'/></form:label>
            <form:textarea class='form-control' path='helpText' type='text' size='30' maxlength='255'/>
            <form:errors path='helpText' cssClass='error' element='div'/>
        </div>
    </div>

</div>   