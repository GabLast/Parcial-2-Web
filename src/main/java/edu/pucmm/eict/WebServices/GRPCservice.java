package edu.pucmm.eict.WebServices;

import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Services.UrlServices;
import urlrn.UrlRnGrpc;
import urlrn.UrlRnOuterClass;
import io.grpc.stub.StreamObserver;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class GRPCservice extends UrlRnGrpc.UrlRnImplBase {

    private final UrlServices urlServices = UrlServices.getInstancia();

    public void listaUrl(UrlRnOuterClass.UrlRequest request , StreamObserver<UrlRnOuterClass.ListaUrl> responseObserver){
        List<Url> urlPorUsuario = urlServices.getUrlByUsername(request.getUsuario());
        List<UrlRnOuterClass.UrlResponse> urlReponseList = new ArrayList<>();
    }
}
