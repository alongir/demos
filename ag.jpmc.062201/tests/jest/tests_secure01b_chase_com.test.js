const authenticate = require("./authentication");
const {CSSselect, JSONBuild, clearSession, dataset, getHttpClient, readFileSync, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_42.json"))("test_42_post_events_eventId", (app, eventId, modifiedSince, referer, svrid, visitID) => {
    it("test_42_post_events_eventId", () => {
        clearSession();

        // POST https://secure01b.chase.com/events/{eventId} (endp 42)
        const secure01b_chase_com = getHttpClient("https://secure01b.chase.com", authenticate);
        return secure01b_chase_com.fetch("/events/" + eventId + urlencode([["app", app], ["flavor", "post"], ["modifiedSince", modifiedSince], ["referer", referer], ["session", String(response.headers.raw()["set-cookie"]["dtCookie"])], ["svrid", svrid], ["type", "js"], ["visitID", visitID]]), {
            method: "POST",
            headers: {
                "content-type": "text/plain"
            },
            body: readFileSync("data/payload_for_endp_42.txt", "r")
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});

describe.each(dataset("data/dataset_41.json"))("test_41_post_events_analytics_public_v1_events_raw_", (adobeData, browserRes, colorDepth, currentURL, javaScriptVer, referrerURL, screenRes, server_offset, site, tz_offset, version, visitor, visitorId) => {
    it("test_41_post_events_analytics_public_v1_events_raw_", () => {
        clearSession();

        // POST https://secure01b.chase.com/events/analytics/public/v1/events/raw/ (endp 41)
        const secure01b_chase_com = getHttpClient("https://secure01b.chase.com", authenticate);
        return secure01b_chase_com.fetch("/events/analytics/public/v1/events/raw/", {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_41.json", {
                "$.events[*].app.version": version,
                "$.events[*].device.browserRes": browserRes,
                "$.events[*].device.colorDepth": colorDepth,
                "$.events[*].device.javaScriptVer": javaScriptVer,
                "$.events[*].device.screenRes": screenRes,
                "$.events[*].location.server_offset": server_offset,
                "$.events[*].location.tz_offset": tz_offset,
                "$.events[*].payload.data.referrerURL": referrerURL,
                "$.events[*].payload.timestamp": parseInt(Date.now()),
                "$.events[*].screen.currentURL": currentURL,
                "$.events[*].site": site,
                "$.events[*].visitor.*": visitor,
                "$.events[*].visitor.adobeData": adobeData,
                "$.events[*].visitor.visitorId": visitorId
            })
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});

// authentication-related test
describe.each(dataset("data/dataset_43.json"))("test_43_get_web_auth_logonbox", (fromOrigin) => {
    it("test_43_get_web_auth_logonbox", () => {
        clearSession();

        // GET https://secure01b.chase.com/web/auth/logonbox (endp 43)
        const secure01b_chase_com = getHttpClient("https://secure01b.chase.com");
        return secure01b_chase_com.fetch("/web/auth/logonbox" + urlencode([["fromOrigin", fromOrigin], ["lang", "en"]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("html head title", text)).toContain("Chase Account login");
        })
        .then((data) => {
        });
    });
});
