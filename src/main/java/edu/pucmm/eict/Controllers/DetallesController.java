package edu.pucmm.eict.Controllers;

import edu.pucmm.eict.Services.DetailsUrlServices;

import java.util.HashMap;
import java.util.Map;

public class DetallesController {

    private static DetallesController instance;
    private DetailsUrlServices detail = new DetailsUrlServices();
    public static DetallesController getInstance(){return instance == null ? new DetallesController() : instance;}

    public Map<String,Object> getStats(String urlShort) {

        //Browsers
        long chromeVisits = detail.getSizeVisitaByShortUrlBrowser(urlShort, "Chrome");
        long operaVisits = detail.getSizeVisitaByShortUrlBrowser(urlShort, "Opera");
        long firefoxVisits = detail.getSizeVisitaByShortUrlBrowser(urlShort, "Firefox");
        long edgeVisits = detail.getSizeVisitaByShortUrlBrowser(urlShort, "Edge");
        long safariVisits = detail.getSizeVisitaByShortUrlBrowser(urlShort, "Safari");

        long windows10 = detail.getSizeVisitaByShortUrlOs(urlShort, "Win10");
        long windows7 = detail.getSizeVisitaByShortUrlOs(urlShort, "Windows 7");
        long ubuntu1604 = detail.getSizeVisitaByShortUrlOs(urlShort, "Ubuntu 16.04");
        long ubuntu1804 = detail.getSizeVisitaByShortUrlOs(urlShort, "Ubuntu 18.04");
        long android9 = detail.getSizeVisitaByShortUrlOs(urlShort, "Android 9");
        long android8 = detail.getSizeVisitaByShortUrlOs(urlShort, "Android 8");

        long dateVisits = detail.getSizeVisitaByDate(urlShort);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("chromeVisits", chromeVisits);
        attributes.put("operaVisits", operaVisits);
        attributes.put("firefoxVisits", firefoxVisits);
        attributes.put("edgeVisits", edgeVisits);
        attributes.put("safariVisits", safariVisits);

        attributes.put("windows10",windows10);
        attributes.put("windows7",windows7);
        attributes.put("ubuntu1604",ubuntu1604);
        attributes.put("ubuntu1804",ubuntu1804);
        attributes.put("android8",android8);
        attributes.put("android9",android9);

        attributes.put("Date", dateVisits);
        return attributes;
    }

}
