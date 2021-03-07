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
public class TestsOrdersTest
{
    @Test
    public void testGetOrdersSearchCustomerid14() throws IOException
    {
        // GET http://orders/orders/search/customerId (endp 14)
        final HttpTarget orders = getHttpTarget("TARGET_ORDERS", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("custId", "undefined");
            put("sort", "date");
        }});
        final Response response = orders.get(request, "/orders/search/customerId");
        assertStatusCode(response.code(), 200);
    }
}

