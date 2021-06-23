package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import java.net.MalformedURLException;
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
public class TestsTargetChaseComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_31.json")
    public void testPostRestV1Delivery31(final JsonObject json) throws MalformedURLException, IOException
    {
        final String client = json.getString("client");
        final int colorDepth = json.getInt("colorDepth");
        final int height = json.getInt("height");
        final int height1 = json.getInt("height1");
        final String locationHint = json.getString("locationHint");
        final String logging = json.getString("logging");
        final String marketingCloudVisitorId = json.getString("marketingCloudVisitorId");
        final String mboxName = json.getString("mboxName");
        final String name = json.getString("name");
        final String pageTitle = json.getString("pageTitle");
        final String profileParameters = json.getString("profileParameters");
        final String q = json.getString("q");
        final String requestId = json.getString("requestId");
        final String sessionId = json.getString("sessionId");
        final int timeOffsetInMinutes = json.getInt("timeOffsetInMinutes");
        final String tntId = json.getString("tntId");
        final String token = json.getString("token");
        final String userAgent = json.getString("userAgent");
        final String version = json.getString("version");
        final String webGLRenderer = json.getString("webGLRenderer");
        final int width = json.getInt("width");
        final int width1 = json.getInt("width1");

        // GET https://www.chase.com/personal/investments/advisor (endp 28)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/personal/investments/advisor");
        assertStatusCode(response.code(), 200);
        assertCSSselect("main#main h1.accessible-text", "J.P. Morgan Financial Advisors", response.body().string());
        assertCSSselect("html head title", "Connect with a J.P. Morgan Financial Advisor | Chase.com", response.body().string());

        // GET https://locator.chase.com/search (endp 76)
        final HttpTarget locatorChaseCom = getHttpClient("https://locator.chase.com", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("q", q);
        }});
        final Response response2 = locatorChaseCom.get(request2, "/search");
        assertStatusCode(response2.code(), 200);
        assertCSSselect("div#js-locator div.Locator-content h1.Locator-title.Heading--locator.js-Locator-title", "Find a Chase ATM or branch near you", response2.body().string());
        assertCSSselect("html head title", "Branches and ATMs | Chase Bank", response2.body().string());
        final String referringUrl = CSSselect("header#Header div.Header-wrapper.l-container div.Header-mainRow div.Header-left div div.js-focustrap div div a.Header-expandedLink--atm.Text--expanded[href] @href", response2.body().string());
        final String url = CSSselect("header#Header div.Header-wrapper.l-container div.Header-mainRow div.Header-left div div.js-focustrap div a.Text--expanded[href] @href", response2.body().string());

        // POST https://target.chase.com/rest/v1/delivery (endp 31)
        final HttpTarget targetChaseCom = getHttpClient("https://target.chase.com", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        request3.setQueryString(new Hashtable<String, Object>() {{
            put("client", client);
            put("sessionId", sessionId);
            put("version", version);
        }});
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
        }});
        request3.setJsonBody("payload_for_endp_31.json", new Hashtable<String, Object>() {{
            put("$.context.address.referringUrl", referringUrl);
            put("$.context.address.url", url);
            put("$.context.browser.webGLRenderer", webGLRenderer);
            put("$.context.screen.colorDepth", colorDepth);
            put("$.context.screen.height", height);
            put("$.context.screen.width", width);
            put("$.context.timeOffsetInMinutes", timeOffsetInMinutes);
            put("$.context.userAgent", userAgent);
            put("$.context.window.height", height1);
            put("$.context.window.width", width1);
            put("$.execute.mboxes[*].name", name);
            put("$.execute.mboxes[*].parameters.mboxName", mboxName);
            put("$.execute.mboxes[*].parameters.pageTitle", pageTitle);
            put("$.execute.mboxes[*].profileParameters.*", profileParameters);
            put("$.experienceCloud.analytics.logging", logging);
            put("$.experienceCloud.audienceManager.locationHint", locationHint);
            put("$.id.marketingCloudVisitorId", marketingCloudVisitorId);
            put("$.id.tntId", tntId);
            put("$.property.token", token);
            put("$.requestId", requestId);
        }});
        final Response response3 = targetChaseCom.post(request3, "/rest/v1/delivery");
        assertStatusCode(response3.code(), 200);
        assertJSONPath("$.execute.mboxes[*].analytics.payload.pe", "tnt", response3.body().string());
    }
}

