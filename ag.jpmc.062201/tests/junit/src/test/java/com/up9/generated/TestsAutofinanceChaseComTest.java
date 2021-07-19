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
public class TestsAutofinanceChaseComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_82.json")
    public void testGetAutoFinanceAutoLoans082(final JsonObject json) throws MalformedURLException, IOException
    {
        final String offercode = json.getString("offercode");

        // GET https://autofinance.chase.com/auto-finance/auto-loans (endp 82)
        final HttpTarget autofinanceChaseCom = getHttpClient("https://autofinance.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("offercode", offercode);
        }});
        final Response response = autofinanceChaseCom.get(request, "/auto-finance/auto-loans");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#masthead div.finance-masthead.bkgd-image div.masthead-header.container div.row div.headline.text-left h1", "Finance a car", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_83.json")
    public void testGetAutoFinanceHome083(final JsonObject json) throws MalformedURLException, IOException
    {
        final String offercode = json.getString("offercode");

        // GET https://autofinance.chase.com/auto-finance/home (endp 83)
        final HttpTarget autofinanceChaseCom = getHttpClient("https://autofinance.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("offercode", offercode);
        }});
        final Response response = autofinanceChaseCom.get(request, "/auto-finance/home");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#masthead div.home-masthead.bkgd-image div.masthead-header.container div.row div.headline.text-left h1", "It's your road, choose where to go.", response.body().string());
    }
}

