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
public class TestsCartsTest
{
    @Test
    public void testDeleteCartsCustomerid22() throws MalformedURLException, IOException
    {
        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpClient("http://user", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customerId = JSONPath("$.user.id", response.body().string());

        // DELETE http://carts/carts/{customerId} (endp 22)
        final HttpTarget carts = getHttpClient("http://carts", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = carts.delete(request2, "/carts/" + customerId);
        assertStatusCode(response2.code(), 202);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_23.json")
    public void testPostCartsCustomeridItems23(final JsonObject json) throws MalformedURLException, IOException
    {
        final String size = json.getString("size");

        // GET http://catalogue/tags (endp 30)
        final HttpTarget catalogue = getHttpClient("http://catalogue", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = catalogue.get(request, "/tags");
        assertStatusCode(response.code(), 200);
        final String tags = JSONPath("$.tags[*]", response.body().string());

        // GET http://catalogue/catalogue (endp 29)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("sort", "id");
            put("tags", tags);
        }});
        final Response response2 = catalogue.get(request2, "/catalogue");
        assertStatusCode(response2.code(), 200);
        final String itemId = JSONPath("$[*].id", response2.body().string());
        final String unitPrice = JSONPath("$[*].price", response2.body().string());

        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpClient("http://user", new Authentication());
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = user.get(request3, "/login");
        assertStatusCode(response3.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response3.body().string());
        final String customerId = JSONPath("$.user.id", response3.body().string());

        // POST http://carts/carts/{customerId}/items (endp 23)
        final HttpTarget carts = getHttpClient("http://carts", new Authentication());
        final HttpRequest request4 = new HttpRequest();
        request4.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request4.setJsonBody("payload_for_endp_23.json", new Hashtable<String, Object>() {{
            put("$.itemId", itemId);
            put("$.unitPrice", unitPrice);
        }});
        final Response response4 = carts.post(request4, "/carts/" + customerId + "/items");
        assertStatusCode(response4.code(), 201);
    }

    @Test
    public void testGetCartsCustomeridItems26() throws MalformedURLException, IOException
    {
        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpClient("http://user", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customerId = JSONPath("$.user.id", response.body().string());

        // GET http://carts/carts/{customerId}/items (endp 26)
        final HttpTarget carts = getHttpClient("http://carts", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
        }});
        final Response response2 = carts.get(request2, "/carts/" + customerId + "/items");
        assertStatusCode(response2.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_24.json")
    public void testGetCartsCustomeridMerge24(final JsonObject json) throws MalformedURLException, IOException
    {
        final String sessionId = json.getString("sessionId");

        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpClient("http://user", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customerId = JSONPath("$.user.id", response.body().string());

        // GET http://carts/carts/{customerId}/merge (endp 24)
        final HttpTarget carts = getHttpClient("http://carts", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("sessionId", sessionId);
        }});
        final Response response2 = carts.get(request2, "/carts/" + customerId + "/merge");
        assertStatusCode(response2.code(), 202);
    }
}

