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
public class TestsSiteinterceptQualtricsComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_18.json")
    public void testPostWrsiteinterceptengine18(final JsonObject json) throws MalformedURLException, IOException
    {
        final String BrandDC = json.getString("BrandDC");
        final String Module = json.getString("Module");
        final String Q_CLIENTVERSION = json.getString("Q_CLIENTVERSION");
        final String Q_CLIENTVERSION1 = json.getString("Q_CLIENTVERSION1");
        final String Q_CLIENTVERSION2 = json.getString("Q_CLIENTVERSION2");
        final String Q_InterceptID = json.getString("Q_InterceptID");
        final String Q_ORIGIN = json.getString("Q_ORIGIN");

        // GET https://www.usbank.com/bank-accounts/checking-accounts.html (endp 1)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwUsbankCom.get(request, "/bank-accounts/checking-accounts.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("dialog.dialog.shield-zipcodes[name=\"#saveXZip\"] div.content div.heading.large h1", "Zip Code", response.body().string());
        assertCSSselect("html head title", "Checking accounts | Open a Personal Checking Account | U.S. Bank", response.body().string());
        final String Q_LOC = CSSselect("html head meta[content] @content", response.body().string());

        // GET https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Asset.php (endp 19)
        final HttpTarget siteinterceptQualtricsCom = getHttpClient("https://siteintercept.qualtrics.com", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("Module", Module);
            put("Q_CLIENTTYPE", "web");
            put("Q_CLIENTVERSION", Q_CLIENTVERSION);
            put("Q_InterceptID", Q_InterceptID);
            put("Q_ORIGIN", Q_ORIGIN);
            put("Version", randomInteger(1, 26));
        }});
        final Response response2 = siteinterceptQualtricsCom.get(request2, "/WRSiteInterceptEngine/Asset.php");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.CreativeDefinition.Options.Message.Headline.Text", "Will you take our survey?", response2.body().string());
        final String Q_ZoneID = JSONPath("$.CreativeDefinition.ZoneID", response2.body().string());

        // POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Targeting.php (endp 20)
        final HttpRequest request3 = new HttpRequest();
        request3.setQueryString(new Hashtable<String, Object>() {{
            put("Q_CLIENTTYPE", "web");
            put("Q_CLIENTVERSION", Q_CLIENTVERSION1);
            put("Q_ZoneID", Q_ZoneID);
        }});
        request3.setFormData(new Hashtable<String, Object>() {{
            put("Q_LOC", Q_LOC);
        }});
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/x-www-form-urlencoded");
        }});
        final Response response3 = siteinterceptQualtricsCom.post(request3, "/WRSiteInterceptEngine/Targeting.php");
        assertStatusCode(response3.code(), 200);
        assertJSONPath("$.ClientSideIntercepts[*].LogicTree.Left.Left.Type", "LogicNode", response3.body().string());
        final String Q_ASID = JSONPath("$.ClientSideIntercepts[*].ActionSets[*].ActionSetID", response3.body().string());
        final String Q_CID = urlPart("?Q_CID", JSONPath("$.ClientSideIntercepts[*].ActionSets[*].Target.*", response3.body().string()));
        final String Q_SIID = urlPart("?Q_SIID", JSONPath("$.ClientSideIntercepts[*].ActionSets[*].Target.*", response3.body().string()));
        final String SurveyID = urlPart("/3", JSONPath("$.ClientSideIntercepts[*].ActionSets[*].Target.OriginalURL", response3.body().string()));

        // POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/ (endp 18)
        final HttpRequest request4 = new HttpRequest();
        request4.setQueryString(new Hashtable<String, Object>() {{
            put("Q_ASID", Q_ASID);
            put("Q_CID", Q_CID);
            put("Q_CLIENTTYPE", "web");
            put("Q_CLIENTVERSION", Q_CLIENTVERSION2);
            put("Q_Impress", "1");
            put("Q_SIID", Q_SIID);
            put("r", getCurrentTimestamp());
        }});
        request4.setFormData(new Hashtable<String, Object>() {{
            put("BrandDC", BrandDC);
            put("BrandID", "usbank");
            put("SurveyID", SurveyID);
        }});
        request4.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/x-www-form-urlencoded");
        }});
        final Response response4 = siteinterceptQualtricsCom.post(request4, "/WRSiteInterceptEngine/");
        assertStatusCode(response4.code(), 200);
    }
}

