package com.up9.up9lib;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.riversun.okhttp3.OkHttp3CookieHelper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

import static com.up9.up9lib.Common.CSSselect;
import static com.up9.up9lib.Common.JSONPath;

public class HttpTarget {
    private final static Logger logger = LoggerFactory.getLogger(HttpTarget.class);
    private final URL targetServicesFilepath = getClass().getClassLoader().getResource("target_services.json");

    public OkHttpClient client;
    public String baseURL;
    public HttpUrl.Builder urlBuilder;
    public Request.Builder requestBuilder;
    public RequestBody requestBody;

    public String targetKey;
    public Map<String, Object> queryParameters = new HashMap<>();
    public Map<String, Object> headers = new HashMap<>();
    public Map<String, Object> formData = new HashMap<>();
    public Map<String, Object> multipartData = new HashMap<>();
    public Map<String, Object> jsonApply = new HashMap<>();

    public OkHttp3CookieHelper cookieHelper;

    public boolean usesAuth = false;

    public HttpTarget(String targetKey) {
        logger.info(() -> String.format("Target services path: %s", targetServicesFilepath));

        this.targetKey = targetKey;
        final JSONParser parser = new JSONParser();

        try {
            final Object obj = parser.parse(new FileReader(
                Objects.requireNonNull(this.targetServicesFilepath).getPath())
            );
            final JSONObject jsonObject = (JSONObject) obj;
            this.baseURL = (String) jsonObject.get(this.targetKey);
        } catch (FileNotFoundException e) {
            logger.warn(() -> String.format("%s file is missing!", this.targetServicesFilepath));
            this.baseURL = this.targetKey;
        } catch (IOException e) {
            logger.warn(() -> String.format("Unable to read %s file!", this.targetServicesFilepath));
            this.baseURL = this.targetKey;
        } catch (Exception e) {
            logger.warn(() -> String.format(
                    "Service %s is not found in target URL mapping (%s)!",
                    this.targetKey,
                    this.targetServicesFilepath
            ));
            this.baseURL = this.targetKey;
        }

        this.cookieHelper = new OkHttp3CookieHelper();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        this.client = new OkHttpClient.Builder()
                .cookieJar(cookieHelper.cookieJar())
                .addInterceptor(interceptor)
                .build();
    }

    public Response get(HttpRequest request, String path) throws IOException {
        return this.fetch("get", request, path);
    }

    public Response head(HttpRequest request, String path) throws IOException {
        return this.fetch("head", request, path);
    }

    public Response post(HttpRequest request, String path) throws IOException {
        return this.fetch("post", request, path);
    }

    public Response put(HttpRequest request, String path) throws IOException {
        return this.fetch("put", request, path);
    }

    public Response delete(HttpRequest request, String path) throws IOException {
        return this.fetch("delete", request, path);
    }

    public Response connect(HttpRequest request, String path) throws IOException {
        return this.fetch("connect", request, path);
    }

    public Response options(HttpRequest request, String path) throws IOException {
        return this.fetch("options", request, path);
    }

    public Response trace(HttpRequest request, String path) throws IOException {
        return this.fetch("trace", request, path);
    }

    public Response patch(HttpRequest request, String path) throws IOException {
        return this.fetch("patch", request, path);
    }

    private Response fetch(String httpVerb, HttpRequest httpRequest, String path) throws IOException {
        logger.info(() -> String.format("%s %s%s",
                httpVerb.toUpperCase(),
                this.baseURL,
                path
        ));

        if (!this.usesAuth) {
            logger.debug(() -> "Not using global authentication modifications for this request");
        } else {
            logger.debug(() -> "Request will use global authentication modifications");
        }

        // Set path
        this.urlBuilder = HttpUrl.parse(this.baseURL + path).newBuilder();

        // Set query parameters
        Map<String, Object> finalQueryParameters = this.mergeMaps(
                this.queryParameters,
                httpRequest.queryParameters
        );
        for (Map.Entry<String, Object> entry : finalQueryParameters.entrySet()) {
            this.urlBuilder.addQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
        }
        final String url = this.urlBuilder.build().toString();
        this.requestBuilder = new Request.Builder()
                .url(url);

        // Set headers
        this.requestBuilder.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        this.requestBuilder.addHeader("Referer", this.baseURL + "/");
        this.requestBuilder.addHeader("Accept", "*/*");
        Map<String, Object> finalHeaders = this.mergeMaps(
                this.headers,
                httpRequest.headers
        );
        for (Map.Entry<String, Object> entry : finalHeaders.entrySet()) {
            this.requestBuilder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
        }

        if (httpRequest.rawBody != null && httpRequest.rawBody.length() != 0) {
            // Set raw body
            this.requestBody = RequestBody.create(MediaType.parse("text/plain"), httpRequest.rawBody);
        } else {
            // Set form data
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            Map<String, Object> finalFormData = this.mergeMaps(
                    this.formData,
                    httpRequest.formData
            );
            for (Map.Entry<String, Object> entry : finalFormData.entrySet()) {
                formBodyBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
            this.requestBody = formBodyBuilder.build();
        }

        // Set multipart data
        if (!httpRequest.multipartData.isEmpty()) {
            MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
            multipartBodyBuilder.setType(MultipartBody.FORM);
            Map<String, Object> finalMultipartData = this.mergeMaps(
                    this.multipartData,
                    httpRequest.multipartData
            );
            for (Map.Entry<String, Object> entry : finalMultipartData.entrySet()) {
                multipartBodyBuilder.addFormDataPart(entry.getKey(), String.valueOf(entry.getValue()));
            }
            this.requestBody = multipartBodyBuilder.build();
        }

        // Set JSON body
        if (httpRequest.jsonPath != null && httpRequest.jsonPath.length() != 0) {
            URL jsonResourcePath = getClass().getClassLoader().getResource(httpRequest.jsonPath);
            logger.info(() -> String.format("JSON payload path: %s", jsonResourcePath));

            String json = new String(Files.readAllBytes(Paths.get(
                Objects.requireNonNull(jsonResourcePath).getPath()
            )));
            DocumentContext jsonPathDocument = JsonPath.parse(json);
            Map<String, Object> finalJsonApply = this.mergeMaps(
                    this.jsonApply,
                    httpRequest.jsonApply
            );
            for (Map.Entry<String, Object> entry : finalJsonApply.entrySet()) {
                try {
                    jsonPathDocument.set(entry.getKey(), entry.getValue());
                } catch (PathNotFoundException e) {
                    throw new AssertionError(e.getMessage());
                }
            }
            String newJson = jsonPathDocument.jsonString();
            this.requestBody = RequestBody.create(MediaType.parse("application/json"), newJson);
        }

        if (!httpVerb.equals("get")) {
            try {
                Method method = requestBuilder.getClass().getMethod(httpVerb, RequestBody.class);
                try {
                    method.invoke(this.requestBuilder, this.requestBody);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        final Request request = requestBuilder.build();
        return client.newCall(request).execute();
    }

    private Map<String, Object> mergeMaps(
            Map<String, Object> Map1,
            Map<String, Object> Map2
    ) {
        Map<String, Object> mergedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : Map1.entrySet()) {
            mergedMap.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Object> entry : Map2.entrySet()) {
            mergedMap.put(entry.getKey(), entry.getValue());
        }
        return mergedMap;
    }
}
