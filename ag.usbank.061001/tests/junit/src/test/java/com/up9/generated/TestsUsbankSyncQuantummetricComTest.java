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
    @JsonFileSource(resources = "/dataset_23.json")
    public void testPost23(final JsonObject json) throws MalformedURLException, IOException
    {
        final String PRODUCT_CODE = json.getString("PRODUCT_CODE");
        final String X = json.getString("X");
        final String s = json.getString("s");

        // GET https://apply.usbank.com/apply/apply.html (endp 33)
        final HttpTarget applyUsbankCom = getHttpClient("https://apply.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("PRODUCT_CODE", PRODUCT_CODE);
            put("SUB_PRODUCT_CODE", "PI");
        }});
        final Response response = applyUsbankCom.get(request, "/apply/apply.html");
        assertStatusCode(response.code(), 302);
        final String u = getHeader(response, "location");

        // POST https://usbank-sync.quantummetric.com/ (endp 23)
        final HttpTarget usbankSyncQuantummetricCom = getHttpClient("https://usbank-sync.quantummetric.com", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
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
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "text/plain");
        }});
        request2.setRawBody(readFile("payload_for_endp_23.txt", StandardCharsets.UTF_8));
        final Response response2 = usbankSyncQuantummetricCom.post(request2, "/");
        assertStatusCode(response2.code(), 200);
    }
}

