package edu.pucmm.eict.Services;

import edu.pucmm.eict.Database.DBEntityManager;
import edu.pucmm.eict.Models.DetallesURL;

import javax.persistence.Query;

public class DetailsUrlServices extends DBEntityManager<DetallesURL> {

    private static DetailsUrlServices instancia;

    public DetailsUrlServices() {
        super(DetallesURL.class);
    }

    public static DetailsUrlServices getInstancia() {
        if (instancia == null) {
            instancia = new DetailsUrlServices();
        }
        return instancia;
    }

    public long getSizeVisitaByShortUrlBrowser(String urlShort, String browser){
        Query query = getEntityManager().createQuery("Select count(d.navegador) from DetallesURL d where d.url.shortUrl =:urlShort and" +
                " d.navegador =: browser");
        query.setParameter("urlShort", urlShort);
        query.setParameter("browser", browser);
        return (long) query.getSingleResult();
    }

    public long getSizeVisitaByShortUrlOs(String urlShort, String os){
        Query query = getEntityManager().createQuery("Select count(d.sistemaOperativo) from DetallesURL d where d.url.shortUrl =:urlShort and" +
                " d.sistemaOperativo =: os");
        query.setParameter("urlShort", urlShort);
        query.setParameter("os", os);
        return (long) query.getSingleResult();
    }

    public long getSizeVisitaByDate(String urlShort){
        Query query = getEntityManager().createQuery("Select count(d.fechaAcceso) from DetallesURL d where d.url.shortUrl =:urlShort ");
        query.setParameter("urlShort", urlShort);
        return (long) query.getSingleResult();
    }
}
