const authenticate = require("./authentication");
const {CSSselect, JSONPath, clearSession, dataset, getHttpClient, randomInteger, readFileSync, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_21.json"))("test_21_post_", (H, PRODUCT_CODE, U, s) => {
    it("test_21_post_", () => {
        clearSession();

        // GET https://apply.usbank.com/apply/apply.html (endp 33)
        const apply_usbank_com = getHttpClient("https://apply.usbank.com", authenticate);
        return apply_usbank_com.fetch("/apply/apply.html" + urlencode([["PRODUCT_CODE", PRODUCT_CODE], ["SUB_PRODUCT_CODE", "PI"]]))
        .then((response) => {
            expect(response.status).toEqual(302);
            const u = response.headers.get("location");
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
            // POST https://usbank-app.quantummetric.com/ (endp 21)
            const usbank_app_quantummetric_com = getHttpClient("https://usbank-app.quantummetric.com", authenticate);
            return usbank_app_quantummetric_com.fetch("/" + urlencode([["H", H], ["N", String(randomInteger(0, 1434))], ["P", String(randomInteger(0, 15))], ["Q", "2"], ["S", String(randomInteger(0, 102475))], ["T", "B"], ["U", U], ["s", s], ["t", String(Date.now())], ["u", u], ["v", String(Date.now())], ["z", "1"]]), {
                method: "POST",
                headers: {
                    "content-type": "text/plain"
                },
                body: readFileSync("data/payload_for_endp_21.txt", "r")
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

describe.each(dataset("data/dataset_22.json"))("test_22_get_", (H, Module, Q, Q_CLIENTVERSION, Q_CLIENTVERSION1, Q_InterceptID, Q_ORIGIN, s) => {
    it("test_22_get_", () => {
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

                    // GET https://usbank-app.quantummetric.com/ (endp 22)
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
