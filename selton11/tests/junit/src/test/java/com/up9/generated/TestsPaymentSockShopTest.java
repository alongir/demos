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
        final String address = json.getString("address");
        final String addresseId = json.getString("addresseId");
        final String card = json.getString("card");
        final String cardId = json.getString("cardId");
        final String items = json.getString("items");
        final String longNum = json.getString("longNum");
        final String number = json.getString("number");
        final String size = json.getString("size");
        final String username = json.getString("username");

        // GET http://catalogue.sock-shop/tags (endp 27)
        final HttpTarget catalogueSockShop = getHttpClient("http://catalogue.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = catalogueSockShop.get(request, "/tags");
        assertStatusCode(response.code(), 200);
        final String tags = JSONPath("$.tags[*]", response.body().string());

        // GET http://user.sock-shop/login (endp 31)
        final HttpTarget userSockShop = getHttpClient("http://user.sock-shop", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = userSockShop.get(request2, "/login");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response2.body().string());
        final String customer = JSONPath("$.user._links.customer.href", response2.body().string());

        // GET http://catalogue.sock-shop/catalogue (endp 24)
        final HttpRequest request3 = new HttpRequest();
        request3.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("sort", "id");
            put("tags", tags);
        }});
        final Response response3 = catalogueSockShop.get(request3, "/catalogue");
        assertStatusCode(response3.code(), 200);

        // POST http://orders.sock-shop/orders (endp 37)
        final HttpTarget ordersSockShop = getHttpClient("http://orders.sock-shop", new Authentication());
        final HttpRequest request4 = new HttpRequest();
        request4.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request4.setJsonBody("payload_for_endp_37.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response4 = ordersSockShop.post(request4, "/orders");
        assertStatusCode(response4.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response4.body().string());
        final String customerId = JSONPath("$.customerId", response4.body().string());

        // GET http://carts.sock-shop/carts/{customerId}/items (endp 18)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request5 = new HttpRequest();
        request5.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
        }});
        final Response response5 = cartsSockShop.get(request5, "/carts/" + customerId + "/items");
        assertStatusCode(response5.code(), 200);
        assertJSONPath("$[*].id", response5.body().string());

        // GET http://user.sock-shop/customers/{customerId} (endp 21)
        final HttpRequest request6 = new HttpRequest();
        request6.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/hal+json");
        }});
        final Response response6 = userSockShop.get(request6, "/customers/" + customerId);
        assertStatusCode(response6.code(), 200);
        assertJSONPath("$.lastName", "Name", response6.body().string());
        final String firstName = JSONPath("$.firstName", response6.body().string());

        // GET http://user.sock-shop/addresses/{addresseId} (endp 19)
        final HttpRequest request7 = new HttpRequest();
        request7.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/hal+json");
        }});
        final Response response7 = userSockShop.get(request7, "/addresses/" + addresseId);
        assertStatusCode(response7.code(), 200);
        assertJSONPath("$.city", "Glasgow", response7.body().string());
        final String country = JSONPath("$.country", response7.body().string());
        final String postcode = JSONPath("$.postcode", response7.body().string());
        final String street = JSONPath("$.street", response7.body().string());

        // GET http://user.sock-shop/cards/{cardId} (endp 20)
        final HttpRequest request8 = new HttpRequest();
        request8.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/hal+json");
        }});
        final Response response8 = userSockShop.get(request8, "/cards/" + cardId);
        assertStatusCode(response8.code(), 200);
        assertJSONPath("$._links.card.href", response8.body().string());
        final String ccv = JSONPath("$.ccv", response8.body().string());
        final String expires = JSONPath("$.expires", response8.body().string());

        // POST http://payment.sock-shop/paymentAuth (endp 22)
        final HttpTarget paymentSockShop = getHttpClient("http://payment.sock-shop", new Authentication());
        final HttpRequest request9 = new HttpRequest();
        request9.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request9.setJsonBody("payload_for_endp_22.json", new Hashtable<String, Object>() {{
            put("$.address.country", country);
            put("$.address.number", number);
            put("$.address.postcode", postcode);
            put("$.address.street", street);
            put("$.amount", randomFloat(4.99f, 270.96997f));
            put("$.card.ccv", ccv);
            put("$.card.expires", expires);
            put("$.card.longNum", longNum);
            put("$.customer.username", username);
        }});
        final Response response9 = paymentSockShop.post(request9, "/paymentAuth");
        assertStatusCode(response9.code(), 200);
        assertJSONPath("$.message", response9.body().string());
    }
}

