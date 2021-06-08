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
public class TestsCatalogueSockShopTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_35.json")
    public void testGetCatalogueId035(final JsonObject json) throws MalformedURLException, IOException
    {
        final String size = json.getString("size");
        final String tags = json.getString("tags");

        // GET http://catalogue.sock-shop/catalogue (endp 2)
        final HttpTarget catalogueSockShop = getHttpClient("http://catalogue.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("sort", "id");
            put("tags", tags);
        }});
        final Response response = catalogueSockShop.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);
        final String id = JSONPath("$[*].id", response.body().string());

        // GET http://catalogue.sock-shop/catalogue/{id} (endp 35)
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = catalogueSockShop.get(request2, "/catalogue/" + id);
        assertStatusCode(response2.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_3.json")
    public void testGetCatalogueSize003(final JsonObject json) throws MalformedURLException, IOException
    {
        final String tags = json.getString("tags");

        // GET http://catalogue.sock-shop/catalogue/size (endp 3)
        final HttpTarget catalogueSockShop = getHttpClient("http://catalogue.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("tags", tags);
        }});
        final Response response = catalogueSockShop.get(request, "/catalogue/size");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetTags005() throws MalformedURLException, IOException
    {
        // GET http://catalogue.sock-shop/tags (endp 5)
        final HttpTarget catalogueSockShop = getHttpClient("http://catalogue.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = catalogueSockShop.get(request, "/tags");
        assertStatusCode(response.code(), 200);
    }
}

