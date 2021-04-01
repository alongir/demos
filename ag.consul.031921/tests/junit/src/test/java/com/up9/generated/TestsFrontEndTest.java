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
public class TestsFrontEndTest
{
    @Test
    public void testGetCatalogueId13() throws MalformedURLException, IOException
    {
        // GET http://front-end/orders (endp 19)
        final HttpTarget frontEnd = getHttpClient("http://front-end", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/orders");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$[*].address.city", "Glasgow", response.body().string());
        final String id = JSONPath("$[*].items[*].itemId", response.body().string());

        // GET http://front-end/catalogue/{id} (endp 13)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEnd.get(request2, "/catalogue/" + id);
        assertStatusCode(response2.code(), 200);
    }

    @Test
    public void testGetOrdersHref20() throws MalformedURLException, IOException
    {
        // GET http://front-end/orders (endp 19)
        final HttpTarget frontEnd = getHttpClient("http://front-end", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/orders");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$[*].address.city", "Glasgow", response.body().string());
        final String href = urlPart("/2", JSONPath("$[*]._links.self.href", response.body().string()));

        // GET http://front-end/orders/{href} (endp 20)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEnd.get(request2, "/orders/" + href);
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.address.city", "Glasgow", response2.body().string());
    }
}

