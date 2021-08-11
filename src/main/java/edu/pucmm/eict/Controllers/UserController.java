package edu.pucmm.eict.Controllers;

import edu.pucmm.eict.Models.Usuario;
import edu.pucmm.eict.Services.UserServices;
import io.javalin.Javalin;
import org.jasypt.util.text.StrongTextEncryptor;

import java.util.HashMap;
import java.util.Map;

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
                        System.out.println("El usuario o la contraseña son erróneos");
                        ctx.redirect("/user/login");
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

                        if(GeneralController.getInstancia().getRequestedURL().equalsIgnoreCase(""))
                        {
                            ctx.redirect("/");
                        }
                        else {
                            String aux = GeneralController.getInstancia().getRequestedURL();
                            GeneralController.getInstancia().setRequestedURL("");
                            ctx.redirect(aux);
                        }
                    }
                });

                get("/sign-in", ctx -> {
                    Map<String, Object> freeMarkerVars = new HashMap<>();
                    freeMarkerVars.put("title", "Sign-Up");
                    ctx.render("/templates/RegistrarUsuario.ftl", freeMarkerVars);
                });

                post("/sign-in", ctx -> {
                    String username = ctx.formParam("username");
                    String password = ctx.formParam("password");

                    if(UserServices.getInstancia().usernameUnico(username)){
                        if(UserServices.getInstancia().registrarUser(username, password))
                        {
                            ctx.redirect("/user/login");
                        }else{
                            System.out.println("Hubo un problema al registrar el usuario.");
                            ctx.redirect("/user/sign-in");
                        }
                    }else{
                        System.out.println("El usuario digitado ya está en uso");
                        ctx.redirect("/user/sign-in");
                    }
                });
            });

            path("/logout", () -> {

                get("/", ctx -> {
                    if (ctx.cookie("rememberme") != null)
                    {

                        ctx.removeCookie("rememberme");
//                        GeneralController.getInstancia().setUser(null);
                    }
                    ctx.req.getSession().invalidate();
                    ctx.redirect("/");
                });
            });
        });
    }
}
