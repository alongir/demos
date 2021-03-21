const authenticate = require("./authentication");
const {JSONBuild, clearSession, dataset, getHttpTarget, randomFloat} = require("./up9lib");

describe.each(dataset("data/dataset_38.json"))("test_38_post_paymentAuth", (ccv, country, expires, id, id1, id2, longNum, number, postcode, street) => {
    it("test_38_post_paymentAuth", () => {
        clearSession();

        // POST http://payment/paymentAuth (endp 38)
        const payment = getHttpTarget("TARGET_PAYMENT", authenticate);
        return payment.fetch("/paymentAuth", {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_38.json", {
                "$.address.country": country,
                "$.address.id": id,
                "$.address.number": number,
                "$.address.postcode": postcode,
                "$.address.street": street,
                "$.amount": parseFloat(randomFloat(12.98, 122.979996)),
                "$.card.ccv": ccv,
                "$.card.expires": expires,
                "$.card.id": id1,
                "$.card.longNum": longNum,
                "$.customer.id": id2
            })
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
