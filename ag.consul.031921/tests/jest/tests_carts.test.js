const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_22.json"))("test_22_delete_carts_customerId", (address, card, items) => {
    it("test_22_delete_carts_customerId", () => {
        clearSession();

        // GET http://user/login (endp 32)
        const user = getHttpClient("http://user", authenticate);
        return user.fetch("/login")
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

            // POST http://orders/orders (endp 35)
            const orders = getHttpClient("http://orders", authenticate);
            return orders.fetch("/orders", {
                method: "POST",
                headers: {
                    "accept": "application/json",
                    "content-type": "application/json"
                },
                body: JSONBuild("data/payload_for_endp_35.json", {
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

                // DELETE http://carts/carts/{customerId} (endp 22)
                const carts = getHttpClient("http://carts", authenticate);
                return carts.fetch("/carts/" + customerId, {
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

describe.each(dataset("data/dataset_23.json"))("test_23_post_carts_customerId_items", (address, card, items) => {
    it("test_23_post_carts_customerId_items", () => {
        clearSession();

        // GET http://user/login (endp 32)
        const user = getHttpClient("http://user", authenticate);
        return user.fetch("/login")
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

            // POST http://orders/orders (endp 35)
            const orders = getHttpClient("http://orders", authenticate);
            return orders.fetch("/orders", {
                method: "POST",
                headers: {
                    "accept": "application/json",
                    "content-type": "application/json"
                },
                body: JSONBuild("data/payload_for_endp_35.json", {
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

                // POST http://carts/carts/{customerId}/items (endp 23)
                const carts = getHttpClient("http://carts", authenticate);
                return carts.fetch("/carts/" + customerId + "/items", {
                    method: "POST",
                    headers: {
                        "accept": "application/json",
                        "content-type": "application/json"
                    },
                    body: JSONBuild("data/payload_for_endp_23.json", {
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

describe.each(dataset("data/dataset_25.json"))("test_25_patch_carts_customerId_items", (address, card, items) => {
    it("test_25_patch_carts_customerId_items", () => {
        clearSession();

        // GET http://user/login (endp 32)
        const user = getHttpClient("http://user", authenticate);
        return user.fetch("/login")
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

            // POST http://orders/orders (endp 35)
            const orders = getHttpClient("http://orders", authenticate);
            return orders.fetch("/orders", {
                method: "POST",
                headers: {
                    "accept": "application/json",
                    "content-type": "application/json"
                },
                body: JSONBuild("data/payload_for_endp_35.json", {
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
                const quantity = JSONPath({
                    path: "$.items[*].quantity",
                    json: data
                })[0];
                const customerId = JSONPath({
                    path: "$.customerId",
                    json: data
                })[0];

                // GET http://carts/carts/{customerId}/items (endp 26)
                const carts = getHttpClient("http://carts", authenticate);
                return carts.fetch("/carts/" + customerId + "/items", {
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

                    // PATCH http://carts/carts/{customerId}/items (endp 25)
                    return carts.fetch("/carts/" + customerId + "/items", {
                        method: "PATCH",
                        headers: {
                            "accept": "application/json",
                            "content-type": "application/json"
                        },
                        body: JSONBuild("data/payload_for_endp_25.json", {
                            "$.itemId": itemId,
                            "$.quantity": quantity,
                            "$.unitPrice": unitPrice
                        })
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

describe.each(dataset("data/dataset_24.json"))("test_24_get_carts_customerId_merge", (address, card, items, sessionId) => {
    it("test_24_get_carts_customerId_merge", () => {
        clearSession();

        // GET http://user/login (endp 32)
        const user = getHttpClient("http://user", authenticate);
        return user.fetch("/login")
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

            // POST http://orders/orders (endp 35)
            const orders = getHttpClient("http://orders", authenticate);
            return orders.fetch("/orders", {
                method: "POST",
                headers: {
                    "accept": "application/json",
                    "content-type": "application/json"
                },
                body: JSONBuild("data/payload_for_endp_35.json", {
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

                // GET http://carts/carts/{customerId}/merge (endp 24)
                const carts = getHttpClient("http://carts", authenticate);
                return carts.fetch("/carts/" + customerId + "/merge" + urlencode([["sessionId", sessionId]]))
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
