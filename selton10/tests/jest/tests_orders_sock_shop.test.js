const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_60.json"))("test_060_get_orders_id", (address, card, customer, items) => {
    it("test_060_get_orders_id", () => {
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
            const id = JSONPath({
                path: "$.id",
                json: data
            })[0];

            // GET http://orders.sock-shop/orders/{id} (endp 60)
            return orders_sock_shop.fetch("/orders/" + id)
            .then((response) => {
                expect(response.status).toEqual(200);
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

describe.each(dataset("data/dataset_61.json"))("test_061_get_orders_search_customerId", (address, card, customer, items) => {
    it("test_061_get_orders_search_customerId", () => {
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
            const custId = JSONPath({
                path: "$.customerId",
                json: data
            })[0];

            // GET http://orders.sock-shop/orders/search/customerId (endp 61)
            return orders_sock_shop.fetch("/orders/search/customerId" + urlencode([["custId", custId], ["sort", "date"]]))
            .then((response) => {
                expect(response.status).toEqual(200);
                return response.text();
            })
            .then((text) => {
                return JSON.parse(text);
            })
            .then((data) => {
                expect(JSONPath({
                    path: "$._embedded.customerOrders[*].address.city",
                    json: data
                })).toContain("Glasgow");
            });
        });
    });
});
