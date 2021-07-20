const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, randomFloat, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_22.json"))("test_22_post_paymentAuth", (address, addresseId, card, cardId, items, longNum, number, size, username) => {
    it("test_22_post_paymentAuth", () => {
        clearSession();

        // GET http://catalogue.sock-shop/tags (endp 27)
        const catalogue_sock_shop = getHttpClient("http://catalogue.sock-shop", authenticate);
        return catalogue_sock_shop.fetch("/tags")
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            const tags = JSONPath({
                path: "$.tags[*]",
                json: data
            })[0];

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
                const customer = JSONPath({
                    path: "$.user._links.customer.href",
                    json: data
                })[0];

                // GET http://catalogue.sock-shop/catalogue (endp 24)
                return catalogue_sock_shop.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["sort", "id"], ["tags", tags]]))
                .then((response) => {
                    expect(response.status).toEqual(200);
                    return response.text();
                })
                .then((text) => {
                })
                .then((data) => {
                    // POST http://orders.sock-shop/orders (endp 37)
                    const orders_sock_shop = getHttpClient("http://orders.sock-shop", authenticate);
                    return orders_sock_shop.fetch("/orders", {
                        method: "POST",
                        headers: {
                            "accept": "application/json",
                            "content-type": "application/json"
                        },
                        body: JSONBuild("data/payload_for_endp_37.json", {
                            "$.address": address,
                            "$.card": card,
                            "$.customer": customer,
                            "$.items": items
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
                            path: "$.address.city",
                            json: data
                        })).toContain("Glasgow");
                        const customerId = JSONPath({
                            path: "$.customerId",
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
                            return JSON.parse(text);
                        })
                        .then((data) => {
                            expect(JSONPath({
                                path: "$[*].id",
                                json: data
                            })).not.toBeNull();

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
                                        expect(JSONPath({
                                            path: "$._links.card.href",
                                            json: data
                                        })).not.toBeNull();
                                        const ccv = JSONPath({
                                            path: "$.ccv",
                                            json: data
                                        })[0];
                                        const expires = JSONPath({
                                            path: "$.expires",
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
                                                "$.card.longNum": longNum,
                                                "$.customer.username": username
                                            })
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
                                                path: "$.message",
                                                json: data
                                            })).not.toBeNull();
                                        });
                                    });
                                });
                            });
                        });
                    });
                });
            });
        });
    });
});
