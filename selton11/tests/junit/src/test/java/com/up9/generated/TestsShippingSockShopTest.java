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
public class TestsShippingSockShopTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_23.json")
    public void testPostShipping23(final JsonObject json) throws MalformedURLException, IOException
    {
        final String name = json.getString("name");

        // POST http://shipping.sock-shop/shipping (endp 23)
        final HttpTarget shippingSockShop = getHttpClient("http://shipping.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_23.json", new Hashtable<String, Object>() {{
            put("$.id", uuidv4());
            put("$.name", name);
        }});
        final Response response = shippingSockShop.post(request, "/shipping");
        assertStatusCode(response.code(), 201);
        assertJSONPath("$.id", response.body().string());
    }
}

