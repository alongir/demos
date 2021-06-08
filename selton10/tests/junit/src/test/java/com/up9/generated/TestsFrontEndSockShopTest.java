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
public class TestsFrontEndSockShopTest
{
    @Test
    public void testGet001() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/ (endp 1)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEndSockShop.get(request, "/");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#hot div.box div.container div h2", "Hot this week", response.body().string());
    }

    @Test
    public void testGetAddress066() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/address (endp 66)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/address");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.city", "Glasgow", response.body().string());
    }

    @Test
    public void testGetCard067() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/card (endp 67)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/card");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testPostCart069() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/cart (endp 22)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/cart");
        assertStatusCode(response.code(), 200);
        final String id = JSONPath("$[*].itemId", response.body().string());

        // POST http://front-end.sock-shop/cart (endp 69)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
            put("x-requested-with", "XMLHttpRequest");
        }});
        request2.setJsonBody("payload_for_endp_69.json", new Hashtable<String, Object>() {{
            put("$.id", id);
        }});
        final Response response2 = frontEndSockShop.post(request2, "/cart");
        assertStatusCode(response2.code(), 201);
    }

    @Test
    public void testDeleteCart083() throws MalformedURLException, IOException
    {
        // DELETE http://front-end.sock-shop/cart (endp 83)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.delete(request, "/cart");
        assertStatusCode(response.code(), 202);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_70.json")
    public void testDeleteCartId070(final JsonObject json) throws MalformedURLException, IOException
    {
        final String size = json.getString("size");
        final String tags = json.getString("tags");

        // GET http://front-end.sock-shop/catalogue (endp 23)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("sort", "id");
            put("tags", tags);
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);
        final String id = JSONPath("$[*].id", response.body().string());

        // DELETE http://front-end.sock-shop/cart/{id} (endp 70)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEndSockShop.delete(request2, "/cart/" + id);
        assertStatusCode(response2.code(), 202);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_71.json")
    public void testGetCatalogueId071(final JsonObject json) throws MalformedURLException, IOException
    {
        final String size = json.getString("size");
        final String tags = json.getString("tags");

        // GET http://front-end.sock-shop/catalogue (endp 23)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("sort", "id");
            put("tags", tags);
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);
        final String id = JSONPath("$[*].id", response.body().string());

        // GET http://front-end.sock-shop/catalogue/{id} (endp 71)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEndSockShop.get(request2, "/catalogue/" + id);
        assertStatusCode(response2.code(), 200);
    }

    @Test
    public void testGetCatalogueSize025() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/tags (endp 28)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/tags");
        assertStatusCode(response.code(), 200);
        final String tags = JSONPath("$.tags[*]", response.body().string());

        // GET http://front-end.sock-shop/catalogue/size (endp 25)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("tags", tags);
        }});
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEndSockShop.get(request2, "/catalogue/size");
        assertStatusCode(response2.code(), 200);
    }

    @Test
    public void testGetCategoryHtml027() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/tags (endp 28)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/tags");
        assertStatusCode(response.code(), 200);
        final String tags = JSONPath("$.tags[*]", response.body().string());

        // GET http://front-end.sock-shop/category.html (endp 27)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("tags", tags);
        }});
        final Response response2 = frontEndSockShop.get(request2, "/category.html");
        assertStatusCode(response2.code(), 200);
        assertCSSselect("div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title", "Filters ", response2.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_87.json")
    public void testGetCustomerOrderHtml087(final JsonObject json) throws MalformedURLException, IOException
    {
        final String order = json.getString("order");

        // GET http://front-end.sock-shop/customer-order.html (endp 87)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("order", order);
        }});
        final Response response = frontEndSockShop.get(request, "/customer-order.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#customer-order div.box h2", "Order #", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_75.json")
    public void testGetCustomersCustomerid075(final JsonObject json) throws MalformedURLException, IOException
    {
        final String customerId = json.getString("customerId");

        // GET http://front-end.sock-shop/customers/{customerId} (endp 75)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/customers/" + customerId);
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.lastName", "Name", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_118.json")
    public void testGetDetailHtml118(final JsonObject json) throws MalformedURLException, IOException
    {
        final String size = json.getString("size");
        final String tags = json.getString("tags");

        // GET http://front-end.sock-shop/catalogue (endp 23)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("sort", "id");
            put("tags", tags);
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);
        final String id = JSONPath("$[*].id", response.body().string());

        // GET http://front-end.sock-shop/detail.html (endp 118)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("id", id);
        }});
        final Response response2 = frontEndSockShop.get(request2, "/detail.html");
        assertStatusCode(response2.code(), 200);
        assertCSSselect("div#content div.container div div.row.same-height-row div div.box.same-height h3", "You may also like these products", response2.body().string());
    }

    @Test
    public void testGetFooterHtml032() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/footer.html (endp 32)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/footer.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#copyright div.container div p.pull-left a", "Weaveworks", response.body().string());
    }

    @Test
    public void testGetIndexHtml089() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/index.html (endp 89)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEndSockShop.get(request, "/index.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#hot div.box div.container div h2", "Hot this week", response.body().string());
    }

    @Test
    public void testGetNavbarHtml033() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/navbar.html (endp 33)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/navbar.html");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetOrders090() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/orders (endp 90)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/orders");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$[*].address.city", "Glasgow", response.body().string());
    }

    @Test
    public void testPostOrders091() throws MalformedURLException, IOException
    {
        // POST http://front-end.sock-shop/orders (endp 91)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.post(request, "/orders");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_103.json")
    public void testGetOrdersOrderid103(final JsonObject json) throws MalformedURLException, IOException
    {
        final String orderId = json.getString("orderId");

        // GET http://front-end.sock-shop/orders/{orderId} (endp 103)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/orders/" + orderId);
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.address.city", "Glasgow", response.body().string());
    }

    @Test
    public void testGetTopbarHtml034() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/topbar.html (endp 34)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/topbar.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#top div.container div.offer a.btn.btn-success", "Offer of the day", response.body().string());
    }
}

