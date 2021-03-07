package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.DummyAuth;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsMockintoshTest
{
    @Test
    public void testGetCustomersId17() throws IOException
    {
        // GET http://mockintosh/customers/undefined (endp 18)
        final HttpTarget mockintosh = getHttpTarget("TARGET_MOCKINTOSH", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = mockintosh.get(request, "/customers/undefined");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.lastName", "Name", response.body().string());
        final String id = JSONPath("$.id", response.body().string());

        // GET http://mockintosh/customers/{id} (endp 17)
        final HttpRequest request2 = new HttpRequest();
        final Response response2 = mockintosh.get(request2, "/customers/" + id);
        assertStatusCode(response2.code(), 200);
        assertJSONPath("$.lastName", "Name", response2.body().string());
    }

    /**
     * authentication-related test
     */
    @Test
    public void testGetLogin19() throws IOException
    {
        // GET http://mockintosh/login (endp 19)
        final HttpTarget mockintosh = getHttpTarget("TARGET_MOCKINTOSH", new DummyAuth());
        final HttpRequest request = new HttpRequest();
        final Response response = mockintosh.get(request, "/login");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
    }
}

