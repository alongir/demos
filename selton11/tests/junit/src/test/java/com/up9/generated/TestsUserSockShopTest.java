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
public class TestsUserSockShopTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_29.json")
    public void testGetCustomersCustomeridAddresses29(final JsonObject json) throws MalformedURLException, IOException
    {
        final String address = json.getString("address");
        final String card = json.getString("card");
        final String items = json.getString("items");

        // GET http://user.sock-shop/login (endp 31)
        final HttpTarget userSockShop = getHttpClient("http://user.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = userSockShop.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customer = JSONPath("$.user._links.customer.href", response.body().string());

        // POST http://orders.sock-shop/orders (endp 37)
        final HttpTarget ordersSockShop = getHttpClient("http://orders.sock-shop", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request2.setJsonBody("payload_for_endp_37.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response2 = ordersSockShop.post(request2, "/orders");
        assertStatusCode(response2.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response2.body().string());
        final String customerId = JSONPath("$.customerId", response2.body().string());

        // GET http://user.sock-shop/customers/{customerId}/cards (endp 30)
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = userSockShop.get(request3, "/customers/" + customerId + "/cards");
        assertStatusCode(response3.code(), 200);

        // GET http://user.sock-shop/customers/{customerId}/addresses (endp 29)
        final HttpRequest request4 = new HttpRequest();
        final Response response4 = userSockShop.get(request4, "/customers/" + customerId + "/addresses");
        assertStatusCode(response4.code(), 200);
        assertJSONPath("$._embedded.address[*].city", "Glasgow", response4.body().string());
    }
}

