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
    @JsonFileSource(resources = "/dataset_32.json")
    public void testDeleteCartsCustomerid32(final JsonObject json) throws MalformedURLException, IOException
    {
        final String address = json.getString("address");
        final String card = json.getString("card");
        final String customer = json.getString("customer");
        final String items = json.getString("items");
        final String size = json.getString("size");

        // GET http://catalogue.sock-shop/tags (endp 27)
        final HttpTarget catalogueSockShop = getHttpClient("http://catalogue.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = catalogueSockShop.get(request, "/tags");
        assertStatusCode(response.code(), 200);
        final String tags = JSONPath("$.tags[*]", response.body().string());

        // GET http://catalogue.sock-shop/catalogue (endp 24)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("sort", "id");
            put("tags", tags);
        }});
        final Response response2 = catalogueSockShop.get(request2, "/catalogue");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$[*].tag[*]", response2.body().string());

        // POST http://orders.sock-shop/orders (endp 37)
        final HttpTarget ordersSockShop = getHttpClient("http://orders.sock-shop", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request3.setJsonBody("payload_for_endp_37.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response3 = ordersSockShop.post(request3, "/orders");
        assertStatusCode(response3.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response3.body().string());
        final String customerId = JSONPath("$.customerId", response3.body().string());

        // DELETE http://carts.sock-shop/carts/{customerId} (endp 32)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request4 = new HttpRequest();
        final Response response4 = cartsSockShop.delete(request4, "/carts/" + customerId);
        assertStatusCode(response4.code(), 202);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_33.json")
    public void testPostCartsCustomeridItems33(final JsonObject json) throws MalformedURLException, IOException
    {
        final String address = json.getString("address");
        final String card = json.getString("card");
        final String customer = json.getString("customer");
        final String items = json.getString("items");
        final String sessionId = json.getString("sessionId");

        // POST http://orders.sock-shop/orders (endp 37)
        final HttpTarget ordersSockShop = getHttpClient("http://orders.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_37.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response = ordersSockShop.post(request, "/orders");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response.body().string());
        final String itemId = JSONPath("$.items[*].itemId", response.body().string());
        final String unitPrice = JSONPath("$.items[*].unitPrice", response.body().string());
        final String customerId = JSONPath("$.customerId", response.body().string());

        // GET http://carts.sock-shop/carts/{customerId}/merge (endp 35)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("sessionId", sessionId);
        }});
        final Response response2 = cartsSockShop.get(request2, "/carts/" + customerId + "/merge");
        assertStatusCode(response2.code(), 202);

        // POST http://carts.sock-shop/carts/{customerId}/items (endp 33)
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request3.setJsonBody("payload_for_endp_33.json", new Hashtable<String, Object>() {{
            put("$.itemId", itemId);
            put("$.unitPrice", unitPrice);
        }});
        final Response response3 = cartsSockShop.post(request3, "/carts/" + customerId + "/items");
        assertStatusCode(response3.code(), 201);
        assertJSONPath("$.id", response3.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_34.json")
    public void testDeleteCartsCustomeridItemsItemid34(final JsonObject json) throws MalformedURLException, IOException
    {
        final String address = json.getString("address");
        final String card = json.getString("card");
        final String customer = json.getString("customer");
        final String items = json.getString("items");
        final String size = json.getString("size");

        // GET http://catalogue.sock-shop/tags (endp 27)
        final HttpTarget catalogueSockShop = getHttpClient("http://catalogue.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = catalogueSockShop.get(request, "/tags");
        assertStatusCode(response.code(), 200);
        final String tags = JSONPath("$.tags[*]", response.body().string());

        // GET http://catalogue.sock-shop/catalogue (endp 24)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("sort", "id");
            put("tags", tags);
        }});
        final Response response2 = catalogueSockShop.get(request2, "/catalogue");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$[*].tag[*]", response2.body().string());

        // POST http://orders.sock-shop/orders (endp 37)
        final HttpTarget ordersSockShop = getHttpClient("http://orders.sock-shop", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request3.setJsonBody("payload_for_endp_37.json", new Hashtable<String, Object>() {{
            put("$.address", address);
            put("$.card", card);
            put("$.customer", customer);
            put("$.items", items);
        }});
        final Response response3 = ordersSockShop.post(request3, "/orders");
        assertStatusCode(response3.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response3.body().string());
        final String customerId = JSONPath("$.customerId", response3.body().string());

        // GET http://carts.sock-shop/carts/{customerId}/items (endp 18)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request4 = new HttpRequest();
        request4.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
        }});
        final Response response4 = cartsSockShop.get(request4, "/carts/" + customerId + "/items");
        assertStatusCode(response4.code(), 200);
        assertJSONPath("$[*].id", response4.body().string());
        final String itemId = JSONPath("$[*].itemId", response4.body().string());

        // DELETE http://carts.sock-shop/carts/{customerId}/items/{itemId} (endp 34)
        final HttpRequest request5 = new HttpRequest();
        final Response response5 = cartsSockShop.delete(request5, "/carts/" + customerId + "/items/" + itemId);
        assertStatusCode(response5.code(), 202);
    }
}

