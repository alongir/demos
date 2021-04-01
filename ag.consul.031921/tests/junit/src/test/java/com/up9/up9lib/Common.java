package com.up9.up9lib;

import com.jayway.jsonpath.JsonPath;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import okhttp3.Response;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Common {
    private final static Logger logger = LoggerFactory.getLogger(HttpTarget.class);

    public static HttpTarget getHttpClient(
        String key,
        AuthenticationInterface authenticationInterface
    ) throws MalformedURLException {
        final String targetKey = Common.resolveTargetKey(key);
        HttpTarget httpTarget = new HttpTarget(targetKey);
        logger.debug(() -> String.format("Created a new context: %s", targetKey));
        if (!(authenticationInterface instanceof DummyAuth)) {
            logger.debug(() -> String.format("Triggering authentication callback for context: %s", targetKey));
            httpTarget.usesAuth = true;
        }
        authenticationInterface.authenticate(httpTarget);
        if (httpTarget.usesAuth) {
            logger.debug(() -> String.format("Returned from the authentication callback of %s", targetKey));
        }
        return httpTarget;
    }

    private static String resolveTargetKey(String baseAddr) throws MalformedURLException {
        String targetKey = null;

        if (baseAddr.contains("://")) {
            final String regex = "\\W|^(?=\\d)";
            final URL url = new URL(baseAddr);
            targetKey = url.getHost().replaceAll(regex, "_");
            if (targetKey.isEmpty()) {
                targetKey = baseAddr.replaceAll(regex, "_");
            }
            targetKey = "TARGET_" + targetKey.toUpperCase();
        } else {
            targetKey = baseAddr;
        }

        return targetKey;
    }

    public static String JSONPath(String path, String text) {
        Object result;
        try {
            result = JsonPath.read(text, path);
        } catch (IllegalArgumentException e) {
            throw new AssertionError(String.format("JSONPath: Provided JSON is empty, null or invalid: %s", text));
        }

        if (result instanceof List) {
            List list = (List) result;
            if (list.size() == 0) {
                throw new AssertionError(String.format("JSONPath: No values found for %s", path));
            } else {
                Object el = list.get(0);
                if (el instanceof String) {
                    return (String) el;
                } else {
                    return String.valueOf(el);
                }
            }
        } else if (result instanceof String) {
            return (String) result;
        } else {
            return String.valueOf(result);
        }
    }

    public static String CSSselect(String selector, String text) {
        Document doc = Jsoup.parse(text);
        Element el = doc.select(selector).first();
        if (el == null) {
            throw new AssertionError(String.format("CSSselect: No values found for %s", selector));
        } else {
            return el.text();
        }
    }

    public static String fromRegex(String regex, String text) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.group(0);
    }

    public static String uuidv4() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static int randomInteger(int min, int max) throws IllegalArgumentException {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public static long randomInteger(long min, long max) throws IllegalArgumentException {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (long) (Math.random() * ((max - min) + 1)) + min;
    }

    public static float randomFloat(float min, float max) throws IllegalArgumentException {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (float) (Math.random() * ((max - min) + 1)) + min;
    }

    public static double randomFloat(double min, double max) throws IllegalArgumentException {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (double) (Math.random() * ((max - min) + 1)) + min;
    }

    private static String randomEmailUsername() {
        final String[] names = {"John", "Peter", "Bob", "David", "Harry"};
        final String[] surnames = {"Black", "Clark", "Duncan", "Gibson", "James"};

        final String username = String.format(
            "%s.%s",
            names[randomInteger(0, names.length - 1)],
            surnames[randomInteger(0, surnames.length - 1)]
        ).toLowerCase();

        return username;
    }

    public static String randomEmail() {
        final String[] emailDomains = {"gmail.com", "yahoo.com", "hotmail.com"};

        final String email = String.format(
            "%s@%s",
            randomEmailUsername(),
            emailDomains[randomInteger(0, emailDomains.length)]
        );
        return email;
    }

    public static String randomEmail(String domain) {
        final String email = String.format(
            "%s@%s",
            randomEmailUsername(),
            domain
        );
        return email;
    }

    private static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();

        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    public static String urlPart(String ospec, String paramURL) throws MalformedURLException {
        logger.debug(() -> String.format("Extracting %s from %s", ospec, paramURL));

        final char flag = ospec.charAt(0);
        final String spec = ospec.substring(1);

        final URL url = new URL(paramURL);
        if (flag == '/') {
            final int ind = Integer.parseInt(spec);
            final String path = url.getPath();
            final String[] pathParts = path.split("/");
            return pathParts[ind];
        } else {
            Map<String, String> queryMap = Common.getQueryMap(url.getQuery());
            return queryMap.get(spec);
        }
    }

    public static long getCurrentTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(
            Objects.requireNonNull(
                Common.class.getClassLoader().getResource(path)
            ).getPath()
        ));
        return new String(encoded, encoding);
    }

    public static String getHeader(Response response, String key) {
        List<String> headers = response.headers(key);
        return headers.get(0);
    }

    public static String getCookie(Response response, String key) {
        List<String> setCookieHeaders = response.headers().values("Set-Cookie");
        for (String setCookieHeader : setCookieHeaders) {
            String cookie = setCookieHeader.split(";")[1];
            String cookieKey = cookie.split("=")[0];
            String cookieValue = cookie.split("=")[1];
            if (cookieKey.equals(key)) {
                return cookieValue;
            }
        }
        throw new AssertionError("key is not found in the cookies");
    }

    public static void assertStatusCode(int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(
                String.format("Actual status code (%d) didn't match expected (%d)",
                    actual,
                    expected
                )
            );
        }
    }

    public static void assertStatusCodeIn(int statusCode, int[] statusCodeList) {
        if (!Arrays.asList(statusCodeList).contains(statusCode)) {
            throw new AssertionError(
                String.format("Actual status code (%d) is not one of expected (%s)",
                    statusCode,
                    Arrays.stream(statusCodeList)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(", "))
                )
            );
        }
    }

    public static void assertRegexInBody(String regex, String responseText) {
        if (!Pattern.compile(regex).matcher(responseText).find()) {
            throw new AssertionError(
                String.format("Regex %s unexpectedly found something in text %s",
                    regex,
                    shorten(responseText, 100)
                )
            );
        }
    }

    public static void assertJSONPath(String jsonPath, String value, String responseText) {
        try {
            String jsonPathMatchedString = JSONPath(jsonPath, responseText);
            if (!jsonPathMatchedString.contains(value)) {
                throw new AssertionError(
                    String.format("Actual value at JSONPath query (%s) isn't equal to expected (%s)",
                        jsonPath,
                        value
                    )
                );
            }
        } catch (RuntimeException e) {
            throw new AssertionError(
                String.format("JSONPath query %s didn't match response content: %s",
                    jsonPath,
                    shorten(responseText, 100)
                )
            );
        }
    }

    public static void assertCSSselect(String query, String value, String responseText) {
        String cssSelectMatchedString = CSSselect(query, responseText);
        if (cssSelectMatchedString.isEmpty()) {
            throw new AssertionError(
                String.format("CSS select query %s didn't match response content: %s",
                    query,
                    shorten(responseText, 100)
                )
            );
        }

        if (!cssSelectMatchedString.contains(value)) {
            throw new AssertionError(
                String.format("Actual value at CSS select query (%s) isn't equal to expected (%s)",
                    query,
                    value
                )
            );
        }
    }

    public static String shorten(String text, int upto) {
        if (text.length() > upto) {
            return text.substring(0, upto) + "...";
        } else {
            return text;
        }
    }
}
