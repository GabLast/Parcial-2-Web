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
            <#else>
                <span class="navbar-text">Bienvenido</span>
            </#if>
            <ul class="navbar-nav ms-auto">
                <#if usuario?has_content>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Cerrar sesi&oacute;n</a>
                    </li>
                <#else>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/sign-in">Registrarse</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/login">Iniciar sesi&oacute;n</a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</nav>