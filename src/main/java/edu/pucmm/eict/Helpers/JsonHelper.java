package edu.pucmm.eict.Helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonHelper {
    private static Gson gson = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
