package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.json.JsonObject;
import net.joshka.junit.json.params.JsonFileSource;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsOrdersTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_36.json")
    public void testGetOrdersHref36(final JsonObject json) throws MalformedURLException, IOException
    {
        final String href = json.getString("href");

        // GET http://orders/orders/{href} (endp 36)
        final HttpTarget orders = getHttpClient("http://orders", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = orders.get(request, "/orders/" + href);
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.address.city", "Glasgow", response.body().string());
    }
}

