const authenticate = require("./authentication");
const {CSSselect, clearSession, dataset, getHttpClient, readFileSync, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_105.json"))("test_105_post_events_eventId", (app, eventId, modifiedSince, referer, visitID) => {
    it("test_105_post_events_eventId", () => {
        clearSession();

        // POST https://secure01a.chase.com/events/{eventId} (endp 105)
        const secure01a_chase_com = getHttpClient("https://secure01a.chase.com", authenticate);
        return secure01a_chase_com.fetch("/events/" + eventId + urlencode([["app", app], ["flavor", "post"], ["modifiedSince", modifiedSince], ["referer", referer], ["session", String(response.headers.raw()["set-cookie"]["dtCookie"])], ["svrid", "1"], ["type", "js"], ["visitID", visitID]]), {
            method: "POST",
            headers: {
                "content-type": "text/plain"
            },
            body: readFileSync("data/payload_for_endp_105.txt", "r")
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
it("test_106_get_web_auth_logonbox", () => {
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
        // GET https://secure01a.chase.com/web/auth/logonbox (endp 106)
        const secure01a_chase_com = getHttpClient("https://secure01a.chase.com");
        return secure01a_chase_com.fetch("/web/auth/logonbox" + urlencode([["fromOrigin", fromOrigin], ["lang", "en"]]))
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
