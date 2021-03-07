package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.DummyAuth;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
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
public class TestsFrontEndTest
{
    @Test
    public void testGet01() throws IOException
    {
        // GET http://front-end/ (endp 1)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEnd.get(request, "/");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#hot div.box div.container div h2", "Hot this week", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_26.json")
    public void testGetParam26(final JsonObject json) throws IOException
    {
        final String param = json.getString("param");

        // GET http://front-end/{param} (endp 26)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/" + param);
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetCart02() throws IOException
    {
        // GET http://front-end/cart (endp 2)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/cart");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_4.json")
    public void testGetCatalogue04(final JsonObject json) throws IOException
    {
        final String size = json.getString("size");

        // GET http://front-end/catalogue (endp 4)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("tags", "");
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetCatalogueSize03() throws IOException
    {
        // GET http://front-end/catalogue/size (endp 3)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("tags", "");
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/catalogue/size");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_5.json")
    public void testGetCustomersCustomerid05(final JsonObject json) throws IOException
    {
        final String customerId = json.getString("customerId");

        // GET http://front-end/customers/{customerId} (endp 5)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/customers/" + customerId);
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.lastName", "Name", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_30.json")
    public void testGetCustomersCustomerid30(final JsonObject json) throws IOException
    {
        final String customerId = json.getString("customerId");

        // GET http://front-end/customers/{customerId} (endp 30)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/customers/" + customerId);
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetFooterHtml23() throws IOException
    {
        // GET http://front-end/footer.html (endp 23)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/footer.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#copyright div.container div p.pull-left a", "Weaveworks", response.body().string());
    }

    @Test
    public void testGetIndexHtml06() throws IOException
    {
        // GET http://front-end/index.html (endp 6)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEnd.get(request, "/index.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#hot div.box div.container div h2", "Hot this week", response.body().string());
    }

    /**
     * authentication-related test
     */
    @Test
    public void testGetLogin25() throws IOException
    {
        // GET http://front-end/login (endp 25)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new DummyAuth());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertCSSselect("p", "Cookie is set", response.body().string());
    }

    @Test
    public void testGetOrders07() throws IOException
    {
        // GET http://front-end/orders (endp 7)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/orders");
        assertStatusCode(response.code(), 201);
    }

    @Test
    public void testGetTags08() throws IOException
    {
        // GET http://front-end/tags (endp 8)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/tags");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetTopbarHtml27() throws IOException
    {
        // GET http://front-end/topbar.html (endp 27)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/topbar.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#top div.container div.offer a.btn.btn-success.btn-sm", "Offer of the day", response.body().string());
    }
}

