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
public class TestsOnboardingUsbankComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_73.json")
    public void testGetParamV1ApplicationsEnvironment73(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");

        // GET https://onboarding.usbank.com/{param}/v1/applications/environment (endp 73)
        final HttpTarget onboardingUsbankCom = getHttpClient("https://onboarding.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = onboardingUsbankCom.get(request, "/" + param + "/v1/applications/environment");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.name", "onboarding-application-intake", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_71.json")
    public void testPostKrncmrParam1Param2Param3Param4Param5Param6Param771(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");
        final String param1 = json.getString("param1");
        final String param2 = json.getString("param2");
        final String param3 = json.getString("param3");
        final String param4 = json.getString("param4");
        final String param5 = json.getString("param5");
        final String param6 = json.getString("param6");
        final String sensor_data = json.getString("sensor_data");

        // POST https://onboarding.usbank.com/KrNCmr/{param1}/{param2}/{param3}/{param4}/{param5}/{param6}/{param7} (endp 71)
        final HttpTarget onboardingUsbankCom = getHttpClient("https://onboarding.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_71.json", new Hashtable<String, Object>() {{
            put("$.sensor_data", sensor_data);
        }});
        final Response response = onboardingUsbankCom.post(request, "/KrNCmr/" + param + "/" + param1 + "/" + param2 + "/" + param3 + "/" + param4 + "/" + param5 + "/" + param6);
        assertStatusCode(response.code(), 201);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_74.json")
    public void testGetCardsCardidParam1Param2Intro74(final JsonObject json) throws MalformedURLException, IOException
    {
        final String cardId = json.getString("cardId");
        final String param = json.getString("param");
        final String param1 = json.getString("param1");

        // GET https://onboarding.usbank.com/cards/{cardId}/{param1}/{param2}/intro (endp 74)
        final HttpTarget onboardingUsbankCom = getHttpClient("https://onboarding.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = onboardingUsbankCom.get(request, "/cards/" + cardId + "/" + param + "/" + param1 + "/intro");
        assertStatusCode(response.code(), 200);
        assertCSSselect("html head title", "U.S. Bank Credit Card", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_39.json")
    public void testGetDepositsDepositidPiStart39(final JsonObject json) throws MalformedURLException, IOException
    {
        final String depositId = json.getString("depositId");

        // GET https://onboarding.usbank.com/deposits/{depositId}/PI/start (endp 39)
        final HttpTarget onboardingUsbankCom = getHttpClient("https://onboarding.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = onboardingUsbankCom.get(request, "/deposits/" + depositId + "/PI/start");
        assertStatusCode(response.code(), 200);
        assertCSSselect("html head title", "U.S. Bank Checking", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_75.json")
    public void testPostProxiesV1Param75(final JsonObject json) throws MalformedURLException, IOException
    {
        final String icsLocationCode = json.getString("icsLocationCode");
        final String offerID = json.getString("offerID");
        final String param = json.getString("param");
        final String sourceCode = json.getString("sourceCode");

        // POST https://onboarding.usbank.com/proxies/v1/{param} (endp 75)
        final HttpTarget onboardingUsbankCom = getHttpClient("https://onboarding.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
            put("x-requested-with", "XMLHttpRequest");
        }});
        request.setJsonBody("payload_for_endp_75.json", new Hashtable<String, Object>() {{
            put("$.icsLocationCode", icsLocationCode);
            put("$.offerID", offerID);
            put("$.sourceCode", sourceCode);
        }});
        final Response response = onboardingUsbankCom.post(request, "/proxies/v1/" + param);
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.ratesAndFeesResponse.productDetailData.productVariableData[*].productName", "U.S. Bank Altitude\u00c2\u00ae Connect Visa Signature\u00c2\u00ae Card", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_76.json")
    public void testPostProxiesV1OadTermsAndConditions76(final JsonObject json) throws MalformedURLException, IOException
    {
        final String locationCode = json.getString("locationCode");
        final String offerId = json.getString("offerId");
        final String sourceCode = json.getString("sourceCode");

        // POST https://onboarding.usbank.com/proxies/v1/oad/terms-and-conditions (endp 76)
        final HttpTarget onboardingUsbankCom = getHttpClient("https://onboarding.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
            put("x-requested-with", "XMLHttpRequest");
        }});
        request.setJsonBody("payload_for_endp_76.json", new Hashtable<String, Object>() {{
            put("$.locationCode", locationCode);
            put("$.offerId", offerId);
            put("$.sourceCode", sourceCode);
        }});
        final Response response = onboardingUsbankCom.post(request, "/proxies/v1/oad/terms-and-conditions");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_40.json")
    public void testPostProxiesV1Validateaddress40(final JsonObject json) throws MalformedURLException, IOException
    {
        final String securitytoken = json.getString("securitytoken");
        final String usAddress = json.getString("usAddress");
        final String zipCode = json.getString("zipCode");

        // POST https://onboarding.usbank.com/proxies/v1/validateAddress (endp 40)
        final HttpTarget onboardingUsbankCom = getHttpClient("https://onboarding.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
            put("securitytoken", securitytoken);
            put("x-requested-with", "X-Requested-With");
        }});
        request.setJsonBody("payload_for_endp_40.json", new Hashtable<String, Object>() {{
            put("$.customerAddress.usAddress.*", usAddress);
            put("$.customerAddress.usAddress.zipCode", zipCode);
        }});
        final Response response = onboardingUsbankCom.post(request, "/proxies/v1/validateAddress");
        assertStatusCode(response.code(), 200);
    }
}

