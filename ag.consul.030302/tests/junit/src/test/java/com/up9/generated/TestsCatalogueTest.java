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
public class TestsCatalogueTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_10.json")
    public void testGetCatalogue10(final JsonObject json) throws IOException
    {
        final String size = json.getString("size");

        // GET http://catalogue/catalogue (endp 10)
        final HttpTarget catalogue = getHttpTarget("TARGET_CATALOGUE", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("page", "1");
            put("size", size);
            put("tags", "");
        }});
        final Response response = catalogue.get(request, "/catalogue");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetCatalogueSize09() throws IOException
    {
        // GET http://catalogue/catalogue/size (endp 9)
        final HttpTarget catalogue = getHttpTarget("TARGET_CATALOGUE", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("tags", "");
        }});
        final Response response = catalogue.get(request, "/catalogue/size");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetTags11() throws IOException
    {
        // GET http://catalogue/tags (endp 11)
        final HttpTarget catalogue = getHttpTarget("TARGET_CATALOGUE", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = catalogue.get(request, "/tags");
        assertStatusCode(response.code(), 200);
    }
}
