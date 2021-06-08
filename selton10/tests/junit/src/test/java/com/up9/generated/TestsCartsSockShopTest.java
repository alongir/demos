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
public class TestsCartsSockShopTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_6.json")
    public void testDeleteCartsCustomerid006(final JsonObject json) throws MalformedURLException, IOException
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
        final String customerId = JSONPath("$.customerId", response.body().string());

        // DELETE http://carts.sock-shop/carts/{customerId} (endp 6)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = cartsSockShop.delete(request2, "/carts/" + customerId);
        assertStatusCode(response2.code(), 202);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_7.json")
    public void testPostCartsCustomeridItems007(final JsonObject json) throws MalformedURLException, IOException
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
        final String customerId = JSONPath("$.customerId", response.body().string());

        // GET http://carts.sock-shop/carts/{customerId}/items (endp 9)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
        }});
        final Response response2 = cartsSockShop.get(request2, "/carts/" + customerId + "/items");
        assertStatusCode(response2.code(), 200);
        final String itemId = JSONPath("$[*].itemId", response2.body().string());
        final String unitPrice = JSONPath("$[*].unitPrice", response2.body().string());

        // POST http://carts.sock-shop/carts/{customerId}/items (endp 7)
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request3.setJsonBody("payload_for_endp_7.json", new Hashtable<String, Object>() {{
            put("$.itemId", itemId);
            put("$.unitPrice", unitPrice);
        }});
        final Response response3 = cartsSockShop.post(request3, "/carts/" + customerId + "/items");
        assertStatusCode(response3.code(), 201);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_44.json")
    public void testDeleteCartsCustomeridItemsItemid044(final JsonObject json) throws MalformedURLException, IOException
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
        final String customerId = JSONPath("$.customerId", response.body().string());

        // GET http://carts.sock-shop/carts/{customerId}/items (endp 9)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
        }});
        final Response response2 = cartsSockShop.get(request2, "/carts/" + customerId + "/items");
        assertStatusCode(response2.code(), 200);
        final String itemId = JSONPath("$[*].itemId", response2.body().string());

        // DELETE http://carts.sock-shop/carts/{customerId}/items/{itemId} (endp 44)
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = cartsSockShop.delete(request3, "/carts/" + customerId + "/items/" + itemId);
        assertStatusCode(response3.code(), 202);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_8.json")
    public void testGetCartsCustomeridMerge008(final JsonObject json) throws MalformedURLException, IOException
    {
        final String address = json.getString("address");
        final String card = json.getString("card");
        final String customer = json.getString("customer");
        final String items = json.getString("items");
        final String sessionId = json.getString("sessionId");

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
        final String customerId = JSONPath("$.customerId", response.body().string());

        // GET http://carts.sock-shop/carts/{customerId}/merge (endp 8)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("sessionId", sessionId);
        }});
        final Response response2 = cartsSockShop.get(request2, "/carts/" + customerId + "/merge");
        assertStatusCode(response2.code(), 202);
    }
}

