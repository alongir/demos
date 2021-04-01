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
public class TestsPaymentTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_38.json")
    public void testPostPaymentauth38(final JsonObject json) throws MalformedURLException, IOException
    {
        final String ccv = json.getString("ccv");
        final String country = json.getString("country");
        final String expires = json.getString("expires");
        final String id = json.getString("id");
        final String id1 = json.getString("id1");
        final String id2 = json.getString("id2");
        final String longNum = json.getString("longNum");
        final String number = json.getString("number");
        final String postcode = json.getString("postcode");
        final String street = json.getString("street");

        // POST http://payment/paymentAuth (endp 38)
        final HttpTarget payment = getHttpClient("http://payment", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_38.json", new Hashtable<String, Object>() {{
            put("$.address.country", country);
            put("$.address.id", id);
            put("$.address.number", number);
            put("$.address.postcode", postcode);
            put("$.address.street", street);
            put("$.amount", randomFloat(12.98f, 122.979996f));
            put("$.card.ccv", ccv);
            put("$.card.expires", expires);
            put("$.card.id", id1);
            put("$.card.longNum", longNum);
            put("$.customer.id", id2);
        }});
        final Response response = payment.post(request, "/paymentAuth");
        assertStatusCode(response.code(), 200);
    }
}

