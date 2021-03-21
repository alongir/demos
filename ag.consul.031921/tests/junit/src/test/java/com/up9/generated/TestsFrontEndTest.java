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
    public void testGet01() throws IOException
    {
        // GET http://front-end/ (endp 1)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = frontEnd.get(request, "/");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#hot div.box div.container div h2", "Hot this week", response.body().string());
    }

    @Test
    public void testGetCart12() throws IOException
    {
        // GET http://front-end/tags (endp 21)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/tags");
        assertStatusCode(response.code(), 200);
        final String tags = JSONPath("$.tags[*]", response.body().string());

        // GET http://front-end/catalogue/size (endp 14)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("tags", tags);
        }});
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEnd.get(request2, "/catalogue/size");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.name", "Holy", response2.body().string());

        // GET http://front-end/cart (endp 12)
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response3 = frontEnd.get(request3, "/cart");
        assertStatusCode(response3.code(), 200);
    }

    @Test
    public void testGetCatalogue15() throws IOException
    {
        // GET http://front-end/tags (endp 21)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/tags");
        assertStatusCode(response.code(), 200);
        final String tags = JSONPath("$.tags[*]", response.body().string());

        // GET http://front-end/catalogue (endp 15)
        final String size = "6";
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("tags", tags);
        }});
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEnd.get(request2, "/catalogue");
        assertStatusCode(response2.code(), 200);
    }

    @Test
    public void testGetCategoryHtml06() throws IOException
    {
        // GET http://front-end/category.html (endp 6)
        final String size = "5";
        final String tags = "geek,blue";
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("tags", tags);
        }});
        final Response response = frontEnd.get(request, "/category.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#content div.container div div.panel.panel-default.sidebar-menu div.panel-heading h3.panel-title", "Filters ", response.body().string());
    }

    @Test
    public void testGetCustomerOrderHtml16() throws IOException
    {
        // GET http://front-end/customer-order.html (endp 16)
        final String order = "/orders/6053f8feeae7630007e44bcf";
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("order", order);
        }});
        final Response response = frontEnd.get(request, "/customer-order.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#customer-order div.box h2", "Order #", response.body().string());
    }

    @Test
    public void testGetCustomersCustomerid18() throws IOException
    {
        // GET http://front-end/login (endp 8)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertCSSselect("p", "Cookie is set", response.body().string());
        final String customerId = getCookie(response, "logged_in");

        // GET http://front-end/customers/{customerId} (endp 18)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEnd.get(request2, "/customers/" + customerId);
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.lastName", "Name", response2.body().string());
    }

    @Test
    public void testGetCustomersCustomerid44() throws IOException
    {
        // GET http://front-end/login (endp 8)
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertCSSselect("p", "Cookie is set", response.body().string());
        final String customerId = getCookie(response, "logged_in");

        // GET http://front-end/customers/{customerId} (endp 44)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEnd.get(request2, "/customers/" + customerId);
        assertStatusCode(response2.code(), 200);
    }

    @Test
    public void testGetDetailHtml07() throws IOException
    {
        // GET http://front-end/catalogue (endp 5)
        final String size = "5";
        final String tags = "";
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
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
        final Response response = frontEnd.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);
        final String id = JSONPath("$[*].id", response.body().string());

        // POST http://front-end/cart (endp 4)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
        }});
        request2.setJsonBody("payload_for_endp_4.json", new Hashtable<String, Object>() {{
            put("$.id", id);
        }});
        final Response response2 = frontEnd.post(request2, "/cart");
        assertStatusCode(response2.code(), 201);

        // GET http://front-end/basket.html (endp 2)
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = frontEnd.get(request3, "/basket.html");
        assertStatusCode(response3.code(), 200);
        assertCSSselect("div#basket div.box form h1", "Shopping cart", response3.body().string());

        // DELETE http://front-end/cart (endp 3)
        final HttpRequest request4 = new HttpRequest();
        final Response response4 = frontEnd.delete(request4, "/cart");
        assertStatusCode(response4.code(), 202);

        // POST http://front-end/orders (endp 9)
        final HttpRequest request5 = new HttpRequest();
        request5.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response5 = frontEnd.post(request5, "/orders");
        assertStatusCode(response5.code(), 201);
        assertJSONPath("$.address.city", "Glasgow", response5.body().string());
        final String id1 = JSONPath("$.items[*].itemId", response5.body().string());

        // GET http://front-end/detail.html (endp 7)
        final HttpRequest request6 = new HttpRequest();
        request6.setQueryString(new Hashtable<String, Object>() {{
            put("id", id1);
        }});
        final Response response6 = frontEnd.get(request6, "/detail.html");
        assertStatusCode(response6.code(), 200);
        assertCSSselect("div#content div.container div div.row.same-height-row div div.box.same-height h3", "You may also like these products", response6.body().string());
    }

    @Test
    public void testGetOrdersHref20() throws IOException, MalformedURLException
    {
        // GET http://front-end/catalogue (endp 5)
        final String size = "5";
        final String tags = "";
        final HttpTarget frontEnd = getHttpTarget("TARGET_FRONT_END", new Authentication());
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
        final Response response = frontEnd.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);
        final String id = JSONPath("$[*].id", response.body().string());

        // GET http://front-end/catalogue/{id} (endp 13)
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response2 = frontEnd.get(request2, "/catalogue/" + id);
        assertStatusCode(response2.code(), 200);

        // GET http://front-end/address (endp 10)
        final HttpRequest request3 = new HttpRequest();
        request3.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response3 = frontEnd.get(request3, "/address");
        assertStatusCode(response3.code(), 200);
        assertJSONPath("$.city", "Glasgow", response3.body().string());

        // GET http://front-end/card (endp 11)
        final HttpRequest request4 = new HttpRequest();
        request4.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response4 = frontEnd.get(request4, "/card");
        assertStatusCode(response4.code(), 200);

        // GET http://front-end/customer-orders.html (endp 17)
        final HttpRequest request5 = new HttpRequest();
        final Response response5 = frontEnd.get(request5, "/customer-orders.html");
        assertStatusCode(response5.code(), 200);
        assertCSSselect("div#customer-orders div.box h1", "My orders", response5.body().string());

        // GET http://front-end/orders (endp 19)
        final HttpRequest request6 = new HttpRequest();
        request6.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response6 = frontEnd.get(request6, "/orders");
        assertStatusCode(response6.code(), 201);
        assertJSONPath("$[*].address.city", "Glasgow", response6.body().string());
        final String href = urlPart("/2", JSONPath("$[*]._links.self.href", response6.body().string()));

        // GET http://front-end/orders/{href} (endp 20)
        final HttpRequest request7 = new HttpRequest();
        request7.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response7 = frontEnd.get(request7, "/orders/" + href);
        assertStatusCode(response7.code(), 200);
        assertJSONPath("$.address.city", "Glasgow", response7.body().string());
    }
}

