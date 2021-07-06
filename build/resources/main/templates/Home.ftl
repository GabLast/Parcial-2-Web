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
                <form method="post" action="/home/acotar">
                    <div class="row">
                        <div class="col-md-2">
                        </div>
                        <div class="col-md-7">
                            <#if url?has_content>
                                <input type="text" class="form-control" id="originalURL" name="originalURL"
                                       value="${url.url}"
                                       placeholder="https://www.google.com.do/" required>
                            <#else>
                                <input type="text" class="form-control" id="originalURL" name="originalURL"
                                       placeholder="https://www.google.com.do/" required>
                            </#if>
                        </div>
                        <div class="col md-1">
                            <button class="btn btn-group-lg btn-dark" type="submit">
                                Acortar
                            </button>
                        </div>
                        <div class="col md-2">
                        </div>
                    </div>
                </form>
                <#if url?has_content>
                    <div class="row">
                        <div class="col-md-8 offset-md-2">
                            <input type="text" class="form-control" value="${url.shortUrl}"
                                   placeholder="https://www.my.url/" required>
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
                                                        <a class="page-link" href="/home/acortar/view_page/${index}">${index}</a>
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
                                    <tr class="table-secondary">
                                        <td>$</td>
                                        <td>$</td>
                                        <form method="POST" action="#">
                                            <td>
                                                <input hidden value="$">
                                                <button class="btn btn-success" type="submit">
                                                    $
                                                </button>
                                            </td>
                                        </form>
                                        <#if usuario?has_content>
                                            <td>
                                                <div class="row justify-content-evenly">
                                                    <div class="col-md-6">
                                                        <a href="">
                                                            <button class="btn btn-sm btn-custom1">
                                                                <span class="material-icons">visibility</span>
                                                            </button>
                                                        </a>
                                                    </div>
                                                    <#if usuario.admin == 1>
                                                        <div class="col-md-6">
                                                            <button class="btn btn-success btn-sm btn-eliminar"
                                                                    type="submit"
                                                                    form="">
                                                                <span class="material-icons">delete</span>
                                                            </button>
                                                        </div>
                                                    <#else>
                                                    </#if>
                                                </div>
                                            </td>
                                        <#else>
                                        </#if>
                                    </tr>
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