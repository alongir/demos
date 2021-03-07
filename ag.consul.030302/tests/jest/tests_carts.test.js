const authenticate = require("./authentication");
const {clearSession, dataset, getHttpTarget, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_13.json"))("test_13_get_carts_id_items", (id) => {
    it("test_13_get_carts_id_items", () => {
        clearSession();

        // GET http://carts/carts/{id}/items (endp 13)
        const carts = getHttpTarget("TARGET_CARTS", authenticate);
        return carts.fetch("/carts/" + id + "/items")
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

describe.each(dataset("data/dataset_12.json"))("test_12_get_carts_id_merge", (id, sessionId) => {
    it("test_12_get_carts_id_merge", () => {
        clearSession();

        // GET http://carts/carts/{id}/merge (endp 12)
        const carts = getHttpTarget("TARGET_CARTS", authenticate);
        return carts.fetch("/carts/" + id + "/merge" + urlencode([["sessionId", sessionId]]))
        .then((response) => {
            expect(response.status).toEqual(202);
            return response.text();
        })
        .then((text) => {
        })
        .then((data) => {
        });
    });
});
