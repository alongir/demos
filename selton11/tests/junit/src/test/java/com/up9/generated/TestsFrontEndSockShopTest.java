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
    public void testGet01() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/ (endp 1)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEndSockShop.get(request, "/");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#hot div.box div.container div h2", "Hot this week", response.body().string());
    }

    @Test
    public void testGetAddress02() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/address (endp 2)
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
    public void testGetBasketHtml03() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/basket.html (endp 3)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEndSockShop.get(request, "/basket.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#basket div.box form h1", "Shopping cart", response.body().string());
    }

    @Test
    public void testGetCard04() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/card (endp 4)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/card");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testDeleteCart06() throws MalformedURLException, IOException
    {
        // DELETE http://front-end.sock-shop/cart (endp 6)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.delete(request, "/cart");
        assertStatusCode(response.code(), 202);
    }

    @Test
    public void testPostCart07() throws MalformedURLException, IOException
    {
        // POST http://front-end.sock-shop/orders (endp 16)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.post(request, "/orders");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response.body().string());
        final String id = JSONPath("$.items[*].itemId", response.body().string());
        final String quantity = JSONPath("$.items[*].quantity", response.body().string());

        // POST http://front-end.sock-shop/cart (endp 7)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
            put("x-requested-with", "XMLHttpRequest");
        }});
        request2.setJsonBody("payload_for_endp_7.json", new Hashtable<String, Object>() {{
            put("$.id", id);
        }});
        final Response response2 = frontEndSockShop.post(request2, "/cart");
        assertStatusCode(response2.code(), 201);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_8.json")
    public void testDeleteCartItemid08(final JsonObject json) throws MalformedURLException, IOException
    {
        final String size = json.getString("size");

        // GET http://front-end.sock-shop/catalogue (endp 11)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("tags", "");
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);

        // GET http://front-end.sock-shop/cart (endp 5)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEndSockShop.get(request2, "/cart");
        assertStatusCode(response2.code(), 200);
        final String itemId = JSONPath("$[*].itemId", response2.body().string());

        // DELETE http://front-end.sock-shop/cart/{itemId} (endp 8)
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response3 = frontEndSockShop.delete(request3, "/cart/" + itemId);
        assertStatusCode(response3.code(), 202);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_9.json")
    public void testGetCatalogueId09(final JsonObject json) throws MalformedURLException, IOException
    {
        final String size = json.getString("size");

        // GET http://front-end.sock-shop/catalogue (endp 11)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("tags", "");
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);
        final String id = JSONPath("$[*].id", response.body().string());

        // GET http://front-end.sock-shop/catalogue/{id} (endp 9)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEndSockShop.get(request2, "/catalogue/" + id);
        assertStatusCode(response2.code(), 200);
    }

    @Test
    public void testGetCatalogueSize10() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/catalogue/size (endp 10)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("tags", "");
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/catalogue/size");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetCategoryHtml12() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/category.html (endp 12)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEndSockShop.get(request, "/category.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title", "Filters ", response.body().string());
    }

    @Test
    public void testGetCustomersCustomerid13() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/login (endp 15)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertCSSselect("p", "Cookie is set", response.body().string());
        final String customerId = getCookie(response, "logged_in");

        // GET http://front-end.sock-shop/customers/{customerId} (endp 13)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEndSockShop.get(request2, "/customers/" + customerId);
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.lastName", "Name", response2.body().string());
    }

    @Test
    public void testGetDetailHtml14() throws MalformedURLException, IOException
    {
        // POST http://front-end.sock-shop/orders (endp 16)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.post(request, "/orders");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response.body().string());
        final String id = JSONPath("$.items[*].itemId", response.body().string());

        // GET http://front-end.sock-shop/detail.html (endp 14)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("id", id);
        }});
        final Response response2 = frontEndSockShop.get(request2, "/detail.html");
        assertStatusCode(response2.code(), 200);
        assertCSSselect("div#content div.container div div.row.same-height-row div div.box.same-height h3", "You may also like these products", response2.body().string());
    }

    @Test
    public void testGetOrders54() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/orders (endp 54)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/orders");
        assertStatusCode(response.code(), 201);
    }

    @Test
    public void testGetTags17() throws MalformedURLException, IOException
    {
        // GET http://front-end.sock-shop/tags (endp 17)
        final HttpTarget frontEndSockShop = getHttpClient("http://front-end.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEndSockShop.get(request, "/tags");
        assertStatusCode(response.code(), 200);
    }
}

