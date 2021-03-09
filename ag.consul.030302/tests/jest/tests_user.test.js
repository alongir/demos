const authenticate = require("./authentication");
const {JSONPath, clearSession, getHttpTarget} = require("./up9lib");

it("test_15_get_customers_id", () => {
    clearSession();

    // GET http://user/customers/{id} (endp 15)
    const id = "57a98d98e4b00679b4a830b2";
    const user = getHttpTarget("TARGET_USER", authenticate);
    return user.fetch("/customers/" + id)
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
