const authenticate = require("./authentication");
const {clearSession, dataset, getHttpClient, readFileSync, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_23.json"))("test_23_post_", (PRODUCT_CODE, X, s) => {
    it("test_23_post_", () => {
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
            // POST https://usbank-sync.quantummetric.com/ (endp 23)
            const usbank_sync_quantummetric_com = getHttpClient("https://usbank-sync.quantummetric.com", authenticate);
            return usbank_sync_quantummetric_com.fetch("/" + urlencode([["Q", "1"], ["T", "B"], ["X", X], ["Y", "1"], ["s", s], ["t", String(Date.now())], ["u", u], ["v", String(Date.now())], ["z", "1"]]), {
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
