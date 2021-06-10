const authenticate = require("./authentication");
const {clearSession, dataset, getHttpClient, randomInteger, readFileSync, urlencode} = require("./up9lib");

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

describe.each(dataset("data/dataset_22.json"))("test_22_get_", (H, Q, s) => {
    it("test_22_get_", () => {
        clearSession();

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
