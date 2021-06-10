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
public class TestsLocationsUsbankComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_40.json")
    public void testGetParam40(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");

        // GET https://locations.usbank.com/{param} (endp 40)
        final HttpTarget locationsUsbankCom = getHttpClient("https://locations.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = locationsUsbankCom.get(request, "/" + param);
        assertStatusCode(response.code(), 200);
        assertCSSselect("html head title", "Location Search", response.body().string());
    }

    @Test
    public void testGetSearchHtml39() throws MalformedURLException, IOException
    {
        // GET https://locations.usbank.com/search.html (endp 39)
        final HttpTarget locationsUsbankCom = getHttpClient("https://locations.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = locationsUsbankCom.get(request, "/search.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("html head title", "Location Search", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_41.json")
    public void testGetSearchParam41(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");

        // GET https://locations.usbank.com/search/{param} (endp 41)
        final HttpTarget locationsUsbankCom = getHttpClient("https://locations.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = locationsUsbankCom.get(request, "/search/" + param);
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#searchRest div.searchResultsLocations div.cq-dd-pages h1.hidden-sm.hidden-xs", "Search Results", response.body().string());
    }
}

