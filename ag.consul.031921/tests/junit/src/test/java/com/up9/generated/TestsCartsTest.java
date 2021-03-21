package com.up9.generated;

import com.up9.generated.Authentication;
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
public class TestsCartsTest
{
    @Test
    public void testDeleteCartsCustomerid22() throws IOException
    {
        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpTarget("TARGET_USER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customer = JSONPath("$.user._links.customer.href", response.body().string());

        // POST http://orders/orders (endp 35)
        final String address = "http://user/addresses/57a98d98e4b00679b4a830b0";
        final String card = "http://user/cards/57a98d98e4b00679b4a830b1";
        final String items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
        final HttpTarget orders = getHttpTarget("TARGET_ORDERS", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request2.setJsonBody("payload_for_endp_35.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response2 = orders.post(request2, "/orders");
        assertStatusCode(response2.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response2.body().string());
        final String customerId = JSONPath("$.customerId", response2.body().string());

        // DELETE http://carts/carts/{customerId} (endp 22)
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = carts.delete(request3, "/carts/" + customerId);
        assertStatusCode(response3.code(), 202);
    }

    @Test
    public void testPostCartsCustomeridItems23() throws IOException
    {
        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpTarget("TARGET_USER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customer = JSONPath("$.user._links.customer.href", response.body().string());

        // POST http://orders/orders (endp 35)
        final String address = "http://user/addresses/57a98d98e4b00679b4a830b0";
        final String card = "http://user/cards/57a98d98e4b00679b4a830b1";
        final String items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
        final HttpTarget orders = getHttpTarget("TARGET_ORDERS", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request2.setJsonBody("payload_for_endp_35.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response2 = orders.post(request2, "/orders");
        assertStatusCode(response2.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response2.body().string());
        final String itemId = JSONPath("$.items[*].itemId", response2.body().string());
        final String unitPrice = JSONPath("$.items[*].unitPrice", response2.body().string());
        final String customerId = JSONPath("$.customerId", response2.body().string());

        // POST http://carts/carts/{customerId}/items (endp 23)
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request3.setJsonBody("payload_for_endp_23.json", new Hashtable<String, Object>() {{
            put("$.itemId", itemId);
            put("$.unitPrice", unitPrice);
        }});
        final Response response3 = carts.post(request3, "/carts/" + customerId + "/items");
        assertStatusCode(response3.code(), 201);
    }

    @Test
    public void testPatchCartsCustomeridItems25() throws IOException
    {
        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpTarget("TARGET_USER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customer = JSONPath("$.user._links.customer.href", response.body().string());

        // POST http://orders/orders (endp 35)
        final String address = "http://user/addresses/57a98d98e4b00679b4a830b0";
        final String card = "http://user/cards/57a98d98e4b00679b4a830b1";
        final String items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
        final HttpTarget orders = getHttpTarget("TARGET_ORDERS", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request2.setJsonBody("payload_for_endp_35.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response2 = orders.post(request2, "/orders");
        assertStatusCode(response2.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response2.body().string());
        final String quantity = JSONPath("$.items[*].quantity", response2.body().string());
        final String customerId = JSONPath("$.customerId", response2.body().string());

        // GET http://carts/carts/{customerId}/items (endp 26)
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
        }});
        final Response response3 = carts.get(request3, "/carts/" + customerId + "/items");
        assertStatusCode(response3.code(), 200);
        final String itemId = JSONPath("$[*].itemId", response3.body().string());
        final String unitPrice = JSONPath("$[*].unitPrice", response3.body().string());

        // PATCH http://carts/carts/{customerId}/items (endp 25)
        final HttpRequest request4 = new HttpRequest();
        request4.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request4.setJsonBody("payload_for_endp_25.json", new Hashtable<String, Object>() {{
            put("$.itemId", itemId);
            put("$.quantity", quantity);
            put("$.unitPrice", unitPrice);
        }});
        final Response response4 = carts.patch(request4, "/carts/" + customerId + "/items");
        assertStatusCode(response4.code(), 202);
    }

    @Test
    public void testGetCartsCustomeridMerge24() throws IOException
    {
        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpTarget("TARGET_USER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customer = JSONPath("$.user._links.customer.href", response.body().string());

        // POST http://orders/orders (endp 35)
        final String address = "http://user/addresses/57a98d98e4b00679b4a830b0";
        final String card = "http://user/cards/57a98d98e4b00679b4a830b1";
        final String items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
        final HttpTarget orders = getHttpTarget("TARGET_ORDERS", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request2.setJsonBody("payload_for_endp_35.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response2 = orders.post(request2, "/orders");
        assertStatusCode(response2.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response2.body().string());
        final String customerId = JSONPath("$.customerId", response2.body().string());

        // GET http://carts/carts/{customerId}/merge (endp 24)
        final String sessionId = "1J9WwRDQsVd4ymiharhdwevNEWEUP2x2";
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        request3.setQueryString(new Hashtable<String, Object>() {{
            put("sessionId", sessionId);
        }});
        final Response response3 = carts.get(request3, "/carts/" + customerId + "/merge");
        assertStatusCode(response3.code(), 202);
    }
}

