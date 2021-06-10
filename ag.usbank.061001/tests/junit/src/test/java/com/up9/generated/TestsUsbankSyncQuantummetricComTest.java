package com.up9.generated;

import com.up9.generated.Authentication;
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
public class TestsUsbankSyncQuantummetricComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_25.json")
    public void testPost25(final JsonObject json) throws MalformedURLException, IOException
    {
        final String Module = json.getString("Module");
        final String Q_CLIENTVERSION = json.getString("Q_CLIENTVERSION");
        final String Q_CLIENTVERSION1 = json.getString("Q_CLIENTVERSION1");
        final String Q_InterceptID = json.getString("Q_InterceptID");
        final String Q_ORIGIN = json.getString("Q_ORIGIN");
        final String X = json.getString("X");
        final String s = json.getString("s");

        // GET https://www.usbank.com/index.html (endp 4)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwUsbankCom.get(request, "/index.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("html head title", "Consumer banking | Personal banking | U.S. Bank", response.body().string());
        final String Q_LOC = CSSselect("html head link[href] @href", response.body().string());
        final String u = CSSselect("html head link[href] @href", response.body().string());

        // GET https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Asset.php (endp 21)
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

        // POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Targeting.php (endp 22)
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

        // POST https://usbank-sync.quantummetric.com/ (endp 25)
        final HttpTarget usbankSyncQuantummetricCom = getHttpClient("https://usbank-sync.quantummetric.com", new Authentication());
        final HttpRequest request4 = new HttpRequest();
        request4.setQueryString(new Hashtable<String, Object>() {{
            put("Q", "1");
            put("T", "B");
            put("X", X);
            put("Y", "1");
            put("s", s);
            put("t", getCurrentTimestamp());
            put("u", u);
            put("v", getCurrentTimestamp());
            put("z", "1");
        }});
        request4.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "text/plain");
        }});
        request4.setRawBody(readFile("payload_for_endp_25.txt", StandardCharsets.UTF_8));
        final Response response4 = usbankSyncQuantummetricCom.post(request4, "/");
        assertStatusCode(response4.code(), 200);
    }
}
