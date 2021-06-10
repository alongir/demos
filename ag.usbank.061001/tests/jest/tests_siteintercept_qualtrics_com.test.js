const authenticate = require("./authentication");
const {CSSselect, JSONPath, clearSession, dataset, getHttpClient, randomInteger, urlPart, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_18.json"))("test_18_post_WRSiteInterceptEngine_", (BrandDC, Module, Q_CLIENTVERSION, Q_CLIENTVERSION1, Q_CLIENTVERSION2, Q_InterceptID, Q_ORIGIN) => {
    it("test_18_post_WRSiteInterceptEngine_", () => {
        clearSession();

        // GET https://www.usbank.com/index.html (endp 3)
        const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
        return www_usbank_com.fetch("/index.html")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("html head title", text)).toContain("Consumer banking | Personal banking | U.S. Bank");
            const Q_LOC = CSSselect("html head link[href] @href", response).text().trim();
        })
        .then((data) => {
            // GET https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Asset.php (endp 19)
            const siteintercept_qualtrics_com = getHttpClient("https://siteintercept.qualtrics.com", authenticate);
            return siteintercept_qualtrics_com.fetch("/WRSiteInterceptEngine/Asset.php" + urlencode([["Module", Module], ["Q_CLIENTTYPE", "web"], ["Q_CLIENTVERSION", Q_CLIENTVERSION], ["Q_InterceptID", Q_InterceptID], ["Q_ORIGIN", Q_ORIGIN], ["Version", String(randomInteger(1, 26))]]))
            .then((response) => {
                expect(response.status).toEqual(200);
                return response.text();
            })
            .then((text) => {
                return JSON.parse(text);
            })
            .then((data) => {
                expect(JSONPath({
                    path: "$.CreativeDefinition.Options.Message.Headline.Text",
                    json: data
                })).toContain("Will you take our survey?");
                const Q_ZoneID = JSONPath({
                    path: "$.CreativeDefinition.ZoneID",
                    json: data
                })[0];

                // POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Targeting.php (endp 20)
                return siteintercept_qualtrics_com.fetch("/WRSiteInterceptEngine/Targeting.php" + urlencode([["Q_CLIENTTYPE", "web"], ["Q_CLIENTVERSION", Q_CLIENTVERSION1], ["Q_ZoneID", Q_ZoneID]]), {
                    method: "POST",
                    headers: {
                        "content-type": "application/x-www-form-urlencoded"
                    },
                    body: new URLSearchParams({
                        "Q_LOC": Q_LOC
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
                        path: "$.ClientSideIntercepts[*].LogicTree.Left.Left.Type",
                        json: data
                    })).toContain("LogicNode");
                    const Q_ASID = JSONPath({
                        path: "$.ClientSideIntercepts[*].ActionSets[*].ActionSetID",
                        json: data
                    })[0];
                    const Q_CID = urlPart("?Q_CID", JSONPath({
                        path: "$.ClientSideIntercepts[*].ActionSets[*].Target.*",
                        json: data
                    })[0]);
                    const Q_SIID = urlPart("?Q_SIID", JSONPath({
                        path: "$.ClientSideIntercepts[*].ActionSets[*].Target.*",
                        json: data
                    })[0]);
                    const SurveyID = urlPart("/3", JSONPath({
                        path: "$.ClientSideIntercepts[*].ActionSets[*].Target.OriginalURL",
                        json: data
                    })[0]);

                    // POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/ (endp 18)
                    return siteintercept_qualtrics_com.fetch("/WRSiteInterceptEngine/" + urlencode([["Q_ASID", Q_ASID], ["Q_CID", Q_CID], ["Q_CLIENTTYPE", "web"], ["Q_CLIENTVERSION", Q_CLIENTVERSION2], ["Q_Impress", "1"], ["Q_SIID", Q_SIID], ["r", String(Date.now())]]), {
                        method: "POST",
                        headers: {
                            "content-type": "application/x-www-form-urlencoded"
                        },
                        body: new URLSearchParams({
                            "BrandDC": BrandDC,
                            "BrandID": "usbank",
                            "SurveyID": SurveyID
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
});
