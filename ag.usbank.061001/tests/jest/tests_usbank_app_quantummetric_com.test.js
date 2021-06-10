const authenticate = require("./authentication");
const {CSSselect, JSONPath, clearSession, dataset, getHttpClient, randomInteger, readFileSync, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_23.json"))("test_23_post_", (H, U, s) => {
    it("test_23_post_", () => {
        clearSession();

        // GET https://www.usbank.com/index.html (endp 4)
        const www_usbank_com = getHttpClient("https://www.usbank.com", authenticate);
        return www_usbank_com.fetch("/index.html")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            expect(CSSselect("html head title", text)).toContain("Consumer banking | Personal banking | U.S. Bank");
            const u = CSSselect("html head link[href] @href", response).text().trim();
        })
        .then((data) => {
            // POST https://usbank-app.quantummetric.com/ (endp 23)
            const usbank_app_quantummetric_com = getHttpClient("https://usbank-app.quantummetric.com", authenticate);
            return usbank_app_quantummetric_com.fetch("/" + urlencode([["H", H], ["N", String(randomInteger(0, 1434))], ["P", String(randomInteger(0, 15))], ["Q", "2"], ["S", String(randomInteger(0, 102475))], ["T", "B"], ["U", U], ["s", s], ["t", String(Date.now())], ["u", u], ["v", String(Date.now())], ["z", "1"]]), {
                method: "POST",
                headers: {
                    "content-type": "text/plain"
                },
                body: readFileSync("data/payload_for_endp_23.txt", "r")
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

describe.each(dataset("data/dataset_24.json"))("test_24_get_", (H, Module, Q, Q_CLIENTVERSION, Q_CLIENTVERSION1, Q_InterceptID, Q_ORIGIN, s) => {
    it("test_24_get_", () => {
        clearSession();

        // GET https://www.usbank.com/index.html (endp 4)
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
            // GET https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Asset.php (endp 21)
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

                // POST https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Targeting.php (endp 22)
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

                    // GET https://usbank-app.quantummetric.com/ (endp 24)
                    const usbank_app_quantummetric_com = getHttpClient("https://usbank-app.quantummetric.com", authenticate);
                    return usbank_app_quantummetric_com.fetch("/" + urlencode([["H", H], ["Q", Q], ["s", s]]))
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
