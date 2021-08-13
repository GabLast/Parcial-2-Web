package edu.pucmm.eict.WebServices;

import edu.pucmm.eict.Helpers.ServiciosRetorno;
import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Models.Usuario;
import edu.pucmm.eict.Services.UrlServices;
import edu.pucmm.eict.Services.UserServices;

import org.h2.engine.User;
import urlrn.UrlRnGrpc;
import urlrn.UrlRnOuterClass;
import io.grpc.stub.StreamObserver;

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
            urlReponseList.add(convertir(e));
        }
        UrlRnOuterClass.ListaUrl build = UrlRnOuterClass.ListaUrl.newBuilder().addAllUrl(urlReponseList).build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }

    private UrlRnOuterClass.UrlResponse convertir(Url url){
        return  UrlRnOuterClass.UrlResponse.newBuilder()
                .setUrl(url.getUrl())
                .setShortUrl(url.getShortUrl())
                .setDate(url.getFechaRegistro().toString())
                .build();
    }
}
