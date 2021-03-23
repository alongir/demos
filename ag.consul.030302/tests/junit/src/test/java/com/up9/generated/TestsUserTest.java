package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.DummyAuth;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import java.util.Hashtable;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsUserTest
{
    @Test
    public void testGetCustomersCustomerid15() throws IOException
    {
        // POST http://orders/orders (endp 57)
        final String address = "http://user/addresses/57a98d98e4b00679b4a830b0";
        final String card = "http://user/cards/57a98d98e4b00679b4a830b1";
        final String customer = "http://user/customers/57a98d98e4b00679b4a830b2";
        final String items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
        final HttpTarget orders = getHttpTarget("TARGET_ORDERS", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_57.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response = orders.post(request, "/orders");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response.body().string());
        final String customerId = JSONPath("$.customerId", response.body().string());

        // GET http://user/customers/{customerId} (endp 15)
        final HttpTarget user = getHttpTarget("TARGET_USER", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = user.get(request2, "/customers/" + customerId);
        assertJSONPath("$.lastName", "Name", response2.body().string());
    }

    /**
     * authentication-related test
     */
    @Test
    public void testGetLogin16() throws IOException
    {
        // GET http://user/login (endp 16)
        final HttpTarget user = getHttpTarget("TARGET_USER", new DummyAuth());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertJSONPath("$.user.lastName", "Name", response.body().string());
    }
}

