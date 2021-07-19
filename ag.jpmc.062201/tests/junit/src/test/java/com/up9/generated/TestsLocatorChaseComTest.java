package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsLocatorChaseComTest
{
    @Test
    public void testGet073() throws MalformedURLException, IOException
    {
        // GET https://locator.chase.com/ (endp 73)
        final HttpTarget locatorChaseCom = getHttpClient("https://locator.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("locale", "en_US");
        }});
        final Response response = locatorChaseCom.get(request, "/");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#hero-label h1", "Find a Chase ATM or branch near you", response.body().string());
        assertCSSselect("html head title", "Find a Chase ATM or branch near you | Chase Bank", response.body().string());
    }

    @Test
    public void testGetAdspace074() throws MalformedURLException, IOException
    {
        // GET https://locator.chase.com/adspace (endp 74)
        final HttpTarget locatorChaseCom = getHttpClient("https://locator.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = locatorChaseCom.get(request, "/adspace");
        assertStatusCode(response.code(), 200);
    }
}

