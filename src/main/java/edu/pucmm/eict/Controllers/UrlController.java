package edu.pucmm.eict.Controllers;

import edu.pucmm.eict.Helpers.UserAgent;
import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Models.Usuario;
import edu.pucmm.eict.Services.UrlServices;
import edu.pucmm.eict.Services.UserServices;
import io.javalin.Javalin;
import org.jasypt.util.text.StrongTextEncryptor;


import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;

public class UrlController {

    Javalin app;

    public UrlController(Javalin app) {
        this.app = app;
    }

    public void routes() {

        app.routes(() -> {

            before(ctx -> {
                GeneralController.getInstancia().setUser(ctx.sessionAttribute("usuario"));

                if (ctx.cookie("rememberme") != null && GeneralController.getInstancia().getUser() == null) {
                    StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
                    textEncryptor.setPassword("TH!SisTH3P@SSw0rD"); //this should be put in an enviroment variable?
                    String myDecryptedText = textEncryptor.decrypt(ctx.cookie("rememberme"));
                    GeneralController.getInstancia().setUser(UserServices.getInstancia().getUserByUsername(myDecryptedText));
                    ctx.sessionAttribute("usuario", GeneralController.getInstancia().getUser());
                }

            });

            path("/home", () -> {


                get("/", ctx -> {
                    ctx.redirect("/home/acortar");
                });

                get("/acortar", ctx -> {
                    Map<String, Object> freeMarkerVars = new HashMap<>();
                    freeMarkerVars.put("usuario", GeneralController.getInstancia().getUser());
                    ctx.render("/templates/Home.ftl", freeMarkerVars);
                });

                post("/acortar", ctx -> {

                    String test = "https://www.programmableweb.com/category/url-shortener/libraries";
                    Url generated = UrlServices.getInstancia().generateShortURL(test, null);
                    String aux = ctx.req.getHeader("User-Agent");
                    String temp = UserAgent.getNavegador(aux);
                    String ip = ctx.req.getRemoteAddr();
                    ctx.result(generated.getShortUrl() + "\n\n" + aux + "\n\n" + temp + "\n\n" + UserAgent.getSistemaOperativo(aux) + "\n\n" + ip);
                });

            });

            path("/dsadasdsad", () -> {

                get("/", ctx -> {
                    Map<String, Object> freeMarkerVars = new HashMap<>();

                });
            });
        });
    }
}
