package edu.pucmm.eict.Services;

import edu.pucmm.eict.Database.DBEntityManager;
import edu.pucmm.eict.Models.DetallesURL;

public class DetailsUrlServices extends DBEntityManager<DetallesURL> {

    private static DetailsUrlServices instancia;

    private DetailsUrlServices() {
        super(DetallesURL.class);
    }

    public static DetailsUrlServices getInstancia() {
        if (instancia == null) {
            instancia = new DetailsUrlServices();
        }
        return instancia;
    }
}
