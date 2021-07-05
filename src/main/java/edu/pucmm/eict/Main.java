package edu.pucmm.eict;

import edu.pucmm.eict.Controllers.GeneralController;
import edu.pucmm.eict.Controllers.UrlController;
import edu.pucmm.eict.Controllers.UserController;
import edu.pucmm.eict.Database.DBConfig;
import edu.pucmm.eict.Database.DBConnection;
import edu.pucmm.eict.Services.UserServices;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinFreemarker;
import org.jasypt.util.text.StrongTextEncryptor;

import static io.javalin.apibuilder.ApiBuilder.before;

public class Main {

    public static void main(String[] args) {

        //******************************************************//
        //Base de datos
        DBConfig.startDb();
        //Prueba de Conexión.
        DBConnection.getInstancia().testConexion();
        //******************************************************//

        if (UserServices.getInstancia().findAll().isEmpty()) {
            UserServices.init();
        }

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("rutas"));
            config.enableCorsForAllOrigins();

        }).start(7000);


        app.get("/", ctx -> {
           ctx.redirect("/home");
        });

        app.error(404, ctx -> {
            ctx.redirect("/404.html");
        });

        new UserController(app).routes();
        new UrlController(app).routes();
    }

}
