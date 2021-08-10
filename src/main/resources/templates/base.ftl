<#macro page_head>
    <title>Parcial-2</title>
</#macro>

<#macro page_body>
</#macro>

<#macro display_page>
    <!DOCTYPE html>
    <html lang="en" >
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
        <link href="../public/css/custom.css" rel="stylesheet">
        <@page_head/>

    </head>
    <body>
    <style>
        html, body {
            background-image: url("https://img.wallpapersafari.com/desktop/1920/1080/19/78/OSjfwn.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;
        }
        .btn-eliminar {
            background-color: darkred !important;
            color: whitesmoke !important;
        }

        .btn-custom1 {
            background-color: dodgerblue !important;
            color: whitesmoke !important;
        }
    </style>
    <#--body here-->
    <#if usuario?has_content>
        <#if usuario.admin == 1>
            <#include "adminNavBar.ftl">
        <#else>
            <#include "normalNavBar.ftl">
        </#if>
    <#else>
        <#include "normalNavBar.ftl">
    </#if>
    <br><br><br>
    <@page_body/>
    <#--scripts-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    </body>
    </body>
    </html>
</#macro>