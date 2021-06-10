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
    @JsonFileSource(resources = "/dataset_35.json")
    public void testPostParamV1ApplicationsApplicationidPatch35(final JsonObject json) throws MalformedURLException, IOException
    {
        final String countriesOfCitizenship = json.getString("countriesOfCitizenship");
        final String email = json.getString("email");
        final String lastName = json.getString("lastName");
        final String monthlyHousingPayment = json.getString("monthlyHousingPayment");
        final String number = json.getString("number");
        final String op = json.getString("op");
        final String param = json.getString("param");
        final String path = json.getString("path");
        final String productCode = json.getString("productCode");

        // POST https://onboarding.usbank.com/{param}/v1/applications (endp 34)
        final HttpTarget onboardingUsbankCom = getHttpClient("https://onboarding.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
            put("x-requested-with", "X-Requested-With");
        }});
        request.setJsonBody("payload_for_endp_34.json", new Hashtable<String, Object>() {{
            put("$.products[*].productCode", productCode);
        }});
        final Response response = onboardingUsbankCom.post(request, "/" + param + "/v1/applications");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$.applicants[*].addresses.primary.country", "US", response.body().string());
        final String applicationId = JSONPath("$.applicationId", response.body().string());
        final String securitytoken = getHeader(response, "securitytoken");

        // POST https://onboarding.usbank.com/{param}/v1/applications/{applicationId}/patch (endp 35)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
            put("securitytoken", securitytoken);
            put("x-requested-with", "X-Requested-With");
        }});
        request2.setJsonBody("payload_for_endp_35.json", new Hashtable<String, Object>() {{
            put("$[*].op", op);
            put("$[*].path", path);
            put("$[*].value.countriesOfCitizenship[*]", countriesOfCitizenship);
            put("$[*].value.lastName", lastName);
            put("$[*].value.monthlyHousingPayment", monthlyHousingPayment);
            put("$[*].value.number", number);
            put("$[*].value.personal.email", email);
        }});
        final Response response2 = onboardingUsbankCom.post(request2, "/" + param + "/v1/applications/" + applicationId + "/patch");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.applicants[*].addresses.primary.country", "US", response2.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_36.json")
    public void testGetDepositsDepositidPiStart36(final JsonObject json) throws MalformedURLException, IOException
    {
        final String depositId = json.getString("depositId");

        // GET https://onboarding.usbank.com/deposits/{depositId}/PI/start (endp 36)
        final HttpTarget onboardingUsbankCom = getHttpClient("https://onboarding.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = onboardingUsbankCom.get(request, "/deposits/" + depositId + "/PI/start");
        assertStatusCode(response.code(), 200);
        assertCSSselect("html head title", "U.S. Bank Checking", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_37.json")
    public void testPostProxiesV1Validateaddress37(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");
        final String productCode = json.getString("productCode");
        final String usAddress = json.getString("usAddress");
        final String zipCode = json.getString("zipCode");

        // POST https://onboarding.usbank.com/{param}/v1/applications (endp 34)
        final HttpTarget onboardingUsbankCom = getHttpClient("https://onboarding.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
            put("x-requested-with", "X-Requested-With");
        }});
        request.setJsonBody("payload_for_endp_34.json", new Hashtable<String, Object>() {{
            put("$.products[*].productCode", productCode);
        }});
        final Response response = onboardingUsbankCom.post(request, "/" + param + "/v1/applications");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$.applicants[*].addresses.primary.country", "US", response.body().string());
        final String securitytoken = getHeader(response, "securitytoken");

        // POST https://onboarding.usbank.com/proxies/v1/validateAddress (endp 37)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
            put("securitytoken", securitytoken);
            put("x-requested-with", "X-Requested-With");
        }});
        request2.setJsonBody("payload_for_endp_37.json", new Hashtable<String, Object>() {{
            put("$.customerAddress.usAddress.*", usAddress);
            put("$.customerAddress.usAddress.zipCode", zipCode);
        }});
        final Response response2 = onboardingUsbankCom.post(request2, "/proxies/v1/validateAddress");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.address.city", "NEWARK", response2.body().string());
    }
}

