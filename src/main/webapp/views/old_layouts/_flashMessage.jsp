<!-- flash message -->
<div class="flashMessage">
    <g:if test="${flash.success}">
        <div class="alert alert-success alert-dismissable success">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="icon fa fa-check"></i>
            ${flash.success}
        </div>
    </g:if>
    <g:elseif test="${flash.message}">
        <div class="alert alert-info alert-dismissable message">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="icon fa fa-info"></i>
            ${flash.message}
        </div>
    </g:elseif>
    <g:elseif test="${flash.warning}">
        <div class="alert alert-warning alert-dismissable warning">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="icon fa fa-remove"></i>
            ${flash.warning}
        </div>
    </g:elseif>
    <g:elseif test="${flash.error}">
        <div class="alert alert-danger alert-dismissable error">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="icon fa fa-remove"></i>
            ${flash.error}
        </div>
    </g:elseif>
</div>
<!-- flash message end -->