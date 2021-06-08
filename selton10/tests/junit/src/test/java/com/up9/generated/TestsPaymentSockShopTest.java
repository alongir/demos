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
public class TestsPaymentSockShopTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_20.json")
    public void testPostPaymentauth020(final JsonObject json) throws MalformedURLException, IOException
    {
        final String addresseId = json.getString("addresseId");
        final String cardId = json.getString("cardId");
        final String longNum = json.getString("longNum");
        final String number = json.getString("number");

        // GET http://user.sock-shop/addresses/{addresseId} (endp 17)
        final HttpTarget userSockShop = getHttpClient("http://user.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/hal+json");
        }});
        final Response response = userSockShop.get(request, "/addresses/" + addresseId);
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.city", "Glasgow", response.body().string());
        final String country = JSONPath("$.country", response.body().string());
        final String postcode = JSONPath("$.postcode", response.body().string());
        final String street = JSONPath("$.street", response.body().string());

        // GET http://user.sock-shop/cards/{cardId} (endp 18)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/hal+json");
        }});
        final Response response2 = userSockShop.get(request2, "/cards/" + cardId);
        assertStatusCode(response2.code(), 200);
        final String ccv = JSONPath("$.ccv", response2.body().string());
        final String expires = JSONPath("$.expires", response2.body().string());

        // POST http://payment.sock-shop/paymentAuth (endp 20)
        final HttpTarget paymentSockShop = getHttpClient("http://payment.sock-shop", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request3.setJsonBody("payload_for_endp_20.json", new Hashtable<String, Object>() {{
            put("$.address.country", country);
            put("$.address.number", number);
            put("$.address.postcode", postcode);
            put("$.address.street", street);
            put("$.amount", randomFloat(4.99f, 350.94f));
            put("$.card.ccv", ccv);
            put("$.card.expires", expires);
            put("$.card.longNum", longNum);
        }});
        final Response response3 = paymentSockShop.post(request3, "/paymentAuth");
        assertStatusCode(response3.code(), 200);
    }
}

