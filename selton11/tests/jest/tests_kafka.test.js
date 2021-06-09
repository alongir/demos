const authenticate = require("./authentication");
const {JSONBuild, clearSession, dataset, getHttpClient, uuidv4} = require("./up9lib");

describe.each(dataset("data/dataset_39.json"))("test_39_put_shipping_task", (name) => {
    it("test_39_put_shipping_task", () => {
        clearSession();

        // PUT kafka://kafka/shipping-task (endp 39)
        const kafka = getHttpClient("kafka://kafka", authenticate);
        return kafka.fetch("/shipping-task", {
            method: "PUT",
            headers: {
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_39.json", {
                "$.id": String(uuidv4()),
                "$.name": name
            })
        })
        .then((response) => {
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});
