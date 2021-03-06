package edu.pucmm.eict.Controllers;

import edu.pucmm.eict.Helpers.UserAgent;
import edu.pucmm.eict.Models.DetallesURL;
import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Models.Usuario;
import edu.pucmm.eict.Services.DetailsUrlServices;
import edu.pucmm.eict.Services.UrlServices;
import edu.pucmm.eict.Services.UserServices;
import io.javalin.Javalin;
import kong.unirest.JsonNode;
import org.jasypt.util.text.StrongTextEncryptor;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONObject;

import static io.javalin.apibuilder.ApiBuilder.*;

public class UrlController {

    Javalin app;

    public UrlController(Javalin app) {
        this.app = app;
    }

    public void routes() {

        app.routes(() -> {

            before(ctx -> {
//                GeneralController.getInstancia().setUser(ctx.sessionAttribute("usuario"));

                if (ctx.cookie("rememberme") != null /*&& GeneralController.getInstancia().getUser() == null*/) {
                    StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
                    textEncryptor.setPassword("TH!SisTH3P@SSw0rD"); //this should be put in an enviroment variable?
                    String myDecryptedText = textEncryptor.decrypt(ctx.cookie("rememberme"));
//                    GeneralController.getInstancia().setUser(UserServices.getInstancia().getUserByUsername(myDecryptedText));
//                    ctx.sessionAttribute("usuario", GeneralController.getInstancia().getUser());
                    ctx.sessionAttribute("usuario", UserServices.getInstancia().getUserByUsername(myDecryptedText));
                }

            });

            path("/use", () -> {

                get("/:shorturl", ctx -> {
                    String shorturl = ctx.pathParam("shorturl");
//                    System.out.println("\n\nTEST: " + shorturl);
                    String useragent = ctx.req.getHeader("User-Agent");
                    String ip = ctx.req.getRemoteAddr();

                    Url url = UrlServices.getInstancia().getUrlByShortURL(shorturl);
                    if (url != null) {
                        DetallesURL nuevo = new DetallesURL(UserAgent.getNavegador(useragent), ip, UserAgent.getSistemaOperativo(useragent), url);
                        DetailsUrlServices.getInstancia().insert(nuevo);
                    } else {
                        System.out.println("Url no existe.");
                        ctx.redirect("/404.html");
                    }

                    ctx.redirect(url.getUrl());
                });
            });

            path("/home", () -> {


                get("/", ctx -> {
                    ctx.redirect("/home/acortar/view_page/1");
                });

                get("/acortar/view_page/:page", ctx -> {
                    Map<String, Object> freeMarkerVars = new HashMap<>();
                    freeMarkerVars.put("title", "Home");
                    int page = ctx.pathParam("page", Integer.class).get();

                    freeMarkerVars.put("usuario", ctx.sessionAttribute("usuario"));

                    int pageSize = 10;
                    EntityManager em = UrlServices.getInstancia().getEntityManager();
                    String count = "Select count (p.idURL) from Url p where p.borrado = 0";
                    Query countQuery = em.createQuery(count);
                    Long countResults = (Long) countQuery.getSingleResult();
                    int totalPags = (int) (Math.ceil(((float) countResults / (float) pageSize)));
                    freeMarkerVars.put("paginas", totalPags);


                    if (ctx.sessionAttribute("urlshort") != null) {
                        freeMarkerVars.put("urlshort", ctx.sessionAttribute("urlshort"));
                        freeMarkerVars.put("ogurl", ctx.sessionAttribute("og-url"));
                    }

                    freeMarkerVars.put("urls", UrlServices.getInstancia().getUrlPaginated(page));
                    freeMarkerVars.put("cloudlink", GeneralController.getInstancia().getCloudlink());

                    ctx.render("/templates/Home.ftl", freeMarkerVars);
                });

                post("/acortar", ctx -> {
                    String url = ctx.formParam("originalURL");
                    Url generated;

                    if (ctx.sessionAttribute("usuario") != null) {
                        generated = UrlServices.getInstancia().generateShortURL(url, ctx.sessionAttribute("usuario"));
                    } else {
                        generated = UrlServices.getInstancia().generateShortURL(url, null);
                    }

                    ctx.sessionAttribute("urlshort", generated.getShortUrl());
                    ctx.sessionAttribute("og-url", url);

                    ctx.redirect("/");
                });

                post("/acortar/preview", ctx -> {
                    String url = ctx.formParam("originalURL");
                    String LinkPreviewApi = "http://api.linkpreview.net/?key=b52a19dee126b74e271903b0131548ca&&fields=image_x&q="+ url;
                    HttpResponse<JsonNode> request = Unirest.get(LinkPreviewApi)
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .asJson();
                    JSONObject preview = request.getBody().getObject();
                    String imageLink = preview.getString("image");

                    Map<String, Object> freeMarkerVars = new HashMap<>();
                    freeMarkerVars.put("title", "Link Preview");
                    freeMarkerVars.put("Imagen", imageLink);
                    freeMarkerVars.put("originalURL", url);
                    freeMarkerVars.put("usuario", ctx.sessionAttribute("usuario"));

                    ctx.render("/templates/LinkPreview.ftl", freeMarkerVars);
                });


                get("/view-url/:id", ctx -> {
                    Long id = ctx.pathParam("id", Long.class).get();

                    Url url = UrlServices.getInstancia().find(id);

                    if (url != null) {
                        String shortURL = url.getShortUrl();
                        Map<String, Object> attributes = DetallesController.getInstance().getStats(shortURL);
                        attributes.put("title", "Estad&iacute;sticas");
                        attributes.put("id", id);
                        attributes.put("URL", url);
                        attributes.put("UrlShort", shortURL);
                        attributes.put("usuario", ctx.sessionAttribute("usuario"));
                        if (GeneralController.getInstancia().getCloudlink().isEmpty()) {
                            attributes.put("cloudlink", "no-cloud-domain-assigned/");
                        } else {
                            attributes.put("cloudlink", GeneralController.getInstancia().getCloudlink());
                        }
                        ctx.render("/templates/SummaryPage.ftl", attributes);
                    } else {
                        System.out.println("Url no existe.");
                        ctx.render("/templates/No-autorizado.ftl");
                    }


                });

                post("/delete/:id", ctx -> {
                    long id = ctx.pathParam("id", Long.class).get();

                    Url url = UrlServices.getInstancia().find(id);
                    if (url != null) {
                        url.setBorrado(1);
                        UrlServices.getInstancia().update(url);
                    } else {
                        System.out.println("Url no existe.");
                    }

                    ctx.redirect("/");
                });

            });

            path("/administracion", () -> {

                before(ctx -> {
                    Usuario user = ctx.sessionAttribute("usuario");
                    if (ctx.sessionAttribute("usuario") == null) {
                        GeneralController.getInstancia().setRequestedURL(ctx.req.getRequestURI());
                        ctx.redirect("/Login.html");
                    } else if (user.getAdmin() == 0) {
                        ctx.redirect("/error/not-authorized");
                    }
                });

                get("/", ctx -> {
                    ctx.redirect("/administracion/listar-usuarios/view_page/1");
                });

                get("/listar-usuarios/view_page/:page", ctx -> {
                    Map<String, Object> freeMarkerVars = new HashMap<>();
                    freeMarkerVars.put("title", "Listado de Usuarios");
                    int page = ctx.pathParam("page", Integer.class).get();
                    freeMarkerVars.put("usuarios", UserServices.getInstancia().getUsersPaginated(page));

                    int pageSize = 10;
                    EntityManager em = UrlServices.getInstancia().getEntityManager();
                    String count = "Select count (p.idUser) from Usuario p where p.borrado = 0";
                    Query countQuery = em.createQuery(count);
                    Long countResults = (Long) countQuery.getSingleResult();
                    int totalPags = (int) (Math.ceil(((float) countResults / (float) pageSize)));
                    freeMarkerVars.put("paginas", totalPags);

                    freeMarkerVars.put("usuario", ctx.sessionAttribute("usuario"));

                    ctx.render("/templates/ListarUsuarios.ftl", freeMarkerVars);

                });

                post("/user/give-admin", ctx -> {
                    long id = ctx.formParam("iduser", Long.class).get();

                    Usuario user = UserServices.getInstancia().find(id);
                    if (user != null) {
                        if (user.getAdmin() == 1) {
                            user.setAdmin(0);
                        } else {
                            user.setAdmin(1);
                        }
                        UserServices.getInstancia().update(user);
                    } else {
                        System.out.println("Usuario no existe.");
                    }

                    ctx.redirect("/administracion/listar-usuarios/view_page/1");
                });

                post("/user/delete", ctx -> {
                    long id = ctx.formParam("iduser", Long.class).get();

                    Usuario user = UserServices.getInstancia().find(id);
                    if (user != null) {
                        user.setBorrado(1);
                        UserServices.getInstancia().update(user);
                    } else {
                        System.out.println("Usuario no existe.");
                    }

                    ctx.redirect("/administracion/listar-usuarios/view_page/1");
                });
            });

            path("/error", () -> {
                get("/not-authorized", ctx -> {
                    Map<String, Object> freeMarkerVars = new HashMap<>();
                    freeMarkerVars.put("title", "Error 401");
                    freeMarkerVars.put("usuario", ctx.sessionAttribute("usuario"));
                    ctx.render("/templates/No-autorizado.ftl", freeMarkerVars);
                });
            });
        });
    }
}

//user agent stuff
//    String aux = ctx.req.getHeader("User-Agent");
//    String temp = UserAgent.getNavegador(aux);
//    String ip = ctx.req.getRemoteAddr();