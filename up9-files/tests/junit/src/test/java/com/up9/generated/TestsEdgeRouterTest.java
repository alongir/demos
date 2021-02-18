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
public class TestsEdgeRouterTest
{
    @Test
    public void testGet15() throws IOException
    {
        // GET http://edge-router/ (endp 15)
        final HttpTarget edgeRouter = getHttpTarget("TARGET_EDGE_ROUTER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = edgeRouter.get(request, "/");
        edgeRouter.assertStatusCode(200);
        edgeRouter.assertCSSselect("div#hot div.box div.container div h2", "Hot this week");
    }

    @Test
    public void testGetBasketHtml16() throws IOException
    {
        // GET http://edge-router/basket.html (endp 16)
        final HttpTarget edgeRouter = getHttpTarget("TARGET_EDGE_ROUTER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = edgeRouter.get(request, "/basket.html");
        edgeRouter.assertStatusCode(200);
        edgeRouter.assertCSSselect("div#basket div.box form h1", "Shopping cart");
    }

    @Test
    public void testGetCategoryHtml18() throws IOException
    {
        // GET http://edge-router/category.html (endp 18)
        final HttpTarget edgeRouter = getHttpTarget("TARGET_EDGE_ROUTER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = edgeRouter.get(request, "/category.html");
        edgeRouter.assertStatusCode(200);
        edgeRouter.assertCSSselect("div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title", "Filters ");
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_19.json")
    public void testGetDetailHtml19(final JsonObject json) throws IOException
    {
        final String size = json.getString("size");

        // GET http://edge-router/catalogue (endp 17)
        final HttpTarget edgeRouter = getHttpTarget("TARGET_EDGE_ROUTER", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("size", size);
            put("sort", "id");
            put("tags", "brown");
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = edgeRouter.get(request, "/catalogue");
        edgeRouter.assertStatusCode(200);
        final String id = JSONPath("$.[*].id", edgeRouter.responseString);

        // GET http://edge-router/detail.html (endp 19)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("id", id);
        }});
        final Response response2 = edgeRouter.get(request2, "/detail.html");
        edgeRouter.assertStatusCode(200);
        edgeRouter.assertCSSselect("div#content div.container div div.row.same-height-row div div.box.same-height h3", "You may also like these products");
    }

    @Test
    public void testGetCart27() throws IOException
    {
        // GET http://edge-router/cart (endp 27)
        final HttpTarget edgeRouter = getHttpTarget("TARGET_EDGE_ROUTER", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = edgeRouter.get(request, "/cart");
        edgeRouter.assertStatusCode(200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_28.json")
    public void testGetCatalogueId28(final JsonObject json) throws IOException
    {
        final String size = json.getString("size");

        // GET http://edge-router/catalogue (endp 17)
        final HttpTarget edgeRouter = getHttpTarget("TARGET_EDGE_ROUTER", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("size", size);
            put("sort", "id");
            put("tags", "brown");
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = edgeRouter.get(request, "/catalogue");
        edgeRouter.assertStatusCode(200);
        final String id = JSONPath("$.[*].id", edgeRouter.responseString);

        // GET http://edge-router/catalogue/{id} (endp 28)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = edgeRouter.get(request2, "/catalogue/" + id);
        edgeRouter.assertStatusCode(200);
        edgeRouter.assertJSONPath("$.imageUrl.[*]", "/catalogue/images/colourful_socks.jpg");
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_30.json")
    public void testGetCustomersCustomerid30(final JsonObject json) throws IOException
    {
        final String customerId = json.getString("customerId");

        // GET http://edge-router/customers/{customerId} (endp 30)
        final HttpTarget edgeRouter = getHttpTarget("TARGET_EDGE_ROUTER", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = edgeRouter.get(request, "/customers/" + customerId);
        edgeRouter.assertStatusCode(200);
        edgeRouter.assertJSONPath("$.lastName", "Name");
    }

    @Test
    public void testGetIndexHtml31() throws IOException
    {
        // GET http://edge-router/index.html (endp 31)
        final HttpTarget edgeRouter = getHttpTarget("TARGET_EDGE_ROUTER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = edgeRouter.get(request, "/index.html");
        edgeRouter.assertStatusCode(200);
        edgeRouter.assertCSSselect("div#hot div.box div.container div h2", "Hot this week");
    }
}

