const authenticate = require("./authentication");
const {CSSselect, JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_31.json"))("test_31_post_rest_v1_delivery", (client, colorDepth, height, height1, locationHint, logging, marketingCloudVisitorId, mboxName, name, pageTitle, profileParameters, q, requestId, sessionId, timeOffsetInMinutes, tntId, token_, userAgent, version, webGLRenderer, width, width1) => {
    it("test_31_post_rest_v1_delivery", () => {
        clearSession();

        // GET https://www.chase.com/personal/investments/advisor (endp 28)
        const www_chase_com = getHttpClient("https://www.chase.com", authenticate);
        return www_chase_com.fetch("/personal/investments/advisor")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("main#main h1.accessible-text", text)).toContain("J.P. Morgan Financial Advisors");
            expect(CSSselect("html head title", text)).toContain("Connect with a J.P. Morgan Financial Advisor | Chase.com");
        })
        .then((data) => {
            // GET https://locator.chase.com/search (endp 76)
            const locator_chase_com = getHttpClient("https://locator.chase.com", authenticate);
            return locator_chase_com.fetch("/search" + urlencode([["q", q]]))
            .then((response) => {
                expect(response.status).toEqual(200);
                return response.text();
            })
            .then((text) => {
                expect(CSSselect("div#js-locator div.Locator-content h1.Locator-title.Heading--locator.js-Locator-title", text)).toContain("Find a Chase ATM or branch near you");
                expect(CSSselect("html head title", text)).toContain("Branches and ATMs | Chase Bank");
                const referringUrl = CSSselect("header#Header div.Header-wrapper.l-container div.Header-mainRow div.Header-left div div.js-focustrap div div a.Header-expandedLink--atm.Text--expanded[href] @href", response).text().trim();
                const url = CSSselect("header#Header div.Header-wrapper.l-container div.Header-mainRow div.Header-left div div.js-focustrap div a.Text--expanded[href] @href", response).text().trim();
            })
            .then((data) => {
                // POST https://target.chase.com/rest/v1/delivery (endp 31)
                const target_chase_com = getHttpClient("https://target.chase.com", authenticate);
                return target_chase_com.fetch("/rest/v1/delivery" + urlencode([["client", client], ["sessionId", sessionId], ["version", version]]), {
                    method: "POST",
                    headers: {
                        "content-type": "application/json"
                    },
                    body: JSONBuild("data/payload_for_endp_31.json", {
                        "$.context.address.referringUrl": referringUrl,
                        "$.context.address.url": url,
                        "$.context.browser.webGLRenderer": webGLRenderer,
                        "$.context.screen.colorDepth": colorDepth,
                        "$.context.screen.height": height,
                        "$.context.screen.width": width,
                        "$.context.timeOffsetInMinutes": timeOffsetInMinutes,
                        "$.context.userAgent": userAgent,
                        "$.context.window.height": height1,
                        "$.context.window.width": width1,
                        "$.execute.mboxes[*].name": name,
                        "$.execute.mboxes[*].parameters.mboxName": mboxName,
                        "$.execute.mboxes[*].parameters.pageTitle": pageTitle,
                        "$.execute.mboxes[*].profileParameters.*": profileParameters,
                        "$.experienceCloud.analytics.logging": logging,
                        "$.experienceCloud.audienceManager.locationHint": locationHint,
                        "$.id.marketingCloudVisitorId": marketingCloudVisitorId,
                        "$.id.tntId": tntId,
                        "$.property.token": token_,
                        "$.requestId": requestId
                    })
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
                        path: "$.execute.mboxes[*].analytics.payload.pe",
                        json: data
                    })).toContain("tnt");
                });
            });
        });
    });
});
