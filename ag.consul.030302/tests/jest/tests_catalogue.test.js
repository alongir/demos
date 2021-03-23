const authenticate = require("./authentication");
const {JSONPath, clearSession, getHttpTarget, urlencode} = require("./up9lib");

it("test_54_get_catalogue_id", () => {
    clearSession();

    // GET http://catalogue/catalogue (endp 10)
    const size = "5";
    const catalogue = getHttpTarget("TARGET_CATALOGUE", authenticate);
    return catalogue.fetch("/catalogue" + urlencode([["page", "1"], ["size", size], ["tags", ""]]))
    .then((response) => {
        return response.text();
    })
    .then((text) => {
        expect(/.*Holy.*/.test(response)).toEqual(true);
        return JSON.parse(text);
    })
    .then((data) => {
        const id = JSONPath({
            path: "$[*].id",
            json: data
        })[0];

        // GET http://catalogue/catalogue/{id} (endp 54)
        return catalogue.fetch("/catalogue/" + id)
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

it("test_09_get_catalogue_size", () => {
    clearSession();

    // GET http://catalogue/catalogue/size (endp 9)
    const catalogue = getHttpTarget("TARGET_CATALOGUE", authenticate);
    return catalogue.fetch("/catalogue/size" + urlencode([["tags", ""]]))
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});

it("test_11_get_tags", () => {
    clearSession();

    // GET http://catalogue/tags (endp 11)
    const catalogue = getHttpTarget("TARGET_CATALOGUE", authenticate);
    return catalogue.fetch("/tags")
    .then((response) => {
        expect(response.status).toEqual(200);
        return response.text();
    })
    .then((text) => {
    })
    .then((data) => {
    });
});
