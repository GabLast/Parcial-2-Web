package edu.pucmm.eict.Services;

import edu.pucmm.eict.Models.Usuario;
import org.apache.commons.validator.routines.UrlValidator;
import edu.pucmm.eict.Database.DBEntityManager;
import edu.pucmm.eict.Models.Url;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.net.*;
import java.time.LocalTime;
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
            long ipRandomized = Long.parseLong(ia.getHostAddress().replace(".", "") + random);
            String shorturlsegment = Long.toHexString(ipRandomized);

            String newUrl =/* dominio +*/ shorturlsegment;

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

        Query selectQuery = em.createQuery("select p From Url p where p.borrado = 0 order by p.idURL desc");
        selectQuery.setFirstResult((selectedPage - 1) * rowsPerPage);
        selectQuery.setMaxResults(rowsPerPage);

        selectQuery.getFirstResult();
        List<Url> lista = selectQuery.getResultList();

        return lista;
    }

    public Url getUrlByShortURL(String shorturl) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Url u where u.shortUrl = :shorturl", Url.class);
        query.setParameter("shorturl", shorturl);
        List<Url> lista = query.getResultList();
        if(lista.size() > 0)
        {
            return lista.get(0);
        }else {
            return null;
        }
    }

    public List<Url> getUrlByUser(Usuario user) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Url u where u.user.idUser = :userid", Url.class);
        query.setParameter("userid", user.getIdUser());
        List<Url> lista = query.getResultList();
        return lista;
    }

    /*public List<Url> getUrlByUsername(String username) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT u FROM Url u where u.user.username = :userName", Url.class);
        query.setParameter("userName", username);
        List<Url> lista = query.getResultList();
        return lista;
    }*/
}
