const authenticate = require("./authentication");
const {JSONBuild, clearSession, getHttpTarget, randomFloat} = require("./up9lib");

it("test_67_post_paymentAuth", () => {
    clearSession();

    // POST http://payment/paymentAuth (endp 67)
    const ccv = "958";
    const country = "United Kingdom";
    const expires = "08/19";
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
        body: JSONBuild("data/payload_for_endp_67.json", {
            "$.address.country": country,
            "$.address.number": number,
            "$.address.postcode": postcode,
            "$.address.street": street,
            "$.amount": parseFloat(randomFloat(4.99, 104.979996)),
            "$.card.ccv": ccv,
            "$.card.expires": expires,
            "$.card.longNum": longNum
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
