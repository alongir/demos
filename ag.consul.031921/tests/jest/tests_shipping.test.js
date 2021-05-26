const authenticate = require("./authentication");
const {JSONBuild, clearSession, dataset, getHttpClient, uuidv4} = require("./up9lib");

describe.each(dataset("data/dataset_39.json"))("test_39_post_shipping", (name) => {
    it("test_39_post_shipping", () => {
        clearSession();

        // POST http://shipping/shipping (endp 39)
        const shipping = getHttpClient("http://shipping", authenticate);
        return shipping.fetch("/shipping", {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_39.json", {
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
