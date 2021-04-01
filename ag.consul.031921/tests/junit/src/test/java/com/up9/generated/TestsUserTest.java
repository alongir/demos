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
public class TestsUserTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_31.json")
    public void testGetCustomersCustomerid31(final JsonObject json) throws MalformedURLException, IOException
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
        final String customerId = JSONPath("$.customerId", response2.body().string());

        // GET http://user/customers/{customerId} (endp 31)
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = user.get(request3, "/customers/" + customerId);
        assertStatusCode(response3.code(), 200);
        assertJSONPath("$.lastName", "Name", response3.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_33.json")
    public void testGetCustomersCustomeridAddresses33(final JsonObject json) throws MalformedURLException, IOException
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
        final String customerId = JSONPath("$.customerId", response2.body().string());

        // GET http://user/customers/{customerId}/addresses (endp 33)
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = user.get(request3, "/customers/" + customerId + "/addresses");
        assertStatusCode(response3.code(), 200);
        assertJSONPath("$._embedded.address[*].city", "Glasgow", response3.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_34.json")
    public void testGetCustomersCustomeridCards34(final JsonObject json) throws MalformedURLException, IOException
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
        final String customerId = JSONPath("$.customerId", response2.body().string());

        // GET http://user/customers/{customerId}/cards (endp 34)
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = user.get(request3, "/customers/" + customerId + "/cards");
        assertStatusCode(response3.code(), 200);
    }
}

