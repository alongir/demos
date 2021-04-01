package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import java.net.MalformedURLException;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsUserTest
{
    @Test
    public void testGetCustomersCustomerid31() throws MalformedURLException, IOException
    {
        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpClient("http://user", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customerId = JSONPath("$.user.id", response.body().string());

        // GET http://user/customers/{customerId} (endp 31)
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = user.get(request2, "/customers/" + customerId);
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.lastName", "Name", response2.body().string());
    }

    @Test
    public void testGetCustomersCustomeridAddresses33() throws MalformedURLException, IOException
    {
        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpClient("http://user", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customerId = JSONPath("$.user.id", response.body().string());

        // GET http://user/customers/{customerId}/addresses (endp 33)
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = user.get(request2, "/customers/" + customerId + "/addresses");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$._embedded.address[*].city", "Glasgow", response2.body().string());
    }

    @Test
    public void testGetCustomersCustomeridCards34() throws MalformedURLException, IOException
    {
        // GET http://user/login (endp 32)
        final HttpTarget user = getHttpClient("http://user", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
        final String customerId = JSONPath("$.user.id", response.body().string());

        // GET http://user/customers/{customerId}/cards (endp 34)
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = user.get(request2, "/customers/" + customerId + "/cards");
        assertStatusCode(response2.code(), 200);
    }
}

