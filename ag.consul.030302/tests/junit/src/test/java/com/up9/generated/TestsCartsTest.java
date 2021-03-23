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
    public void testDeleteCartsCustomerid46() throws IOException
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

        // DELETE http://carts/carts/{customerId} (endp 46)
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = carts.delete(request2, "/carts/" + customerId);
        assertStatusCode(response2.code(), 202);
    }

    @Test
    public void testGetCartsCustomeridItems13() throws IOException
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

        // GET http://carts/carts/{customerId}/items (endp 13)
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
        }});
        final Response response2 = carts.get(request2, "/carts/" + customerId + "/items");
        assertStatusCode(response2.code(), 200);
    }

    @Test
    public void testPostCartsCustomeridItems56() throws IOException
    {
        // GET http://catalogue/catalogue (endp 10)
        final String size = "5";
        final HttpTarget catalogue = getHttpTarget("TARGET_CATALOGUE", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("tags", "");
        }});
        final Response response = catalogue.get(request, "/catalogue");
        assertRegexInBody(".*Holy.*", response.body().string());
        final String itemId = JSONPath("$[*].id", response.body().string());
        final String unitPrice = JSONPath("$[*].price", response.body().string());

        // POST http://orders/orders (endp 57)
        final String address = "http://user/addresses/57a98d98e4b00679b4a830b0";
        final String card = "http://user/cards/57a98d98e4b00679b4a830b1";
        final String customer = "http://user/customers/57a98d98e4b00679b4a830b2";
        final String items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
        final HttpTarget orders = getHttpTarget("TARGET_ORDERS", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request2.setJsonBody("payload_for_endp_57.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response2 = orders.post(request2, "/orders");
        assertStatusCode(response2.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response2.body().string());
        final String customerId = JSONPath("$.customerId", response2.body().string());

        // POST http://carts/carts/{customerId}/items (endp 56)
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request3.setJsonBody("payload_for_endp_56.json", new Hashtable<String, Object>() {{
            put("$.itemId", itemId);
            put("$.unitPrice", unitPrice);
        }});
        final Response response3 = carts.post(request3, "/carts/" + customerId + "/items");
        assertStatusCode(response3.code(), 201);
    }

    @Test
    public void testGetCartsCustomeridMerge12() throws IOException
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

        // GET http://carts/carts/{customerId}/merge (endp 12)
        final String sessionId = "XQmVKODtqAcXS6ZfF4HlZubZBEgShjds";
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("sessionId", sessionId);
        }});
        final Response response2 = carts.get(request2, "/carts/" + customerId + "/merge");
        assertStatusCode(response2.code(), 202);
    }
}

