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
public class TestsOrdersSockShopTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_37.json")
    public void testPostOrders37(final JsonObject json) throws MalformedURLException, IOException
    {
        final String items = json.getString("items");

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

        // GET http://user.sock-shop/customers/{customerId}/cards (endp 30)
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = userSockShop.get(request3, "/customers/" + customerId + "/cards");
        assertStatusCode(response3.code(), 200);
        final String card = JSONPath("$._embedded.card[*]._links.card.href", response3.body().string());

        // GET http://user.sock-shop/customers/{customerId} (endp 21)
        final HttpRequest request4 = new HttpRequest();
        request4.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/hal+json");
        }});
        final Response response4 = userSockShop.get(request4, "/customers/" + customerId);
        assertStatusCode(response4.code(), 200);
        assertJSONPath("$.lastName", "Name", response4.body().string());
        final String customer = JSONPath("$._links.self.href", response4.body().string());

        // GET http://user.sock-shop/customers/{customerId}/addresses (endp 29)
        final HttpRequest request5 = new HttpRequest();
        final Response response5 = userSockShop.get(request5, "/customers/" + customerId + "/addresses");
        assertStatusCode(response5.code(), 200);
        assertJSONPath("$._embedded.address[*].city", "Glasgow", response5.body().string());
        final String address = JSONPath("$._embedded.address[*]._links.address.href", response5.body().string());

        // POST http://orders.sock-shop/orders (endp 37)
        final HttpTarget ordersSockShop = getHttpClient("http://orders.sock-shop", new Authentication());
        final HttpRequest request6 = new HttpRequest();
        request6.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request6.setJsonBody("payload_for_endp_37.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response6 = ordersSockShop.post(request6, "/orders");
        assertStatusCode(response6.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response6.body().string());
    }

    @Test
    public void testGetOrdersSearchCustomerid38() throws MalformedURLException, IOException
    {
        // GET http://user.sock-shop/login (endp 31)
        final HttpTarget userSockShop = getHttpClient("http://user.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = userSockShop.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String custId = JSONPath("$.user.id", response.body().string());

        // GET http://orders.sock-shop/orders/search/customerId (endp 38)
        final HttpTarget ordersSockShop = getHttpClient("http://orders.sock-shop", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("custId", custId);
            put("sort", "date");
        }});
        final Response response2 = ordersSockShop.get(request2, "/orders/search/customerId");
        assertStatusCode(response2.code(), 200);
    }
}

