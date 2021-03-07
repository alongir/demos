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
public class TestsCartsTest
{
    @Test
    public void testGetCartsIdItems13() throws IOException
    {
        // GET http://carts/carts/{id}/items (endp 13)
        final String id = "57a98d98e4b00679b4a830b2";
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = carts.get(request, "/carts/" + id + "/items");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetCartsIdMerge12() throws IOException
    {
        // GET http://carts/carts/{id}/merge (endp 12)
        final String id = "57a98d98e4b00679b4a830b2";
        final String sessionId = "7w4TGI0pnIxn5TEFoHX6O2spPdXa3dut";
        final HttpTarget carts = getHttpTarget("TARGET_CARTS", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("sessionId", sessionId);
        }});
        final Response response = carts.get(request, "/carts/" + id + "/merge");
        assertStatusCode(response.code(), 202);
    }
}

