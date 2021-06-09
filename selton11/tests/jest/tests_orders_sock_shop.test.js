const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_37.json"))("test_37_post_orders", (items) => {
    it("test_37_post_orders", () => {
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
                // GET http://user.sock-shop/customers/{customerId}/cards (endp 30)
                return user_sock_shop.fetch("/customers/" + customerId + "/cards")
                .then((response) => {
                    expect(response.status).toEqual(200);
                    return response.text();
                })
                .then((text) => {
                    return JSON.parse(text);
                })
                .then((data) => {
                    const card = JSONPath({
                        path: "$._embedded.card[*]._links.card.href",
                        json: data
                    })[0];

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
                        const customer = JSONPath({
                            path: "$._links.self.href",
                            json: data
                        })[0];

                        // GET http://user.sock-shop/customers/{customerId}/addresses (endp 29)
                        return user_sock_shop.fetch("/customers/" + customerId + "/addresses")
                        .then((response) => {
                            expect(response.status).toEqual(200);
                            return response.text();
                        })
                        .then((text) => {
                            return JSON.parse(text);
                        })
                        .then((data) => {
                            expect(JSONPath({
                                path: "$._embedded.address[*].city",
                                json: data
                            })).toContain("Glasgow");
                            const address = JSONPath({
                                path: "$._embedded.address[*]._links.address.href",
                                json: data
                            })[0];

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
                            });
                        });
                    });
                });
            });
        });
    });
});

it("test_38_get_orders_search_customerId", () => {
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
        const custId = JSONPath({
            path: "$.user.id",
            json: data
        })[0];

        // GET http://orders.sock-shop/orders/search/customerId (endp 38)
        const orders_sock_shop = getHttpClient("http://orders.sock-shop", authenticate);
        return orders_sock_shop.fetch("/orders/search/customerId" + urlencode([["custId", custId], ["sort", "date"]]))
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
