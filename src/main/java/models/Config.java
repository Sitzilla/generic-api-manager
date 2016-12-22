package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by evan on 12/20/16.
 */
public class Config {
    @JsonProperty
    private String url;
    @JsonProperty("auth_token")
    private String authToken;
    @JsonProperty
    private ArrayList<String> headers;

    public Config() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(final String authToken) {
        this.authToken = authToken;
    }

    public ArrayList<String> getHeaders() {
        return headers;
    }

    public void setHeaders(final ArrayList<String> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return "Config{" +
                "url='" + url + '\'' +
                ", authToken='" + authToken + '\'' +
                ", headers=" + headers +
                '}';
    }
}
