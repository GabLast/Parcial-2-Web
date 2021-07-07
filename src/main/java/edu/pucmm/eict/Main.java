package edu.pucmm.eict;


import edu.pucmm.eict.Controllers.GeneralController;
import edu.pucmm.eict.Controllers.UrlController;
import edu.pucmm.eict.Controllers.UserController;
import edu.pucmm.eict.Database.DBConfig;
import edu.pucmm.eict.Database.DBConnection;
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
            GeneralController.getInstancia().setCloudlink("https://apptest.projects-domain.me/");
            //******************************************************//
        }else {
            GeneralController.getInstancia().setCloudlink("");
        }

        if (UserServices.getInstancia().findAll().isEmpty()) {
            UserServices.init();
        }

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("rutas"));
            config.enableCorsForAllOrigins();

        }).start(getHerokuAssignedPort());

        new UrlController(app).routes();
        new UserController(app).routes();

        app.get("/", ctx -> {
            ctx.redirect("/home");
        });

        app.error(404, ctx -> {
            ctx.redirect("/404.html");
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

}
