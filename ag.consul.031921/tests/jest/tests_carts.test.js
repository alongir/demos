const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, getHttpTarget, urlencode} = require("./up9lib");

it("test_22_delete_carts_customerId", () => {
    clearSession();

    // GET http://user/login (endp 32)
    const user = getHttpTarget("TARGET_USER", authenticate);
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
        const address = "http://user/addresses/57a98d98e4b00679b4a830b0";
        const card = "http://user/cards/57a98d98e4b00679b4a830b1";
        const items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
        const orders = getHttpTarget("TARGET_ORDERS", authenticate);
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
            const carts = getHttpTarget("TARGET_CARTS", authenticate);
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

it("test_23_post_carts_customerId_items", () => {
    clearSession();

    // GET http://user/login (endp 32)
    const user = getHttpTarget("TARGET_USER", authenticate);
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
        const address = "http://user/addresses/57a98d98e4b00679b4a830b0";
        const card = "http://user/cards/57a98d98e4b00679b4a830b1";
        const items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
        const orders = getHttpTarget("TARGET_ORDERS", authenticate);
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
            const carts = getHttpTarget("TARGET_CARTS", authenticate);
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

it("test_25_patch_carts_customerId_items", () => {
    clearSession();

    // GET http://user/login (endp 32)
    const user = getHttpTarget("TARGET_USER", authenticate);
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
        const address = "http://user/addresses/57a98d98e4b00679b4a830b0";
        const card = "http://user/cards/57a98d98e4b00679b4a830b1";
        const items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
        const orders = getHttpTarget("TARGET_ORDERS", authenticate);
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
            const carts = getHttpTarget("TARGET_CARTS", authenticate);
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

it("test_24_get_carts_customerId_merge", () => {
    clearSession();

    // GET http://user/login (endp 32)
    const user = getHttpTarget("TARGET_USER", authenticate);
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
        const address = "http://user/addresses/57a98d98e4b00679b4a830b0";
        const card = "http://user/cards/57a98d98e4b00679b4a830b1";
        const items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
        const orders = getHttpTarget("TARGET_ORDERS", authenticate);
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
            const sessionId = "1J9WwRDQsVd4ymiharhdwevNEWEUP2x2";
            const carts = getHttpTarget("TARGET_CARTS", authenticate);
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
