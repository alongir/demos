package com.up9.generated;

import com.up9.generated.Authentication;
import com.up9.up9lib.HttpRequest;
import com.up9.up9lib.HttpTarget;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.json.JsonObject;
import net.joshka.junit.json.params.JsonFileSource;
import okhttp3.Response;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import static com.up9.up9lib.Common.*;

@TestMethodOrder(Alphanumeric.class)
public class TestsSitesChaseComTest
{
    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_36.json")
    public void testGetContentCreativesPublicHeroesHeroeidMarchCid36(final JsonObject json) throws MalformedURLException, IOException
    {
        final String cid = json.getString("cid");
        final String heroeId = json.getString("heroeId");

        // GET https://sites.chase.com/content/Creatives/Public/Heroes/{heroeId}/March/{cid} (endp 36)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = sitesChaseCom.get(request, "/content/Creatives/Public/Heroes/" + heroeId + "/March/" + cid);
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_37.json")
    public void testGetContentCreativesPublicTilesTileidMarchCid37(final JsonObject json) throws MalformedURLException, IOException
    {
        final String cid = json.getString("cid");
        final String tileId = json.getString("tileId");

        // GET https://sites.chase.com/content/Creatives/Public/Tiles/{tileId}/March/{cid} (endp 37)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = sitesChaseCom.get(request, "/content/Creatives/Public/Tiles/" + tileId + "/March/" + cid);
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_38.json")
    public void testGetContentCreativesPublicTripletsTripletidJulyCid38(final JsonObject json) throws MalformedURLException, IOException
    {
        final String cid = json.getString("cid");
        final String tripletId = json.getString("tripletId");

        // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/July/{cid} (endp 38)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = sitesChaseCom.get(request, "/content/Creatives/Public/Triplets/" + tripletId + "/July/" + cid);
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_40.json")
    public void testGetContentCreativesPublicTripletsTripletidMayCid40(final JsonObject json) throws MalformedURLException, IOException
    {
        final String cid = json.getString("cid");
        final String tripletId = json.getString("tripletId");

        // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/May/{cid} (endp 40)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = sitesChaseCom.get(request, "/content/Creatives/Public/Triplets/" + tripletId + "/May/" + cid);
        assertStatusCode(response.code(), 200);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset_39.json")
    public void testGetContentCreativesPublicTripletsTripletidNovemberCid39(final JsonObject json) throws MalformedURLException, IOException
    {
        final String cid = json.getString("cid");
        final String tripletId = json.getString("tripletId");

        // GET https://sites.chase.com/content/Creatives/Public/Triplets/{tripletId}/November/{cid} (endp 39)
        final HttpTarget sitesChaseCom = getHttpClient("https://sites.chase.com", new Authentication());
        final HttpRequest request = new HttpRequest();
        final Response response = sitesChaseCom.get(request, "/content/Creatives/Public/Triplets/" + tripletId + "/November/" + cid);
        assertStatusCode(response.code(), 200);
    }
}

