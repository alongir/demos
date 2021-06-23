const authenticate = require("./authentication");
const {CSSselect, JSONBuild, JSONPath, clearSession, dataset, getHttpClient, readFileSync, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_80.json"))("test_80_post_events_eventId", (app, eventId, modifiedSince, referer, session, svrid, visitID) => {
    it("test_80_post_events_eventId", () => {
        clearSession();

        // POST https://secure07a.chase.com/events/{eventId} (endp 80)
        const secure07a_chase_com = getHttpClient("https://secure07a.chase.com", authenticate);
        return secure07a_chase_com.fetch("/events/" + eventId + urlencode([["app", app], ["flavor", "post"], ["modifiedSince", modifiedSince], ["referer", referer], ["session", session], ["svrid", svrid], ["type", "js"], ["visitID", visitID]]), {
            method: "POST",
            headers: {
                "content-type": "text/plain"
            },
            body: readFileSync("data/payload_for_endp_80.txt", "r")
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

describe.each(dataset("data/dataset_79.json"))("test_79_post_events_analytics_public_v1_events_raw_", (adobeData, browserRes, colorDepth, currentURL, javaScriptVer, q, referrerURL, screenRes, server_offset, site, tz_offset, version, visitor, visitorId) => {
    it("test_79_post_events_analytics_public_v1_events_raw_", () => {
        clearSession();

        // GET https://locator.chase.com/search (endp 77)
        const locator_chase_com = getHttpClient("https://locator.chase.com", authenticate);
        return locator_chase_com.fetch("/search" + urlencode([["l", "en"], ["q", q]]), {
            headers: {
                "accept": "application/json"
            }
        })
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            expect(JSONPath({
                path: "$.response.entities[*].profile.address.region",
                json: data
            })).toContain("CA");
            const metaId = JSONPath({
                path: "$.response.entities[*].profile.*[*]",
                json: data
            })[0];

            // GET https://locator.chase.com/atmsearch (endp 75)
            return locator_chase_com.fetch("/atmsearch" + urlencode([["metaId", metaId]]), {
                headers: {
                    "accept": "application/json"
                }
            })
            .then((response) => {
                expect(response.status).toEqual(200);
                return response.text();
            })
            .then((text) => {
                return JSON.parse(text);
            })
            .then((data) => {
                const redirectScreen = JSONPath({
                    path: "$.schema.alternateWebsites.archived",
                    json: data
                })[0];

                // POST https://secure07a.chase.com/events/analytics/public/v1/events/raw/ (endp 79)
                const secure07a_chase_com = getHttpClient("https://secure07a.chase.com", authenticate);
                return secure07a_chase_com.fetch("/events/analytics/public/v1/events/raw/", {
                    method: "POST",
                    headers: {
                        "content-type": "application/json"
                    },
                    body: JSONBuild("data/payload_for_endp_79.json", {
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
});

// authentication-related test
describe.each(dataset("data/dataset_81.json"))("test_81_get_web_auth_logonbox", (q) => {
    it("test_81_get_web_auth_logonbox", () => {
        clearSession();

        // GET https://locator.chase.com/search (endp 76)
        const locator_chase_com = getHttpClient("https://locator.chase.com");
        return locator_chase_com.fetch("/search" + urlencode([["q", q]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("div#js-locator div.Locator-content h1.Locator-title.Heading--locator.js-Locator-title", text)).toContain("Find a Chase ATM or branch near you");
            expect(CSSselect("html head title", text)).toContain("Branches and ATMs | Chase Bank");
            const fromOrigin = CSSselect("a#brand-logo[href] @href", response).text().trim();
        })
        .then((data) => {
            // GET https://secure07a.chase.com/web/auth/logonbox (endp 81)
            const secure07a_chase_com = getHttpClient("https://secure07a.chase.com");
            return secure07a_chase_com.fetch("/web/auth/logonbox" + urlencode([["fromOrigin", fromOrigin], ["lang", "en"]]))
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
});
