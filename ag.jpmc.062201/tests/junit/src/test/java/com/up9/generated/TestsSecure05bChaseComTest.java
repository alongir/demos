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
public class TestsSecure05bChaseComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_63.json")
    public void testPostEventsEventid063(final JsonObject json) throws MalformedURLException, IOException
    {
        final String app = json.getString("app");
        final String eventId = json.getString("eventId");
        final String modifiedSince = json.getString("modifiedSince");
        final String referer = json.getString("referer");
        final String session = json.getString("session");
        final String svrid = json.getString("svrid");
        final String visitID = json.getString("visitID");

        // POST https://secure05b.chase.com/events/{eventId} (endp 63)
        final HttpTarget secure05bChaseCom = getHttpClient("https://secure05b.chase.com", new Authentication());
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
        request.setRawBody(readFile("payload_for_endp_63.txt", StandardCharsets.UTF_8));
        final Response response = secure05bChaseCom.post(request, "/events/" + eventId);
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_62.json")
    public void testPostEventsAnalyticsPublicV1EventsRaw062(final JsonObject json) throws MalformedURLException, IOException
    {
        final String adobeData = json.getString("adobeData");
        final String browserRes = json.getString("browserRes");
        final int colorDepth = json.getInt("colorDepth");
        final String currentURL = json.getString("currentURL");
        final String javaScriptVer = json.getString("javaScriptVer");
        final String screenRes = json.getString("screenRes");
        final int server_offset = json.getInt("server_offset");
        final String site = json.getString("site");
        final String tz_offset = json.getString("tz_offset");
        final String version = json.getString("version");
        final String visitor = json.getString("visitor");
        final String visitorId = json.getString("visitorId");

        // GET https://www.chase.com/ (endp 1)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/");
        assertStatusCode(response.code(), 200);
        assertCSSselect("main#main h1.accessible-text", "Chase.com home", response.body().string());
        assertCSSselect("html head title", "Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com", response.body().string());
        final String referrerURL = CSSselect("div div header.header-navigation section.mobile-header div.row section a.chaseanalytics-track-link[href] @href", response.body().string());

        // POST https://secure05b.chase.com/events/analytics/public/v1/events/raw/ (endp 62)
        final HttpTarget secure05bChaseCom = getHttpClient("https://secure05b.chase.com", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
        }});
        request2.setJsonBody("payload_for_endp_62.json", new Hashtable<String, Object>() {{
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
        final Response response2 = secure05bChaseCom.post(request2, "/events/analytics/public/v1/events/raw/");
        assertStatusCode(response2.code(), 200);
    }

    /**
     * authentication-related test
     */
    @Test
    public void testGetWebAuthLogonbox064() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/ (endp 1)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new DummyAuth());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/");
        assertStatusCode(response.code(), 200);
        assertCSSselect("main#main h1.accessible-text", "Chase.com home", response.body().string());
        assertCSSselect("html head title", "Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com", response.body().string());
        final String fromOrigin = CSSselect("html head link[href] @href", response.body().string());

        // GET https://secure05b.chase.com/web/auth/logonbox (endp 64)
        final HttpTarget secure05bChaseCom = getHttpClient("https://secure05b.chase.com", new DummyAuth());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("fromOrigin", fromOrigin);
            put("lang", "en");
        }});
        final Response response2 = secure05bChaseCom.get(request2, "/web/auth/logonbox");
        assertStatusCode(response2.code(), 200);
        assertCSSselect("html head title", "Chase Account login", response2.body().string());
    }
}

