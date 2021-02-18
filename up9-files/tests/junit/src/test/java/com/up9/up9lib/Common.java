package com.up9.up9lib;

import com.jayway.jsonpath.JsonPath;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

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

public class Common {
    private final static Logger logger = LoggerFactory.getLogger(HttpTarget.class);

    public static HttpTarget getHttpTarget(String targetName, AuthenticationInterface authenticationInterface) {
        HttpTarget httpTarget = new HttpTarget(targetName);
        logger.debug(() -> String.format("Created a new context: %s", targetName));
        if (!(authenticationInterface instanceof DummyAuth)) {
            logger.debug(() -> String.format("Triggering authentication callback for context: %s", targetName));
            httpTarget.usesAuth = true;
        }
        authenticationInterface.authenticate(httpTarget);
        if (httpTarget.usesAuth) {
            logger.debug(() -> String.format("Returned from the authentication callback of %s", targetName));
        }
        return httpTarget;
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
}
