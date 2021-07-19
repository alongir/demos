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
    public void testPostRestV1Delivery031(final JsonObject json) throws MalformedURLException, IOException
    {
        final String client = json.getString("client");
        final int colorDepth = json.getInt("colorDepth");
        final int height = json.getInt("height");
        final int height1 = json.getInt("height1");
        final String heroeId = json.getString("heroeId");
        final String locationHint = json.getString("locationHint");
        final String logging = json.getString("logging");
        final String marketingCloudVisitorId = json.getString("marketingCloudVisitorId");
        final String mboxName = json.getString("mboxName");
        final String name = json.getString("name");
        final String pageID = json.getString("pageID");
        final String pageTitle = json.getString("pageTitle");
        final String param = json.getString("param");
        final String param1 = json.getString("param1");
        final String param2 = json.getString("param2");
        final String param3 = json.getString("param3");
        final String param4 = json.getString("param4");
        final String param5 = json.getString("param5");
        final String param6 = json.getString("param6");
        final int pixelRatio = json.getInt("pixelRatio");
        final String profileParameters = json.getString("profileParameters");
        final String q = json.getString("q");
        final String requestId = json.getString("requestId");
        final String sessionId = json.getString("sessionId");
        final String ssv_adf_traceid = json.getString("ssv_adf_traceid");
        final String ssv_locale = json.getString("ssv_locale");
        final String ssv_pageLayout = json.getString("ssv_pageLayout");
        final String ssv_v1st = json.getString("ssv_v1st");
        final int timeOffsetInMinutes = json.getInt("timeOffsetInMinutes");
        final String tntId = json.getString("tntId");
        final String token = json.getString("token");
        final String userAgent = json.getString("userAgent");
        final String version = json.getString("version");
        final String webGLRenderer = json.getString("webGLRenderer");
        final int width = json.getInt("width");
        final int width1 = json.getInt("width1");

        // GET https://www.chase.com/ (endp 1)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/");
        assertStatusCode(response.code(), 200);
        assertCSSselect("main#main h1.accessible-text", "Chase.com home", response.body().string());
        assertCSSselect("html head title", "Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com", response.body().string());

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/primary-triplet/{param2}/module.html (endp 16)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = wwwChaseCom.get(request2, "/content/chase-ux/en/structured/module/" + param + "/primary-triplet/" + param1 + "/module.html");
        assertStatusCode(response2.code(), 200);

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/ad-geo/{param2}/module.html (endp 21)
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response3 = wwwChaseCom.get(request3, "/content/chase-ux/en/structured/module/" + param + "/ad-geo/" + param2 + "/module.html");
        assertStatusCode(response3.code(), 200);

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/index/{param2}/{param3}/module.html (endp 22)
        final HttpRequest request4 = new HttpRequest();
        request4.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response4 = wwwChaseCom.get(request4, "/content/chase-ux/en/structured/module/" + param + "/index/" + param3 + "/" + param4 + "/module.html");
        assertStatusCode(response4.code(), 200);

        // GET https://midas.chase.com/prweb/PRRestService/{param}/v1/MakeDecision (endp 32)
        final HttpTarget midasChaseCom = getHttpClient("https://midas.chase.com", new Authentication());
        final HttpRequest request5 = new HttpRequest();
        request5.setQueryString(new Hashtable<String, Object>() {{
            put("pageID", pageID);
            put("ssv_accttype", "");
            put("ssv_adf_traceid", ssv_adf_traceid);
            put("ssv_channel", "web");
            put("ssv_cigseg", "");
            put("ssv_eci", "");
            put("ssv_locale", ssv_locale);
            put("ssv_origin", "");
            put("ssv_pageLayout", ssv_pageLayout);
            put("ssv_pfid", "");
            put("ssv_pnpc", "");
            put("ssv_product", "");
            put("ssv_random", randomInteger(220, 977));
            put("ssv_siteacct", "");
            put("ssv_sitebrand", "");
            put("ssv_userType", "");
            put("ssv_v1st", ssv_v1st);
            put("ssv_zip", "");
            put("ssvm_lids", "");
            put("ssvm_pnpcs", "");
            put("ssvm_products", "");
            put("time", getCurrentTimestamp());
        }});
        final Response response5 = midasChaseCom.get(request5, "/prweb/PRRestService/" + param5 + "/v1/MakeDecision");
        assertStatusCode(response5.code(), 200);
        assertJSONPath("$.offers[*].ctype", "html", response5.body().string());
        final String cid = urlPart("/7", JSONPath("$.offers[*].cid", response5.body().string()));

        // GET https://sites.chase.com/content/Creatives/Public/Heroes/{heroeId}/March/{cid} (endp 36)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request6 = new HttpRequest();
        final Response response6 = sitesChaseCom.get(request6, "/content/Creatives/Public/Heroes/" + heroeId + "/March/" + cid);
        assertStatusCode(response6.code(), 200);

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/carousel-single-images_alt/{param2}/module.html (endp 19)
        final HttpRequest request7 = new HttpRequest();
        request7.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response7 = wwwChaseCom.get(request7, "/content/chase-ux/en/structured/module/" + param + "/carousel-single-images_alt/" + param6 + "/module.html");
        assertStatusCode(response7.code(), 200);

        // GET https://www.chase.com/personal/investments/advisor (endp 28)
        final HttpRequest request8 = new HttpRequest();
        final Response response8 = wwwChaseCom.get(request8, "/personal/investments/advisor");
        assertStatusCode(response8.code(), 200);
        assertCSSselect("main#main h1.accessible-text", "J.P. Morgan Financial Advisors", response8.body().string());
        assertCSSselect("html head title", "Connect with a J.P. Morgan Financial Advisor | Chase.com", response8.body().string());

        // GET https://locator.chase.com/search (endp 76)
        final HttpTarget locatorChaseCom = getHttpClient("https://locator.chase.com", new Authentication());
        final HttpRequest request9 = new HttpRequest();
        request9.setQueryString(new Hashtable<String, Object>() {{
            put("q", q);
        }});
        final Response response9 = locatorChaseCom.get(request9, "/search");
        assertStatusCode(response9.code(), 200);
        assertCSSselect("div#js-locator div.Locator-content h1.Locator-title.Heading--locator.js-Locator-title", "Find a Chase ATM or branch near you", response9.body().string());
        assertCSSselect("html head title", "Branches and ATMs | Chase Bank", response9.body().string());
        final String referringUrl = CSSselect("header#Header div.Header-wrapper.l-container div.Header-mainRow div.Header-left div div.js-focustrap div div a.Header-expandedLink--atm.Text--expanded[href] @href", response9.body().string());
        final String url = CSSselect("header#Header div.Header-wrapper.l-container div.Header-mainRow div.Header-left div div.js-focustrap div a.Text--expanded[href] @href", response9.body().string());

        // POST https://target.chase.com/rest/v1/delivery (endp 31)
        final HttpTarget targetChaseCom = getHttpClient("https://target.chase.com", new Authentication());
        final HttpRequest request10 = new HttpRequest();
        request10.setQueryString(new Hashtable<String, Object>() {{
            put("client", client);
            put("sessionId", sessionId);
            put("version", version);
        }});
        request10.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
        }});
        request10.setJsonBody("payload_for_endp_31.json", new Hashtable<String, Object>() {{
            put("$.context.address.referringUrl", referringUrl);
            put("$.context.address.url", url);
            put("$.context.browser.webGLRenderer", webGLRenderer);
            put("$.context.screen.colorDepth", colorDepth);
            put("$.context.screen.height", height);
            put("$.context.screen.pixelRatio", pixelRatio);
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
        final Response response10 = targetChaseCom.post(request10, "/rest/v1/delivery");
        assertStatusCode(response10.code(), 200);
        assertJSONPath("$.execute.mboxes[*].analytics.payload.pe", "tnt", response10.body().string());
    }
}

