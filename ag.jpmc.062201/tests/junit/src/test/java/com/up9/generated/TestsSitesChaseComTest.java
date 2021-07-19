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
public class TestsSitesChaseComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_102.json")
    public void testGetContentCreativesPublicHeroesHeroeidJuneCid102(final JsonObject json) throws MalformedURLException, IOException
    {
        final String heroeId = json.getString("heroeId");
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
        final String ssv_v1st = json.getString("ssv_v1st");

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

        // GET https://sites.chase.com/content/Creatives/Public/Heroes/{heroeId}/June/{cid} (endp 102)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request6 = new HttpRequest();
        final Response response6 = sitesChaseCom.get(request6, "/content/Creatives/Public/Heroes/" + heroeId + "/June/" + cid);
        assertStatusCode(response6.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_37.json")
    public void testGetContentCreativesPublicTilesTileidMarchCid037(final JsonObject json) throws MalformedURLException, IOException
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
        final String ssv_v1st = json.getString("ssv_v1st");
        final String tileId = json.getString("tileId");

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

        // GET https://sites.chase.com/content/Creatives/Public/Tiles/{tileId}/March/{cid} (endp 37)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request6 = new HttpRequest();
        final Response response6 = sitesChaseCom.get(request6, "/content/Creatives/Public/Tiles/" + tileId + "/March/" + cid);
        assertStatusCode(response6.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_38.json")
    public void testGetContentCreativesPublicTripletsTripletidJulyCid038(final JsonObject json) throws MalformedURLException, IOException
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
        final String ssv_v1st = json.getString("ssv_v1st");
        final String tripletId = json.getString("tripletId");

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

        // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/July/{cid} (endp 38)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request6 = new HttpRequest();
        final Response response6 = sitesChaseCom.get(request6, "/content/Creatives/Public/Triplets/" + tripletId + "/July/" + cid);
        assertStatusCode(response6.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_103.json")
    public void testGetContentCreativesPublicTripletsTripletidMarchCid103(final JsonObject json) throws MalformedURLException, IOException
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
        final String ssv_v1st = json.getString("ssv_v1st");
        final String tripletId = json.getString("tripletId");

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

        // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/March/{cid} (endp 103)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request6 = new HttpRequest();
        final Response response6 = sitesChaseCom.get(request6, "/content/Creatives/Public/Triplets/" + tripletId + "/March/" + cid);
        assertStatusCode(response6.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_40.json")
    public void testGetContentCreativesPublicTripletsTripletidMayCid040(final JsonObject json) throws MalformedURLException, IOException
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
        final String ssv_v1st = json.getString("ssv_v1st");
        final String tripletId = json.getString("tripletId");

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

        // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/May/{cid} (endp 40)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request6 = new HttpRequest();
        final Response response6 = sitesChaseCom.get(request6, "/content/Creatives/Public/Triplets/" + tripletId + "/May/" + cid);
        assertStatusCode(response6.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_39.json")
    public void testGetContentCreativesPublicTripletsTripletidNovemberCid039(final JsonObject json) throws MalformedURLException, IOException
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
        final String ssv_v1st = json.getString("ssv_v1st");
        final String tripletId = json.getString("tripletId");

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

        // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/November/{cid} (endp 39)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request6 = new HttpRequest();
        final Response response6 = sitesChaseCom.get(request6, "/content/Creatives/Public/Triplets/" + tripletId + "/November/" + cid);
        assertStatusCode(response6.code(), 200);
    }
}

