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
                <form method="post" id="acortarurlform" action="/home/acortar">
                    <div class="row">
                        <div class="col-md-2">
                        </div>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="originalURL" name="originalURL"
                                   placeholder="https://www.google.com.do/" required>
                        </div>
                        <div class="col md-1">
                            <button class="btn btn-group-lg btn-custom1" type="submit" form="acortarurlform">
                                Acortar
                            </button>
                        </div>
                        <div class="col md-2">
                        </div>
                    </div>
                </form>
                <br><br>
                <#if urlshort?has_content>
                    <div class="row">
                        <div class="col-md-8 offset-md-2">
                            <label for="copyurl" class="col-form-label text-light">Enlace:</label>
                            <input id="copyurl" type="text" class="form-control" value="https://apptest.projects-domain.me/use/${urlshort}"
                                       placeholder="https://www.my.url/" readonly>
                            <br>
                            <form method="get" action="/use/${urlshort}">
                                <button class="btn btn-success" type="submit">
                                    https://apptest.projects-domain.me/use/${urlshort}
                                </button>
                            </form>
                        </div>
                    </div>
                <#else>

                </#if>
            </div>
        </div>

        <div class="row">
            <br>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-1">
                        </div>
                        <div class="col-md-10 me-auto">
                            <#if urls?has_content>
                                <div class="table-responsive" style="height: 600px">
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination justify-content-end">
                                            <#if paginas?has_content>
                                                <#if paginas < 1>

                                                <#else>
                                                    <#list 1..paginas as index>
                                                        <li class="page-item">
                                                            <a class="page-link"
                                                               href="/home/acortar/view_page/${index}">${index}</a>
                                                        </li>
                                                    </#list>
                                                </#if>
                                            </#if>
                                        </ul>
                                    </nav>
                                    <table class="table table-striped table-hover">
                                        <thead class="table-dark text-center">
                                        <tr>
                                            <th scope="col">URL ID</th>
                                            <th scope="col">URL Original</th>
                                            <th scope="col">URL Generada</th>
                                            <#if usuario?has_content>
                                                <th scope="col"></th>
                                            <#else>

                                            </#if>
                                        </tr>
                                        </thead>
                                        <tbody class="text-center table-bordered">
                                        <#list urls as u>
                                            <tr class="table-secondary">
                                                <td>${u.idURL}</td>
                                                <td>${u.url}</td>
                                                <form id="shorturluse" method="get" action="/use/${u.shortUrl}">
                                                    <td>
                                                        <button class="btn btn-success" type="submit">
                                                            https://apptest.projects-domain.me/use/${u.shortUrl}
                                                        </button>
                                                    </td>
                                                </form>
                                                <#if usuario?has_content>
                                                    <td>
                                                        <div class="row justify-content-evenly">
                                                            <div class="col-md-6">
                                                                <form id="resumenURL" method="GET"
                                                                      action="/home/view-url/${u.idURL}">
                                                                    <button class="btn btn-sm btn-custom1"
                                                                            type="submit">
                                                                        <span class="material-icons">visibility</span>
                                                                    </button>

                                                                </form>
                                                            </div>
                                                            <#if usuario.admin == 1>
                                                                <div class="col-md-6">
                                                                    <form method="post"
                                                                          action="/home/delete/${u.idURL}">
                                                                        <button class="btn btn-success btn-sm btn-eliminar"
                                                                                type="submit">
                                                                            <span class="material-icons">delete</span>
                                                                        </button>
                                                                    </form>
                                                                </div>
                                                            </#if>
                                                    </td>
                                                </#if>
                                            </tr>
                                        </#list>
                                        </tbody>
                                    </table>
                                </div>
                            <#else>

                            </#if>
                        </div>
                        <div class="col md-1">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</#macro>
<@display_page/>