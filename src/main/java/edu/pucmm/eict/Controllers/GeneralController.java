package edu.pucmm.eict.Controllers;

import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Models.Usuario;
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
    private Url lastURLShortened = null;
    private String cloudlink = "";

    public String getCloudlink() {
        return cloudlink;
    }

    public void setCloudlink(String cloudlink) {
        this.cloudlink = cloudlink;
    }

    public Url getLastURLShortened() {
        return lastURLShortened;
    }

    public void setLastURLShortened(Url lastURLShortened) {
        this.lastURLShortened = lastURLShortened;
    }

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
