<#include "base.ftl">
<#macro page_head>
    <#if title?has_content>
        <title>${title}</title>
    </#if>
    <script>
        //dependiendo el navegador busco la referencia del objeto.
        var indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB

        //indicamos el nombre y la versión
        var dataBase = indexedDB.open("urlshortener", 1);


        //se ejecuta la primera vez que se crea la estructura
        //o se cambia la versión de la base de datos.
        dataBase.onupgradeneeded = function (e) {
            console.log("Creando la estructura de la tabla");

            //obteniendo la conexión activa
            active = dataBase.result;

            //creando la colección:
            //En este caso, la colección, tendrá un ID autogenerado.
            var urls = active.createObjectStore("urls", {keyPath: 'id', autoIncrement: true});

            //creando los indices. (Dado por el nombre, campo y opciones)
            urls.createIndex('por_id', 'id', {unique: true});
        };

        //El evento que se dispara una vez, lo
        dataBase.onsuccess = function (e) {
            console.log('IDB Success');
        };

        dataBase.onerror = function (e) {
            console.error('Error en el proceso: ' + e.target.errorCode);
        };


        function addURL() {
            var dbActiva = dataBase.result; //Nos retorna una referencia al IDBDatabase.

            //Para realizar una operación de agregar, actualización o borrar.
            // Es necesario abrir una transacción e indicar un modo: readonly, readwrite y versionchange
            var transaccion = dbActiva.transaction(["urls"], "readwrite");

            //Manejando los errores.
            transaccion.onerror = function (e) {
                alert(request.error.name + '\n\n' + request.error.message);
            };
            transaccion.oncomplete = function (e) {
                // alert('URL agregado correctamente');
            };

            //abriendo la colección de datos que estaremos usando.
            var urls = transaccion.objectStore("urls");


            var index = urls.index("por_id");
            var openCursorRequest = index.openCursor(null, 'prev');

            openCursorRequest.onsuccess = function (event) {

                //cuando ya hay datos
                if (event.target.result) {
                    //https://stackoverflow.com/questions/21909932/indexeddb-retrieve-item-with-max-value

                    lastinsert = event.target.result.value; //the object with max revision
                    // console.log(lastinsert)

                    if (lastinsert.shorturl !== document.querySelector("#url-short").value) {
                        //Para agregar se puede usar add o put, el add requiere que no exista
                        // el objeto.
                        var request = urls.put({
                            originalURL: document.querySelector("#og-url").value,
                            shorturl: document.querySelector("#url-short").value
                        });

                        request.onerror = function (e) {
                            var mensaje = "Error: " + e.target.errorCode;
                            console.error(mensaje);
                            alert(mensaje)
                        };

                        request.onsuccess = function (e) {
                            console.log("Datos Procesado con exito");
                        };
                    }
                }else {
                    //para que funcione en caso de que no haya datos
                    var request = urls.put({
                        originalURL: document.querySelector("#og-url").value,
                        shorturl: document.querySelector("#url-short").value
                    });

                    request.onerror = function (e) {
                        var mensaje = "Error: " + e.target.errorCode;
                        console.error(mensaje);
                        alert(mensaje)
                    };

                    request.onsuccess = function (e) {
                        console.log("Datos Procesado con exito");
                    };
                }
            };

        }

    </script>
</#macro>

<#macro page_body>
<#if urlshort?has_content>
<body onload="addURL()">
<#else>
<body>
</#if>
<div class="container-fluid">
    <div class="row">
        <div class="container-fluid">
            <form method="post" id="acortarurlform" action="/home/acortar/preview">
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
                        <input id="og-url" value="${ogurl}" hidden>
                        <input id="url-short" type="text" class="form-control"
                               value="${cloudlink}use/${urlshort}"
                               placeholder="https://www.my.url/" readonly>
                        <br>
                        <form method="get" action="/use/${urlshort}">
                            <button class="btn btn-success" type="submit">
                                Visitar enlace
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
                                            <form method="get" action="/use/${u.shortUrl}">
                                                <td>
                                                    <button class="btn btn-success" type="submit">
                                                        ${cloudlink}use/${u.shortUrl}
                                                    </button>
                                                </td>
                                            </form>
                                            <#if usuario?has_content>
                                                <td>
                                                    <div class="row justify-content-evenly">
                                                        <div class="col-md-6">
                                                            <form method="GET"
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