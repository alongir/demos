const authenticate = require("./authentication");
const {clearSession, dataset, getHttpClient, randomInteger, readFileSync, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_25.json"))("test_25_post_", (Module, PRODUCT_CODE, Q_CLIENTVERSION, Q_InterceptID, Q_ORIGIN, X, s) => {
    it("test_25_post_", () => {
        clearSession();

        // GET https://siteintercept.qualtrics.com/WRSiteInterceptEngine/Asset.php (endp 21)
        const siteintercept_qualtrics_com = getHttpClient("https://siteintercept.qualtrics.com", authenticate);
        return siteintercept_qualtrics_com.fetch("/WRSiteInterceptEngine/Asset.php" + urlencode([["Module", Module], ["Q_CLIENTTYPE", "web"], ["Q_CLIENTVERSION", Q_CLIENTVERSION], ["Q_InterceptID", Q_InterceptID], ["Q_ORIGIN", Q_ORIGIN], ["Version", String(randomInteger(1, 26))]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
            // GET https://apply.usbank.com/apply/apply.html (endp 36)
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
                // POST https://usbank-sync.quantummetric.com/ (endp 25)
                const usbank_sync_quantummetric_com = getHttpClient("https://usbank-sync.quantummetric.com", authenticate);
                return usbank_sync_quantummetric_com.fetch("/" + urlencode([["Q", "1"], ["T", "B"], ["X", X], ["Y", "1"], ["s", s], ["t", String(Date.now())], ["u", u], ["v", String(Date.now())], ["z", "1"]]), {
                    method: "POST",
                    headers: {
                        "content-type": "text/plain"
                    },
                    body: readFileSync("data/payload_for_endp_25.txt", "r")
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
