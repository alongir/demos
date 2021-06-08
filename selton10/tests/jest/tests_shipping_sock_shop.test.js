const authenticate = require("./authentication");
const {JSONBuild, clearSession, dataset, getHttpClient, uuidv4} = require("./up9lib");

describe.each(dataset("data/dataset_21.json"))("test_021_post_shipping", (name) => {
    it("test_021_post_shipping", () => {
        clearSession();

        // POST http://shipping.sock-shop/shipping (endp 21)
        const shipping_sock_shop = getHttpClient("http://shipping.sock-shop", authenticate);
        return shipping_sock_shop.fetch("/shipping", {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_21.json", {
                "$.id": String(uuidv4()),
                "$.name": name
            })
        })
        .then((response) => {
            expect(response.status).toEqual(201);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});
