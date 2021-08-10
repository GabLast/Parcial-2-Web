<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Inicio</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" style="margin-top: 5px;" id="navbarNav">
            <#if usuario?has_content>
            <span class="navbar-text">Bienvenido, ${usuario.username}</span>
            </#if>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        Final Ac&aacute;pites
                    </a>
                    <ul class="dropdown-menu bg-light" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/SinConexion-ShortURL.html">URLs Acortadas Sin Conexi&oacute;n</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        Administraci&oacute;n
                    </a>
                    <ul class="dropdown-menu bg-light" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/administracion/listar-usuarios/view_page/1">Listar Usuarios</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Cerrar sesi&oacute;n</a>
                </li>
            </ul>
        </div>
    </div>
</nav>