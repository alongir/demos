const authenticate = require("./authentication");
const {JSONPath, clearSession, getHttpClient} = require("./up9lib");

it("test_31_get_customers_customerId", () => {
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
        const customerId = JSONPath({
            path: "$.user.id",
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

it("test_33_get_customers_customerId_addresses", () => {
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
        const customerId = JSONPath({
            path: "$.user.id",
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

it("test_34_get_customers_customerId_cards", () => {
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
        const customerId = JSONPath({
            path: "$.user.id",
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
