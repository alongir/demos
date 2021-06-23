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
public class TestsCreditcardsChaseComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_66.json")
    public void testGetCashBackCreditCards66(final JsonObject json) throws MalformedURLException, IOException
    {
        final String CELL = json.getString("CELL");

        // GET https://creditcards.chase.com/cash-back-credit-cards (endp 66)
        final HttpTarget creditcardsChaseCom = getHttpClient("https://creditcards.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("CELL", CELL);
            put("jp_ltg", "chsecate_cashback");
        }});
        final Response response = creditcardsChaseCom.get(request, "/cash-back-credit-cards");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#jsEnabled div.compatibility footer.footer div.row.content-section div.d-none.container div.section h3.placeholder-hidden", "Credit Cards", response.body().string());
        assertCSSselect("html head title", "Compare Cash Back Credit Cards | Chase", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_65.json")
    public void testGetCashBackCreditCardsFreedomFlex65(final JsonObject json) throws MalformedURLException, IOException
    {
        final String CELL = json.getString("CELL");

        // GET https://creditcards.chase.com/cash-back-credit-cards/freedom/flex (endp 65)
        final HttpTarget creditcardsChaseCom = getHttpClient("https://creditcards.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("CELL", CELL);
        }});
        final Response response = creditcardsChaseCom.get(request, "/cash-back-credit-cards/freedom/flex");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#leftPanel div.left-rail-inner.clearfix div.no-padding.left-rail-header.freedomflex h1.card-title sup.sm-fix", "SM", response.body().string());
        assertCSSselect("html head title", "Chase Freedom Flex Credit Card | Chase.com", response.body().string());
    }
}

