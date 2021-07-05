package edu.pucmm.eict.Controllers;

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
    static Usuario user = null;

    public UrlController(Javalin app)
    {
        this.app = app;
    }

    public void routes() {

        app.routes(() -> {

            before(ctx -> {
                user = ctx.sessionAttribute("usuario");

                if (ctx.cookie("rememberme") != null && user == null) {
                    StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
                    textEncryptor.setPassword("TH!SisTH3P@SSw0rD"); //this should be put in an enviroment variable?
                    String myDecryptedText = textEncryptor.decrypt(ctx.cookie("rememberme"));
                    user = UserServices.getInstancia().getUserByUsername(myDecryptedText);
                    ctx.sessionAttribute("usuario", user);
                }

            });

            path("/home", () -> {

               get("/", ctx -> {
                   Map<String, Object> freeMarkerVars = new HashMap<>();
               });

                get("/acortarURL", ctx -> {

                    String test = "https://www.amazon.com/ref=nav_logo";
                    Url generated = UrlServices.getInstancia().generateShortURL( test,null);
                    ctx.result(generated.getShortUrl());
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
