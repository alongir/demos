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
public class TestsApplyUsbankComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_36.json")
    public void testGetApplyApplyHtml36(final JsonObject json) throws MalformedURLException, IOException
    {
        final String PRODUCT_CODE = json.getString("PRODUCT_CODE");

        // GET https://apply.usbank.com/apply/apply.html (endp 36)
        final HttpTarget applyUsbankCom = getHttpClient("https://apply.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("PRODUCT_CODE", PRODUCT_CODE);
            put("SUB_PRODUCT_CODE", "PI");
        }});
        final Response response = applyUsbankCom.get(request, "/apply/apply.html");
        assertStatusCode(response.code(), 302);
    }
}

