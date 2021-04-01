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
public class TestsFrontEndTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_13.json")
    public void testGetCatalogueId13(final JsonObject json) throws MalformedURLException, IOException
    {
        final String id = json.getString("id");

        // GET http://front-end/catalogue/{id} (endp 13)
        final HttpTarget frontEnd = getHttpClient("http://front-end", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = frontEnd.get(request, "/catalogue/" + id);
        assertStatusCode(response.code(), 200);
    }
}

