const authenticate = require("./authentication");
const {clearSession, getHttpTarget, urlencode} = require("./up9lib");

it("test_10_get_catalogue", () => {
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
    })
    .then((data) => {
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
