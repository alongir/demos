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
public class TestsSecure03bChaseComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_71.json")
    public void testPostEventsEventid71(final JsonObject json) throws MalformedURLException, IOException
    {
        final String app = json.getString("app");
        final String eventId = json.getString("eventId");
        final String modifiedSince = json.getString("modifiedSince");
        final String referer = json.getString("referer");
        final String session = json.getString("session");
        final String svrid = json.getString("svrid");
        final String visitID = json.getString("visitID");

        // POST https://secure03b.chase.com/events/{eventId} (endp 71)
        final HttpTarget secure03bChaseCom = getHttpClient("https://secure03b.chase.com", new Authentication());
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
        request.setRawBody(readFile("payload_for_endp_71.txt", StandardCharsets.UTF_8));
        final Response response = secure03bChaseCom.post(request, "/events/" + eventId);
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testPostEventsAnalyticsPublicV1EventsRaw70() throws MalformedURLException, IOException
    {
        // POST https://secure03b.chase.com/events/analytics/public/v1/events/raw/ (endp 70)
        final HttpTarget secure03bChaseCom = getHttpClient("https://secure03b.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_70.json", new Hashtable<String, Object>() {{
        }});
        final Response response = secure03bChaseCom.post(request, "/events/analytics/public/v1/events/raw/");
        assertStatusCode(response.code(), 200);
    }

    /**
     * authentication-related test
     */
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_72.json")
    public void testGetWebAuthLogonbox72(final JsonObject json) throws MalformedURLException, IOException
    {
        final String CELL = json.getString("CELL");

        // GET https://creditcards.chase.com/cash-back-credit-cards/freedom/flex (endp 65)
        final HttpTarget creditcardsChaseCom = getHttpClient("https://creditcards.chase.com", new DummyAuth());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("CELL", CELL);
        }});
        final Response response = creditcardsChaseCom.get(request, "/cash-back-credit-cards/freedom/flex");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#leftPanel div.left-rail-inner.clearfix div.no-padding.left-rail-header.freedomflex h1.card-title sup.sm-fix", "SM", response.body().string());
        assertCSSselect("html head title", "Chase Freedom Flex Credit Card | Chase.com", response.body().string());
        final String fromOrigin = CSSselect("ul#hamNav-links-general li a.chaseanalytics-track-link[href] @href", response.body().string());

        // GET https://secure03b.chase.com/web/auth/logonbox (endp 72)
        final HttpTarget secure03bChaseCom = getHttpClient("https://secure03b.chase.com", new DummyAuth());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("fromOrigin", fromOrigin);
            put("lang", "en");
            put("navKey", "reviewCreditCardOffers");
        }});
        final Response response2 = secure03bChaseCom.get(request2, "/web/auth/logonbox");
        assertStatusCode(response2.code(), 200);
        assertCSSselect("html head title", "Chase Account login", response2.body().string());
    }
}

