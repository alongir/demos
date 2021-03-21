package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsOrdersTest
{
    @Test
    public void testGetOrdersHref36() throws IOException, MalformedURLException
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
        final String custId = JSONPath("$.customerId", response2.body().string());

        // GET http://orders/orders/search/customerId (endp 37)
        final HttpRequest request3 = new HttpRequest();
        request3.setQueryString(new Hashtable<String, Object>() {{
            put("custId", custId);
            put("sort", "date");
        }});
        final Response response3 = orders.get(request3, "/orders/search/customerId");
        assertStatusCode(response3.code(), 200);
        assertJSONPath("$._embedded.customerOrders[*].address.city", "Glasgow", response3.body().string());
        final String href = urlPart("/2", JSONPath("$._embedded.customerOrders[*]._links.order.href", response3.body().string()));

        // GET http://orders/orders/{href} (endp 36)
        final HttpRequest request4 = new HttpRequest();
        final Response response4 = orders.get(request4, "/orders/" + href);
        assertStatusCode(response4.code(), 200);
        assertJSONPath("$.address.city", "Glasgow", response4.body().string());
    }
}

