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
public class TestsUserTest
{
    @Test
    public void testGetCustomersId15() throws IOException
    {
        // GET http://user/customers/{id} (endp 15)
        final String id = "57a98d98e4b00679b4a830b2";
        final HttpTarget user = getHttpTarget("TARGET_USER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/customers/" + id);
        assertJSONPath("$.lastName", "Name", response.body().string());
    }

    /**
     * authentication-related test
     */
    @Test
    public void testGetLogin16() throws IOException
    {
        // GET http://user/login (endp 16)
        final HttpTarget user = getHttpTarget("TARGET_USER", new DummyAuth());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/login");
        assertJSONPath("$.user.lastName", "Name", response.body().string());
    }
}

