package edu.pucmm.eict.Models;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class DetallesURL implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDetallesURL;
    @NotNull @Column(columnDefinition = "varchar(255) default 'Desconocido'")
    private String navegador;
    @NotNull @Column(columnDefinition = "varchar(255) default 'Desconocido'")
    private String ip;
    @NotNull @Column(columnDefinition = "varchar(255) default 'Desconocido'")
    private String sistemaOperativo;
    @NotNull
    private Date fechaAcceso;

    @ManyToOne
    private Url url;

    public DetallesURL() {
    }

    public DetallesURL(@NotNull String navegador, @NotNull String ip, @NotNull String sistemaOperativo, Url url) {
        this.navegador = navegador;
        this.ip = ip;
        this.sistemaOperativo = sistemaOperativo;
        this.url = url;
        this.fechaAcceso = new Date();
    }

    public long getIdDetallesURL() {
        return idDetallesURL;
    }

    public void setIdDetallesURL(long idDetallesURL) {
        this.idDetallesURL = idDetallesURL;
    }

    @NotNull
    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(@NotNull String navegador) {
        this.navegador = navegador;
    }

    @NotNull
    public String getIp() {
        return ip;
    }

    public void setIp(@NotNull String ip) {
        this.ip = ip;
    }

    @NotNull
    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(@NotNull String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    @NotNull
    public Date getFechaAcceso() {
        return fechaAcceso;
    }

    public void setFechaAcceso(@NotNull Date fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }
}
