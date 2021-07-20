const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpClient} = require("./up9lib");

describe.each(dataset("data/dataset_29.json"))("test_29_get_customers_customerId_addresses", (address, card, customer, items) => {
    it("test_29_get_customers_customerId_addresses", () => {
        clearSession();

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

            // GET http://user.sock-shop/customers/{customerId}/cards (endp 30)
            const user_sock_shop = getHttpClient("http://user.sock-shop", authenticate);
            return user_sock_shop.fetch("/customers/" + customerId + "/cards")
            .then((response) => {
                expect(response.status).toEqual(200);
                return response.text();
            })
            .then((text) => {
                return JSON.parse(text);
            })
            .then((data) => {
                expect(JSONPath({
                    path: "$._embedded.card[*]._links.card.href",
                    json: data
                })).not.toBeNull();

                // GET http://user.sock-shop/customers/{customerId}/addresses (endp 29)
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
});

// authentication-related test
it("test_31_get_login", () => {
    clearSession();

    // GET http://user.sock-shop/login (endp 31)
    const user_sock_shop = getHttpClient("http://user.sock-shop");
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
    });
});
