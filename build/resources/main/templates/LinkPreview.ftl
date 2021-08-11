<#include "base.ftl">
<#macro page_head>
    <head>
        <#if title?has_content>
            <title>${title}</title>
        </#if>

    </head>
</#macro>
<#macro page_body>
    <body>

        <<div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="modal-content bg-dark text-light">
                    <div class="modal-header">
                        <h5 class="modal-title">Preview del Link</h5>
                    </div>
                    <div class="modal-body">
                        <form method="post" id="acortarurlform" action="/home/acortar">
                            <div class="row mb-3 form-group">
                                <img src="${Imagen}" alt="Aqui va la imagen del URL" style="height:300px;width:500px;align-content: center">
                            </div>
                            <div class="row mb-3 form-group">
                                <input type="text" id="originalURL" name="originalURL" class="form-control" value="${originalURL}" placeholder="${originalURL}" readonly>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" form="acortarurlform" class="btn btn-primary btn-dark">Acortar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</#macro>
<@display_page/>

