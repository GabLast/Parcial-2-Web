package edu.pucmm.eict.Models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Url implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idURL;
    @NotNull @Column (length = 1000)
    private String url;
    @NotNull
    private String shortUrl;
    @NotNull
    private Date fechaRegistro;
    @NotNull
    @Column(columnDefinition = "int default 0")
    private int borrado;
    @ManyToOne
    private Usuario user;
    private String mimeType;
    @Lob
    private String previewBase64;

    @OneToMany(mappedBy = "url", fetch = FetchType.EAGER)
    private Set<DetallesURL> misdetalles;

    public Url() {
    }

    public Url(String url, String shortUrl) {
        this.url = url;
        this.shortUrl = shortUrl;
        this.user = null;
        this.fechaRegistro = new Date();
    }

    public Url(String url, String shortUrl, Usuario user) {
        this.url = url;
        this.shortUrl = shortUrl;
        this.user = user;
        this.fechaRegistro = new Date();
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getPreviewBase64() {
        return previewBase64;
    }

    public void setPreviewBase64(String previewBase64) {
        this.previewBase64 = previewBase64;
    }

    public long getIdURL() {
        return idURL;
    }

    public void setIdURL(long idURL) {
        this.idURL = idURL;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Set<DetallesURL> getMisdetalles() {
        return misdetalles;
    }

    public void setMisdetalles(Set<DetallesURL> misdetalles) {
        this.misdetalles = misdetalles;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
