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
    @JsonFileSource(resources = "/dataset_25.json")
    public void testGetCatalogueId25(final JsonObject json) throws MalformedURLException, IOException
    {
        final String size = json.getString("size");

        // GET http://catalogue.sock-shop/tags (endp 27)
        final HttpTarget catalogueSockShop = getHttpClient("http://catalogue.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = catalogueSockShop.get(request, "/tags");
        assertStatusCode(response.code(), 200);
        final String tags = JSONPath("$.tags[*]", response.body().string());

        // GET http://catalogue.sock-shop/catalogue (endp 24)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("sort", "id");
            put("tags", tags);
        }});
        final Response response2 = catalogueSockShop.get(request2, "/catalogue");
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$[*].tag[*]", response2.body().string());
        final String id = JSONPath("$[*].id", response2.body().string());

        // GET http://catalogue.sock-shop/catalogue/{id} (endp 25)
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = catalogueSockShop.get(request3, "/catalogue/" + id);
        assertStatusCode(response3.code(), 200);
        assertJSONPath("$.tag[*]", response3.body().string());
    }

    @Test
    public void testGetCatalogueSize26() throws MalformedURLException, IOException
    {
        // GET http://catalogue.sock-shop/catalogue/size (endp 26)
        final HttpTarget catalogueSockShop = getHttpClient("http://catalogue.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("tags", "");
        }});
        final Response response = catalogueSockShop.get(request, "/catalogue/size");
        assertStatusCode(response.code(), 200);
    }
}

