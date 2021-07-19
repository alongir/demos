package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import java.net.MalformedURLException;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsPersonalChaseComTest
{
    @Test
    public void testGetPersonalChecking056() throws MalformedURLException, IOException
    {
        // GET https://personal.chase.com/personal/checking (endp 56)
        final HttpTarget personalChaseCom = getHttpClient("https://personal.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = personalChaseCom.get(request, "/personal/checking");
        assertStatusCode(response.code(), 200);
        assertCSSselect("div#jsEnabled div.generic-modal.card-modal div.modal-dialog div.modal-content div.row.modal-heading h3 sup.sm-fix", "SM", response.body().string());
        assertCSSselect("html head title", "Chase Checking Accounts: Compare & Apply Today | Chase", response.body().string());
    }
}

