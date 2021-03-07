const authenticate = require("./authentication");
const {JSONPath, clearSession, getHttpTarget} = require("./up9lib");

it("test_17_get_customers_id", () => {
    clearSession();

    // GET http://mockintosh/customers/undefined (endp 18)
    const mockintosh = getHttpTarget("TARGET_MOCKINTOSH", authenticate);
    return mockintosh.fetch("/customers/undefined")
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
        const id = JSONPath({
            path: "$.id",
            json: data
        })[0];

        // GET http://mockintosh/customers/{id} (endp 17)
        return mockintosh.fetch("/customers/" + id)
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
it("test_19_get_login", () => {
    clearSession();

    // GET http://mockintosh/login (endp 19)
    const mockintosh = getHttpTarget("TARGET_MOCKINTOSH");
    return mockintosh.fetch("/login")
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
