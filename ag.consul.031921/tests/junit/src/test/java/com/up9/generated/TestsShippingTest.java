package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import java.util.Hashtable;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsShippingTest
{
    @Test
    public void testPostShipping39() throws IOException
    {
        // POST http://shipping/shipping (endp 39)
        final String name = "57a98d98e4b00679b4a830b2";
        final HttpTarget shipping = getHttpTarget("TARGET_SHIPPING", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_39.json", new Hashtable<String, Object>() {{
            put("$.id", uuidv4());
            put("$.name", name);
        }});
        final Response response = shipping.post(request, "/shipping");
        assertStatusCode(response.code(), 201);
    }
}

