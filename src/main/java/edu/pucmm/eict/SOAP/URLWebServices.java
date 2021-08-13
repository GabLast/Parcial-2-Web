package edu.pucmm.eict.SOAP;

import edu.pucmm.eict.Helpers.ServiciosRetorno;
import edu.pucmm.eict.Models.Url;
import edu.pucmm.eict.Models.Usuario;
import edu.pucmm.eict.Services.UrlServices;
import edu.pucmm.eict.Services.UserServices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@WebService
public class URLWebServices {

    @WebMethod
    public List<ServiciosRetorno> getListaURLS(String username, String password) {
        List<ServiciosRetorno> urls = new ArrayList<>();
        for (Url url :  UrlServices.getInstancia().getUrlByUser(UserServices.getInstancia().login(username,password))) {
            urls.add(new ServiciosRetorno(url));
        }
        return urls;
    }

    @WebMethod
        public ServiciosRetorno registrarURL(String url, String username, String password) throws MalformedURLException, UnknownHostException {

        Url nueva = UrlServices.getInstancia().generateShortURL(url, UserServices.getInstancia().login(username,password));

        return new ServiciosRetorno(nueva);
    }
}
