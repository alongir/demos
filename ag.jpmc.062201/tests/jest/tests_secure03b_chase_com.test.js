const authenticate = require("./authentication");
const {CSSselect, JSONBuild, clearSession, dataset, getHttpClient, readFileSync, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_71.json"))("test_071_post_events_eventId", (app, eventId, modifiedSince, referer, session, svrid, visitID) => {
    it("test_071_post_events_eventId", () => {
        clearSession();

        // POST https://secure03b.chase.com/events/{eventId} (endp 71)
        const secure03b_chase_com = getHttpClient("https://secure03b.chase.com", authenticate);
        return secure03b_chase_com.fetch("/events/" + eventId + urlencode([["app", app], ["flavor", "post"], ["modifiedSince", modifiedSince], ["referer", referer], ["session", session], ["svrid", svrid], ["type", "js"], ["visitID", visitID]]), {
            method: "POST",
            headers: {
                "content-type": "text/plain"
            },
            body: readFileSync("data/payload_for_endp_71.txt", "r")
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

it("test_070_post_events_analytics_public_v1_events_raw_", () => {
    clearSession();

    // POST https://secure03b.chase.com/events/analytics/public/v1/events/raw/ (endp 70)
    const secure03b_chase_com = getHttpClient("https://secure03b.chase.com", authenticate);
    return secure03b_chase_com.fetch("/events/analytics/public/v1/events/raw/", {
        method: "POST",
        headers: {
            "content-type": "application/json"
        },
        body: JSONBuild("data/payload_for_endp_70.json", {})
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

// authentication-related test
it("test_072_get_web_auth_logonbox", () => {
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
        // GET https://secure03b.chase.com/web/auth/logonbox (endp 72)
        const secure03b_chase_com = getHttpClient("https://secure03b.chase.com");
        return secure03b_chase_com.fetch("/web/auth/logonbox" + urlencode([["fromOrigin", fromOrigin], ["lang", "en"], ["navKey", "reviewCreditCardOffers"]]))
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
