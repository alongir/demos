const authenticate = require("./authentication");
const {JSONPath, clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_41.json"))("test_41_get_product_configuration_v1_products_usbank_param_PI", (param) => {
    it("test_41_get_product_configuration_v1_products_usbank_param_PI", () => {
        clearSession();

        // GET https://sales.usbank.com/product-configuration/v1/products/usbank/{param}/PI (endp 41)
        const sales_usbank_com = getHttpClient("https://sales.usbank.com", authenticate);
        return sales_usbank_com.fetch("/product-configuration/v1/products/usbank/" + param + "/PI", {
            headers: {
                "content-type": "application/json",
                "x-requested-with": "X-Requested-With"
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
                path: "$.tenant",
                json: data
            })).toContain("usbank");
        });
    });
});
