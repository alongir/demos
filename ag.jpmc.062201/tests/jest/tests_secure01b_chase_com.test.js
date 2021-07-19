const authenticate = require("./authentication");
const {CSSselect, JSONBuild, clearSession, dataset, getHttpClient, readFileSync, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_42.json"))("test_042_post_events_eventId", (app, eventId, modifiedSince, referer, svrid, visitID) => {
    it("test_042_post_events_eventId", () => {
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

describe.each(dataset("data/dataset_41.json"))("test_041_post_events_analytics_public_v1_events_raw_", (adobeData, browserRes, colorDepth, currentURL, javaScriptVer, screenRes, server_offset, site, tz_offset, version, visitor, visitorId) => {
    it("test_041_post_events_analytics_public_v1_events_raw_", () => {
        clearSession();

        // GET https://www.chase.com/ (endp 1)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("main#main h1.accessible-text", text)).toContain("Chase.com home");
            expect(CSSselect("html head title", text)).toContain("Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com");
            const referrerURL = CSSselect("div div header.header-navigation section.mobile-header div.row section a.chaseanalytics-track-link[href] @href", response).text().trim();
        })
        .then((data) => {
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
});

// authentication-related test
it("test_043_get_web_auth_logonbox", () => {
    clearSession();

    // GET https://www.chase.com/ (endp 1)
    const www_chase_com = getHttpClient("https://www.chase.com");
    return www_chase_com.fetch("/")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("main#main h1.accessible-text", text)).toContain("Chase.com home");
        expect(CSSselect("html head title", text)).toContain("Credit Card, Mortgage, Banking, Auto | Chase Online | Chase.com");
        const fromOrigin = CSSselect("html head link[href] @href", response).text().trim();
    })
    .then((data) => {
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
