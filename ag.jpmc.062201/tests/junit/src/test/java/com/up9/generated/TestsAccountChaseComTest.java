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
public class TestsAccountChaseComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_58.json")
    public void testGetConsumerBankingSeo58(final JsonObject json) throws MalformedURLException, IOException
    {
        final String ssv_creativeid = json.getString("ssv_creativeid");

        // GET https://account.chase.com/consumer/banking/seo (endp 58)
        final HttpTarget accountChaseCom = getHttpClient("https://account.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("jp_aid_a", ssv_creativeid);
            put("jp_aid_p", "retail_checking_hp/tile");
        }});
        final Response response = accountChaseCom.get(request, "/consumer/banking/seo");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#leaving-modal div.leaving-modal-center div.leaving-modal-title h1", "You're Now Leaving Chase", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_59.json")
    public void testPostConsumerBankingSeo59(final JsonObject json) throws MalformedURLException, IOException
    {
        final String Payload_ContactPoint = json.getString("Payload_ContactPoint");
        final String Payload_ProductSelection = json.getString("Payload_ProductSelection");
        final String form_build_id = json.getString("form_build_id");
        final String referingURL = json.getString("referingURL");
        final String ssv_creativeid = json.getString("ssv_creativeid");

        // POST https://account.chase.com/consumer/banking/seo (endp 59)
        final HttpTarget accountChaseCom = getHttpClient("https://account.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("jp_aid_a", ssv_creativeid);
            put("jp_aid_p", "retail_checking_hp/tile");
        }});
        request.setFormData(new Hashtable<String, Object>() {{
            put("Payload_ContactPoint", Payload_ContactPoint);
            put("Payload_IsDaoWithEmailSubmission", "false");
            put("Payload_IsEmailSubmission", "true");
            put("Payload_ProductSelection", Payload_ProductSelection);
            put("Payload_ValidationMsg", "");
            put("device_type", "Desktop");
            put("form_build_id", form_build_id);
            put("form_id", "email_my_coupon");
            put("gclid", "");
            put("optimizelyID", "default");
            put("prd_link", "");
            put("referingURL", referingURL);
            put("v1stCookie", "");
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/x-www-form-urlencoded");
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = accountChaseCom.post(request, "/consumer/banking/seo");
        assertStatusCode(response.code(), 200);
    }
}

