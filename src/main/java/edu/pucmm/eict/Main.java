package edu.pucmm.eict;


import edu.pucmm.eict.Controllers.GeneralController;
import edu.pucmm.eict.Controllers.UrlController;
import edu.pucmm.eict.Controllers.UserController;
import edu.pucmm.eict.Database.DBConfig;
import edu.pucmm.eict.Database.DBConnection;
import edu.pucmm.eict.Helpers.ServiciosRetorno;
import edu.pucmm.eict.WebServices.RestAPIController;
import edu.pucmm.eict.WebServices.SOAPController;
import io.javalin.plugin.openapi.OpenApiOptions;
import io.javalin.plugin.openapi.OpenApiPlugin;
import io.javalin.plugin.openapi.ui.SwaggerOptions;
import io.swagger.v3.oas.models.info.Info;
import edu.pucmm.eict.Services.UserServices;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class Main {

    private static String modoConexion = "";

    public static void main(String[] args) {

        if(args.length >= 1){
            modoConexion = args[0];
            System.out.println("Modo de Operacion: "+modoConexion);
        }

        if(modoConexion.isEmpty()) {
            //******************************************************//
            //Base de datos
            DBConfig.startDb();
            //Prueba de Conexión.
            DBConnection.getInstancia().testConexion();
            GeneralController.getInstancia().setCloudlink("http://localhost:7000/");
            //******************************************************//
        }else {
            GeneralController.getInstancia().setCloudlink("");
        }

        if (UserServices.getInstancia().findAll().isEmpty()) {
            UserServices.getInstancia().init();
        }

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("rutas"));
            config.enableCorsForAllOrigins();
            config.registerPlugin(new OpenApiPlugin(getOpenApiOptions()));

        });

        new SOAPController(app).routes();

        app.start(getHerokuAssignedPort());

        new UrlController(app).routes();
        new UserController(app).routes();
        new RestAPIController(app).routes();

        app.get("/", ctx -> {
            ctx.redirect("/home");
        });

        app.error(404, ctx -> {
            ctx.redirect("/404.html");
        });

        //Enviando la información a solicitud del CORS
        app.options("/*", ctx -> {
            System.out.println("Entrando al metodo de options");
            String accessControlRequestHeaders = ctx.req.getHeader("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                ctx.res.setHeader("Access-Control-Allow-Headers",accessControlRequestHeaders);
            }

            String accessControlRequestMethod = ctx.req.getHeader("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                ctx.res.setHeader("Access-Control-Allow-Methods",accessControlRequestMethod);
            }
        });

        //Filtro para validar el CORS
        app.before(ctx -> {
            ctx.res.setHeader("Access-Control-Allow-Origin",  "*");
            //response.type("application/json");
        });

    }


    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000; //Retorna el puerto por defecto en caso de no estar en Heroku.
    }

    public static String getModoConexion(){
        return modoConexion;
    }

    private static OpenApiOptions getOpenApiOptions() {
        Info applicationInfo = new Info()
                .version("1.0")
                .description("My Application");
        return new OpenApiOptions(applicationInfo).path("/openapi").swagger(new SwaggerOptions("/openapi-ui"));
    }

}
