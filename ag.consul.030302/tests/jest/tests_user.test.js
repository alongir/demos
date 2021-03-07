const authenticate = require("./authentication");
const {JSONPath, clearSession, dataset, getHttpTarget} = require("./up9lib");

describe.each(dataset("data/dataset_15.json"))("test_15_get_customers_id", (id) => {
    it("test_15_get_customers_id", () => {
        clearSession();

        // GET http://user/customers/{id} (endp 15)
        const user = getHttpTarget("TARGET_USER", authenticate);
        return user.fetch("/customers/" + id)
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

// authentication-related test
it("test_16_get_login", () => {
    clearSession();

    // GET http://user/login (endp 16)
    const user = getHttpTarget("TARGET_USER");
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
    });
});
