const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, getHttpTarget} = require("./up9lib");

it("test_31_get_customers_customerId", () => {
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

            // GET http://user/customers/{customerId} (endp 31)
            return user.fetch("/customers/" + customerId)
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
            });
        });
    });
});

it("test_33_get_customers_customerId_addresses", () => {
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

            // GET http://user/customers/{customerId}/addresses (endp 33)
            return user.fetch("/customers/" + customerId + "/addresses")
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
            });
        });
    });
});

it("test_34_get_customers_customerId_cards", () => {
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

            // GET http://user/customers/{customerId}/cards (endp 34)
            return user.fetch("/customers/" + customerId + "/cards")
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
