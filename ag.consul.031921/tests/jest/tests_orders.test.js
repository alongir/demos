const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient, urlPart, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_36.json"))("test_36_get_orders_href", (address, card, items) => {
    it("test_36_get_orders_href", () => {
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
                const custId = JSONPath({
                    path: "$.customerId",
                    json: data
                })[0];

                // GET http://orders/orders/search/customerId (endp 37)
                return orders.fetch("/orders/search/customerId" + urlencode([["custId", custId], ["sort", "date"]]))
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
                    const href = urlPart("/2", JSONPath({
                        path: "$._embedded.customerOrders[*]._links.order.href",
                        json: data
                    })[0]);

                    // GET http://orders/orders/{href} (endp 36)
                    return orders.fetch("/orders/" + href)
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
    });
});
