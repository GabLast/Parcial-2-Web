package edu.pucmm.eict.Services;

import edu.pucmm.eict.Database.DBEntityManager;
import edu.pucmm.eict.Models.Url;

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


}
