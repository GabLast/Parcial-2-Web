package edu.pucmm.eict.Services;

import edu.pucmm.eict.Models.Usuario;
import org.apache.commons.validator.routines.UrlValidator;
import edu.pucmm.eict.Database.DBEntityManager;
import edu.pucmm.eict.Models.Url;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.net.*;
import java.util.List;

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
            System.out.println("La URL no es válida");
            return null;
        }else{
            Url nueva;

            URL aux = new URL(url);
            InetAddress ia = InetAddress.getByName(aux.getHost());

            Long random = Long.valueOf((long) (Math.random() * (9999999 - 0) + 0));
            Long ipRandomized = Long.parseLong(ia.getHostAddress().replace(".", "") + random);
            String shorturlsegment = Long.toHexString(ipRandomized);

            String newUrl = dominio + shorturlsegment;

            if(user == null)
            {
                nueva = new Url(url, newUrl);
            }else{
                nueva = new Url(url, newUrl, user);
            }

            UrlServices.getInstancia().insert(nueva);

            return nueva;
        }
    }

    public List<Url> getUrlPaginated(int page){
        EntityManager em = getEntityManager();

        int rowsPerPage = 10;
        int selectedPage = page;

        Query selectQuery = em.createQuery("select p From Url p where p.borrado = 0");
        selectQuery.setFirstResult((selectedPage - 1) * rowsPerPage);
        selectQuery.setMaxResults(rowsPerPage);

        selectQuery.getFirstResult();
        List<Url> lista = selectQuery.getResultList();

        return lista;
    }
}
