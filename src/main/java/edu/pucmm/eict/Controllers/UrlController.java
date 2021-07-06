package edu.pucmm.eict.Controllers;

import edu.pucmm.eict.Helpers.UserAgent;
import edu.pucmm.eict.Models.DetallesURL;
import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Services.DetailsUrlServices;
import edu.pucmm.eict.Services.UrlServices;
import edu.pucmm.eict.Services.UserServices;
import io.javalin.Javalin;
import org.jasypt.util.text.StrongTextEncryptor;


import javax.persistence.EntityManager;
import javax.persistence.Query;
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
                    ctx.redirect("/home/acortar/view_page/1");
                });

                get("/acortar/view_page/:page", ctx -> {
                    Map<String, Object> freeMarkerVars = new HashMap<>();
                    freeMarkerVars.put("title", "Home");
                    int page = ctx.pathParam("page", Integer.class).get();

                    freeMarkerVars.put("usuario", GeneralController.getInstancia().getUser());

                    int pageSize = 10;
                    EntityManager em = UrlServices.getInstancia().getEntityManager();
                    String count = "Select count (p.idURL) from Url p where p.borrado = 0";
                    Query countQuery = em.createQuery(count);
                    Long countResults = (Long) countQuery.getSingleResult();
                    int totalPags = (int) (Math.ceil(((float)countResults / (float)pageSize)));
                    freeMarkerVars.put("paginas", totalPags);

                    if (GeneralController.getInstancia().getLastURLShortened() != null)
                    {
                        freeMarkerVars.put("url", GeneralController.getInstancia().getLastURLShortened());
                    }

                    freeMarkerVars.put("urls", UrlServices.getInstancia().getUrlPaginated(page));

                    ctx.render("/templates/Home.ftl", freeMarkerVars);
                });

                get("/resumen/:id" , ctx -> {
                    Long urlid = ctx.pathParam("id", Long.class).get();
                    Url url = UrlServices.getInstancia().findUrlById(urlid);
                    System.out.println(url);
                    String shortURL = url.getShortUrl();
                    Map<String, Object> attributes = DetallesController.getInstance().getStats(shortURL);
                    attributes.put("title", "Resumen");

                    ctx.render("/templates/SummaryPage.ftl" , attributes);
                });

                post("/acortar", ctx -> {
                    String url = ctx.formParam("originalURL");
                    Url generated;

                    if(GeneralController.getInstancia().getUser() != null)
                    {
                        generated = UrlServices.getInstancia().generateShortURL(url, GeneralController.getInstancia().getUser());
                    }else {
                        generated = UrlServices.getInstancia().generateShortURL(url, null);
                    }

                    GeneralController.getInstancia().setLastURLShortened(generated);

                    ctx.redirect("/home/acortar/view_page/1");
                });

                post("/use-shorturl", ctx -> {
                    long id = ctx.formParam("idurl", Long.class).get();
                     System.out.print(id + "    ");
                    String useragent = ctx.req.getHeader("User-Agent");
                    String ip = ctx.req.getRemoteAddr();

                    Url url = UrlServices.getInstancia().find(id);
                    if(url != null)
                    {
                        DetallesURL nuevo = new DetallesURL(UserAgent.getNavegador(useragent), ip, UserAgent.getSistemaOperativo(useragent), url);
                        DetailsUrlServices.getInstancia().insert(nuevo);
                    }else {
                        System.out.println("Url no existe.");
                        ctx.redirect("/");
                    }

                    ctx.redirect("/");
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

//user agent stuff
//    String aux = ctx.req.getHeader("User-Agent");
//    String temp = UserAgent.getNavegador(aux);
//    String ip = ctx.req.getRemoteAddr();