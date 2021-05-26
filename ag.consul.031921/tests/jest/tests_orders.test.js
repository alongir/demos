const authenticate = require("./authentication");
const {JSONPath, clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_36.json"))("test_36_get_orders_href", (href) => {
    it("test_36_get_orders_href", () => {
        clearSession();

        // GET http://orders/orders/{href} (endp 36)
        const orders = getHttpClient("http://orders", authenticate);
        return orders.fetch("/orders/" + href)
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            expect(JSONPath({
                path: "$.address.city",
                json: data
            })).toContain("Glasgow");
        });
    });
});
