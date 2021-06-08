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
public class TestsKafkaTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_104.json")
    public void testPutShippingTask104(final JsonObject json) throws MalformedURLException, IOException
    {
        final String id = json.getString("id");
        final String name = json.getString("name");

        // PUT kafka://kafka/shipping-task (endp 104)
        final HttpTarget kafka = getHttpClient("kafka://kafka", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_104.json", new Hashtable<String, Object>() {{
            put("$.id", id);
            put("$.name", name);
        }});
        final Response response = kafka.put(request, "/shipping-task");
    }
}

