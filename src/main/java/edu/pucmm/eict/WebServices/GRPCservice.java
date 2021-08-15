package edu.pucmm.eict.WebServices;

import edu.pucmm.eict.Helpers.ServiciosRetorno;
import edu.pucmm.eict.Models.DetallesURL;
import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Models.Usuario;
import edu.pucmm.eict.Services.UrlServices;
import edu.pucmm.eict.Services.UserServices;

import urlrn.UrlRnGrpc;
import urlrn.UrlRnOuterClass;
import io.grpc.stub.StreamObserver;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class GRPCservice extends UrlRnGrpc.UrlRnImplBase {

    private final UrlServices urlServices = UrlServices.getInstancia();
    private final UserServices userServices = UserServices.getInstancia();

    public void listaUrl(UrlRnOuterClass.UrlRequest request , StreamObserver<UrlRnOuterClass.ListaUrl> responseObserver){
        List<Url> urls = new ArrayList<>();
        Usuario usuario = userServices.getUserByUsername(request.getUsuario());
        urls = UrlServices.getInstancia().getUrlByUser(usuario);
        List<UrlRnOuterClass.UrlResponse> urlReponseList = new ArrayList<>();
        for(Url e : urls){
            ServiciosRetorno s = new ServiciosRetorno(e);
            urlReponseList.add(convertirServicioAurlResponse(s));
        }
        UrlRnOuterClass.ListaUrl build = UrlRnOuterClass.ListaUrl.newBuilder().addAllUrl(urlReponseList).build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }

    public void crearUrl(UrlRnOuterClass.newUrlRequest request, StreamObserver<UrlRnOuterClass.UrlResponse> responseObserver){
        ServiciosRetorno s = null;
        try {
            s = new ServiciosRetorno(urlServices.generateShortURL(request.getUrl(), userServices.getUserByUsername(request.getUsuario())));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        responseObserver.onNext(convertirServicioAurlResponse(s));
        responseObserver.onCompleted();
    }


    private UrlRnOuterClass.UrlResponse convertirServicioAurlResponse(ServiciosRetorno s){
        return  UrlRnOuterClass.UrlResponse.newBuilder()
                .setUrl(s.getUrloriginal())
                .setShortUrl(s.getUrlcorta())
                .setDate(s.getFechaRegistro().toString())
                .setImg64(s.getPreviewIMG())
                .setVisitaFirefox(s.getVisitasFirefox())
                .setVisitasChrome(s.getVisitasChrome())
                .setVisitaWindows(s.getVisitaswindows())
                .setVisitaUbuntu(s.getVisitasubuntu())
                .build();
    }

}
