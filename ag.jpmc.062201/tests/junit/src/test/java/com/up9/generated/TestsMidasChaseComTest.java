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
public class TestsMidasChaseComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_33.json")
    public void testGetStreamClick33(final JsonObject json) throws MalformedURLException, IOException
    {
        final String ssv_creativeid = json.getString("ssv_creativeid");
        final String ssv_midas_id = json.getString("ssv_midas_id");
        final String ssv_random = json.getString("ssv_random");
        final String ssv_transid = json.getString("ssv_transid");
        final String ssv_v1st = json.getString("ssv_v1st");

        // GET https://midas.chase.com/stream/click (endp 33)
        final HttpTarget midasChaseCom = getHttpClient("https://midas.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("ssv_channel", "web");
            put("ssv_creativeid", ssv_creativeid);
            put("ssv_locale", "en_us");
            put("ssv_midas_id", ssv_midas_id);
            put("ssv_random", ssv_random);
            put("ssv_transid", ssv_transid);
            put("ssv_v1st", ssv_v1st);
        }});
        final Response response = midasChaseCom.get(request, "/stream/click");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_34.json")
    public void testGetStreamTag34(final JsonObject json) throws MalformedURLException, IOException
    {
        final String pageID = json.getString("pageID");
        final String ssv_eid = json.getString("ssv_eid");
        final String ssv_tmc = json.getString("ssv_tmc");
        final String ssv_v1st = json.getString("ssv_v1st");

        // GET https://midas.chase.com/stream/tag (endp 34)
        final HttpTarget midasChaseCom = getHttpClient("https://midas.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("pageID", pageID);
            put("ssv_eci", "");
            put("ssv_eid", ssv_eid);
            put("ssv_pfid", "");
            put("ssv_productid", "");
            put("ssv_src", "");
            put("ssv_tmc", ssv_tmc);
            put("ssv_v1st", ssv_v1st);
        }});
        final Response response = midasChaseCom.get(request, "/stream/tag");
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_35.json")
    public void testGetStreamView35(final JsonObject json) throws MalformedURLException, IOException
    {
        final String ssv_creativeid = json.getString("ssv_creativeid");
        final String ssv_locale = json.getString("ssv_locale");
        final String ssv_midas_id = json.getString("ssv_midas_id");
        final String ssv_v1st = json.getString("ssv_v1st");

        // GET https://midas.chase.com/stream/view (endp 35)
        final HttpTarget midasChaseCom = getHttpClient("https://midas.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setQueryString(new Hashtable<String, Object>() {{
            put("ssv_channel", "web");
            put("ssv_creativeid", ssv_creativeid);
            put("ssv_locale", ssv_locale);
            put("ssv_midas_id", ssv_midas_id);
            put("ssv_pageLayout", "prospect_a");
            put("ssv_random", randomInteger(220, 977));
            put("ssv_transid", randomInteger(-8655682384752228023L, 2808894671955032376L));
            put("ssv_v1st", ssv_v1st);
        }});
        final Response response = midasChaseCom.get(request, "/stream/view");
        assertStatusCode(response.code(), 200);
    }
}

