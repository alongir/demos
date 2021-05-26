const authenticate = require("./authentication");
const {JSONPath, clearSession, getHttpClient, urlPart} = require("./up9lib");

it("test_13_get_catalogue_id", () => {
    clearSession();

    // GET http://front-end/orders (endp 19)
    const front_end = getHttpClient("http://front-end", authenticate);
    return front_end.fetch("/orders", {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
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
            path: "$[*].address.city",
            json: data
        })).toContain("Glasgow");
        const id = JSONPath({
            path: "$[*].items[*].itemId",
            json: data
        })[0];

        // GET http://front-end/catalogue/{id} (endp 13)
        return front_end.fetch("/catalogue/" + id, {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
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

it("test_20_get_orders_href", () => {
    clearSession();

    // GET http://front-end/orders (endp 19)
    const front_end = getHttpClient("http://front-end", authenticate);
    return front_end.fetch("/orders", {
        headers: {
            "x-requested-with": "XMLHttpRequest"
        }
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
            path: "$[*].address.city",
            json: data
        })).toContain("Glasgow");
        const href = urlPart("/2", JSONPath({
            path: "$[*]._links.self.href",
            json: data
        })[0]);

        // GET http://front-end/orders/{href} (endp 20)
        return front_end.fetch("/orders/" + href, {
            headers: {
                "x-requested-with": "XMLHttpRequest"
            }
        })
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
