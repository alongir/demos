const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, getHttpTarget} = require("./up9lib");

it("test_15_get_customers_customerId", () => {
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

        // GET http://user/customers/{customerId} (endp 15)
        const user = getHttpTarget("TARGET_USER", authenticate);
        return user.fetch("/customers/" + customerId)
        .then((response) => {
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

// authentication-related test
it("test_16_get_login", () => {
    clearSession();

    // GET http://user/login (endp 16)
    const user = getHttpTarget("TARGET_USER");
    return user.fetch("/login")
    .then((response) => {
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
    });
});
