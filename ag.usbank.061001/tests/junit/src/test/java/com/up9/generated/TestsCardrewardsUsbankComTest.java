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
public class TestsCardrewardsUsbankComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_69.json")
    public void testGetConnectParam69(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");

        // GET https://cardrewards.usbank.com/connect/{param} (endp 69)
        final HttpTarget cardrewardsUsbankCom = getHttpClient("https://cardrewards.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = cardrewardsUsbankCom.get(request, "/connect/" + param);
        assertStatusCode(response.code(), 200);
        assertCSSselect("html head title", "Rewards Calculator", response.body().string());
    }
}

