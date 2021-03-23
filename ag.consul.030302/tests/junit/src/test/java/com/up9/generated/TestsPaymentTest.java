package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import java.util.Hashtable;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsPaymentTest
{
    @Test
    public void testPostPaymentauth67() throws IOException
    {
        // POST http://payment/paymentAuth (endp 67)
        final String ccv = "958";
        final String country = "United Kingdom";
        final String expires = "08/19";
        final String longNum = "5544154011345918";
        final String number = "246";
        final String postcode = "G67 3DL";
        final String street = "Whitelees Road";
        final HttpTarget payment = getHttpTarget("TARGET_PAYMENT", new Authentication());
        final HttpRequest request = new HttpRequest();
        request.setHeaders(new Hashtable<String, Object>() {{
            put("accept", "application/json");
            put("content-type", "application/json");
        }});
        request.setJsonBody("payload_for_endp_67.json", new Hashtable<String, Object>() {{
            put("$.address.country", country);
            put("$.address.number", number);
            put("$.address.postcode", postcode);
            put("$.address.street", street);
            put("$.amount", randomFloat(4.99f, 104.979996f));
            put("$.card.ccv", ccv);
            put("$.card.expires", expires);
            put("$.card.longNum", longNum);
        }});
        final Response response = payment.post(request, "/paymentAuth");
        assertStatusCode(response.code(), 200);
    }
}

