package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.DummyAuth;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import javax.json.JsonObject;
import net.joshka.junit.json.params.JsonFileSource;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsUserTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_15.json")
    public void testGetCustomersId15(final JsonObject json) throws IOException
    {
        final String id = json.getString("id");

        // GET http://user/customers/{id} (endp 15)
        final HttpTarget user = getHttpTarget("TARGET_USER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/customers/" + id);
        assertStatusCode(response.code(), 200);
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
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.user.lastName", "Name", response.body().string());
    }
}

