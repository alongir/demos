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
public class TestsSalesUsbankComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_38.json")
    public void testGetProductConfigurationV1ProductsUsbankParamPi38(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");

        // GET https://sales.usbank.com/product-configuration/v1/products/usbank/{param}/PI (endp 38)
        final HttpTarget salesUsbankCom = getHttpClient("https://sales.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
            put("x-requested-with", "X-Requested-With");
        }});
        final Response response = salesUsbankCom.get(request, "/product-configuration/v1/products/usbank/" + param + "/PI");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$.tenant", "usbank", response.body().string());
    }
}

