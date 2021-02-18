package com.up9.generated;

import com.up9.generated.Authentication;
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
    @JsonFileSource(resources = "/dataset_1.json")
    public void testGetCustomersId01(final JsonObject json) throws IOException
    {
        final String id = json.getString("id");

        // GET http://user/customers/{id} (endp 1)
        final HttpTarget user = getHttpTarget("TARGET_USER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/customers/" + id);
        user.assertStatusCode(200);
        user.assertJSONPath("$.lastName", "Name");
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_2.json")
    public void testGetCustomersIdAddresses02(final JsonObject json) throws IOException
    {
        final String id = json.getString("id");

        // GET http://user/customers/{id}/addresses (endp 2)
        final HttpTarget user = getHttpTarget("TARGET_USER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/customers/" + id + "/addresses");
        user.assertStatusCode(200);
        user.assertJSONPath("$._embedded.address.[*].city", "Glasgow");
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_3.json")
    public void testGetCustomersIdCards03(final JsonObject json) throws IOException
    {
        final String id = json.getString("id");

        // GET http://user/customers/{id}/cards (endp 3)
        final HttpTarget user = getHttpTarget("TARGET_USER", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = user.get(request, "/customers/" + id + "/cards");
        user.assertStatusCode(200);
    }
}

