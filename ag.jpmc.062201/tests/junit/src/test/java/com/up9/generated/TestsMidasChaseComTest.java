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
public class TestsMidasChaseComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_33.json")
    public void testGetStreamClick033(final JsonObject json) throws MalformedURLException, IOException
    {
        final String pageID = json.getString("pageID");
        final String param = json.getString("param");
        final String param1 = json.getString("param1");
        final String param2 = json.getString("param2");
        final String param3 = json.getString("param3");
        final String param4 = json.getString("param4");
        final String param5 = json.getString("param5");
        final String ssv_adf_traceid = json.getString("ssv_adf_traceid");
        final String ssv_locale = json.getString("ssv_locale");
        final String ssv_pageLayout = json.getString("ssv_pageLayout");
        final String ssv_random = json.getString("ssv_random");
        final String ssv_v1st = json.getString("ssv_v1st");
        final String ssv_v1st1 = json.getString("ssv_v1st1");

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
        final String ssv_creativeid = urlPart("?ssv_creativeid", JSONPath("$.offers[*].turl", response5.body().string()));
        final String ssv_midas_id = urlPart("?ssv_midas_id", JSONPath("$.offers[*].turl", response5.body().string()));
        final String ssv_transid = urlPart("?ssv_transid", JSONPath("$.offers[*].turl", response5.body().string()));

        // GET https://midas.chase.com/stream/click (endp 33)
        final HttpRequest request6 = new HttpRequest();
        request6.setQueryString(new Hashtable<String, Object>() {{
            put("ssv_channel", "web");
            put("ssv_creativeid", ssv_creativeid);
            put("ssv_locale", "en_us");
            put("ssv_midas_id", ssv_midas_id);
            put("ssv_random", ssv_random);
            put("ssv_transid", ssv_transid);
            put("ssv_v1st", ssv_v1st1);
        }});
        final Response response6 = midasChaseCom.get(request6, "/stream/click");
        assertStatusCode(response6.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_34.json")
    public void testGetStreamTag034(final JsonObject json) throws MalformedURLException, IOException
    {
        final String pageID = json.getString("pageID");
        final String ssv_eid = json.getString("ssv_eid");
        final String ssv_tmc = json.getString("ssv_tmc");
        final String ssv_v1st = json.getString("ssv_v1st");

        // GET https://midas.chase.com/stream/tag (endp 34)
        final HttpTarget midasChaseCom = getHttpClient("https://midas.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("pageID", pageID);
            put("ssv_eci", "");
            put("ssv_eid", ssv_eid);
            put("ssv_pfid", "");
            put("ssv_productid", "");
            put("ssv_src", "");
            put("ssv_tmc", ssv_tmc);
            put("ssv_v1st", ssv_v1st);
        }});
        final Response response = midasChaseCom.get(request, "/stream/tag");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_35.json")
    public void testGetStreamView035(final JsonObject json) throws MalformedURLException, IOException
    {
        final String pageID = json.getString("pageID");
        final String param = json.getString("param");
        final String param1 = json.getString("param1");
        final String param2 = json.getString("param2");
        final String param3 = json.getString("param3");
        final String param4 = json.getString("param4");
        final String param5 = json.getString("param5");
        final String ssv_adf_traceid = json.getString("ssv_adf_traceid");
        final String ssv_locale = json.getString("ssv_locale");
        final String ssv_locale1 = json.getString("ssv_locale1");
        final String ssv_pageLayout = json.getString("ssv_pageLayout");
        final String ssv_v1st = json.getString("ssv_v1st");
        final String ssv_v1st1 = json.getString("ssv_v1st1");

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
        final String ssv_creativeid = urlPart("?ssv_creativeid", JSONPath("$.offers[*].turl", response5.body().string()));
        final String ssv_midas_id = urlPart("?ssv_midas_id", JSONPath("$.offers[*].turl", response5.body().string()));
        final String ssv_transid = urlPart("?ssv_transid", JSONPath("$.offers[*].turl", response5.body().string()));

        // GET https://midas.chase.com/stream/view (endp 35)
        final HttpRequest request6 = new HttpRequest();
        request6.setQueryString(new Hashtable<String, Object>() {{
            put("ssv_channel", "web");
            put("ssv_creativeid", ssv_creativeid);
            put("ssv_locale", ssv_locale1);
            put("ssv_midas_id", ssv_midas_id);
            put("ssv_pageLayout", "prospect_a");
            put("ssv_random", randomInteger(220, 977));
            put("ssv_transid", ssv_transid);
            put("ssv_v1st", ssv_v1st1);
        }});
        final Response response6 = midasChaseCom.get(request6, "/stream/view");
        assertStatusCode(response6.code(), 200);
    }
}

