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
public class TestsOrdersSockShopTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_38.json")
    public void testGetOrdersSearchCustomerid38(final JsonObject json) throws MalformedURLException, IOException
    {
        final String custId = json.getString("custId");

        // GET http://orders.sock-shop/orders/search/customerId (endp 38)
        final HttpTarget ordersSockShop = getHttpClient("http://orders.sock-shop", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("custId", custId);
            put("sort", "date");
        }});
        final Response response = ordersSockShop.get(request, "/orders/search/customerId");
        assertStatusCode(response.code(), 200);
    }
}

