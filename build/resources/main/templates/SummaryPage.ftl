<#include "base.ftl">
<#macro page_head>
    <head>
        <#if title?has_content>
            <title>${title}</title>
        </#if>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {

                var data = google.visualization.arrayToDataTable([
                    ['Navegador', 'Visitas'],
                    ['Chrome', ${chromeVisits}],
                    ['Firefox', ${firefoxVisits}],
                    ['Opera', ${operaVisits}],
                    ['Edge', ${edgeVisits}],
                    ['Safari', ${safariVisits}]
                ]);

                var options = {
                    title: 'Visitas por navegador',
                    width: 650,
                    height: 450,
                    pieHole: 0.4,
                    legend: {position: "none"},
                };

                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                chart.draw(data, options);
            }
        </script>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Sistema Operativo', 'Visitas'],
                    ['Windows 10', ${windows10}],
                    ['Windows 7', ${windows7}],
                    ['Ubuntu 16.04', ${ubuntu1604}],
                    ['Ubuntu 18.04', ${ubuntu1804}],
                    ['Android 8', ${android8}],
                    ['Android 9', ${android9}]
                ]);
                var options = {
                    title: 'Visitas por Sistemas Operativos',
                    width: 650,
                    height: 450,
                    pieHole: 0.4,

                };
                var chart = new google.visualization.PieChart(document.getElementById('piechart2'));
                chart.draw(data, options);
            }
        </script>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Fechas', 'Visitas'],
                    ['Julio', ${Date}],

                ]);
                var options = {
                    title: 'Visitas por Fecha',
                    width: 650,
                    height: 450,
                };
                var chart = new google.visualization.BarChart(document.getElementById('barchart'));
                chart.draw(data, options);
            }
        </script>
    </head>
</#macro>
<#macro page_body>
    <body>
    <#if usuario?has_content>
        <div class="header m-5 text-light">
            <h2>Resumen de Url: ${UrlShort} </h2>
        </div>
        <div class="container-fluid">
            <div class="row row-cols-2">
             <!--   <div class="col-sm">
                    <div id="piechart"
                         style="width: 900px; height: 500px; display: flex; text-align: center ; justify-content: center"></div>
                </div> -->
               <!-- <div class="col-sm">
                    <div id="piechart2"
                         style="width: 900px; height: 500px; display: flex; text-align: center ; justify-content: center"></div>
                </div>-->
                <div class="col-sm">
                    <div id="barchart"
                         style="width: 900px; height: 500px; display: flex; text-align: center ; justify-content: center"></div>
                </div>
                    <div class="col-lg">
                        <div id="qrcode${URL.url?substring(12)}"></div>
                        <img alt="qrcode" src="https://quickchart.io/qr?text=https://localhost:7000/home/view-url/${URL.idURL}" />
                        <p class="text-light">Ver mas</p>
                    </div>
            </div>
        </div>
    <#else>
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
    </#if>
    </body>
</#macro>
<@display_page/>