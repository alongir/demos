package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.DummyAuth;
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
public class TestsOnlinebankingUsbankComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_45.json")
    public void testPostParam1XwntParam2MpdlqParam3Param4IfsxwParam545(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");
        final String param1 = json.getString("param1");
        final String param2 = json.getString("param2");
        final String param3 = json.getString("param3");
        final String param4 = json.getString("param4");
        final String sensor_data = json.getString("sensor_data");

        // POST https://onlinebanking.usbank.com/{param1}/xwNT/{param2}/mpdLQ/{param3}/{param4}/IFsXW/{param5} (endp 45)
        final HttpTarget onlinebankingUsbankCom = getHttpClient("https://onlinebanking.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_45.json", new Hashtable<String, Object>() {{
            put("$.sensor_data", sensor_data);
        }});
        final Response response = onlinebankingUsbankCom.post(request, "/" + param + "/xwNT/" + param1 + "/mpdLQ/" + param2 + "/" + param3 + "/IFsXW/" + param4);
        assertStatusCode(response.code(), 201);
    }

    /**
     * authentication-related test
     */
    @Test
    public void testGetOlsLoginassistRetriveid46() throws MalformedURLException, IOException
    {
        // GET https://onlinebanking.usbank.com/OLS/LoginAssist/RetriveId (endp 46)
        final HttpTarget onlinebankingUsbankCom = getHttpClient("https://onlinebanking.usbank.com", new DummyAuth());
        final HttpRequest request = new HttpRequest();
        final Response response = onlinebankingUsbankCom.get(request, "/OLS/LoginAssist/RetriveId");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div.mainBodyWidth div.body-content.bodyDiv div div.la__touchnav-container a", "Help", response.body().string());
        assertCSSselect("html head title", "Login Assistance", response.body().string());
    }

    @Test
    public void testGetOlsPublicEnrollmentIndex47() throws MalformedURLException, IOException
    {
        // GET https://onlinebanking.usbank.com/OLS/Public/Enrollment/Index (endp 47)
        final HttpTarget onlinebankingUsbankCom = getHttpClient("https://onlinebanking.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("isPartnerOLB", "true");
        }});
        final Response response = onlinebankingUsbankCom.get(request, "/OLS/Public/Enrollment/Index");
        assertStatusCode(response.code(), 200);
        assertCSSselect("html head title", "Enrollment", response.body().string());
    }
}

