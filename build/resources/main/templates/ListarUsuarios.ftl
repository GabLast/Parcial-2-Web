<#include "base.ftl">
<#macro page_head>
    <#if title?has_content>
        <title>${title}</title>
    </#if>
</#macro>

<#macro page_body>
    <body>
    <div class="container-fluid">
        <div class="row">
            <div class="container-fluid">
                <div class="row">
                    <div class="col md-1">
                    </div>
                    <div class="col-md-9 me-auto">
                        <#if usuarios?has_content>
                            <div class="table-responsive" style="height: 600px">
                                <nav aria-label="Page navigation">
                                    <ul class="pagination justify-content-end">
                                        <#if paginas?has_content>
                                            <#if paginas < 1>

                                            <#else>
                                                <#list 1..paginas as index>
                                                    <li class="page-item">
                                                        <a class="page-link"
                                                           href="/administracion/listar-usuarios/view_page/${index}">${index}</a>
                                                    </li>
                                                </#list>
                                            </#if>
                                        </#if>
                                    </ul>
                                </nav>
                                <table class="table table-striped table-hover">
                                    <thead class="table-dark text-center">
                                    <tr>
                                        <th scope="col">User ID</th>
                                        <th scope="col">Usuario</th>
<#--                                        <th scope="col">Contrase&ntilde;a</th>-->
                                        <th scope="col">Nivel de acceso</th>
                                        <th scope="col"></th>
                                    </tr>
                                    </thead>
                                    <tbody class="text-center table-bordered">
                                    <#list usuarios as u>
                                        <tr class="table-secondary">
                                            <td>${u.idUser}</td>
                                            <td>${u.username}</td>
<#--                                            <td>${u.password}</td>-->
                                            <#if u.admin==1>
                                                <td>Administrador</td>
                                            <#else>
                                                <td>Cliente</td>
                                            </#if>
                                            <td>
                                                <div class="row justify-content-evenly">
                                                    <div class="col-md-6">
                                                        <form id="giveadmin${u.idUser}" method="post" action="/administracion/user/give-admin">
                                                            <input hidden value="${u.idUser}" name="iduser">
                                                            <button class="btn btn-sm btn-custom1"
                                                                    type="submit" form="giveadmin${u.idUser}">
                                                                <span class="material-icons">admin_panel_settings</span>
                                                            </button>
                                                        </form>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <form id="delete${u.idUser}" method="post" action="/administracion/user/delete">
                                                            <input hidden value="${u.idUser}" name="iduser">
                                                            <button class="btn btn-success btn-sm btn-eliminar"
                                                                    type="submit" form="delete${u.idUser}">
                                                                <span class="material-icons">delete</span>
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </table>
                            </div>
                        <#else>
                            <div class="row justify-content-evenly">
                                <div class="col-md-4 offset-md-1">
                                    <h4>No hay usuarios registrados</h4>
                                </div>
                            </div>
                        </#if>
                    </div>
                    <div class="col md-1">
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</#macro>
<@display_page/>