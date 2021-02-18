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
public class TestsCartsTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_7.json")
    public void testGetCartsIdItems07(final JsonObject json) throws IOException
    {
        final String id = json.getString("id");

        // GET http://carts/carts/{id}/items (endp 7)
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = carts.get(request, "/carts/" + id + "/items");
        carts.assertStatusCode(200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_8.json")
    public void testPostCartsIdItems08(final JsonObject json) throws IOException
    {
        final String id = json.getString("id");
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
        final String itemId = JSONPath("$.[*].id", catalogue.responseString);
        final String unitPrice = JSONPath("$.[*].price", catalogue.responseString);

        // POST http://carts/carts/{id}/items (endp 8)
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request2.setJsonBody("payload_for_endp_8.json", new Hashtable<String, Object>() {{
            put("$.itemId", itemId);
            put("$.unitPrice", unitPrice);
        }});
        final Response response2 = carts.post(request2, "/carts/" + id + "/items");
        carts.assertStatusCode(201);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_21.json")
    public void testGetCartsIdMerge21(final JsonObject json) throws IOException
    {
        final String id = json.getString("id");
        final String sessionId = json.getString("sessionId");

        // GET http://carts/carts/{id}/merge (endp 21)
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("sessionId", sessionId);
        }});
        final Response response = carts.get(request, "/carts/" + id + "/merge");
        carts.assertStatusCode(202);
    }
}

