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
public class TestsWwwChaseComTest
{
    @Test
    public void testGetAppsServicesTagsHttpsAccountChaseComConsumerBankingSeo02() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/account.chase.com/consumer/banking/seo (endp 2)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/account.chase.com/consumer/banking/seo");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsAutofinanceChaseComAutoFinanceAutoLoans03() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/autofinance.chase.com/auto-finance/auto-loans (endp 3)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/autofinance.chase.com/auto-finance/auto-loans");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsAutofinanceChaseComAutoFinanceHome04() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/autofinance.chase.com/auto-finance/home (endp 4)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/autofinance.chase.com/auto-finance/home");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsCreditcardsChaseComCashBackCreditCards05() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards (endp 5)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsCreditcardsChaseComCashBackCreditCardsFreedomFlex06() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards/freedom/flex (endp 6)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards/freedom/flex");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsLocatorChaseCom07() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/locator.chase.com/ (endp 7)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/locator.chase.com/");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsLocatorChaseComEs08() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/locator.chase.com/es (endp 8)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/locator.chase.com/es");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsLocatorChaseComSearch09() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/locator.chase.com/search (endp 9)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/locator.chase.com/search");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsPersonalChaseComPersonalChecking10() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/personal.chase.com/personal/checking (endp 10)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/personal.chase.com/personal/checking");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsWwwChaseCom11() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/www.chase.com/ (endp 11)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/www.chase.com/");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsWwwChaseComContentChaseUxEnStructuredModuleDirectToDealerPrimaryToolLiquid12() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/www.chase.com/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid (endp 12)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/www.chase.com/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsWwwChaseComPersonalInvestmentsAdvisor13() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/investments/advisor (endp 13)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/www.chase.com/personal/investments/advisor");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsWwwChaseComPersonalInvestmentsAdvisorContactForm14() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/investments/advisor-contact-form (endp 14)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/www.chase.com/personal/investments/advisor-contact-form");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsWwwChaseComPersonalOffersMarketplace15() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/offers/marketplace (endp 15)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/www.chase.com/personal/offers/marketplace");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_22.json")
    public void testGetContentChaseUxEnStructuredModuleParam1IndexParam2Param3ModuleHtml22(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");
        final String param1 = json.getString("param1");
        final String param2 = json.getString("param2");

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/index/{param2}/{param3}/module.html (endp 22)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwChaseCom.get(request, "/content/chase-ux/en/structured/module/" + param + "/index/" + param1 + "/" + param2 + "/module.html");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_23.json")
    public void testGetContentChaseUxEnStructuredModuleParam1ProdPublicLucyAdvisorContactFormMboxParam2ModuleHtml23(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");
        final String param1 = json.getString("param1");

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/prod-public-lucy-advisor-contact-form-mbox/{param2}/module.html (endp 23)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwChaseCom.get(request, "/content/chase-ux/en/structured/module/" + param + "/prod-public-lucy-advisor-contact-form-mbox/" + param1 + "/module.html");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetContentChaseUxEnStructuredModuleDirectToDealerPrimaryToolLiquid20() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid (endp 20)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid");
        assertStatusCode(response.code(), 200);
        assertCSSselect("h2#speedbumpDefaultHeading", "You're now leaving Chase", response.body().string());
        assertCSSselect("html head title", "primary-tool-liquid", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_27.json")
    public void testGetContentExperienceFragmentsMicrositesLucyFormLucylandingpageMasterParamRootHtml27(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");

        // GET https://www.chase.com/content/experience-fragments/microsites/lucy-form/lucylandingpage/master/{param}/root.html (endp 27)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwChaseCom.get(request, "/content/experience-fragments/microsites/lucy-form/lucylandingpage/master/" + param + "/root.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div.aem-Grid div.user-details-form.aem-GridColumn div.landingPageForm div.landingPageForm__header div.landingPageForm__header__message p a.chaseanalytics-track-link", "Sign in here", response.body().string());
    }

    @Test
    public void testGetPersonalInvestmentsAdvisorContactForm29() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/personal/investments/advisor-contact-form (endp 29)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/personal/investments/advisor-contact-form");
        assertStatusCode(response.code(), 200);
        assertCSSselect("main#main h1.accessible-text", "J.P. Morgan Financial Advisor Contact Form | Chase", response.body().string());
        assertCSSselect("html head title", "J.P. Morgan Financial Advisor Contact Form | Chase", response.body().string());
    }
}

