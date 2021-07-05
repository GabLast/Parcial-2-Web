package edu.pucmm.eict.Controllers;

import edu.pucmm.eict.Models.Usuario;
import edu.pucmm.eict.Services.UserServices;
import io.javalin.Javalin;
import org.jasypt.util.text.StrongTextEncryptor;

import static io.javalin.apibuilder.ApiBuilder.*;

public class UserController {

    Javalin app;

    public UserController(Javalin app)
    {
        this.app = app;
    }

    public void routes() {

        app.routes(() -> {

            path("/user", () -> {

                get("/", ctx -> {
                    ctx.redirect("/user/login");
                });

                get("/login", ctx -> {
                    ctx.redirect("/Login.html");
                });

                post("/login/auth", ctx -> {
                    String username = ctx.formParam("username");
                    String password = ctx.formParam("password");
                    String remember = ctx.formParam("remember");

                    Usuario user = UserServices.getInstancia().login(username, password);

                    if(user == null)
                    {
                        throw new RuntimeException("Los datos ingresados fueron erróneos");
                    }
                    else {
                        ctx.sessionAttribute("usuario", user);

                        if(remember != null)
                        {
                            if(remember.equals("true"))
                            {
                                String cookie = user.getUsername();
                                StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
                                textEncryptor.setPassword("TH!SisTH3P@SSw0rD");
                                String myEncryptedText = textEncryptor.encrypt(cookie);
                                ctx.cookie("rememberme", myEncryptedText , 604800);
                            }
                        }
                    }
                });

                get("/logout", ctx -> {
                    //invalidando la sesion.
                    if (ctx.cookie("rememberme") != null)
                    {
                        ctx.removeCookie("rememberme");
                    }
                    ctx.req.getSession().invalidate();
                    ctx.redirect("/");
                });

                get("/sign-in", ctx -> {

                });

                post("/sign-in", ctx -> {
                    String username = ctx.formParam("username");
                    String password = ctx.formParam("password");

                    if(UserServices.getInstancia().usernameUnico(username)){
                        if(UserServices.getInstancia().registrarUser(username, password))
                        {
                            ctx.redirect("/user/login");
                        }else{
                            throw new RuntimeException("Hubo un problema al registrar el usuario.");
                        }
                    }else{
                        throw new RuntimeException("El usuario digitado ya está en uso");
                    }
                });
            });
        });
    }
}
