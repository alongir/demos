const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_32.json"))("test_32_delete_carts_customerId", (address, card, items, size) => {
    it("test_32_delete_carts_customerId", () => {
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

                        // DELETE http://carts.sock-shop/carts/{customerId} (endp 32)
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
        });
    });
});

describe.each(dataset("data/dataset_33.json"))("test_33_post_carts_customerId_items", (address, card, items, sessionId) => {
    it("test_33_post_carts_customerId_items", () => {
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
            const customer = JSONPath({
                path: "$.user._links.customer.href",
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
                const itemId = JSONPath({
                    path: "$.items[*].itemId",
                    json: data
                })[0];
                const unitPrice = JSONPath({
                    path: "$.items[*].unitPrice",
                    json: data
                })[0];
                const customerId = JSONPath({
                    path: "$.customerId",
                    json: data
                })[0];

                // GET http://carts.sock-shop/carts/{customerId}/merge (endp 35)
                const carts_sock_shop = getHttpClient("http://carts.sock-shop", authenticate);
                return carts_sock_shop.fetch("/carts/" + customerId + "/merge" + urlencode([["sessionId", sessionId]]))
                .then((response) => {
                    expect(response.status).toEqual(202);
                    return response.text();
                })
                .then((text) => {
                })
                .then((data) => {
                    // POST http://carts.sock-shop/carts/{customerId}/items (endp 33)
                    return carts_sock_shop.fetch("/carts/" + customerId + "/items", {
                        method: "POST",
                        headers: {
                            "accept": "application/json",
                            "content-type": "application/json"
                        },
                        body: JSONBuild("data/payload_for_endp_33.json", {
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
});

describe.each(dataset("data/dataset_34.json"))("test_34_delete_carts_customerId_items_itemId", (address, card, items, size) => {
    it("test_34_delete_carts_customerId_items_itemId", () => {
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
                            const itemId = JSONPath({
                                path: "$[*].itemId",
                                json: data
                            })[0];

                            // DELETE http://carts.sock-shop/carts/{customerId}/items/{itemId} (endp 34)
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
        });
    });
});
