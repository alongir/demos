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
    @JsonFileSource(resources = "/dataset_6.json")
    public void testGetCatalogueId06(final JsonObject json) throws IOException
    {
        final String size = json.getString("size");
        final String tags = json.getString("tags");

        // GET http://catalogue/catalogue (endp 5)
        final HttpTarget catalogue = getHttpTarget("TARGET_CATALOGUE", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("size", size);
            put("sort", "id");
            put("tags", tags);
        }});
        final Response response = catalogue.get(request, "/catalogue");
        catalogue.assertStatusCode(200);
        final String id = JSONPath("$.[*].id", catalogue.responseString);

        // GET http://catalogue/catalogue/{id} (endp 6)
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = catalogue.get(request2, "/catalogue/" + id);
        catalogue.assertStatusCode(200);
    }
}

