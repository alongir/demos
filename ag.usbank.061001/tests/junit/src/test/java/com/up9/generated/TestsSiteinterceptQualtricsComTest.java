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
public class TestsSiteinterceptQualtricsComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_20.json")
    public void testPostWrsiteinterceptengine20(final JsonObject json) throws MalformedURLException, IOException
    {
        final String BrandDC = json.getString("BrandDC");
        final String Q_ASID = json.getString("Q_ASID");
        final String Q_CID = json.getString("Q_CID");
        final String Q_CLIENTVERSION = json.getString("Q_CLIENTVERSION");
        final String Q_SIID = json.getString("Q_SIID");
        final String SurveyID = json.getString("SurveyID");

        // POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/ (endp 20)
        final HttpTarget siteinterceptQualtricsCom = getHttpClient("https://siteintercept.qualtrics.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("Q_ASID", Q_ASID);
            put("Q_CID", Q_CID);
            put("Q_CLIENTTYPE", "web");
            put("Q_CLIENTVERSION", Q_CLIENTVERSION);
            put("Q_Impress", "1");
            put("Q_SIID", Q_SIID);
            put("r", getCurrentTimestamp());
        }});
        request.setFormData(new Hashtable<String, Object>() {{
            put("BrandDC", BrandDC);
            put("BrandID", "usbank");
            put("SurveyID", SurveyID);
        }});
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/x-www-form-urlencoded");
        }});
        final Response response = siteinterceptQualtricsCom.post(request, "/WRSiteInterceptEngine/");
        assertStatusCode(response.code(), 200);
    }
}

