package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.DummyAuth;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
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
    public void testGet01() throws IOException
    {
        // GET http://front-end/ (endp 1)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEnd.get(request, "/");
        assertCSSselect("div#hot div.box div.container div h2", "Hot this week", response.body().string());
    }

    @Test
    public void testGetParam26() throws IOException
    {
        // GET http://front-end/{param} (endp 26)
        final String param = "navbar.html";
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

    @Test
    public void testDeleteCart43() throws IOException
    {
        // DELETE http://front-end/cart (endp 43)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEnd.delete(request, "/cart");
        assertStatusCode(response.code(), 202);
    }

    @Test
    public void testGetCatalogue37() throws IOException
    {
        // GET http://front-end/catalogue (endp 37)
        final String size = "6";
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
    public void testGetCatalogueId52() throws IOException
    {
        // GET http://front-end/catalogue (endp 4)
        final String size = "5";
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
        final String id = JSONPath("$[*].id", response.body().string());

        // GET http://front-end/catalogue/{id} (endp 52)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEnd.get(request2, "/catalogue/" + id);
        assertStatusCode(response2.code(), 200);
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

    @Test
    public void testGetCatalogueSize36() throws IOException
    {
        // GET http://front-end/catalogue/size (endp 36)
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

    @Test
    public void testGetCategoryHtml34() throws IOException
    {
        // GET http://front-end/category.html (endp 34)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEnd.get(request, "/category.html");
        assertCSSselect("div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title", "Filters ", response.body().string());
    }

    @Test
    public void testGetCustomersCustomerid05() throws IOException
    {
        // GET http://front-end/customers/{customerId} (endp 5)
        final String customerId = "7w4TGI0pnIxn5TEFoHX6O2spPdXa3dut";
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/customers/" + customerId);
        assertJSONPath("$.lastName", "Name", response.body().string());
    }

    @Test
    public void testGetCustomersCustomerid30() throws IOException
    {
        // GET http://front-end/customers/{customerId} (endp 30)
        final String customerId = "7w4TGI0pnIxn5TEFoHX6O2spPdXa3dut";
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/customers/" + customerId);
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetDetailHtml45() throws IOException
    {
        // GET http://front-end/catalogue (endp 4)
        final String size = "5";
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
        final String id = JSONPath("$[*].id", response.body().string());

        // GET http://front-end/detail.html (endp 45)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("id", id);
        }});
        final Response response2 = frontEnd.get(request2, "/detail.html");
        assertCSSselect("div#content div.container div div.row.same-height-row div div.box.same-height h3", "You may also like these products", response2.body().string());
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
        assertCSSselect("div#copyright div.container div p.pull-left a", "Weaveworks", response.body().string());
    }

    @Test
    public void testGetIndexHtml06() throws IOException
    {
        // GET http://front-end/index.html (endp 6)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEnd.get(request, "/index.html");
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
    public void testGetTags38() throws IOException
    {
        // GET http://front-end/tags (endp 38)
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
        assertCSSselect("div#top div.container div.offer a.btn.btn-success.btn-sm", "Offer of the day", response.body().string());
    }
}

