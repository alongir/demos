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
public class TestsOrdersTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_36.json")
    public void testGetOrdersHref36(final JsonObject json) throws MalformedURLException, IOException
    {
        final String address = json.getString("address");
        final String card = json.getString("card");
        final String items = json.getString("items");

        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpClient("http://user", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customer = JSONPath("$.user._links.customer.href", response.body().string());

        // POST http://orders/orders (endp 35)
        final HttpTarget orders = getHttpClient("http://orders", new Authentication());
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

