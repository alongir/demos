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
    @JsonFileSource(resources = "/dataset_13.json")
    public void testGetCartsIdItems13(final JsonObject json) throws IOException
    {
        final String id = json.getString("id");

        // GET http://carts/carts/{id}/items (endp 13)
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = carts.get(request, "/carts/" + id + "/items");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_12.json")
    public void testGetCartsIdMerge12(final JsonObject json) throws IOException
    {
        final String id = json.getString("id");
        final String sessionId = json.getString("sessionId");

        // GET http://carts/carts/{id}/merge (endp 12)
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("sessionId", sessionId);
        }});
        final Response response = carts.get(request, "/carts/" + id + "/merge");
        assertStatusCode(response.code(), 202);
    }
}

