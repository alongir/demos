const authenticate = require("./authentication");
const {JSONBuild, JSONPath, clearSession, dataset, getHttpTarget, urlencode} = require("./up9lib");

describe.each(dataset("data/dataset_7.json"))("test_07_get_carts_id_items", (id) => {
    it("test_07_get_carts_id_items", () => {
        clearSession();

        // GET http://carts/carts/{id}/items (endp 7)
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

describe.each(dataset("data/dataset_8.json"))("test_08_post_carts_id_items", (id, size, tags) => {
    it("test_08_post_carts_id_items", () => {
        clearSession();

        // GET http://catalogue/catalogue (endp 5)
        const catalogue = getHttpTarget("TARGET_CATALOGUE", authenticate);
        return catalogue.fetch("/catalogue" + urlencode([["size", size], ["sort", "id"], ["tags", tags]]))
        .then((response) => {
            expect(response.status).toEqual(200);
            return response.text();
        })
        .then((text) => {
            return JSON.parse(text);
        })
        .then((data) => {
            const itemId = JSONPath({
                path: "$.[*].id",
                json: data
            })[0];
            const unitPrice = JSONPath({
                path: "$.[*].price",
                json: data
            })[0];

            // POST http://carts/carts/{id}/items (endp 8)
            const carts = getHttpTarget("TARGET_CARTS", authenticate);
            return carts.fetch("/carts/" + id + "/items", {
                method: "POST",
                headers: {
                    "accept": "application/json",
                    "content-type": "application/json"
                },
                body: JSONBuild("data/payload_for_endp_8.json", {
                    "$.itemId": itemId,
                    "$.unitPrice": unitPrice
                })
            })
            .then((response) => {
                expect(response.status).toEqual(201);
                return response.text();
            })
            .then((text) => {
            })
            .then((data) => {
            });
        });
    });
});

describe.each(dataset("data/dataset_21.json"))("test_21_get_carts_id_merge", (id, sessionId) => {
    it("test_21_get_carts_id_merge", () => {
        clearSession();

        // GET http://carts/carts/{id}/merge (endp 21)
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
