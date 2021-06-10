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
public class TestsWwwUsbankComTest
{
    @Test
    public void testGetAboutUsBankCustomerServiceHtml01() throws MalformedURLException, IOException
    {
        // GET https://www.usbank.com/about-us-bank/customer-service.html (endp 1)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwUsbankCom.get(request, "/about-us-bank/customer-service.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("html head title", "Customer service | U.S. Bank", response.body().string());
    }

    @Test
    public void testGetBankAccountsCheckingAccountsHtml02() throws MalformedURLException, IOException
    {
        // GET https://www.usbank.com/bank-accounts/checking-accounts.html (endp 2)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwUsbankCom.get(request, "/bank-accounts/checking-accounts.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("dialog.dialog.shield-zipcodes[name=\"#saveXZip\"] div.content div.heading.large h1", "Zip Code", response.body().string());
        assertCSSselect("html head title", "Checking accounts | Open a Personal Checking Account | U.S. Bank", response.body().string());
    }

    @Test
    public void testGetBankAccountsCheckingAccountsGoldCheckingAccountHtml03() throws MalformedURLException, IOException
    {
        // GET https://www.usbank.com/bank-accounts/checking-accounts/gold-checking-account.html (endp 3)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwUsbankCom.get(request, "/bank-accounts/checking-accounts/gold-checking-account.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("section.pubIns.productDetailsPage div div.bodyContent.container-fluid div.row div.bannerResponsiveGrid div div.aem-Grid div.banner.parbase.aem-GridColumn div.USBDesignSystem--Shield.USBHero div div.USBHero__Container.clearfix div.clearfix div.text div div.textContainer h1", "U.S. BANK GOLD CHECKING PACKAGE", response.body().string());
        assertCSSselect("html head title", "Gold Checking account | Personal Checking account | U.S. Bank", response.body().string());
    }

    @Test
    public void testGetCorporateAndCommercialBankingSolutionsCreditAndFinancingHtml81() throws MalformedURLException, IOException
    {
        // GET https://www.usbank.com/corporate-and-commercial-banking/solutions/credit-and-financing.html (endp 81)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwUsbankCom.get(request, "/corporate-and-commercial-banking/solutions/credit-and-financing.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("a#continue", "Continue", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_49.json")
    public void testGetCreditCardsHref49(final JsonObject json) throws MalformedURLException, IOException
    {
        final String c3ch = json.getString("c3ch");
        final String c3nid = json.getString("c3nid");
        final String href = json.getString("href");
        final String icid = json.getString("icid");

        // GET https://www.usbank.com/credit-cards/{href} (endp 49)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("c3ch", c3ch);
            put("c3nid", c3nid);
            put("icid", icid);
        }});
        final Response response = wwwUsbankCom.get(request, "/credit-cards/" + href);
        assertStatusCode(response.code(), 200);
        assertCSSselect("a#continue", "Continue", response.body().string());
    }

    @Test
    public void testGetHomeLoansMortgageFirstTimeHomeBuyersHtml50() throws MalformedURLException, IOException
    {
        // GET https://www.usbank.com/home-loans/mortgage/first-time-home-buyers.html (endp 50)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwUsbankCom.get(request, "/home-loans/mortgage/first-time-home-buyers.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("section.pubIns div.bodyContent.container-fluid div.row.minHeightSection div div.aem-Grid div.containerComp.parbase.aem-GridColumn div.containerComponent.smallPaddingTopDT.smallPaddingRightDT.smallPaddingBottomDT.smallPaddingLeftDT.smallPaddingTopMob.smallPaddingRightMob.noneBottomMob.smallPaddingLeftMob.gray div div.aem-Grid div.text.parbase.aem-GridColumn h3", "Featured articles", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_5.json")
    public void testPostPlpxrbYloParam1Param2Param3Param4AesAeid05(final JsonObject json) throws MalformedURLException, IOException
    {
        final String aeId = json.getString("aeId");
        final String param = json.getString("param");
        final String param1 = json.getString("param1");
        final String param2 = json.getString("param2");
        final String param3 = json.getString("param3");
        final String sensor_data = json.getString("sensor_data");

        // POST https://www.usbank.com/plpXRb/YlO/{param1}/{param2}/{param3}/{param4}/aEs/{aeId} (endp 5)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_5.json", new Hashtable<String, Object>() {{
            put("$.sensor_data", sensor_data);
        }});
        final Response response = wwwUsbankCom.post(request, "/plpXRb/YlO/" + param + "/" + param1 + "/" + param2 + "/" + param3 + "/aEs/" + aeId);
        assertStatusCode(response.code(), 201);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_54.json")
    public void testGetSvtUsbankRpsfetchdisclosurecontent54(final JsonObject json) throws MalformedURLException, IOException
    {
        final String disclosureTitles = json.getString("disclosureTitles");

        // GET https://www.usbank.com/svt/usbank/rpsfetchDisclosureContent (endp 54)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("disclosureTitles", disclosureTitles);
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwUsbankCom.get(request, "/svt/usbank/rpsfetchDisclosureContent");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$[*].status", "success", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_6.json")
    public void testPostSvtUsbankShieldFetchdisclosurecontent06(final JsonObject json) throws MalformedURLException, IOException
    {
        final String businessLines = json.getString("businessLines");
        final String disclosureTitles = json.getString("disclosureTitles");

        // POST https://www.usbank.com/svt/usbank/shield/fetchDisclosureContent (endp 6)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setFormData(new Hashtable<String, Object>() {{
            put("businessLines", businessLines);
            put("disclosureTitles", disclosureTitles);
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/x-www-form-urlencoded");
            put("x-requested-with", "XMLHttpRequest");
        }});
        final Response response = wwwUsbankCom.post(request, "/svt/usbank/shield/fetchDisclosureContent");
        assertStatusCode(response.code(), 200);
        assertJSONPath("$[*].status", "success", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_84.json")
    public void testGetWealthManagementHref84(final JsonObject json) throws MalformedURLException, IOException
    {
        final String href = json.getString("href");

        // GET https://www.usbank.com/wealth-management/{href} (endp 84)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwUsbankCom.get(request, "/wealth-management/" + href);
        assertStatusCode(response.code(), 200);
        assertCSSselect("section.pubIns div.bodyContent.container-fluid div.row div div.aem-Grid div.containerComp.parbase.aem-GridColumn div.containerComponent.mediumPaddingTopDT.largePaddingRightDT.mediumPaddingBottomDT.largePaddingLeftDT.smallPaddingTopMob.smallPaddingRightMob.smallPaddingBottomMob.smallPaddingLeftMob.gray div div.aem-Grid div.aem-GridColumn div.xf-content-height div.aem-Grid div.containerComp.parbase.aem-GridColumn div.containerComponent div div.aem-Grid div.aem-GridColumn div.xf-content-height div.aem-Grid div.containerComp.parbase.aem-GridColumn div.containerComponent div div.aem-Grid div.containerComp.parbase.aem-GridColumn--default--none.aem-GridColumn div.containerComponent.noneTopDT.mediumPaddingRightDT.noneBottomDT.noneleftDT.noneTopMob.noneRightMob.noneBottomMob.noneleftMob.transparent div div.aem-Grid div.parbase.aem-GridColumn div.usbTextImage div.textimage-text.text.largePaddingBottomSeparator div p", "No minimum investment required", response.body().string());
    }
}

