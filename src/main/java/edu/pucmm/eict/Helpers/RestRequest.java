package edu.pucmm.eict.Helpers;

public class RestRequest {
    private String user;
    private String url;

    public RestRequest() {
    }

    public RestRequest(String user, String url) {
        this.user = user;
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
