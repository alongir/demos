const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, randomFloat} = require("./up9lib");

describe.each(dataset("data/dataset_22.json"))("test_22_post_paymentAuth", (addresseId, cardId, country, longNum, number, postcode) => {
    it("test_22_post_paymentAuth", () => {
        clearSession();

        // GET http://user.sock-shop/login (endp 31)
        const user_sock_shop = getHttpClient("http://user.sock-shop", authenticate);
        return user_sock_shop.fetch("/login")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            expect(JSONPath({
                path: "$.user.lastName",
                json: data
            })).toContain("Name");
            const customerId = JSONPath({
                path: "$.user.id",
                json: data
            })[0];

            // GET http://carts.sock-shop/carts/{customerId}/items (endp 18)
            const carts_sock_shop = getHttpClient("http://carts.sock-shop", authenticate);
            return carts_sock_shop.fetch("/carts/" + customerId + "/items", {
                headers: {
                    "accept": "application/json"
                }
            })
            .then((response) => {
                expect(response.status).toEqual(200);
                return response.text();
            })
            .then((text) => {
            })
            .then((data) => {
                // GET http://user.sock-shop/customers/{customerId} (endp 21)
                return user_sock_shop.fetch("/customers/" + customerId, {
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
                        path: "$.lastName",
                        json: data
                    })).toContain("Name");
                    const firstName = JSONPath({
                        path: "$.firstName",
                        json: data
                    })[0];

                    // GET http://user.sock-shop/cards/{cardId} (endp 20)
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

                        // GET http://user.sock-shop/addresses/{addresseId} (endp 19)
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
                            const street = JSONPath({
                                path: "$.street",
                                json: data
                            })[0];

                            // POST http://payment.sock-shop/paymentAuth (endp 22)
                            const payment_sock_shop = getHttpClient("http://payment.sock-shop", authenticate);
                            return payment_sock_shop.fetch("/paymentAuth", {
                                method: "POST",
                                headers: {
                                    "accept": "application/json",
                                    "content-type": "application/json"
                                },
                                body: JSONBuild("data/payload_for_endp_22.json", {
                                    "$.address.country": country,
                                    "$.address.number": number,
                                    "$.address.postcode": postcode,
                                    "$.address.street": street,
                                    "$.amount": parseFloat(randomFloat(4.99, 270.96997)),
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
        });
    });
});
