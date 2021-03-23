const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, getHttpTarget, urlencode} = require("./up9lib");

it("test_46_delete_carts_customerId", () => {
    clearSession();

    // POST http://orders/orders (endp 57)
    const address = "http://user/addresses/57a98d98e4b00679b4a830b0";
    const card = "http://user/cards/57a98d98e4b00679b4a830b1";
    const customer = "http://user/customers/57a98d98e4b00679b4a830b2";
    const items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
    const orders = getHttpTarget("TARGET_ORDERS", authenticate);
    return orders.fetch("/orders", {
        method: "POST",
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        body: JSONBuild("data/payload_for_endp_57.json", {
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

        // DELETE http://carts/carts/{customerId} (endp 46)
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

it("test_13_get_carts_customerId_items", () => {
    clearSession();

    // POST http://orders/orders (endp 57)
    const address = "http://user/addresses/57a98d98e4b00679b4a830b0";
    const card = "http://user/cards/57a98d98e4b00679b4a830b1";
    const customer = "http://user/customers/57a98d98e4b00679b4a830b2";
    const items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
    const orders = getHttpTarget("TARGET_ORDERS", authenticate);
    return orders.fetch("/orders", {
        method: "POST",
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        body: JSONBuild("data/payload_for_endp_57.json", {
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

        // GET http://carts/carts/{customerId}/items (endp 13)
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
        })
        .then((data) => {
        });
    });
});

it("test_56_post_carts_customerId_items", () => {
    clearSession();

    // GET http://catalogue/catalogue (endp 10)
    const size = "5";
    const catalogue = getHttpTarget("TARGET_CATALOGUE", authenticate);
    return catalogue.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["tags", ""]]))
    .then((response) => {
        return response.text();
    })
    .then((text) => {
        expect(/.*Holy.*/.test(response)).toEqual(true);
        return JSON.parse(text);
    })
    .then((data) => {
        const itemId = JSONPath({
            path: "$[*].id",
            json: data
        })[0];
        const unitPrice = JSONPath({
            path: "$[*].price",
            json: data
        })[0];

        // POST http://orders/orders (endp 57)
        const address = "http://user/addresses/57a98d98e4b00679b4a830b0";
        const card = "http://user/cards/57a98d98e4b00679b4a830b1";
        const customer = "http://user/customers/57a98d98e4b00679b4a830b2";
        const items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
        const orders = getHttpTarget("TARGET_ORDERS", authenticate);
        return orders.fetch("/orders", {
            method: "POST",
            headers: {
                "accept": "application/json",
                "content-type": "application/json"
            },
            body: JSONBuild("data/payload_for_endp_57.json", {
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

            // POST http://carts/carts/{customerId}/items (endp 56)
            const carts = getHttpTarget("TARGET_CARTS", authenticate);
            return carts.fetch("/carts/" + customerId + "/items", {
                method: "POST",
                headers: {
                    "accept": "application/json",
                    "content-type": "application/json"
                },
                body: JSONBuild("data/payload_for_endp_56.json", {
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

it("test_12_get_carts_customerId_merge", () => {
    clearSession();

    // POST http://orders/orders (endp 57)
    const address = "http://user/addresses/57a98d98e4b00679b4a830b0";
    const card = "http://user/cards/57a98d98e4b00679b4a830b1";
    const customer = "http://user/customers/57a98d98e4b00679b4a830b2";
    const items = "http://127.0.0.1:15300/carts/57a98d98e4b00679b4a830b2/items";
    const orders = getHttpTarget("TARGET_ORDERS", authenticate);
    return orders.fetch("/orders", {
        method: "POST",
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        body: JSONBuild("data/payload_for_endp_57.json", {
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

        // GET http://carts/carts/{customerId}/merge (endp 12)
        const sessionId = "XQmVKODtqAcXS6ZfF4HlZubZBEgShjds";
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
