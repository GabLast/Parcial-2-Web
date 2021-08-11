package edu.pucmm.eict.Controllers;

import edu.pucmm.eict.Services.DetailsUrlServices;

import java.util.HashMap;
import java.util.Map;

public class DetallesController {

    private static DetallesController instance;
    private DetailsUrlServices detail = DetailsUrlServices.getInstancia();
    public static DetallesController getInstance(){return instance == null ? new DetallesController() : instance;}

    public Map<String,Object> getStats(String urlShort) {

        //Browsers
        long chromeVisits = detail.getSizeVisitaByShortUrlBrowser(urlShort, "Chrome");
        long operaVisits = detail.getSizeVisitaByShortUrlBrowser(urlShort, "Opera");
        long firefoxVisits = detail.getSizeVisitaByShortUrlBrowser(urlShort, "Firefox");
        long edgeVisits = detail.getSizeVisitaByShortUrlBrowser(urlShort, "Edge");
        long safariVisits = detail.getSizeVisitaByShortUrlBrowser(urlShort, "Safari");

        long windows10 = detail.visitasOSLike(urlShort, "Win");
        long ubuntu1604 = detail.visitasOSLike(urlShort, "buntu");
        long android9 = detail.visitasOSLike(urlShort, "ndroid");

        long dateVisits = detail.getSizeVisitaByDate(urlShort);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("chromeVisits", chromeVisits);
        attributes.put("operaVisits", operaVisits);
        attributes.put("firefoxVisits", firefoxVisits);
        attributes.put("edgeVisits", edgeVisits);
        attributes.put("safariVisits", safariVisits);

        attributes.put("windows",windows10);
        attributes.put("ubuntu",ubuntu1604);
        attributes.put("android",android9);

        attributes.put("Date", dateVisits);
        return attributes;
    }

}
