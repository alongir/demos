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

        // GET http://catalogue.sock-shop/catalogue (endp 24)
        final HttpTarget catalogueSockShop = getHttpClient("http://catalogue.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("tags", "");
        }});
        final Response response = catalogueSockShop.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);

        // GET http://user.sock-shop/login (endp 31)
        final HttpTarget userSockShop = getHttpClient("http://user.sock-shop", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = userSockShop.get(request2, "/login");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response2.body().string());
        final String customerId = JSONPath("$.user.id", response2.body().string());

        // DELETE http://carts.sock-shop/carts/{customerId} (endp 32)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = cartsSockShop.delete(request3, "/carts/" + customerId);
        assertStatusCode(response3.code(), 202);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_33.json")
    public void testPostCartsCustomeridItems33(final JsonObject json) throws MalformedURLException, IOException
    {
        final String sessionId = json.getString("sessionId");
        final String size = json.getString("size");

        // GET http://catalogue.sock-shop/catalogue (endp 24)
        final HttpTarget catalogueSockShop = getHttpClient("http://catalogue.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("tags", "");
        }});
        final Response response = catalogueSockShop.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);
        final String itemId = JSONPath("$[*].id", response.body().string());
        final String id = JSONPath("$[*].id", response.body().string());

        // GET http://user.sock-shop/login (endp 31)
        final HttpTarget userSockShop = getHttpClient("http://user.sock-shop", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = userSockShop.get(request2, "/login");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response2.body().string());
        final String customerId = JSONPath("$.user.id", response2.body().string());

        // GET http://carts.sock-shop/carts/{customerId}/merge (endp 35)
        final HttpTarget cartsSockShop = getHttpClient("http://carts.sock-shop", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        request3.setQueryString(new Hashtable<String, Object>() {{
            put("sessionId", sessionId);
        }});
        final Response response3 = cartsSockShop.get(request3, "/carts/" + customerId + "/merge");
        assertStatusCode(response3.code(), 202);

        // GET http://catalogue.sock-shop/catalogue/{id} (endp 25)
        final HttpRequest request4 = new HttpRequest();
        final Response response4 = catalogueSockShop.get(request4, "/catalogue/" + id);
        assertStatusCode(response4.code(), 200);
        final String unitPrice = JSONPath("$.price", response4.body().string());

        // POST http://carts.sock-shop/carts/{customerId}/items (endp 33)
        final HttpRequest request5 = new HttpRequest();
        request5.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request5.setJsonBody("payload_for_endp_33.json", new Hashtable<String, Object>() {{
            put("$.itemId", itemId);
            put("$.unitPrice", unitPrice);
        }});
        final Response response5 = cartsSockShop.post(request5, "/carts/" + customerId + "/items");
        assertStatusCode(response5.code(), 201);
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

