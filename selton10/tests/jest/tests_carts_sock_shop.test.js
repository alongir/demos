const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_6.json"))("test_006_delete_carts_customerId", (address, card, customer, items) => {
    it("test_006_delete_carts_customerId", () => {
        clearSession();

        // POST http://orders.sock-shop/orders (endp 15)
        const orders_sock_shop = getHttpClient("http://orders.sock-shop", authenticate);
        return orders_sock_shop.fetch("/orders", {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_15.json", {
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

            // DELETE http://carts.sock-shop/carts/{customerId} (endp 6)
            const carts_sock_shop = getHttpClient("http://carts.sock-shop", authenticate);
            return carts_sock_shop.fetch("/carts/" + customerId, {
                method: "DELETE"
            })
            .then((response) => {
                expect(response.status).toEqual(202);
                return response.text();
            })
            .then((text) => {
            })
            .then((data) => {
            });
        });
    });
});

describe.each(dataset("data/dataset_7.json"))("test_007_post_carts_customerId_items", (address, card, customer, items) => {
    it("test_007_post_carts_customerId_items", () => {
        clearSession();

        // POST http://orders.sock-shop/orders (endp 15)
        const orders_sock_shop = getHttpClient("http://orders.sock-shop", authenticate);
        return orders_sock_shop.fetch("/orders", {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_15.json", {
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

            // GET http://carts.sock-shop/carts/{customerId}/items (endp 9)
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
                const itemId = JSONPath({
                    path: "$[*].itemId",
                    json: data
                })[0];
                const unitPrice = JSONPath({
                    path: "$[*].unitPrice",
                    json: data
                })[0];

                // POST http://carts.sock-shop/carts/{customerId}/items (endp 7)
                return carts_sock_shop.fetch("/carts/" + customerId + "/items", {
                    method: "POST",
                    headers: {
                        "accept": "application/json",
                        "content-type": "application/json"
                    },
                    body: JSONBuild("data/payload_for_endp_7.json", {
                        "$.itemId": itemId,
                        "$.unitPrice": unitPrice
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
    });
});

describe.each(dataset("data/dataset_44.json"))("test_044_delete_carts_customerId_items_itemId", (address, card, customer, items) => {
    it("test_044_delete_carts_customerId_items_itemId", () => {
        clearSession();

        // POST http://orders.sock-shop/orders (endp 15)
        const orders_sock_shop = getHttpClient("http://orders.sock-shop", authenticate);
        return orders_sock_shop.fetch("/orders", {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_15.json", {
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

            // GET http://carts.sock-shop/carts/{customerId}/items (endp 9)
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
                const itemId = JSONPath({
                    path: "$[*].itemId",
                    json: data
                })[0];

                // DELETE http://carts.sock-shop/carts/{customerId}/items/{itemId} (endp 44)
                return carts_sock_shop.fetch("/carts/" + customerId + "/items/" + itemId, {
                    method: "DELETE"
                })
                .then((response) => {
                    expect(response.status).toEqual(202);
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

describe.each(dataset("data/dataset_8.json"))("test_008_get_carts_customerId_merge", (address, card, customer, items, sessionId) => {
    it("test_008_get_carts_customerId_merge", () => {
        clearSession();

        // POST http://orders.sock-shop/orders (endp 15)
        const orders_sock_shop = getHttpClient("http://orders.sock-shop", authenticate);
        return orders_sock_shop.fetch("/orders", {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_15.json", {
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

            // GET http://carts.sock-shop/carts/{customerId}/merge (endp 8)
            const carts_sock_shop = getHttpClient("http://carts.sock-shop", authenticate);
            return carts_sock_shop.fetch("/carts/" + customerId + "/merge" + urlencode([["sessionId", sessionId]]))
            .then((response) => {
                expect(response.status).toEqual(202);
                return response.text();
            })
            .then((text) => {
            })
            .then((data) => {
            });
        });
    });
});
