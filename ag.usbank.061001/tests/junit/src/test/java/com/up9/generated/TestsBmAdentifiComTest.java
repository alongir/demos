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
public class TestsBmAdentifiComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_30.json")
    public void testGetPixelConvParam30(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");

        // GET https://bm.adentifi.com/pixel/conv/{param} (endp 30)
        final HttpTarget bmAdentifiCom = getHttpClient("https://bm.adentifi.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = bmAdentifiCom.get(request, "/pixel/conv/" + param);
        assertStatusCode(response.code(), 302);
    }
}

