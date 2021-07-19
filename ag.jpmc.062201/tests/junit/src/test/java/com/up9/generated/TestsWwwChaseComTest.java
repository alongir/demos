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
    public void testGetAppsServicesTagsHttpsAccountChaseComConsumerBankingSeo002() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/account.chase.com/consumer/banking/seo (endp 2)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/account.chase.com/consumer/banking/seo");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsAutofinanceChaseComAutoFinanceAutoLoans003() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/autofinance.chase.com/auto-finance/auto-loans (endp 3)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/autofinance.chase.com/auto-finance/auto-loans");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsAutofinanceChaseComAutoFinanceHome004() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/autofinance.chase.com/auto-finance/home (endp 4)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/autofinance.chase.com/auto-finance/home");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsCreditcardsChaseComCashBackCreditCards005() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards (endp 5)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsCreditcardsChaseComCashBackCreditCardsFreedomFlex006() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards/freedom/flex (endp 6)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/creditcards.chase.com/cash-back-credit-cards/freedom/flex");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsLocatorChaseCom007() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/locator.chase.com/ (endp 7)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/locator.chase.com/");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsLocatorChaseComEs008() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/locator.chase.com/es (endp 8)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/locator.chase.com/es");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsLocatorChaseComSearch009() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/locator.chase.com/search (endp 9)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/locator.chase.com/search");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsPersonalChaseComPersonalChecking010() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/personal.chase.com/personal/checking (endp 10)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/personal.chase.com/personal/checking");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsWwwChaseCom011() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/www.chase.com/ (endp 11)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/www.chase.com/");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsWwwChaseComContentChaseUxEnStructuredModuleDirectToDealerPrimaryToolLiquid012() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/www.chase.com/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid (endp 12)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/www.chase.com/content/chase-ux/en/structured/module/direct-to-dealer/primary-tool-liquid");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsWwwChaseComPersonalInvestmentsAdvisor013() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/investments/advisor (endp 13)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/www.chase.com/personal/investments/advisor");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsWwwChaseComPersonalInvestmentsAdvisorContactForm014() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/investments/advisor-contact-form (endp 14)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/www.chase.com/personal/investments/advisor-contact-form");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsWwwChaseComPersonalMortgageMortgageRefinance086() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/mortgage/mortgage-refinance (endp 86)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/www.chase.com/personal/mortgage/mortgage-refinance");
        assertStatusCode(response.code(), 200);
    }

    @Test
    public void testGetAppsServicesTagsHttpsWwwChaseComPersonalOffersMarketplace015() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/apps/services/tags/https/www.chase.com/personal/offers/marketplace (endp 15)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/apps/services/tags/https/www.chase.com/personal/offers/marketplace");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_90.json")
    public void testGetContentChaseUxEnStructuredModuleParamCarouselSingleImagesImageidModuleHtml090(final JsonObject json) throws MalformedURLException, IOException
    {
        final String imageId = json.getString("imageId");
        final String param = json.getString("param");

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param}/carousel-single-images/{imageId}/module.html (endp 90)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwChaseCom.get(request, "/content/chase-ux/en/structured/module/" + param + "/carousel-single-images/" + imageId + "/module.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div.carousel.carousel-version-b h2.carousel--title", "Choose what's right for you", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_17.json")
    public void testGetContentChaseUxEnStructuredModuleParamPersonalInvestmentsInvestmentidIconidModuleHtml017(final JsonObject json) throws MalformedURLException, IOException
    {
        final String iconId = json.getString("iconId");
        final String investmentId = json.getString("investmentId");
        final String param = json.getString("param");

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param}/personal/investments/{investmentId}/{iconId}/module.html (endp 17)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwChaseCom.get(request, "/content/chase-ux/en/structured/module/" + param + "/personal/investments/" + investmentId + "/" + iconId + "/module.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div.colctrl-section.neutral.zeroBottomPadding div.colctrl-section--inner.feature-container.row div.zeroBottomPadding div.bodysection.section div div div div h2", "Reach your financial goals with a J.P. Morgan Advisor", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_88.json")
    public void testGetContentChaseUxEnStructuredModuleParam1PersonalMortgageCtaCallOnlineComeinGreyParam2ModuleHtml088(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");
        final String param1 = json.getString("param1");

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/personal/mortgage/cta/call-online-comein-grey/{param2}/module.html (endp 88)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwChaseCom.get(request, "/content/chase-ux/en/structured/module/" + param + "/personal/mortgage/cta/call-online-comein-grey/" + param1 + "/module.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div.module-bucket.neutral div.feature-container.row div div h2", "Take the first step and get prequalified.", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_94.json")
    public void testGetContentChaseUxEnStructuredModuleParam1PersonalMortgageMortgageRefinanceHeroParam2ModuleHtml094(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");
        final String param1 = json.getString("param1");

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/personal/mortgage/mortgage-refinance-hero/{param2}/module.html (endp 94)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwChaseCom.get(request, "/content/chase-ux/en/structured/module/" + param + "/personal/mortgage/mortgage-refinance-hero/" + param1 + "/module.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div.hero.short.white div.hero--text-container div.center.white h2", "Your refinance journey begins here", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_92.json")
    public void testGetContentChaseUxEnStructuredModuleParam1PersonalMortgageMortgageRefinanceHeroRefinanceHeroParam2ModuleHtml092(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");
        final String param1 = json.getString("param1");

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/personal/mortgage/mortgage-refinance-hero/refinance-hero/{param2}/module.html (endp 92)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwChaseCom.get(request, "/content/chase-ux/en/structured/module/" + param + "/personal/mortgage/mortgage-refinance-hero/refinance-hero/" + param1 + "/module.html");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_89.json")
    public void testGetContentChaseUxEnStructuredModuleParam1PersonalMortgageMortgageRefinanceMortgageRefinanceBucketParam2ModuleHtml089(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");
        final String param1 = json.getString("param1");

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/personal/mortgage/mortgage-refinance/mortgage-refinance-bucket/{param2}/module.html (endp 89)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwChaseCom.get(request, "/content/chase-ux/en/structured/module/" + param + "/personal/mortgage/mortgage-refinance/mortgage-refinance-bucket/" + param1 + "/module.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div.module-bucket.default div.feature-container.row div div h2", "Tools and tips to help you find the right loan for your needs", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_95.json")
    public void testGetContentChaseUxEnStructuredModuleParam1PersonalMortgageRefinanceLpParam2ModuleHtml095(final JsonObject json) throws MalformedURLException, IOException
    {
        final String param = json.getString("param");
        final String param1 = json.getString("param1");

        // GET https://www.chase.com/content/chase-ux/en/structured/module/{param1}/personal/mortgage/refinance-lp/{param2}/module.html (endp 95)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwChaseCom.get(request, "/content/chase-ux/en/structured/module/" + param + "/personal/mortgage/refinance-lp/" + param1 + "/module.html");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_23.json")
    public void testGetContentChaseUxEnStructuredModuleParam1ProdPublicLucyAdvisorContactFormMboxParam2ModuleHtml023(final JsonObject json) throws MalformedURLException, IOException
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
    public void testGetContentChaseUxEnStructuredModuleDirectToDealerPrimaryToolLiquid020() throws MalformedURLException, IOException
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
    public void testGetContentExperienceFragmentsMicrositesLucyFormLucylandingpageMasterParamRootHtml027(final JsonObject json) throws MalformedURLException, IOException
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
    public void testGetPersonalInvestmentsAdvisorContactForm029() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/personal/investments/advisor-contact-form (endp 29)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/personal/investments/advisor-contact-form");
        assertStatusCode(response.code(), 200);
        assertCSSselect("main#main h1.accessible-text", "J.P. Morgan Financial Advisor Contact Form | Chase", response.body().string());
        assertCSSselect("html head title", "J.P. Morgan Financial Advisor Contact Form | Chase", response.body().string());
    }

    @Test
    public void testGetPersonalMortgageMortgageRefinance096() throws MalformedURLException, IOException
    {
        // GET https://www.chase.com/personal/mortgage/mortgage-refinance (endp 96)
        final HttpTarget wwwChaseCom = getHttpClient("https://www.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwChaseCom.get(request, "/personal/mortgage/mortgage-refinance");
        assertStatusCode(response.code(), 200);
        assertCSSselect("main#main h1.accessible-text", "Refinance your mortgage", response.body().string());
        assertCSSselect("html head title", "Refinance your Mortgage | Refinance | Chase.com", response.body().string());
    }
}

