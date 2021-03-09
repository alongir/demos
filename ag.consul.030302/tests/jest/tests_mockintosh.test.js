const authenticate = require("./authentication");
const {CSSselect, JSONPath, clearSession, getHttpTarget, urlencode} = require("./up9lib");

it("test_39_get_", () => {
    clearSession();

    // GET http://mockintosh/ (endp 39)
    const mockintosh = getHttpTarget("TARGET_MOCKINTOSH", authenticate);
    return mockintosh.fetch("/")
    .then((response) => {
        return response.text();
    })
    .then((text) => {
        expect(CSSselect("div#hot div.box div.container div h2", text)).toContain("Hot this week");
    })
    .then((data) => {
    });
});

it("test_47_get_catalogue", () => {
    clearSession();

    // GET http://mockintosh/catalogue (endp 47)
    const size = "6";
    const mockintosh = getHttpTarget("TARGET_MOCKINTOSH", authenticate);
    return mockintosh.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["tags", ""]]))
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_48_get_catalogue_size", () => {
    clearSession();

    // GET http://mockintosh/catalogue/size (endp 48)
    const mockintosh = getHttpTarget("TARGET_MOCKINTOSH", authenticate);
    return mockintosh.fetch("/catalogue/size" + urlencode([["tags", ""]]))
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_17_get_customers_id", () => {
    clearSession();

    // GET http://mockintosh/customers/undefined (endp 18)
    const mockintosh = getHttpTarget("TARGET_MOCKINTOSH", authenticate);
    return mockintosh.fetch("/customers/undefined")
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
        const id = JSONPath({
            path: "$.id",
            json: data
        })[0];

        // GET http://mockintosh/customers/{id} (endp 17)
        return mockintosh.fetch("/customers/" + id)
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
it("test_19_get_login", () => {
    clearSession();

    // GET http://mockintosh/login (endp 19)
    const mockintosh = getHttpTarget("TARGET_MOCKINTOSH");
    return mockintosh.fetch("/login")
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

it("test_50_get_tags", () => {
    clearSession();

    // GET http://mockintosh/tags (endp 50)
    const mockintosh = getHttpTarget("TARGET_MOCKINTOSH", authenticate);
    return mockintosh.fetch("/tags")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});
