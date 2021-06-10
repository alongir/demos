const authenticate = require("./authentication");
const {clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_36.json"))("test_36_get_apply_apply_html", (PRODUCT_CODE) => {
    it("test_36_get_apply_apply_html", () => {
        clearSession();

        // GET https://apply.usbank.com/apply/apply.html (endp 36)
        const apply_usbank_com = getHttpClient("https://apply.usbank.com", authenticate);
        return apply_usbank_com.fetch("/apply/apply.html" + urlencode([["PRODUCT_CODE", PRODUCT_CODE], ["SUB_PRODUCT_CODE", "PI"]]))
        .then((response) => {
            expect(response.status).toEqual(302);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});
