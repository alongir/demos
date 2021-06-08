const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_51.json"))("test_051_post_cards", (ccv, expires, longNum) => {
    it("test_051_post_cards", () => {
        clearSession();

        // GET http://user.sock-shop/login (endp 14)
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
            const userID = JSONPath({
                path: "$.user.id",
                json: data
            })[0];

            // POST http://user.sock-shop/cards (endp 51)
            return user_sock_shop.fetch("/cards", {
                method: "POST",
                headers: {
                    "accept": "application/json",
                    "content-type": "application/json"
                },
                body: JSONBuild("data/payload_for_endp_51.json", {
                    "$.ccv": ccv,
                    "$.expires": expires,
                    "$.longNum": longNum,
                    "$.userID": userID
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

describe.each(dataset("data/dataset_11.json"))("test_011_get_customers_customerId", (address, card, customer, items) => {
    it("test_011_get_customers_customerId", () => {
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

            // GET http://user.sock-shop/customers/{customerId} (endp 11)
            const user_sock_shop = getHttpClient("http://user.sock-shop", authenticate);
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
            });
        });
    });
});

describe.each(dataset("data/dataset_12.json"))("test_012_get_customers_customerId_addresses", (address, card, customer, items) => {
    it("test_012_get_customers_customerId_addresses", () => {
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

            // GET http://user.sock-shop/customers/{customerId}/addresses (endp 12)
            const user_sock_shop = getHttpClient("http://user.sock-shop", authenticate);
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
            });
        });
    });
});

describe.each(dataset("data/dataset_13.json"))("test_013_get_customers_customerId_cards", (address, card, customer, items) => {
    it("test_013_get_customers_customerId_cards", () => {
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

            // GET http://user.sock-shop/customers/{customerId}/cards (endp 13)
            const user_sock_shop = getHttpClient("http://user.sock-shop", authenticate);
            return user_sock_shop.fetch("/customers/" + customerId + "/cards")
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
