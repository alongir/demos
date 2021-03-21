const authenticate = require("./authentication");
const {JSONBuild, clearSession, getHttpTarget, randomFloat} = require("./up9lib");

it("test_38_post_paymentAuth", () => {
    clearSession();

    // POST http://payment/paymentAuth (endp 38)
    const ccv = "958";
    const country = "United Kingdom";
    const expires = "08/19";
    const id = null;
    const id1 = null;
    const id2 = null;
    const longNum = "5544154011345918";
    const number = "246";
    const postcode = "G67 3DL";
    const street = "Whitelees Road";
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
