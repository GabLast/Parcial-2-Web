<#include "base.ftl">
<#macro page_head>
    <#if title?has_content>
        <title>${title}</title>
    </#if>
</#macro>

<#macro page_body>
<body>
<div class="row">
    <div class="col-md-4 offset-md-1">
        <h2>Usted no tiene permisos para acceder a este recurso<h2>
    </div>
</div>
</body>
</#macro>
<@display_page/>