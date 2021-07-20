const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, uuidv4} = require("./up9lib");

describe.each(dataset("data/dataset_23.json"))("test_23_post_shipping", (name) => {
    it("test_23_post_shipping", () => {
        clearSession();

        // POST http://shipping.sock-shop/shipping (endp 23)
        const shipping_sock_shop = getHttpClient("http://shipping.sock-shop", authenticate);
        return shipping_sock_shop.fetch("/shipping", {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_23.json", {
                "$.id": String(uuidv4()),
                "$.name": name
            })
        })
        .then((response) => {
            expect(response.status).toEqual(201);
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            expect(JSONPath({
                path: "$.id",
                json: data
            })).not.toBeNull();
        });
    });
});
