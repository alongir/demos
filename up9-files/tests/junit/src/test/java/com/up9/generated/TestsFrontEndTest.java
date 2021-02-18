package com.up9.generated;

import com.up9.generated.Authentication;
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
    public void testGet09() throws IOException
    {
        // GET http://front-end/ (endp 9)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEnd.get(request, "/");
        frontEnd.assertStatusCode(200);
        frontEnd.assertCSSselect("div#hot div.box div.container div h2", "Hot this week");
    }

    @Test
    public void testGetBasketHtml10() throws IOException
    {
        // GET http://front-end/basket.html (endp 10)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEnd.get(request, "/basket.html");
        frontEnd.assertStatusCode(200);
        frontEnd.assertCSSselect("div#basket div.box form h1", "Shopping cart");
    }

    @Test
    public void testGetCategoryHtml12() throws IOException
    {
        // GET http://front-end/category.html (endp 12)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEnd.get(request, "/category.html");
        frontEnd.assertStatusCode(200);
        frontEnd.assertCSSselect("div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title", "Filters ");
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_13.json")
    public void testGetDetailHtml13(final JsonObject json) throws IOException
    {
        final String size = json.getString("size");

        // GET http://front-end/catalogue (endp 11)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("size", size);
            put("sort", "id");
            put("tags", "brown");
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/catalogue");
        frontEnd.assertStatusCode(200);
        final String id = JSONPath("$.[*].id", frontEnd.responseString);

        // GET http://front-end/detail.html (endp 13)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("id", id);
        }});
        final Response response2 = frontEnd.get(request2, "/detail.html");
        frontEnd.assertStatusCode(200);
        frontEnd.assertCSSselect("div#content div.container div div.row.same-height-row div div.box.same-height h3", "You may also like these products");
    }

    @Test
    public void testGetCart22() throws IOException
    {
        // GET http://front-end/cart (endp 22)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/cart");
        frontEnd.assertStatusCode(200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_23.json")
    public void testGetCatalogueId23(final JsonObject json) throws IOException
    {
        final String size = json.getString("size");

        // GET http://front-end/catalogue (endp 11)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("size", size);
            put("sort", "id");
            put("tags", "brown");
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/catalogue");
        frontEnd.assertStatusCode(200);
        final String id = JSONPath("$.[*].id", frontEnd.responseString);

        // GET http://front-end/catalogue/{id} (endp 23)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEnd.get(request2, "/catalogue/" + id);
        frontEnd.assertStatusCode(200);
        frontEnd.assertJSONPath("$.imageUrl.[*]", "/catalogue/images/colourful_socks.jpg");
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_25.json")
    public void testGetCustomersCustomerid25(final JsonObject json) throws IOException
    {
        final String customerId = json.getString("customerId");

        // GET http://front-end/customers/{customerId} (endp 25)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/customers/" + customerId);
        frontEnd.assertStatusCode(200);
        frontEnd.assertJSONPath("$.lastName", "Name");
    }

    @Test
    public void testGetIndexHtml26() throws IOException
    {
        // GET http://front-end/index.html (endp 26)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEnd.get(request, "/index.html");
        frontEnd.assertStatusCode(200);
        frontEnd.assertCSSselect("div#hot div.box div.container div h2", "Hot this week");
    }
}

