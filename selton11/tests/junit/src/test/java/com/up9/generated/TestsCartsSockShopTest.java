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

        // GET http://user.sock-shop/login (endp 31)
        final HttpTarget userSockShop = getHttpClient("http://user.sock-shop", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = userSockShop.get(request3, "/login");
        assertStatusCode(response3.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response3.body().string());
        final String customerId = JSONPath("$.user.id", response3.body().string());

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
        final String sessionId = json.getString("sessionId");
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
        final String itemId = JSONPath("$[*].id", response2.body().string());
        final String id = JSONPath("$[*].id", response2.body().string());

        // GET http://user.sock-shop/login (endp 31)
        final HttpTarget userSockShop = getHttpClient("http://user.sock-shop", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = userSockShop.get(request3, "/login");
        assertStatusCode(response3.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response3.body().string());
        final String customerId = JSONPath("$.user.id", response3.body().string());

        // GET http://carts.sock-shop/carts/{customerId}/merge (endp 35)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request4 = new HttpRequest();
        request4.setQueryString(new Hashtable<String, Object>() {{
            put("sessionId", sessionId);
        }});
        final Response response4 = cartsSockShop.get(request4, "/carts/" + customerId + "/merge");
        assertStatusCode(response4.code(), 202);

        // GET http://catalogue.sock-shop/catalogue/{id} (endp 25)
        final HttpRequest request5 = new HttpRequest();
        final Response response5 = catalogueSockShop.get(request5, "/catalogue/" + id);
        assertStatusCode(response5.code(), 200);
        final String unitPrice = JSONPath("$.price", response5.body().string());

        // POST http://carts.sock-shop/carts/{customerId}/items (endp 33)
        final HttpRequest request6 = new HttpRequest();
        request6.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request6.setJsonBody("payload_for_endp_33.json", new Hashtable<String, Object>() {{
            put("$.itemId", itemId);
            put("$.unitPrice", unitPrice);
        }});
        final Response response6 = cartsSockShop.post(request6, "/carts/" + customerId + "/items");
        assertStatusCode(response6.code(), 201);
    }

    @Test
    public void testDeleteCartsCustomeridItemsItemid34() throws MalformedURLException, IOException
    {
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
        final String itemId = JSONPath("$[*].itemId", response2.body().string());

        // DELETE http://carts.sock-shop/carts/{customerId}/items/{itemId} (endp 34)
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = cartsSockShop.delete(request3, "/carts/" + customerId + "/items/" + itemId);
        assertStatusCode(response3.code(), 202);
    }
}

