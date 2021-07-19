package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.DummyAuth;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import javax.json.JsonObject;
import net.joshka.junit.json.params.JsonFileSource;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsSecure07aChaseComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_80.json")
    public void testPostEventsEventid080(final JsonObject json) throws MalformedURLException, IOException
    {
        final String app = json.getString("app");
        final String eventId = json.getString("eventId");
        final String modifiedSince = json.getString("modifiedSince");
        final String referer = json.getString("referer");
        final String session = json.getString("session");
        final String svrid = json.getString("svrid");
        final String visitID = json.getString("visitID");

        // POST https://secure07a.chase.com/events/{eventId} (endp 80)
        final HttpTarget secure07aChaseCom = getHttpClient("https://secure07a.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("app", app);
            put("flavor", "post");
            put("modifiedSince", modifiedSince);
            put("referer", referer);
            put("session", session);
            put("svrid", svrid);
            put("type", "js");
            put("visitID", visitID);
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "text/plain");
        }});
        request.setRawBody(readFile("payload_for_endp_80.txt", StandardCharsets.UTF_8));
        final Response response = secure07aChaseCom.post(request, "/events/" + eventId);
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_79.json")
    public void testPostEventsAnalyticsPublicV1EventsRaw079(final JsonObject json) throws MalformedURLException, IOException
    {
        final String adobeData = json.getString("adobeData");
        final String browserRes = json.getString("browserRes");
        final int colorDepth = json.getInt("colorDepth");
        final String currentURL = json.getString("currentURL");
        final String javaScriptVer = json.getString("javaScriptVer");
        final String q = json.getString("q");
        final String screenRes = json.getString("screenRes");
        final int server_offset = json.getInt("server_offset");
        final String site = json.getString("site");
        final String tz_offset = json.getString("tz_offset");
        final String version = json.getString("version");
        final String visitor = json.getString("visitor");
        final String visitorId = json.getString("visitorId");

        // GET https://locator.chase.com/search (endp 77)
        final HttpTarget locatorChaseCom = getHttpClient("https://locator.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("l", "en");
            put("q", q);
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
        }});
        final Response response = locatorChaseCom.get(request, "/search");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.response.entities[*].profile.address.region", "CA", response.body().string());
        final String metaId = JSONPath("$.response.entities[*].profile.*[*]", response.body().string());

        // GET https://locator.chase.com/atmsearch (endp 75)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("metaId", metaId);
        }});
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
        }});
        final Response response2 = locatorChaseCom.get(request2, "/atmsearch");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.response.entities[*].profile.meta.language", "en", response2.body().string());
        final String redirectScreen = JSONPath("$.schema.alternateWebsites.archived", response2.body().string());

        // GET https://www.chase.com/ (endp 1)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = wwwChaseCom.get(request3, "/");
        assertStatusCode(response3.code(), 200);
        assertCSSselect("main#main h1.accessible-text", "Chase.com home", response3.body().string());
        assertCSSselect("html head title", "Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com", response3.body().string());
        final String referrerURL = CSSselect("div div header.header-navigation section.mobile-header div.row section a.chaseanalytics-track-link[href] @href", response3.body().string());

        // POST https://secure07a.chase.com/events/analytics/public/v1/events/raw/ (endp 79)
        final HttpTarget secure07aChaseCom = getHttpClient("https://secure07a.chase.com", new Authentication());
        final HttpRequest request4 = new HttpRequest();
        request4.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
        }});
        request4.setJsonBody("payload_for_endp_79.json", new Hashtable<String, Object>() {{
            put("$.events[*].app.version", version);
            put("$.events[*].device.browserRes", browserRes);
            put("$.events[*].device.colorDepth", colorDepth);
            put("$.events[*].device.javaScriptVer", javaScriptVer);
            put("$.events[*].device.screenRes", screenRes);
            put("$.events[*].location.server_offset", server_offset);
            put("$.events[*].location.tz_offset", tz_offset);
            put("$.events[*].payload.data.referrerURL", referrerURL);
            put("$.events[*].payload.timestamp", getCurrentTimestamp());
            put("$.events[*].screen.currentURL", currentURL);
            put("$.events[*].site", site);
            put("$.events[*].visitor.*", visitor);
            put("$.events[*].visitor.adobeData", adobeData);
            put("$.events[*].visitor.visitorId", visitorId);
        }});
        final Response response4 = secure07aChaseCom.post(request4, "/events/analytics/public/v1/events/raw/");
        assertStatusCode(response4.code(), 200);
    }

    /**
     * authentication-related test
     */
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_81.json")
    public void testGetWebAuthLogonbox081(final JsonObject json) throws MalformedURLException, IOException
    {
        final String q = json.getString("q");

        // GET https://locator.chase.com/search (endp 76)
        final HttpTarget locatorChaseCom = getHttpClient("https://locator.chase.com", new DummyAuth());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("q", q);
        }});
        final Response response = locatorChaseCom.get(request, "/search");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#js-locator div.Locator-content h1.Locator-title.Heading--locator.js-Locator-title", "Find a Chase ATM or branch near you", response.body().string());
        assertCSSselect("html head title", "Branches and ATMs | Chase Bank", response.body().string());
        final String fromOrigin = CSSselect("a#brand-logo[href] @href", response.body().string());

        // GET https://secure07a.chase.com/web/auth/logonbox (endp 81)
        final HttpTarget secure07aChaseCom = getHttpClient("https://secure07a.chase.com", new DummyAuth());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("fromOrigin", fromOrigin);
            put("lang", "en");
        }});
        final Response response2 = secure07aChaseCom.get(request2, "/web/auth/logonbox");
        assertStatusCode(response2.code(), 200);
        assertCSSselect("html head title", "Chase Account login", response2.body().string());
    }
}

