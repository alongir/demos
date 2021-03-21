const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, getHttpTarget, urlPart, urlencode} = require("./up9lib");

it("test_36_get_orders_href", () => {
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
