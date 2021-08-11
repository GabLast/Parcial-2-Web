package edu.pucmm.eict.Helpers;

import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Services.DetailsUrlServices;

import java.util.Date;

public class ServiciosRetorno {

    private String urloriginal;
    private String urlcorta;
    private Date fechaRegistro;
    private long visitasSafari;
    private long visitasOpera;
    private long visitasChrome;
    private long visitasEdge;
    private long visitasFirefox;
    private long visitaswindows;
    private long visitasubuntu;
    private long visitasandroid;

    public ServiciosRetorno(Url url) {
        this.urloriginal = url.getUrl();
        this.urlcorta = url.getShortUrl();
        this.fechaRegistro = url.getFechaRegistro();
        this.visitasSafari = DetailsUrlServices.getInstancia().getSizeVisitaByShortUrlBrowser(url.getUrl(), "Safari");
        this.visitasOpera = DetailsUrlServices.getInstancia().getSizeVisitaByShortUrlBrowser(url.getUrl(), "Opera");
        this.visitasChrome = DetailsUrlServices.getInstancia().getSizeVisitaByShortUrlBrowser(url.getUrl(), "Chrome");
        this.visitasEdge = DetailsUrlServices.getInstancia().getSizeVisitaByShortUrlBrowser(url.getUrl(), "Edge");
        this.visitasFirefox = DetailsUrlServices.getInstancia().getSizeVisitaByShortUrlBrowser(url.getUrl(), "Firefox");
        this.visitaswindows = DetailsUrlServices.getInstancia().visitasOSLike(url.getShortUrl(), "Win");
        this.visitasubuntu = DetailsUrlServices.getInstancia().visitasOSLike(url.getShortUrl(), "buntu");
        this.visitasandroid = DetailsUrlServices.getInstancia().visitasOSLike(url.getShortUrl(), "ndroid");
    }

    public String getUrloriginal() {
        return urloriginal;
    }

    public void setUrloriginal(String urloriginal) {
        this.urloriginal = urloriginal;
    }

    public String getUrlcorta() {
        return urlcorta;
    }

    public void setUrlcorta(String urlcorta) {
        this.urlcorta = urlcorta;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public long getVisitasSafari() {
        return visitasSafari;
    }

    public void setVisitasSafari(long visitasSafari) {
        this.visitasSafari = visitasSafari;
    }

    public long getVisitasOpera() {
        return visitasOpera;
    }

    public void setVisitasOpera(long visitasOpera) {
        this.visitasOpera = visitasOpera;
    }

    public long getVisitasChrome() {
        return visitasChrome;
    }

    public void setVisitasChrome(long visitasChrome) {
        this.visitasChrome = visitasChrome;
    }

    public long getVisitasEdge() {
        return visitasEdge;
    }

    public void setVisitasEdge(long visitasEdge) {
        this.visitasEdge = visitasEdge;
    }

    public long getVisitasFirefox() {
        return visitasFirefox;
    }

    public void setVisitasFirefox(long visitasFirefox) {
        this.visitasFirefox = visitasFirefox;
    }

    public long getVisitaswindows() {
        return visitaswindows;
    }

    public void setVisitaswindows(long visitaswindows) {
        this.visitaswindows = visitaswindows;
    }

    public long getVisitasubuntu() {
        return visitasubuntu;
    }

    public void setVisitasubuntu(long visitasubuntu) {
        this.visitasubuntu = visitasubuntu;
    }

    public long getVisitasandroid() {
        return visitasandroid;
    }

    public void setVisitasandroid(long visitasandroid) {
        this.visitasandroid = visitasandroid;
    }
}
