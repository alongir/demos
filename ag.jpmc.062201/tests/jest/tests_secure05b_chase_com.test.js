const authenticate = require("./authentication");
const {CSSselect, JSONBuild, clearSession, dataset, getHttpClient, readFileSync, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_63.json"))("test_63_post_events_eventId", (app, eventId, modifiedSince, referer, session, svrid, visitID) => {
    it("test_63_post_events_eventId", () => {
        clearSession();

        // POST https://secure05b.chase.com/events/{eventId} (endp 63)
        const secure05b_chase_com = getHttpClient("https://secure05b.chase.com", authenticate);
        return secure05b_chase_com.fetch("/events/" + eventId + urlencode([["app", app], ["flavor", "post"], ["modifiedSince", modifiedSince], ["referer", referer], ["session", session], ["svrid", svrid], ["type", "js"], ["visitID", visitID]]), {
            method: "POST",
            headers: {
                "content-type": "text/plain"
            },
            body: readFileSync("data/payload_for_endp_63.txt", "r")
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

describe.each(dataset("data/dataset_62.json"))("test_62_post_events_analytics_public_v1_events_raw_", (adobeData, browserRes, colorDepth, currentURL, javaScriptVer, screenRes, server_offset, site, tz_offset, version, visitor, visitorId) => {
    it("test_62_post_events_analytics_public_v1_events_raw_", () => {
        clearSession();

        // GET https://personal.chase.com/personal/checking (endp 56)
        const personal_chase_com = getHttpClient("https://personal.chase.com", authenticate);
        return personal_chase_com.fetch("/personal/checking")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div#jsEnabled div.generic-modal.card-modal div.modal-dialog div.modal-content div.row.modal-heading h3 sup.sm-fix", text)).toContain("SM");
            expect(CSSselect("html head title", text)).toContain("Chase Checking Accounts: Compare & Apply Today | Chase");
            const referrerURL = CSSselect("header#header div.bg-gradient div.bg-solid.bluebg div.inner div.container div.row div.header-flex.header-center.no-gutters.text-center.text-nowrap a.chaseanalytics-track-link[href] @href", response).text().trim();
        })
        .then((data) => {
            // POST https://secure05b.chase.com/events/analytics/public/v1/events/raw/ (endp 62)
            const secure05b_chase_com = getHttpClient("https://secure05b.chase.com", authenticate);
            return secure05b_chase_com.fetch("/events/analytics/public/v1/events/raw/", {
                method: "POST",
                headers: {
                    "content-type": "application/json"
                },
                body: JSONBuild("data/payload_for_endp_62.json", {
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
describe.each(dataset("data/dataset_64.json"))("test_64_get_web_auth_logonbox", (fromOrigin) => {
    it("test_64_get_web_auth_logonbox", () => {
        clearSession();

        // GET https://secure05b.chase.com/web/auth/logonbox (endp 64)
        const secure05b_chase_com = getHttpClient("https://secure05b.chase.com");
        return secure05b_chase_com.fetch("/web/auth/logonbox" + urlencode([["fromOrigin", fromOrigin], ["lang", "en"]]))
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
