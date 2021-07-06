<#include "base.ftl">
<#macro page_head>
    <#if title?has_content>
        <title>${title}</title>
    </#if>
</#macro>

<#macro page_body>
<body>
<div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="modal-content bg-dark text-light">
                    <div class="modal-header">
                        <h5 class="modal-title">Registro de un nuevo usuario</h5>
                    </div>
                    <div class="modal-body">
                        <form id="addform" method="post" action="">
                            <div class="row mb-3 form-group">
                                <label for="username" class="col-sm-2 col-form-label">Usuario:</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="username" name="username">
                                </div>
                            </div>
                            <div class="row mb-3 form-group">
                                <label for="password" class="col-sm-2 col-form-label">Contrase&ntilde;a:</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" id="password" name="password">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" form="addform" class="btn btn-primary btn-dark">Registrar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</#macro>
<@display_page/>