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
    public void testGetBankAccountsCheckingAccountsHtml01() throws MalformedURLException, IOException
    {
        // GET https://www.usbank.com/bank-accounts/checking-accounts.html (endp 1)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwUsbankCom.get(request, "/bank-accounts/checking-accounts.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("dialog.dialog.shield-zipcodes[name=\"#saveXZip\"] div.content div.heading.large h1", "Zip Code", response.body().string());
        assertCSSselect("html head title", "Checking accounts | Open a Personal Checking Account | U.S. Bank", response.body().string());
    }

    @Test
    public void testGetBankAccountsCheckingAccountsGoldCheckingAccountHtml02() throws MalformedURLException, IOException
    {
        // GET https://www.usbank.com/bank-accounts/checking-accounts/gold-checking-account.html (endp 2)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = wwwUsbankCom.get(request, "/bank-accounts/checking-accounts/gold-checking-account.html");
        assertStatusCode(response.code(), 200);
        assertCSSselect("section.pubIns.productDetailsPage div div.bodyContent.container-fluid div.row div.bannerResponsiveGrid div div.aem-Grid div.banner.parbase.aem-GridColumn div.USBDesignSystem--Shield.USBHero div div.USBHero__Container.clearfix div.clearfix div.text div div.textContainer h1", "U.S. BANK GOLD CHECKING PACKAGE", response.body().string());
        assertCSSselect("html head title", "Gold Checking account | Personal Checking account | U.S. Bank", response.body().string());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_4.json")
    public void testPostPlpxrbYloParam1Param2Param3Param4AesAeid04(final JsonObject json) throws MalformedURLException, IOException
    {
        final String aeId = json.getString("aeId");
        final String param = json.getString("param");
        final String param1 = json.getString("param1");
        final String param2 = json.getString("param2");
        final String param3 = json.getString("param3");
        final String sensor_data = json.getString("sensor_data");

        // POST https://www.usbank.com/plpXRb/YlO/{param1}/{param2}/{param3}/{param4}/aEs/{aeId} (endp 4)
        final HttpTarget wwwUsbankCom = getHttpClient("https://www.usbank.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_4.json", new Hashtable<String, Object>() {{
            put("$.sensor_data", sensor_data);
        }});
        final Response response = wwwUsbankCom.post(request, "/plpXRb/YlO/" + param + "/" + param1 + "/" + param2 + "/" + param3 + "/aEs/" + aeId);
        assertStatusCode(response.code(), 201);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_5.json")
    public void testPostSvtUsbankShieldFetchdisclosurecontent05(final JsonObject json) throws MalformedURLException, IOException
    {
        final String businessLines = json.getString("businessLines");
        final String disclosureTitles = json.getString("disclosureTitles");

        // POST https://www.usbank.com/svt/usbank/shield/fetchDisclosureContent (endp 5)
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
}

