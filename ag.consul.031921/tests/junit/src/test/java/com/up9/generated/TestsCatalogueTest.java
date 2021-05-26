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
public class TestsCatalogueTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_27.json")
    public void testGetCatalogueId27(final JsonObject json) throws MalformedURLException, IOException
    {
        final String size = json.getString("size");

        // GET http://catalogue/tags (endp 30)
        final HttpTarget catalogue = getHttpClient("http://catalogue", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = catalogue.get(request, "/tags");
        assertStatusCode(response.code(), 200);
        final String tags = JSONPath("$.tags[*]", response.body().string());

        // GET http://catalogue/catalogue (endp 29)
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("sort", "id");
            put("tags", tags);
        }});
        final Response response2 = catalogue.get(request2, "/catalogue");
        assertStatusCode(response2.code(), 200);
        final String id = JSONPath("$[*].id", response2.body().string());

        // GET http://catalogue/catalogue/{id} (endp 27)
        final HttpRequest request3 = new HttpRequest();
        final Response response3 = catalogue.get(request3, "/catalogue/" + id);
        assertStatusCode(response3.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_28.json")
    public void testGetCatalogueSize28(final JsonObject json) throws MalformedURLException, IOException
    {
        final String tags = json.getString("tags");

        // GET http://catalogue/catalogue/size (endp 28)
        final HttpTarget catalogue = getHttpClient("http://catalogue", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("tags", tags);
        }});
        final Response response = catalogue.get(request, "/catalogue/size");
        assertStatusCode(response.code(), 200);
    }
}

