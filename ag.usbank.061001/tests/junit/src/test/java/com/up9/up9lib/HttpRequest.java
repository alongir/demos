package com.up9.up9lib;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HttpRequest {
    Map<String, Object> queryParameters = new HashMap<>();
    Map<String, Object> headers = new HashMap<>();
    Map<String, Object> formData = new HashMap<>();
    Map<String, Object> multipartData = new HashMap<>();
    String jsonPath = "";
    Map<String, Object> jsonApply = new HashMap<>();
    String rawBody = "";

    public void setQueryString(Hashtable<String, Object> queryParameters) {
        this.queryParameters = queryParameters;
    }

    public void setHeaders(Hashtable<String, Object> headers) {
        this.headers = headers;
    }

    public void setFormData(Hashtable<String, Object> formData) {
        this.formData = formData;
    }

    public void setMultipartData(Hashtable<String, Object> multipartData) {
        this.multipartData = multipartData;
    }

    public void setJsonBody(String jsonPath, Hashtable<String, Object> jsonApply) {
        this.jsonPath = jsonPath;
        this.jsonApply = jsonApply;
    }

    public void setRawBody(String body) {
        this.rawBody = body;
    }
}
