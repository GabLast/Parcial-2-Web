package edu.pucmm.eict.WebServices;

import edu.pucmm.eict.Helpers.RestRequest;
import edu.pucmm.eict.Helpers.ServiciosRetorno;
import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Models.Usuario;
import edu.pucmm.eict.Services.UrlServices;
import edu.pucmm.eict.Services.UserServices;
import io.javalin.Javalin;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RestAPIController {

    public final static String SECRET_KEY = "2Ms8vHgCwd66Mgas6kAVGR39GmmNV6kx";
    Javalin app;

    public RestAPIController(Javalin app) {
        this.app = app;
    }


    public void routes() {

        app.routes(() -> {

            path("/rest", () -> {

                before("/*", ctx -> {
                    if(ctx.req.getMethod() == "OPTIONS"){
                        return;
                    }
                    String header = "Authorization";
                    String username = ctx.queryParam("username");
                    String password = ctx.queryParam("password");
                    Usuario user = UserServices.getInstancia().login(username,password);

                    if(user != null)
                    {
                        ctx.res.addHeader(header, generateJWT(user));
                    } else
                    {
                        ctx.res.sendError(403, "No se ha autenticado con un usuario válido");
                    }
                });

                after("/*", ctx -> {
                    String header = ctx.req.getHeader("Authorization");
                    if(header != null) {
                        System.out.println("JWT Recibido " + header);
                    }
                    ctx.header("Content-Type", "application/json");
                });

                path("/url", () -> {

                    get("/", ctx -> {
                        List<ServiciosRetorno> urls = new ArrayList<>();
                        String username = ctx.queryParam("username");
                        Usuario user = UserServices.getInstancia().getUserByUsername(username);
                        for(Url url : UrlServices.getInstancia().getUrlByUser(user))
                        {
                            urls.add(new ServiciosRetorno(url));
                        }
                        ctx.json(urls);
                    });

                    post("/", ctx -> {
                        RestRequest url = ctx.bodyAsClass(RestRequest.class);
                        Usuario user = UserServices.getInstancia().getUserByUsername(url.getUser());
//                        System.out.println(url);
                        Url nueva = UrlServices.getInstancia().generateShortURL(url.getUrl(), user);

                        ctx.json(new ServiciosRetorno(nueva));
                    });
                });

            });
        });
    }

    public static String generateJWT(Usuario user) {

        //generando la llave.
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        //Generando la fecha valida
        long currenttime = System.currentTimeMillis();
        Date current = new Date(currenttime);
//        System.out.println("La fecha ahora: " + current);
        Calendar cal = Calendar.getInstance();
        cal.setTime(current);
        cal.add(Calendar.DATE, 1);

        //fecha de expiracion
        Date fechaExpiracion = cal.getTime();
//        System.out.println("La fecha Expiracion: " + fechaExpiracion);

        // creando la trama.
        String jwt = Jwts.builder()
                .setIssuer("Developers-Final-Web")
                .setSubject("Final-Web")
                .claim("usuario", user.getUsername())
                .claim("iduser", user.getIdUser())
                .setExpiration(fechaExpiracion)
                .signWith(secretKey)
                .compact();

        return jwt;
    }

}