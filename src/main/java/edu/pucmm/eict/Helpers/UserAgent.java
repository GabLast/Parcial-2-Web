package edu.pucmm.eict.Helpers;

import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;

import java.io.IOException;

public class UserAgent {

    static UserAgentParser parser;

    static {
        try {
            parser = new UserAgentService().loadParser();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getNavegador(String useragent) {

        Capabilities capabilities = parser.parse(useragent);

        String navegador = capabilities.getBrowser();

        return navegador;
    }

    public static String getSistemaOperativo(String useragent) {
        Capabilities capabilities = parser.parse(useragent);
        String os = capabilities.getPlatform();
        return os;
    }

}
