package edu.pucmm.eict.Controllers;

import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Models.Usuario;
import edu.pucmm.eict.Services.UrlServices;

import java.util.HashMap;

public class GeneralController {

    private static GeneralController instancia;

    public static GeneralController getInstancia() {
        if (instancia == null) {
            instancia = new GeneralController();
        }
        return instancia;
    }

    private Usuario user = null;
    private String requestedURL = "";

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getRequestedURL() {
        return requestedURL;
    }

    public void setRequestedURL(String requestedURL) {
        this.requestedURL = requestedURL;
    }

}
