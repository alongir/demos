const authenticate = require("./authentication");
const {JSONBuild, clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_104.json"))("test_104_put_shipping_task", (id, name) => {
    it("test_104_put_shipping_task", () => {
        clearSession();

        // PUT kafka://kafka/shipping-task (endp 104)
        const kafka = getHttpClient("kafka://kafka", authenticate);
        return kafka.fetch("/shipping-task", {
            method: "PUT",
            headers: {
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_104.json", {
                "$.id": id,
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
