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
        final String PRODUCT_CODE = json.getString("PRODUCT_CODE");
        final String Q_CLIENTVERSION = json.getString("Q_CLIENTVERSION");
        final String Q_InterceptID = json.getString("Q_InterceptID");
        final String Q_ORIGIN = json.getString("Q_ORIGIN");
        final String X = json.getString("X");
        final String s = json.getString("s");

        // GET https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Asset.php (endp 21)
        final HttpTarget siteinterceptQualtricsCom = getHttpClient("https://siteintercept.qualtrics.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("Module", Module);
            put("Q_CLIENTTYPE", "web");
            put("Q_CLIENTVERSION", Q_CLIENTVERSION);
            put("Q_InterceptID", Q_InterceptID);
            put("Q_ORIGIN", Q_ORIGIN);
            put("Version", randomInteger(1, 26));
        }});
        final Response response = siteinterceptQualtricsCom.get(request, "/WRSiteInterceptEngine/Asset.php");
        assertStatusCode(response.code(), 200);

        // GET https://apply.usbank.com/apply/apply.html (endp 36)
        final HttpTarget applyUsbankCom = getHttpClient("https://apply.usbank.com", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("PRODUCT_CODE", PRODUCT_CODE);
            put("SUB_PRODUCT_CODE", "PI");
        }});
        final Response response2 = applyUsbankCom.get(request2, "/apply/apply.html");
        assertStatusCode(response2.code(), 302);
        final String u = getHeader(response2, "location");

        // POST https://usbank-sync.quantummetric.com/ (endp 25)
        final HttpTarget usbankSyncQuantummetricCom = getHttpClient("https://usbank-sync.quantummetric.com", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        request3.setQueryString(new Hashtable<String, Object>() {{
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
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "text/plain");
        }});
        request3.setRawBody(readFile("payload_for_endp_25.txt", StandardCharsets.UTF_8));
        final Response response3 = usbankSyncQuantummetricCom.post(request3, "/");
        assertStatusCode(response3.code(), 200);
    }
}

