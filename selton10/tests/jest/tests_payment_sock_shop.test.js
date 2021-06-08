const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, randomFloat} = require("./up9lib");

describe.each(dataset("data/dataset_20.json"))("test_020_post_paymentAuth", (addresseId, cardId, longNum, number) => {
    it("test_020_post_paymentAuth", () => {
        clearSession();

        // GET http://user.sock-shop/addresses/{addresseId} (endp 17)
        const user_sock_shop = getHttpClient("http://user.sock-shop", authenticate);
        return user_sock_shop.fetch("/addresses/" + addresseId, {
            headers: {
                "accept": "application/hal+json"
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
                path: "$.city",
                json: data
            })).toContain("Glasgow");
            const country = JSONPath({
                path: "$.country",
                json: data
            })[0];
            const postcode = JSONPath({
                path: "$.postcode",
                json: data
            })[0];
            const street = JSONPath({
                path: "$.street",
                json: data
            })[0];

            // GET http://user.sock-shop/cards/{cardId} (endp 18)
            return user_sock_shop.fetch("/cards/" + cardId, {
                headers: {
                    "accept": "application/hal+json"
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
                const ccv = JSONPath({
                    path: "$.ccv",
                    json: data
                })[0];
                const expires = JSONPath({
                    path: "$.expires",
                    json: data
                })[0];

                // POST http://payment.sock-shop/paymentAuth (endp 20)
                const payment_sock_shop = getHttpClient("http://payment.sock-shop", authenticate);
                return payment_sock_shop.fetch("/paymentAuth", {
                    method: "POST",
                    headers: {
                        "accept": "application/json",
                        "content-type": "application/json"
                    },
                    body: JSONBuild("data/payload_for_endp_20.json", {
                        "$.address.country": country,
                        "$.address.number": number,
                        "$.address.postcode": postcode,
                        "$.address.street": street,
                        "$.amount": parseFloat(randomFloat(4.99, 350.94)),
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
        });
    });
});
