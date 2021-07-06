<#include "base.ftl">
<#macro page_head>
    <#if title?has_content>
        <title>${title}</title>
    </#if>
</#macro>

<#macro page_body>
    <body>
    <div class="row">
        <div class="col-md-6 offset-md-1">
            <h2 class="text-light">Usted no tiene permisos para acceder a este recurso
            </h2>
            <br>
            <a href="/">
                <button class="btn btn-go-home btn-custom1">Volver al Inicio</button>
            </a>
        </div>
    </div>
    </body>
</#macro>
<@display_page/>