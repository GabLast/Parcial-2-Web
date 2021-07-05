package edu.pucmm.eict.Services;

import edu.pucmm.eict.Models.Usuario;
import org.apache.commons.validator.routines.UrlValidator;
import edu.pucmm.eict.Database.DBEntityManager;
import edu.pucmm.eict.Models.Url;

import java.net.*;

public class UrlServices extends DBEntityManager<Url> {

    private static UrlServices instancia;

    private UrlServices() {
        super(Url.class);
    }

    public static UrlServices getInstancia() {
        if (instancia == null) {
            instancia = new UrlServices();
        }
        return instancia;
    }

    public Url generateShortURL(String url, Usuario user) throws UnknownHostException, MalformedURLException {

        String dominio = "mydomain/";

        UrlValidator urlValidator = new UrlValidator();

        if(!urlValidator.isValid(url))
        {
            throw new RuntimeException("La URL no es válida");

        }else{
            Url nueva;

            URL aux = new URL(url);
            InetAddress ia = InetAddress.getByName(aux.getHost());

            Long random = Long.valueOf((long) (Math.random() * (99999999 - 0) + 0));
            Long ipRandomized = Long.parseLong(ia.getHostAddress().replace(".", "") + random);
            String shorturlsegment = Long.toHexString(ipRandomized);

            String newUrl = dominio + shorturlsegment;

            if(user == null)
            {
                nueva = new Url(url, newUrl);
            }else{
                nueva = new Url(url, newUrl, user);
            }

            return nueva;
        }
    }
}
