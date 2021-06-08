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
    @JsonFileSource(resources = "/dataset_60.json")
    public void testGetOrdersId060(final JsonObject json) throws MalformedURLException, IOException
    {
        final String address = json.getString("address");
        final String card = json.getString("card");
        final String customer = json.getString("customer");
        final String items = json.getString("items");

        // POST http://orders.sock-shop/orders (endp 15)
        final HttpTarget ordersSockShop = getHttpClient("http://orders.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_15.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response = ordersSockShop.post(request, "/orders");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response.body().string());
        final String id = JSONPath("$.id", response.body().string());

        // GET http://orders.sock-shop/orders/{id} (endp 60)
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = ordersSockShop.get(request2, "/orders/" + id);
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.address.city", "Glasgow", response2.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_61.json")
    public void testGetOrdersSearchCustomerid061(final JsonObject json) throws MalformedURLException, IOException
    {
        final String address = json.getString("address");
        final String card = json.getString("card");
        final String customer = json.getString("customer");
        final String items = json.getString("items");

        // POST http://orders.sock-shop/orders (endp 15)
        final HttpTarget ordersSockShop = getHttpClient("http://orders.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_15.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response = ordersSockShop.post(request, "/orders");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response.body().string());
        final String custId = JSONPath("$.customerId", response.body().string());

        // GET http://orders.sock-shop/orders/search/customerId (endp 61)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("custId", custId);
            put("sort", "date");
        }});
        final Response response2 = ordersSockShop.get(request2, "/orders/search/customerId");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$._embedded.customerOrders[*].address.city", "Glasgow", response2.body().string());
    }
}

