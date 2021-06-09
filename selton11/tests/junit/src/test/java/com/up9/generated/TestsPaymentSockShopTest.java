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
    @JsonFileSource(resources = "/dataset_22.json")
    public void testPostPaymentauth22(final JsonObject json) throws MalformedURLException, IOException
    {
        final String addresseId = json.getString("addresseId");
        final String cardId = json.getString("cardId");
        final String country = json.getString("country");
        final String longNum = json.getString("longNum");
        final String number = json.getString("number");
        final String postcode = json.getString("postcode");

        // GET http://user.sock-shop/login (endp 31)
        final HttpTarget userSockShop = getHttpClient("http://user.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = userSockShop.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customerId = JSONPath("$.user.id", response.body().string());

        // GET http://carts.sock-shop/carts/{customerId}/items (endp 18)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
        }});
        final Response response2 = cartsSockShop.get(request2, "/carts/" + customerId + "/items");
        assertStatusCode(response2.code(), 200);

        // GET http://user.sock-shop/customers/{customerId} (endp 21)
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/hal+json");
        }});
        final Response response3 = userSockShop.get(request3, "/customers/" + customerId);
        assertStatusCode(response3.code(), 200);
        assertJSONPath("$.lastName", "Name", response3.body().string());
        final String firstName = JSONPath("$.firstName", response3.body().string());

        // GET http://user.sock-shop/cards/{cardId} (endp 20)
        final HttpRequest request4 = new HttpRequest();
        request4.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/hal+json");
        }});
        final Response response4 = userSockShop.get(request4, "/cards/" + cardId);
        assertStatusCode(response4.code(), 200);
        final String ccv = JSONPath("$.ccv", response4.body().string());
        final String expires = JSONPath("$.expires", response4.body().string());

        // GET http://user.sock-shop/addresses/{addresseId} (endp 19)
        final HttpRequest request5 = new HttpRequest();
        request5.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/hal+json");
        }});
        final Response response5 = userSockShop.get(request5, "/addresses/" + addresseId);
        assertStatusCode(response5.code(), 200);
        assertJSONPath("$.city", "Glasgow", response5.body().string());
        final String street = JSONPath("$.street", response5.body().string());

        // POST http://payment.sock-shop/paymentAuth (endp 22)
        final HttpTarget paymentSockShop = getHttpClient("http://payment.sock-shop", new Authentication());
        final HttpRequest request6 = new HttpRequest();
        request6.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request6.setJsonBody("payload_for_endp_22.json", new Hashtable<String, Object>() {{
            put("$.address.country", country);
            put("$.address.number", number);
            put("$.address.postcode", postcode);
            put("$.address.street", street);
            put("$.amount", randomFloat(4.99f, 270.96997f));
            put("$.card.ccv", ccv);
            put("$.card.expires", expires);
            put("$.card.longNum", longNum);
        }});
        final Response response6 = paymentSockShop.post(request6, "/paymentAuth");
        assertStatusCode(response6.code(), 200);
    }
}

