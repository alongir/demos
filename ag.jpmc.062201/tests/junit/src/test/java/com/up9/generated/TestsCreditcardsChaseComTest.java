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
public class TestsCreditcardsChaseComTest
{
    @Test
    public void testGetCashBackCreditCards066() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/ (endp 1)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/");
        assertStatusCode(response.code(), 200);
        assertCSSselect("main#main h1.accessible-text", "Chase.com home", response.body().string());
        assertCSSselect("html head title", "Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com", response.body().string());
        final String CELL = urlPart("?CELL", CSSselect("div div header.header-navigation section.desktop-header section.header-navigation__content.row section nav ul li div.header-navigation__dropdown.hide ul li.colored a.regular-link.chaseanalytics-track-link[href] @href", response.body().string()));

        // GET https://creditcards.chase.com/cash-back-credit-cards (endp 66)
        final HttpTarget creditcardsChaseCom = getHttpClient("https://creditcards.chase.com", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("CELL", CELL);
            put("jp_ltg", "chsecate_cashback");
        }});
        final Response response2 = creditcardsChaseCom.get(request2, "/cash-back-credit-cards");
        assertStatusCode(response2.code(), 200);
        assertCSSselect("div#jsEnabled div.compatibility footer.footer div.row.content-section div.d-none.container div.section h3.placeholder-hidden", "Credit Cards", response2.body().string());
        assertCSSselect("html head title", "Compare Cash Back Credit Cards | Chase", response2.body().string());
    }

    @Test
    public void testGetCashBackCreditCardsFreedomFlex065() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/ (endp 1)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/");
        assertStatusCode(response.code(), 200);
        assertCSSselect("main#main h1.accessible-text", "Chase.com home", response.body().string());
        assertCSSselect("html head title", "Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com", response.body().string());
        final String CELL = urlPart("?CELL", CSSselect("div div header.header-navigation section.desktop-header section.header-navigation__content.row section nav ul li div.header-navigation__dropdown.hide ul li.colored a.regular-link.chaseanalytics-track-link[href] @href", response.body().string()));

        // GET https://creditcards.chase.com/cash-back-credit-cards/freedom/flex (endp 65)
        final HttpTarget creditcardsChaseCom = getHttpClient("https://creditcards.chase.com", new Authentication());
        final HttpRequest request2 = new HttpRequest();
        request2.setQueryString(new Hashtable<String, Object>() {{
            put("CELL", CELL);
        }});
        final Response response2 = creditcardsChaseCom.get(request2, "/cash-back-credit-cards/freedom/flex");
        assertStatusCode(response2.code(), 200);
        assertCSSselect("div#leftPanel div.left-rail-inner.clearfix div.no-padding.left-rail-header.freedomflex h1.card-title sup.sm-fix", "SM", response2.body().string());
        assertCSSselect("html head title", "Chase Freedom Flex Credit Card | Chase.com", response2.body().string());
    }
}

